package com.places.placesRentals.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.places.placesRentals.documents.User;
import com.places.placesRentals.repositories.UserRepository;

@Service
public class UserServices {

	@Autowired
	private UserRepository repo;
	
	public List<User> findAll(){
		return repo.findAll();
	}
	
	public User findById(String id) {
		return repo.findById(id).get();
	}
	
	public User insert(User user) {
		return repo.insert(user);
	}
	
	public void delete(String id) {
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
}
