package com.places.placesRentals.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.places.placesRentals.documents.Reservation;

@Repository
public interface ReservationRepository extends MongoRepository<Reservation, String> {

}
