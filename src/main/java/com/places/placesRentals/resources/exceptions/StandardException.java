package com.places.placesRentals.resources.exceptions;

import java.io.Serializable;

public class StandardException implements Serializable {
	private static final long serialVersionUID = 1L;

	private String timestamp;
	private String status;
	private String erro;
	private String message;
	private String path;
	
	public StandardException() {
		
	}

	public StandardException(String timestamp, String status, String erro, String message, String path) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.erro = erro;
		this.message = message;
		this.path = path;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErro() {
		return erro;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
