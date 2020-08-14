package com.garage.exception;

public class GarageApiException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public GarageApiException(String errorMessage, Exception e) {
		super(errorMessage, e);
	}

	public GarageApiException(String errorMessage) {
		super(errorMessage);
	}
}
