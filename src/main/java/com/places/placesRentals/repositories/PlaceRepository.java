package com.places.placesRentals.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.places.placesRentals.documents.Place;

@Repository
public interface PlaceRepository extends MongoRepository<Place, String> {

	public List<Place> findByNameContainingIgnoreCase(String name);
}
