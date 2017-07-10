package com.rwss.rest;

import java.io.Serializable;


public class Response implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private Object responseData;
	
	private String message;
	
	private int code;

	
	
	public Response(int code, String message,Object response ) {
		super();
		this.responseData = response;
		this.message = message;
		this.code = code;
	}

	

	public Object getResponseData() {
		return responseData;
	}
	public void setResponseData(Object responseData) {
		this.responseData = responseData;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
