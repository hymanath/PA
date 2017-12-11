package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
//Don't add new variables
public class ActivityMemberVO implements Serializable{
	
	private Long userId;
	private Long activityMemberId;
	private Long userTypeId;
	private Long userLocationLevelId;
	private Long userLocationLevelValues;
	
	Map<Long,Map<Long,UserTypeVO>> userTypesMap;
	Map<Long,UserTypeVO> activityMembersMap;
	Map<Long,Set<Long>> locationLevelIdsMap;
	
	private String state;
	private String fromDate;
	private String toDate;
	
	private List<Long> npIds=new ArrayList<Long>(0);
	private List<Long> impactScopeIds=new ArrayList<Long>(0);
	//Don't add new variables
	
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
	public Map<Long, UserTypeVO> getActivityMembersMap() {
		return activityMembersMap;
	}
	public void setActivityMembersMap(Map<Long, UserTypeVO> activityMembersMap) {
		this.activityMembersMap = activityMembersMap;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public List<Long> getNpIds() {
		return npIds;
	}
	public void setNpIds(List<Long> npIds) {
		this.npIds = npIds;
	}
	public List<Long> getImpactScopeIds() {
		return impactScopeIds;
	}
	public void setImpactScopeIds(List<Long> impactScopeIds) {
		this.impactScopeIds = impactScopeIds;
	}
	
}
