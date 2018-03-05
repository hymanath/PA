package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

import com.itgrids.partyanalyst.model.BaseModel;

public class KaizalaMessageVO extends BaseModel implements Serializable{

	private String phoneNumberStr;
	private String groupId;
	private String message;
	private Long userId;
	
	
	public String getPhoneNumberStr() {
		return phoneNumberStr;
	}
	public void setPhoneNumberStr(String phoneNumberStr) {
		this.phoneNumberStr = phoneNumberStr;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
}
