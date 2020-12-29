package com.redeem.mall.customer.service.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerServiceExceptionHandler {

	@ExceptionHandler(CustomerServiceException.class)
	public ResponseEntity<Object> handleException(CustomerServiceException ex) {
		CustomerException orderEx = new CustomerException(ex.getMessage(),  HttpStatus.NOT_FOUND,
				new Date());
		
		return new ResponseEntity<>(orderEx,HttpStatus.NOT_FOUND);
	}
}
