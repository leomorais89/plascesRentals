package com.places.placesRentals.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.places.placesRentals.documents.Reservation;
import com.places.placesRentals.services.ReservationService;

@RestController
@RequestMapping(value = "/reservations")
public class ReservationResource {
	
	@Autowired
	private ReservationService service;
	
	@GetMapping
	public ResponseEntity<List<Reservation>> findAll(){
		List<Reservation> reservations = service.findAll();
		return ResponseEntity.ok().body(reservations);
	}
}
