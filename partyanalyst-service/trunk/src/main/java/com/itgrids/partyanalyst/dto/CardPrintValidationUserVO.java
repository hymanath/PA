package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class CardPrintValidationUserVO implements Serializable{
	
	private static final long serialVersionUID = -1525648264422452121L;
	
	private Long   cardPrintValidationUserId;
	private String name;
	private String mobileno;
	private String username;
	private String password;
	private String status;
	
	public CardPrintValidationUserVO(){}
	
	public CardPrintValidationUserVO(String username, String password , String status){
		this.username = username;
		this.password = password;
		this.status = status;
	}
	
	public Long getCardPrintValidationUserId() {
		return cardPrintValidationUserId;
	}
	public void setCardPrintValidationUserId(Long cardPrintValidationUserId) {
		this.cardPrintValidationUserId = cardPrintValidationUserId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
