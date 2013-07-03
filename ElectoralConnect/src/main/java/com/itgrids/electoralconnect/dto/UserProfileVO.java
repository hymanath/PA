package com.itgrids.electoralconnect.dto;

import java.io.Serializable;

public class UserProfileVO implements Serializable {
 /**
	 * 
	 */
 private static final long serialVersionUID = 1L;
 private String firstName;
 private String lastName;
 private String emailId;
 private String epicId;
 private String mobileNo;
 private String userRole;
 private String userType;
 
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
public String getEmailId() {
	return emailId;
}
public void setEmailId(String emailId) {
	this.emailId = emailId;
}
public String getEpicId() {
	return epicId;
}
public void setEpicId(String epicId) {
	this.epicId = epicId;
}
public String getMobileNo() {
	return mobileNo;
}
public void setMobileNo(String mobileNo) {
	this.mobileNo = mobileNo;
}
public String getUserRole() {
	return userRole;
}
public void setUserRole(String userRole) {
	this.userRole = userRole;
}
public String getUserType() {
	return userType;
}
public void setUserType(String userType) {
	this.userType = userType;
}

 
	
}
