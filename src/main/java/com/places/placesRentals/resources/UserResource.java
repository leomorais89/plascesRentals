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

import com.places.placesRentals.documents.User;
import com.places.placesRentals.documents.enuns.ReservationStatus;
import com.places.placesRentals.dto.ReservationDTO;
import com.places.placesRentals.services.UserServices;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserServices service;
	
	@GetMapping
	public ResponseEntity<List<User>> findAll(){
		List<User> users = service.findAll();
		return ResponseEntity.ok().body(users);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable String id){
		User user = service.findById(id);
		return ResponseEntity.ok().body(user);
	}
	
	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User user){
		user = service.insert(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(user);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id){
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<User> update(@PathVariable String id, @RequestBody User user){
		user = service.update(id, user);
		return ResponseEntity.ok().body(user);
	}
	
	@PutMapping(value = "/{id}/alterPassword")
	public ResponseEntity<Void> alterPassword(@PathVariable String id, @RequestParam(value = "oldPassword", defaultValue = "") String oldPassword, @RequestBody User user){
		service.alterPassword(id, oldPassword, user);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping(value = "/{id}/reservations")
	public ResponseEntity<List<ReservationDTO>> allReservationByClient(@PathVariable String id){
		List<ReservationDTO> reservations = service.allReservationByClient(id);
		return ResponseEntity.ok().body(reservations);
	}
	
	@GetMapping(value = "/{id}/reservationsStatus")
	public ResponseEntity<List<ReservationDTO>> reservationByClientAndStatus(@PathVariable String id, @RequestParam(value = "status") ReservationStatus status){
		List<ReservationDTO> reservations = service.reservationByClientAndStatus(id, status);
		return ResponseEntity.ok().body(reservations);
	}
	
	@GetMapping(value = "/seach")
	public ResponseEntity<List<User>> findByName(@RequestParam(value = "name", defaultValue = "") String name){
		List<User> users = service.findByName(name);
		return ResponseEntity.ok().body(users);
	}
}
