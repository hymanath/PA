package com.itgrids.electoralconnect.dto;

import java.io.Serializable;

public class UserVO implements Serializable{
	/**
	 * 
	 */
private static final long serialVersionUID = -5332293961274583806L;
private String username;
private String pwd;
private String firstname;
private String lastname;
private String emailId;
private String mobileNo;
private String epicId;
private Long profileId;
private Long loginId;
private Long userId;
private String description;
private int resultCode;
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPwd() {
	return pwd;
}
public void setPwd(String pwd) {
	this.pwd = pwd;
}
public String getFirstname() {
	return firstname;
}
public void setFirstname(String firstname) {
	this.firstname = firstname;
}
public String getLastname() {
	return lastname;
}
public void setLastname(String lastname) {
	this.lastname = lastname;
}
public String getEmailId() {
	return emailId;
}
public void setEmailId(String emailId) {
	this.emailId = emailId;
}
public String getMobileNo() {
	return mobileNo;
}
public void setMobileNo(String mobileNo) {
	this.mobileNo = mobileNo;
}
public String getEpicId() {
	return epicId;
}
public void setEpicId(String epicId) {
	this.epicId = epicId;
}
public Long getProfileId() {
	return profileId;
}
public void setProfileId(Long profileId) {
	this.profileId = profileId;
}
public Long getLoginId() {
	return loginId;
}
public void setLoginId(Long loginId) {
	this.loginId = loginId;
}
public Long getUserId() {
	return userId;
}
public void setUserId(Long userId) {
	this.userId = userId;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public int getResultCode() {
	return resultCode;
}
public void setResultCode(int resultCode) {
	this.resultCode = resultCode;
}


}
