package com.places.placesRentals.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.places.placesRentals.documents.Reservation;

@Repository
public interface ReservationRepository extends MongoRepository<Reservation, String> {

	public List<Reservation> findByStatus(Integer status);
}
