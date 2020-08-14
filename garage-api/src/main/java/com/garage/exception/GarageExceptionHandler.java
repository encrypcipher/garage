package com.garage.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.garage.model.ExceptionResponse;

/**
 * Exception handler for the entire API
 *
 */
@ControllerAdvice
public class GarageExceptionHandler {
	
	@ExceptionHandler(GarageApiException.class)
	public final ResponseEntity<?> handleCustomerDetailsApiException(GarageApiException ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
