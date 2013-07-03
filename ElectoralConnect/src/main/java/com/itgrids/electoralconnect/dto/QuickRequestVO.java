package com.itgrids.electoralconnect.dto;

import java.io.Serializable;

public class QuickRequestVO implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8982533925785155628L;
	private String userName;
    private String emailId;
    private String mobileNumber;
    private String userRequirement;
   	private String toEmailId;
   	private String text;
   	private String subject;
   	private String fromEmailId;
	
	public String getFromEmailId() {
		return fromEmailId;
	}
	public void setFromEmailId(String fromEmailId) {
		this.fromEmailId = fromEmailId;
	}
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
	public String getToEmailId() {
		return toEmailId;
	}
	public void setToEmailId(String toEmailId) {
		this.toEmailId = toEmailId;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
}
