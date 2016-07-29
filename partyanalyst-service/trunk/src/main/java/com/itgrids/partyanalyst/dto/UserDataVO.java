package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class UserDataVO implements Serializable{
	
	private Long userId;
	
	private Long   userAccessLevelId;
	private String userAccessLevel;
	private List<Long>   userAccessLevelValuesList;
	
	private Long userTypeId;
	private String userType;
	
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getUserTypeId() {
		return userTypeId;
	}
	public void setUserTypeId(Long userTypeId) {
		this.userTypeId = userTypeId;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public Long getUserAccessLevelId() {
		return userAccessLevelId;
	}
	public void setUserAccessLevelId(Long userAccessLevelId) {
		this.userAccessLevelId = userAccessLevelId;
	}
	public String getUserAccessLevel() {
		return userAccessLevel;
	}
	public void setUserAccessLevel(String userAccessLevel) {
		this.userAccessLevel = userAccessLevel;
	}
	public List<Long> getUserAccessLevelValuesList() {
		return userAccessLevelValuesList;
	}
	public void setUserAccessLevelValuesList(List<Long> userAccessLevelValuesList) {
		this.userAccessLevelValuesList = userAccessLevelValuesList;
	}

}
