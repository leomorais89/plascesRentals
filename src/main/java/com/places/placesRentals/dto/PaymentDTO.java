package com.places.placesRentals.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.places.placesRentals.documents.enuns.StatusPayment;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
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
