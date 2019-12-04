package com.places.placesRentals.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.places.placesRentals.documents.Place;

@Repository
public interface PlaceRepository extends MongoRepository<Place, String> {

}
