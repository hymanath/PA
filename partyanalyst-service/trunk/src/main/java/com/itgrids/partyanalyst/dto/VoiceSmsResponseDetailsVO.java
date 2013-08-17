package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class VoiceSmsResponseDetailsVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	private Long responseId;
	private String responseCode;
	private String dateSent;
	private String numbers;
	public Long getResponseId() {
		return responseId;
	}
	public void setResponseId(Long responseId) {
		this.responseId = responseId;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getDateSent() {
		return dateSent;
	}
	public void setDateSent(String dateSent) {
		this.dateSent = dateSent;
	}
	public String getNumbers() {
		return numbers;
	}
	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}
	

}
