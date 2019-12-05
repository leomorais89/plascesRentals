package com.places.placesRentals.documents;

import java.io.Serializable;
import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.places.placesRentals.documents.enuns.ReservationStatus;
import com.places.placesRentals.dto.ClientDTO;
import com.places.placesRentals.dto.PlaceDTO;

@Document
public class Reservation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private Instant startDate;
	private Instant endDate;
	private Double price;
	private ReservationStatus status;
	
	private ClientDTO client;
	private PlaceDTO place;
	
	public Reservation() {
		
	}

	public Reservation(String id, Instant startDate, Instant endDate, Double price, ReservationStatus status,
			ClientDTO client, PlaceDTO place) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.price = price;
		this.status = status;
		this.client = client;
		this.place = place;
	}

	public Instant getStartDate() {
		return startDate;
	}

	public void setStartDate(Instant startDate) {
		this.startDate = startDate;
	}

	public Instant getEndDate() {
		return endDate;
	}

	public void setEndDate(Instant endDate) {
		this.endDate = endDate;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public ReservationStatus getStatus() {
		return status;
	}

	public void setStatus(ReservationStatus status) {
		this.status = status;
	}

	public PlaceDTO getPlace() {
		return place;
	}

	public void setPlace(PlaceDTO place) {
		this.place = place;
	}

	public String getId() {
		return id;
	}

	public ClientDTO getClient() {
		return client;
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
		Reservation other = (Reservation) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
