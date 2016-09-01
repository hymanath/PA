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
	
	private List<UserDataVO> subList;
	private Long parentUserTypeId;
	private String parentUserType;
	private Long activityMemberId;
	
	private Long id;
	private String name;
	
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
	public List<UserDataVO> getSubList() {
		return subList;
	}
	public void setSubList(List<UserDataVO> subList) {
		this.subList = subList;
	}
	public Long getParentUserTypeId() {
		return parentUserTypeId;
	}
	public void setParentUserTypeId(Long parentUserTypeId) {
		this.parentUserTypeId = parentUserTypeId;
	}
	public String getParentUserType() {
		return parentUserType;
	}
	public void setParentUserType(String parentUserType) {
		this.parentUserType = parentUserType;
	}
	public Long getActivityMemberId() {
		return activityMemberId;
	}
	public void setActivityMemberId(Long activityMemberId) {
		this.activityMemberId = activityMemberId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
