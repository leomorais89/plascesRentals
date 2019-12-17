package com.places.placesRentals.services;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.places.placesRentals.documents.Reservation;
import com.places.placesRentals.documents.enuns.ReservationStatus;
import com.places.placesRentals.dto.PaymentDTO;
import com.places.placesRentals.repositories.ReservationRepository;

@EnableScheduling
@Service
public class ReservationService {

	@Autowired
	private ReservationRepository repo;
	
	public List<Reservation> findAll(){
		return repo.findAll();
	}
	
	public Reservation findById(String id) {
		return repo.findById(id).get();
	}
	
	public Reservation insert(Reservation reservation) {
		return repo.insert(reservation);
	}
	
	public void delete(String id) {
		repo.deleteById(id);
	}
	
	public Reservation update(String id, Reservation reservation) {
		Reservation newReservation = findById(id);
		newReservation.setStartDate(reservation.getStartDate());
		newReservation.setEndDate(reservation.getEndDate());
		return repo.save(newReservation);
	}
	
	public Reservation giveDiscount(String id, Double price) {
		Reservation reservation = findById(id);
		reservation.setPrice(price);
		return repo.save(reservation);
	}
	
	public Reservation cancelReservation(String id, ReservationStatus status) {
		Reservation reservation = findById(id);
		reservation.setStatus(status);
		return repo.save(reservation);
	}
	
	public void toPay(String id) {
		Reservation reservation = findById(id);
		reservation.setPayment(new PaymentDTO(Instant.now()));
		reservation.setStatus(ReservationStatus.PAID);
		repo.save(reservation);
	}
	
	public List<Reservation> findByStatus(String status){
		Integer intStatus = Integer.parseInt(status);
		return repo.findByStatus(intStatus);
	}
	
	@Scheduled(cron = "0 0 0 * * *", zone = "America/Sao_Paulo")
	public void setSpent() {
		List<Reservation> reservations = findByStatus("2");
		reservations = reservations.stream().filter(endDate -> endDate.getEndDate().isBefore(Instant.now())).collect(Collectors.toList());
		for(Reservation reservation : reservations) {
			reservation.setStatus(ReservationStatus.SPENT);
		}
		repo.saveAll(reservations);
	}
}
