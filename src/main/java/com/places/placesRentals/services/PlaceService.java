package com.places.placesRentals.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.places.placesRentals.documents.Place;
import com.places.placesRentals.dto.ImageDTO;
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
	
	public Place alterPrice(String id, Place place) {
		Place newPlace = findById(id);
		newPlace.setPrice(place.getPrice());
		return repo.save(newPlace);
	}
	
	public List<Place> findByName(String name){
		return repo.findByNameContainingIgnoreCase(name);
	}
	
	public List<ImageDTO> findByPlace(String id){
		return findById(id).getImagens();
	}
	
	public List<ImageDTO> addImagens(String id, List<ImageDTO> imagens){
		Place place = findById(id);
		place.getImagens().addAll(imagens);
		repo.save(place);
		return place.getImagens();
	}
	
	public void delImagens(String id, List<ImageDTO> imagens) {
		Place place = findById(id);
		place.getImagens().removeAll(imagens);
		repo.save(place);
	}
}
