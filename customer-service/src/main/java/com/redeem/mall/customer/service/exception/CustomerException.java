package com.redeem.mall.customer.service.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;

public class CustomerException {
	
	private final String message;
	private final HttpStatus status;
	private final Date timestamp;
	public CustomerException(String message, HttpStatus status, Date timestamp) {
		super();
		this.message = message;
		this.status = status;
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public Date getTimestamp() {
		return timestamp;
	}
}
