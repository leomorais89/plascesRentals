package com.places.placesRentals.services;

import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.places.placesRentals.documents.Reservation;
import com.places.placesRentals.documents.enuns.ReservationStatus;
import com.places.placesRentals.dto.PaymentDTO;
import com.places.placesRentals.repositories.ReservationRepository;
import com.places.placesRentals.services.exceptions.ResourceBadRequestException;
import com.places.placesRentals.services.exceptions.ResourceNotFoundException;

@EnableScheduling
@Service
public class ReservationService {

	@Autowired
	private ReservationRepository repo;
	
	public List<Reservation> findAll(){
		return repo.findAll();
	}
	
	public Reservation findById(String id) {
		try {
			return repo.findById(id).get();
		} catch(NoSuchElementException e) {
			throw new ResourceNotFoundException(e.getMessage());
		}
	}
	
	public Reservation insert(Reservation reservation) {
		if(testReservation(reservation)) {
			reservation.setPrice(reservation.getPlace().getPrice());
			reservation.setDiscount(0.0);
			reservation.setStatus(ReservationStatus.WAITING_PAYMENT);
			return repo.insert(reservation);
		} else {
			throw new ResourceBadRequestException("Não foi realizado a reserva, algum preenchimento dos campos de forma errada");
		}
	}
	
	public void delete(String id) {
		repo.deleteById(id);
	}
	
	public Reservation update(String id, Reservation reservation) {
		Reservation newReservation = findById(id);
		if(testReservation(reservation)) {
			newReservation.setStartDate(reservation.getStartDate());
			newReservation.setEndDate(reservation.getEndDate());
			return repo.save(newReservation);
		} else {
			throw new ResourceBadRequestException("Não foi realizado a atualização na reserva, algum preenchimento dos campos de forma errada");
		}
	}
	
	public Reservation giveDiscount(String id, Double discount) {
		Reservation reservation = findById(id);
		if(!discount.equals(null)) {
			reservation.setDiscount(discount);
			return repo.save(reservation);
		} else {
			throw new ResourceBadRequestException("Preço não pode ser nulo");
		}
	}
	
	public Reservation cancelReservation(String id) {
		Reservation reservation = findById(id);
		reservation.setStatus(ReservationStatus.CANCELED);
		return repo.save(reservation);
	}
	
	public void toPay(String id) {
		Reservation reservation = findById(id);
		reservation.setPayment(new PaymentDTO(Instant.now()));
		reservation.setStatus(ReservationStatus.PAID);
		repo.save(reservation);
	}
	
	public List<Reservation> findByStatus(String status){
		if(!status.isEmpty()) {
			Integer intStatus = Integer.parseInt(status);
			return repo.findByStatus(intStatus);
		} else {
			throw new ResourceBadRequestException("Status não pode ser nulo");
		}
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
	
	public Boolean testReservation(Reservation reservation) {
		if(reservation.getStartDate().equals(null))
			return false;
		if(reservation.getEndDate().equals(null))
			return false;
		if(reservation.getStartDate().isBefore(Instant.now()))
			return false;
		if(reservation.getEndDate().isBefore(reservation.getStartDate()))
			return false;
		return true;
	}
}
