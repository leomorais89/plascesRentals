package com.places.placesRentals.documents.enuns;

public enum StatusPayment {

	PENDING(1),
	PAID(2),
	CANCELED(3);
	
	private Integer code;
	
	private StatusPayment(Integer code) {
		this.code = code;
	}
	
	public Integer getCode() {
		return code;
	}
	
	public static StatusPayment valorDe(Integer code) {
		for(StatusPayment statusPayment : StatusPayment.values()) {
			if(statusPayment.getCode() == code) {
				return statusPayment;
			}
		}
		throw new IllegalStateException("Valor invalido");
	}
}
