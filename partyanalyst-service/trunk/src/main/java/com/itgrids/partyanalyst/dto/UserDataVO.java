package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class UserDataVO implements Serializable{
	
	private Long userId;
	
	private Long   userLevelId;
	private String level;
	private Long   levelValue;
	
	private Long userTypeId;
	private String userType;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getUserLevelId() {
		return userLevelId;
	}
	public void setUserLevelId(Long userLevelId) {
		this.userLevelId = userLevelId;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public Long getLevelValue() {
		return levelValue;
	}
	public void setLevelValue(Long levelValue) {
		this.levelValue = levelValue;
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
	
	

}
