package com.places.placesRentals.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.places.placesRentals.documents.Reservation;
import com.places.placesRentals.repositories.ReservationRepository;

@Service
public class ReservationService {

	@Autowired
	private ReservationRepository repo;
	
	public List<Reservation> findAll(){
		return repo.findAll();
	}
}
