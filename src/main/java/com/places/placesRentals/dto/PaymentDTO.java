package com.places.placesRentals.dto;

import java.io.Serializable;

import com.places.placesRentals.documents.enuns.StatusPayment;

public class PaymentDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer status;
	
	public PaymentDTO() {
		
	}

	public PaymentDTO(StatusPayment status) {
		super();
		setStatus(status);
	}

	public StatusPayment getStatus() {
		return StatusPayment.valorDe(status);
	}

	public void setStatus(StatusPayment status) {
		if(status != null) {
			this.status = status.getCode();
		}
	}
}
