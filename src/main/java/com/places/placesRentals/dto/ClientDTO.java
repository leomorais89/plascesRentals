package com.places.placesRentals.dto;

import java.io.Serializable;
import java.time.Instant;

import com.places.placesRentals.documents.User;

public class ClientDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private Instant birthDate;
	private String cpf;
	private String telephone;
	private String cellphone;
	private String email;
	private String address;
	private String state;
	private String city;
	private String neighborhood;
	
	public ClientDTO() {
		
	}
	
	public ClientDTO(User user) {
		id = user.getId();
		name = user.getName();
		birthDate = user.getBirthDate();
		cpf = user.getCpf();
		telephone = user.getTelephone();
		cellphone = user.getCellphone();
		email = user.getEmail();
		address = user.getAddress();
		state = user.getState();
		city = user.getCity();
		neighborhood = user.getNeighborhood();
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Instant getBirthDate() {
		return birthDate;
	}

	public String getCpf() {
		return cpf;
	}

	public String getTelephone() {
		return telephone;
	}

	public String getCellphone() {
		return cellphone;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

	public String getState() {
		return state;
	}

	public String getCity() {
		return city;
	}

	public String getNeighborhood() {
		return neighborhood;
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
		ClientDTO other = (ClientDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
