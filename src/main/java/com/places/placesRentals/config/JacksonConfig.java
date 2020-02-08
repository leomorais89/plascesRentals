package com.places.placesRentals.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.places.placesRentals.dto.BankSlipPaymentDTO;
import com.places.placesRentals.dto.CardPaymantDTO;

@Configuration
public class JacksonConfig {
	// https://stackoverflow.com/questions/41452598/overcome-can-not-construct-instance-ofinterfaceclass-without-hinting-the-pare
	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
			public void configure(ObjectMapper objectMapper) {
				objectMapper.registerSubtypes(CardPaymantDTO.class);
				objectMapper.registerSubtypes(BankSlipPaymentDTO.class);
				super.configure(objectMapper);
		};
	};
	return builder;
	}
}
