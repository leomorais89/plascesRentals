package com.places.placesRentals.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.places.placesRentals.documents.Place;
import com.places.placesRentals.documents.User;
import com.places.placesRentals.repositories.PlaceRepository;
import com.places.placesRentals.repositories.UserRepository;

@Configuration
public class ConfigurationTest implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PlaceRepository placeRepository;

	@Override
	public void run(String... args) throws Exception {
		
		userRepository.deleteAll();
		placeRepository.deleteAll();
		
		User user1 = new User(null, "Leonardo", Instant.parse("1989-05-08T08:38:00Z"), "135.147.587.86", null, "(24)98158-0085", "lenardomorais89@yahoo.com.br", "Rua Antônio das Neves Grillo, Nº 87, AP09", "São Paulo", "Americana", "Morada do Sol", "LeoMorais", "leomorais");
		User user2 = new User(null, "Lenaura", Instant.parse("1990-03-19T08:38:00Z"), "135.147.587.86", null, "(24)98158-0085", "lenaura@yahoo.com.br", "Rua Antônio das Neves Grillo, Nº 87, AP09", "São Paulo", "Americana", "Morada do Sol", "Lenaura", "lenaura");
		User user3 = new User(null, "Junior", Instant.parse("1992-05-08T08:38:00Z"), "135.147.587.86", null, "(24)98158-0085", "junior@yahoo.com.br", "Rua Antônio das Neves Grillo, Nº 89, AP07", "São Paulo", "Americana", "Morada do Sol", "junior", "junior");
		
		Place place1 = new Place(null, "Fazenda do Sertão", "Uma fazendo muito linda em Rio das Flores", "Rua Santa Barbara, Nº 14", "Rio de Janeiro", "Rio das Flores", "Porto", 500.00);
		Place place2 = new Place(null, "Fazenda dos Canarios", "Uma fazendo muito linda em Rio das Flores", "Rua Santa Barbara, Nº 80", "Rio de Janeiro", "Rio das Flores", "Tres Ilhas", 500.00);
		
		userRepository.saveAll(Arrays.asList(user1, user2, user3));
		placeRepository.saveAll(Arrays.asList(place1, place2));
	}
	
	
}
