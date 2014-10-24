package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CardSenderVO implements Serializable {

	private static final long serialVersionUID = -2144751585168111801L;
	
	private String 			 name;
	private String 			 mobileNumber;
	private String			 message;
	private	Long			 userId;
	private Date		 	 insertedTime;
	private List<Long>		 cadreIds;
	private String			 mobileNums;
	
	public String getMobileNums() {
		return mobileNums;
	}
	public void setMobileNums(String mobileNums) {
		this.mobileNums = mobileNums;
	}
	public List<Long> getCadreIds() {
		return cadreIds;
	}
	public void setCadreIds(List<Long> cadreIds) {
		this.cadreIds = cadreIds;
	}
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
	

}
