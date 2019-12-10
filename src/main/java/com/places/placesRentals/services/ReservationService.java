package com.places.placesRentals.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.places.placesRentals.documents.Reservation;
import com.places.placesRentals.documents.enuns.ReservationStatus;
import com.places.placesRentals.repositories.ReservationRepository;

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
}
