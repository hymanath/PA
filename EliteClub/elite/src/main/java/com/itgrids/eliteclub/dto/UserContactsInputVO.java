package com.itgrids.eliteclub.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Anilkumar Ravula Apr 12, 2014
 *
 */
@XmlRootElement
public class UserContactsInputVO  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 765477854784557L;
	
	private Integer userId;
	private String userName;
	private String emailId;
	private String mobileno;
	private String imeiNo;
	private Date userCreationTime;
	
	private Map<String,String> contacts = new HashMap<>();

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public String getImeiNo() {
		return imeiNo;
	}

	public void setImeiNo(String imeiNo) {
		this.imeiNo = imeiNo;
	}

	public Date getUserCreationTime() {
		return userCreationTime;
	}

	public void setUserCreationTime(Date userCreationTime) {
		this.userCreationTime = userCreationTime;
	}

	public Map<String, String> getContacts() {
		return contacts;
	}

	public void setContacts(Map<String, String> contacts) {
		this.contacts = contacts;
	}

	
	

}
