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
import com.places.placesRentals.services.exceptions.ResourceBadRequestException;
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
		boolean test = testUser(user);
		if(test == true) {
			return repo.insert(user);
		} else {
			throw new ResourceBadRequestException("Usuário não foi salvo, existe algum campo obrigatório do Usuário em branco!!!");
		}
	}
	
	public void deleteById(String id) {
		User user = findById(id);
		Long cont = 0L;
		cont = user.getReservations().stream().filter(status -> status.getStatus().equals(ReservationStatus.WAITING_PAYMENT)).count();
		cont += user.getReservations().stream().filter(status -> status.getStatus().equals(ReservationStatus.PAID)).count();
		if(cont > 0)
			throw new ResourceBadRequestException("Usuário contem Reservas em aberto");
		repo.deleteById(id);
	}
	
	public User update(String id, User user) {
		User newUser = findById(id);
		boolean test = testUser(user);
		if(test == true) {
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
		} else {
			throw new ResourceBadRequestException("Usuário não foi atualizado, existe algum campo obrigatório do Usuário em branco!!!");
		}
	}
	
	public void alterPassword(String id, String oldPassword, User user) {
		User newUser = findById(id);
		if(newUser.getPassword().equals(oldPassword)) {
			if(!user.getPassword().isEmpty()) {
				newUser.setPassword(user.getPassword());
				repo.save(newUser);
			} else {
				throw new ResourceBadRequestException("Nova senha está nula!!!");
			}
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
	
	private Boolean testUser(User user) {
		if(user.getName().isEmpty())
			return false;
		if(user.getBirthDate().equals(null))
			return false;
		if(user.getCpf().isEmpty())
			return false;
		if(user.getAddress().isEmpty())
			return false;
		if(user.getState().isEmpty())
			return false;
		if(user.getCity().isEmpty())
			return false;
		if(user.getNeighborhood().isEmpty())
			return false;
		if(user.getUser().isEmpty())
			return false;
		if(user.getPassword().isEmpty())
			return false;
		return true;
	}
}