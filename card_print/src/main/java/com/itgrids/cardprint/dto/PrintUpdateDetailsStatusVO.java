package com.itgrids.cardprint.dto;

import java.io.Serializable;

public class PrintUpdateDetailsStatusVO implements Serializable{
	
	private int resultCode;
	private String message;
	
	public int getResultCode() {
		return resultCode;
	}
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
