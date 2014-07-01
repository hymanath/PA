package com.itgrids.partyanalyst.model;

import java.util.Date;

/**
 * 
 * @author Prasad Thiragabathina
 *
 *This Model Describes About Surey User Detais(firstname,lastname,username,password etc....)
 */
public class SurveyUser
{

	private Long surveyUserId;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String mobileNo;
	private String address;
	private SurveyUserType surveyUserType;
	private String activeStatus;
	private Date insertedTime;
	private Date updatedTime;
	private String remarks;
	
	public Long getSurveyUserId() {
		return surveyUserId;
	}
	public void setSurveyUserId(Long surveyUserId) {
		this.surveyUserId = surveyUserId;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public SurveyUserType getSurveyUserType() {
		return surveyUserType;
	}
	public void setSurveyUserType(SurveyUserType surveyUserType) {
		this.surveyUserType = surveyUserType;
	}
	public String getActiveStatus() {
		return activeStatus;
	}
	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
}
