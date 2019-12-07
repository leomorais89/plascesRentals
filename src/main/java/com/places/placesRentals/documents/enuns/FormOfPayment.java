package com.places.placesRentals.documents.enuns;

public enum FormOfPayment {

	BANK_SLIP(1),
	CARD(2);
	
	private Integer code;
	
	private FormOfPayment(Integer code) {
		this.code = code;
	}
	
	public Integer getCode() {
		return code;
	}
	
	public static FormOfPayment valorDe(Integer code) {
		for(FormOfPayment formOfPayment : FormOfPayment.values()) {
			if(formOfPayment.getCode() == code) {
				return formOfPayment;
			}
		}
		throw new IllegalStateException("Valor invalido");
	}
}
