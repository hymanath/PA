package com.itgrids.partyanalyst.dto;

public class QuickRequestVO {

	
	private String userName;
    private String emailId;
    private String mobileNumber;
    private String userRequirement;
   	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getUserRequirement() {
		return userRequirement;
	}
	public void setUserRequirement(String userRequirement) {
		this.userRequirement = userRequirement;
	}
	
}
