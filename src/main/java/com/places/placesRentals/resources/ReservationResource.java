package com.places.placesRentals.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Reservation> findById(@PathVariable String id){
		Reservation reservation = service.findById(id);
		return ResponseEntity.ok().body(reservation);
	}
	
	@PostMapping
	public ResponseEntity<Reservation> insert(@RequestBody Reservation reservation){
		reservation = service.insert(reservation);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(reservation.getId()).toUri();
		return ResponseEntity.created(uri).body(reservation);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Reservation> update(@PathVariable String id, @RequestBody Reservation reservation){
		reservation = service.update(id, reservation);
		return ResponseEntity.ok().body(reservation);
	}
	
	@PutMapping(value = "/{id}/giveDiscount")
	public ResponseEntity<Reservation> giveDiscount(@PathVariable String id, @RequestBody Reservation reservation){
		reservation = service.giveDiscount(id, reservation.getPrice());
		return ResponseEntity.ok().body(reservation);
	}
	
	@PutMapping(value = "/{id}/cancelReservation")
	public ResponseEntity<Reservation> cancelReservation(@PathVariable String id, @RequestBody Reservation reservation){
		reservation = service.cancelReservation(id, reservation.getStatus());
		return ResponseEntity.ok().body(reservation);
	}
	
	@PutMapping(value = "/{id}/toPay")
	public ResponseEntity<Void> toPay(@PathVariable String id){
		service.toPay(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping(value = "/findByStatus")
	public ResponseEntity<List<Reservation>> findByStatus(@RequestParam(value = "status") String status){
		List<Reservation> reservations = service.findByStatus(status);
		return ResponseEntity.ok().body(reservations);
	}
}
