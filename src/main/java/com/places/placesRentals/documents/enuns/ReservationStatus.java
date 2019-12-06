package com.places.placesRentals.documents.enuns;

public enum ReservationStatus {

	WAITING_PAYMENT(1),
	PAID(2),
	SPENT(3),
	CANCELED(4);
	
	private Integer code;
	
	private ReservationStatus(Integer code) {
		this.code = code;
	}
	
	public Integer getCode() {
		return code;
	}
	
	public static ReservationStatus valorDe(Integer code) {
		for(ReservationStatus reservation : ReservationStatus.values()) {
			if(reservation.getCode() == code) {
				return reservation;
			}
		}
		throw new IllegalStateException("Código invalido!");
	}
}
