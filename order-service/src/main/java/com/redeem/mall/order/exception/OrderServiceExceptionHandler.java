package com.redeem.mall.order.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class OrderServiceExceptionHandler {

	@ExceptionHandler(OrderServiceException.class)
	public ResponseEntity<Object> handleException(OrderServiceException ex) {
		OrderException orderEx = new OrderException(ex.getMessage(),  HttpStatus.BAD_REQUEST,
				new Date());
		
		return new ResponseEntity<>(orderEx,HttpStatus.BAD_REQUEST);
	}
}
