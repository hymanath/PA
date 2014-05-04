package com.itgrids.eliteclub.dto;

import java.util.Date;

public class BoothPollingUpdatesVO {

	
	private int BoothPollingUpdatesId;
	private String message;
	private String mobileNumber;
    private Date  recievedDate;
    private Date  insertedDate;
    
    
	public int getBoothPollingUpdatesId() {
		return BoothPollingUpdatesId;
	}
	public void setBoothPollingUpdatesId(int boothPollingUpdatesId) {
		BoothPollingUpdatesId = boothPollingUpdatesId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public Date getRecievedDate() {
		return recievedDate;
	}
	public void setRecievedDate(Date recievedDate) {
		this.recievedDate = recievedDate;
	}
	public Date getInsertedDate() {
		return insertedDate;
	}
	public void setInsertedDate(Date insertedDate) {
		this.insertedDate = insertedDate;
	}
    
    
}
