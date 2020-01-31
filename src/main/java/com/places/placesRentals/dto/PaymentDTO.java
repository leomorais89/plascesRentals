package com.places.placesRentals.dto;

import java.io.Serializable;

import com.places.placesRentals.documents.Reservation;
import com.places.placesRentals.documents.enuns.StatusPayment;

public class PaymentDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private Integer status;
	
	private Reservation reservation;
	
	public PaymentDTO() {
		
	}

	public PaymentDTO(String id, StatusPayment status, Reservation reservation) {
		super();
		this.id = id;
		
		this.reservation = reservation;
	}

	public String getId() {
		return id;
	}

	public StatusPayment getStatus() {
		return StatusPayment.valorDe(status);
	}

	public void setStatus(StatusPayment status) {
		if(status != null) {
			this.status = status.getCode();
		}
	}

	public Reservation getReservation() {
		return reservation;
	}
}
