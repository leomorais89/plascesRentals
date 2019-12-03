package com.places.placesRentals.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.places.placesRentals.documents.User;
import com.places.placesRentals.repositories.UserRepository;

@Configuration
public class ConfigurationTest implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		
		userRepository.deleteAll();
		
		User user1 = new User(null, "Leonardo", Instant.parse("1989-05-08T08:38:00Z"), "135.147.587.86", null, "(24)98158-0085", "lenardomorais89@yahoo.com.br", "Rua Antônio das Neves Grillo, Nº 87, AP09", "São Paulo", "Americana", "Morada do Sol", "LeoMorais", "leomorais");
		User user2 = new User(null, "Lenaura", Instant.parse("1990-03-19T08:38:00Z"), "135.147.587.86", null, "(24)98158-0085", "lenaura@yahoo.com.br", "Rua Antônio das Neves Grillo, Nº 87, AP09", "São Paulo", "Americana", "Morada do Sol", "Lenaura", "lenaura");
		User user3 = new User(null, "Junior", Instant.parse("1992-05-08T08:38:00Z"), "135.147.587.86", null, "(24)98158-0085", "junior@yahoo.com.br", "Rua Antônio das Neves Grillo, Nº 89, AP07", "São Paulo", "Americana", "Morada do Sol", "junior", "junior");
		
		userRepository.saveAll(Arrays.asList(user1, user2, user3));
	}
	
	
}
