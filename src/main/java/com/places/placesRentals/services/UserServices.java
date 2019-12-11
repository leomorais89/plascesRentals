package com.places.placesRentals.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.places.placesRentals.documents.Reservation;
import com.places.placesRentals.documents.User;
import com.places.placesRentals.documents.enuns.ReservationStatus;
import com.places.placesRentals.dto.ReservationDTO;
import com.places.placesRentals.repositories.UserRepository;
import com.places.placesRentals.services.exceptions.ResourceNotFoundException;

@Service
public class UserServices {

	@Autowired
	private UserRepository repo;
	
	public List<User> findAll(){
		return repo.findAll();
	}
	
	public User findById(String id) {
		try {
			return repo.findById(id).get();
		} catch(NoSuchElementException e) {
			throw new ResourceNotFoundException(e.getMessage());
		}
	}
	
	public User insert(User user) {
		return repo.insert(user);
	}
	
	public void deleteById(String id) {
		repo.deleteById(id);
	}
	
	public User update(String id, User user) {
		User newUser = findById(id);
		newUser.setName(user.getName());
		newUser.setBirthDate(user.getBirthDate());
		newUser.setTelephone(user.getTelephone());
		newUser.setCellphone(user.getCellphone());
		newUser.setEmail(user.getEmail());
		newUser.setAddress(user.getAddress());
		newUser.setState(user.getState());
		newUser.setCity(user.getCity());
		newUser.setNeighborhood(user.getNeighborhood());
		return repo.save(newUser);
	}
	
	public void alterPassword(String id, String oldPassword, User user) {
		User newUser = findById(id);
		if(newUser.getPassword().equals(oldPassword)) {
			newUser.setPassword(user.getPassword());
			repo.save(newUser);
		}
	}
	
	public List<ReservationDTO> allReservationByClient(String id){
		User client = findById(id);
		List<Reservation> reservations = client.getReservations();
		List<ReservationDTO> newReserv = new ArrayList<>();
		for(Reservation reservation : reservations) {
			newReserv.add(new ReservationDTO(reservation));
		}
		return newReserv;
	}
	
	public List<ReservationDTO> reservationByClientAndStatus(String id, ReservationStatus status){
		User client = findById(id);
		List<Reservation> reservations = client.getReservations();
		List<ReservationDTO> newReserv = new ArrayList<>();
		for(Reservation reservation : reservations) {
			if(reservation.getStatus().equals(status))
				newReserv.add(new ReservationDTO(reservation));
		}
		return newReserv;
	}
	
	public List<User> findByName(String name){
		List<User> users = repo.findByNameContainingIgnoreCase(name);
		return users;
	}
}