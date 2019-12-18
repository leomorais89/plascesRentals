package com.places.placesRentals.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.places.placesRentals.documents.Place;
import com.places.placesRentals.documents.enuns.ReservationStatus;
import com.places.placesRentals.dto.ImageDTO;
import com.places.placesRentals.repositories.PlaceRepository;
import com.places.placesRentals.services.exceptions.ResourceBadRequestException;
import com.places.placesRentals.services.exceptions.ResourceNotFoundException;

@Service
public class PlaceService {

	@Autowired
	private PlaceRepository repo;
	
	public List<Place> findAll(){
		return repo.findAll();
	}
	
	public Place findById(String id) {
		try {
			return repo.findById(id).get();
		} catch(NoSuchElementException e) {
			throw new ResourceNotFoundException(e.getMessage());
		}
	}
	
	public Place insert(Place place) {
		if(testPlace(place)) {
			return repo.insert(place);
		} else {
			throw new ResourceBadRequestException("Lugar não foi salvo, algum campo obrigatório está vazio");
		}
	}
	
	public void deleteById(String id) {
		Place place = findById(id);
		Long cont = 0L;
		cont = place.getReservations().stream().filter(status -> status.getStatus().equals(ReservationStatus.WAITING_PAYMENT)).count();
		cont += place.getReservations().stream().filter(status -> status.getStatus().equals(ReservationStatus.PAID)).count();
		if(cont > 0)
			throw new ResourceBadRequestException("Lugar contem reservas em aberto");
		repo.deleteById(id);
	}
	
	public Place update(String id, Place place) {
		Place newPlace = findById(id);
		if(testPlace(place)) {
			newPlace.setName(place.getName());
			newPlace.setDescription(place.getDescription());
			newPlace.setAddress(place.getAddress());
			newPlace.setState(place.getState());
			newPlace.setCity(place.getCity());
			newPlace.setNeighborhook(place.getNeighborhook());
			return repo.save(newPlace);
		} else {
			throw new ResourceBadRequestException("Produto não atualizado, algum campo obrigatório está em branco");
		}
	}
	
	public Place alterPrice(String id, Place place) {
		Place newPlace = findById(id);
		if(place.getPrice() != null) {
			newPlace.setPrice(place.getPrice());
			return repo.save(newPlace);
		} else {
			throw new ResourceBadRequestException("Novo preço não pode ser nulo");
		}
	}
	
	public List<Place> findByName(String name){
		return repo.findByNameContainingIgnoreCase(name);
	}
	
	public List<ImageDTO> findByPlace(String id){
		return findById(id).getImagens();
	}
	
	public List<ImageDTO> addImagens(String id, List<ImageDTO> imagens){
		Place place = findById(id);
		if(!imagens.isEmpty()) {
			place.getImagens().addAll(imagens);
			repo.save(place);
			return place.getImagens();
		} else {
			throw new ResourceBadRequestException("Não está sendo enviado nenhuma imagem para adicionar");
		}
	}
	
	public void delImagens(String id, List<ImageDTO> imagens) {
		Place place = findById(id);
		if(!imagens.isEmpty()) {
			place.getImagens().removeAll(imagens);
			repo.save(place);
		} else {
			throw new ResourceBadRequestException("Não está sendo enviado imagens para excluir no lugar");
		}
	}
	
	public Boolean testPlace(Place place) {
		if(place.getName().isEmpty())
			return false;
		if(place.getAddress().isEmpty())
			return false;
		if(place.getState().isEmpty())
			return false;
		if(place.getCity().isEmpty())
			return false;
		if(place.getNeighborhook().isEmpty())
			return false;
		return true;
	}
}
