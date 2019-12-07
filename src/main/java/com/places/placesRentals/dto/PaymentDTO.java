package com.places.placesRentals.dto;

import java.io.Serializable;
import java.time.Instant;

public class PaymentDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Instant moment;
	
	public PaymentDTO() {
		
	}

	public PaymentDTO(Instant moment) {
		super();
		this.moment = moment;
	}

	public Instant getMoment() {
		return moment;
	}
}
