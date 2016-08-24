package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

public class ActivityMemberVO implements Serializable{
	
	private Long userId;
	private Long activityMemberId;
	private Long userTypeId;
	private Long userLocationLevelId;
	private Long userLocationLevelValues;
	
	Map<Long,Map<Long,UserTypeVO>> userTypesMap;
	Map<Long,Set<Long>> locationLevelIdsMap;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getActivityMemberId() {
		return activityMemberId;
	}
	public void setActivityMemberId(Long activityMemberId) {
		this.activityMemberId = activityMemberId;
	}
	public Long getUserTypeId() {
		return userTypeId;
	}
	public void setUserTypeId(Long userTypeId) {
		this.userTypeId = userTypeId;
	}
	public Long getUserLocationLevelId() {
		return userLocationLevelId;
	}
	public void setUserLocationLevelId(Long userLocationLevelId) {
		this.userLocationLevelId = userLocationLevelId;
	}
	public Long getUserLocationLevelValues() {
		return userLocationLevelValues;
	}
	public void setUserLocationLevelValues(Long userLocationLevelValues) {
		this.userLocationLevelValues = userLocationLevelValues;
	}
	public Map<Long, Map<Long, UserTypeVO>> getUserTypesMap() {
		return userTypesMap;
	}
	public void setUserTypesMap(Map<Long, Map<Long, UserTypeVO>> userTypesMap) {
		this.userTypesMap = userTypesMap;
	}
	public Map<Long, Set<Long>> getLocationLevelIdsMap() {
		return locationLevelIdsMap;
	}
	public void setLocationLevelIdsMap(Map<Long, Set<Long>> locationLevelIdsMap) {
		this.locationLevelIdsMap = locationLevelIdsMap;
	}
	
}
