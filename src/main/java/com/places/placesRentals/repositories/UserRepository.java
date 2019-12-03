package com.places.placesRentals.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.places.placesRentals.documents.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

}
