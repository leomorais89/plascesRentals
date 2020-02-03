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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlaceDTO other = (PlaceDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
