package com.places.placesRentals.dto;

import java.io.Serializable;
import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.places.placesRentals.documents.Reservation;
import com.places.placesRentals.documents.enuns.FormOfPayment;
import com.places.placesRentals.documents.enuns.ReservationStatus;

@Document
public class ReservationDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private Instant startDate;
	private Instant endDate;
	private Double price;
	private Integer status;
	private Integer formOfPayment;
	
	private PlaceDTO place;
	
	public ReservationDTO() {
		
	}

	public ReservationDTO(Reservation reservation) {
		super();
		id = reservation.getId();
		startDate = reservation.getStartDate();
		endDate = reservation.getEndDate();
		price = reservation.getPrice();
		setStatus(reservation.getStatus());
		setFormOfPayment(reservation.getFormOfPayment());
		place = reservation.getPlace();
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
		return ReservationStatus.valorDe(status);
	}

	public void setStatus(ReservationStatus status) {
		if(status != null) {
			this.status = status.getCode();
		}
	}

	public FormOfPayment getFormOfPayment() {
		return FormOfPayment.valorDe(formOfPayment);
	}

	public void setFormOfPayment(FormOfPayment formOfPayment) {
		if(formOfPayment != null) {
			this.formOfPayment = formOfPayment.getCode();
		}
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
		ReservationDTO other = (ReservationDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
