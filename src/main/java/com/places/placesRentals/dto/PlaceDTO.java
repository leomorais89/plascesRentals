package com.places.placesRentals.dto;

import com.places.placesRentals.documents.Place;

public class PlaceDTO {

	private String id;
	private String name;
	private String description;
	private String address;
	private String state;
	private String city;
	private String neighborhook;
	private Double price;
	
	public PlaceDTO() {
		
	}
	
	public PlaceDTO(Place place) {
		id = place.getId();
		name = place.getName();
		description = place.getDescription();
		address = place.getAddress();
		state = place.getState();
		city = place.getCity();
		neighborhook = place.getNeighborhook();
		price = place.getPrice();
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getAddress() {
		return address;
	}

	public String getState() {
		return state;
	}

	public String getCity() {
		return city;
	}

	public String getNeighborhook() {
		return neighborhook;
	}

	public Double getPrice() {
		return price;
	}
}
