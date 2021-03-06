package com.places.placesRentals.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.places.placesRentals.documents.enuns.StatusPayment;

@JsonTypeName("cardPayment")
public class CardPaymantDTO extends PaymentDTO {
	private static final long serialVersionUID = 1L;

	private Integer numberInstallments;
	
	public CardPaymantDTO() {
		
	}

	public CardPaymantDTO(StatusPayment status, Integer numberInstallments) {
		super(status);
		this.numberInstallments = numberInstallments;
	}

	public Integer getNumberInstallments() {
		return numberInstallments;
	}

	public void setNumberInstallments(Integer numberInstallments) {
		this.numberInstallments = numberInstallments;
	}
}
