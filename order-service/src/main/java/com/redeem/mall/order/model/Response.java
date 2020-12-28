package com.redeem.mall.order.model;

import org.springframework.http.HttpStatus;

public class Response {
	
	private HttpStatus statusCode;
	private String statusMsg;
	public Response(HttpStatus statusCode, String statusMsg) {
		super();
		this.statusCode = statusCode;
		this.statusMsg = statusMsg;
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
}
