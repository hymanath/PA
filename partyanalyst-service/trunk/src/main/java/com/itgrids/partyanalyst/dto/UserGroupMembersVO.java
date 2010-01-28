package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class UserGroupMembersVO implements Serializable {
	
	private String userName;
	private Long userId;
	private String address;
	private String phoneNumber;
	private String mobileNumber;
	
	public String getUserName() {
		return userName;
	}
	public Long getUserId() {
		return userId;
	}
	public String getAddress() {
		return address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}


}
