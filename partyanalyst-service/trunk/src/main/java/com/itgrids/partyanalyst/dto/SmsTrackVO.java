package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class SmsTrackVO implements Serializable {
	private Long userSmsTrackId;
	private Long userId;
	private String smsUserName;
	private String smsPassword;
	private String senderId;
	private Long renewalSmsCount;
	private String description;
	private String firstName;
	private String lastName;
	private Long updateStatus;
	private Long sessionStatus;
	private Long recordNotFound;
	
	

	public Long getUserSmsTrackId() {
		return userSmsTrackId;
	}

	public void setUserSmsTrackId(Long userSmsTrackId) {
		this.userSmsTrackId = userSmsTrackId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getSmsUserName() {
		return smsUserName;
	}

	public void setSmsUserName(String smsUserName) {
		this.smsUserName = smsUserName;
	}

	public String getSmsPassword() {
		return smsPassword;
	}

	public void setSmsPassword(String smsPassword) {
		this.smsPassword = smsPassword;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public Long getRenewalSmsCount() {
		return renewalSmsCount;
	}

	public void setRenewalSmsCount(Long renewalSmsCount) {
		this.renewalSmsCount = renewalSmsCount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getUpdateStatus() {
		return updateStatus;
	}

	public void setUpdateStatus(Long updateStatus) {
		this.updateStatus = updateStatus;
	}

	public Long getSessionStatus() {
		return sessionStatus;
	}

	public void setSessionStatus(Long sessionStatus) {
		this.sessionStatus = sessionStatus;
	}

	public Long getRecordNotFound() {
		return recordNotFound;
	}

	public void setRecordNotFound(Long recordNotFound) {
		this.recordNotFound = recordNotFound;
	}
	
    
}
