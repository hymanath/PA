package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.Date;

public class CardSenderVO implements Serializable {

	private static final long serialVersionUID = -2144751585168111801L;
	
	private String 			 name;
	private String 			 mobileNumber;
	private String			 message;
	private	Long			 userId;
	private Date		 	 insertedTime;
	private String			 tdpCadreIds;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	public String getTdpCadreIds() {
		return tdpCadreIds;
	}
	public void setTdpCadreIds(String tdpCadreIds) {
		this.tdpCadreIds = tdpCadreIds;
	}
	
	
	

}
