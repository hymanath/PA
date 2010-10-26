package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class UserGroupMembersVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private Long userId;
	private String address;
	private String phoneNumber;
	private String mobileNumber;
	private String location;
	private Long groupId;
	private String groupName;
	private String groupDesc;
	private String designation;
	private Long groupMemberId;
	private String createdDate;
	private String emailId;
	private ResultStatus rs;
	private Boolean confirmation;
	
	
	
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
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getGroupName() {
		return groupName;
	}
	public Long getGroupMemberId() {
		return groupMemberId;
	}
	public void setGroupMemberId(Long groupMemberId) {
		this.groupMemberId = groupMemberId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ResultStatus getRs() {
		return rs;
	}
	public void setRs(ResultStatus rs) {
		this.rs = rs;
	}
	public void setConfirmation(Boolean confirmation) {
		this.confirmation = confirmation;
	}
	public Boolean getConfirmation() {
		return confirmation;
	}
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	public String getGroupDesc() {
		return groupDesc;
	}
	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}


}
