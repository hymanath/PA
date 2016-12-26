package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class PrintUpdateDetailsStatusVO implements Serializable{
	
	/*  NOTE : Please don't add any Extra Parameters in this VO
	 *  It have multiple dependencies in Web Service.
	 * 
	 */
	
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
