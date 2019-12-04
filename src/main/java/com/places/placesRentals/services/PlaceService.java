package com.places.placesRentals.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.places.placesRentals.documents.Place;
import com.places.placesRentals.repositories.PlaceRepository;

@Service
public class PlaceService {

	@Autowired
	private PlaceRepository repo;
	
	public List<Place> findAll(){
		return repo.findAll();
	}
	
	public Place findById(String id) {
		return repo.findById(id).get();
	}
	
	public Place insert(Place place) {
		return repo.insert(place);
	}
	
	public void deleteById(String id) {
		repo.deleteById(id);
	}
	
	public Place update(String id, Place place) {
		Place newPlace = findById(id);
		newPlace.setName(place.getName());
		newPlace.setDescription(place.getDescription());
		newPlace.setAddress(place.getAddress());
		newPlace.setState(place.getState());
		newPlace.setCity(place.getCity());
		newPlace.setNeighborhook(place.getNeighborhook());
		return repo.save(newPlace);
	}
}
