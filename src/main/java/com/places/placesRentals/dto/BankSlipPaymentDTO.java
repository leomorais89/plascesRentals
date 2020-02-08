package com.places.placesRentals.dto;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.places.placesRentals.documents.enuns.StatusPayment;

@JsonTypeName("bankSlipPayment")
public class BankSlipPaymentDTO extends PaymentDTO {
	private static final long serialVersionUID = 1L;

	private Instant dueDate;
	private Instant payday;
	
	public BankSlipPaymentDTO() {
		
	}

	public BankSlipPaymentDTO(StatusPayment status, Instant dueDate, Instant payday) {
		super(status);
		this.dueDate = dueDate;
		this.payday = payday;
	}

	public Instant getDueDate() {
		return dueDate;
	}

	public void setDueDate(Instant dueDate) {
		this.dueDate = dueDate;
	}

	public Instant getPayday() {
		return payday;
	}

	public void setPayday(Instant payday) {
		this.payday = payday;
	}
}
