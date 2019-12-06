package com.places.placesRentals.documents;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.places.placesRentals.dto.ImageDTO;

@Document
public class Place implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private String name;
	private String description;
	private String address;
	private String state;
	private String city;
	private String neighborhook;
	private Double price;
	
	@DBRef(lazy = true)
	@JsonIgnore
	private List<Reservation> reservations = new ArrayList<>();
	private List<ImageDTO> imagens = new ArrayList<>();
	
	public Place() {
		
	}

	public Place(String id, String name, String description, String address, String state, String city,
			String neighborhook, Double price) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.address = address;
		this.state = state;
		this.city = city;
		this.neighborhook = neighborhook;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getNeighborhook() {
		return neighborhook;
	}

	public void setNeighborhook(String neighborhook) {
		this.neighborhook = neighborhook;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getId() {
		return id;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public List<ImageDTO> getImagens() {
		return imagens;
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
		Place other = (Place) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
