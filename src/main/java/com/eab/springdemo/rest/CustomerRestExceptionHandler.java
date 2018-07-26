package com.eab.springdemo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.eab.springdemo.entity.Customer;

@ControllerAdvice
public class CustomerRestExceptionHandler {

	// add an exception handler for CustomerNotFoundException
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException(CustomerNotFoundException exe) {
		CustomerErrorResponse error = new CustomerErrorResponse(HttpStatus.NOT_FOUND.value(), exe.getMessage(),
				System.currentTimeMillis());

		return new ResponseEntity<CustomerErrorResponse>(error, HttpStatus.NOT_FOUND);
	}

	// add an exception handler for any exception
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException(Exception exe){
		CustomerErrorResponse error = new CustomerErrorResponse(HttpStatus.BAD_REQUEST.value(), exe.getMessage(), System.currentTimeMillis());
		return new ResponseEntity<CustomerErrorResponse>(error,HttpStatus.BAD_REQUEST);
	}

}
