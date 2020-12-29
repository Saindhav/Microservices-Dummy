package com.redeem.mall.order.model;

import org.springframework.http.HttpStatus;

import com.redeem.mall.order.exception.OrderException;

public class Response {
	
	private HttpStatus statusCode;
	private String statusMsg;
	private OrderException exc;
	public Response(HttpStatus statusCode, String statusMsg) {
		super();
		this.statusCode = statusCode;
		this.statusMsg = statusMsg;
	}
	
	
	
	public Response(HttpStatus statusCode, OrderException exc) {
		super();
		this.statusCode = statusCode;
		this.exc = exc;
	}



	public HttpStatus getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusMsg() {
		return statusMsg;
	}
	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}

	public OrderException getExc() {
		return exc;
	}

	public void setExc(OrderException exc) {
		this.exc = exc;
	}
	
}
