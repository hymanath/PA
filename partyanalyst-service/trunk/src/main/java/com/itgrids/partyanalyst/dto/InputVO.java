package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InputVO {

	private String locationLevel;
	private String filterLevel;
	private List<Long> filterValueList;
	private List<Long> boothRoleIds;
	private String fromDateStr;
	private String toDateStr;
	private Date fromDate;
	private Date toDate;
	private Long boothInchargeEnrollmentId;
	private String resultType;
	private Long serialRangeId;
	private Long boothId;
	//Login user related attribute
	private String accessType;
	private Set<Long> accessValues;
	private Long accessLevelId;
	private Long activityMemberId;
	private Long userId;
	private String name;
	private Long levelId;
	private List<Long> levelValues = new ArrayList<Long>(0);
	private Long userTypeId = 0L;
	private Long stateId = 0L;
	private Long overalCount=0l;
	
	public Long getUserTypeId() {
		return userTypeId;
	}
	public void setUserTypeId(Long userTypeId) {
		this.userTypeId = userTypeId;
	}
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public Long getLevelId() {
		return levelId;
	}
	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}
	public List<Long> getLevelValues() {
		return levelValues;
	}
	public void setLevelValues(List<Long> levelValues) {
		this.levelValues = levelValues;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocationLevel() {
		return locationLevel;
	}
	public void setLocationLevel(String locationLevel) {
		this.locationLevel = locationLevel;
	}
	public String getFilterLevel() {
		return filterLevel;
	}
	public void setFilterLevel(String filterLevel) {
		this.filterLevel = filterLevel;
	}
	public List<Long> getFilterValueList() {
		if(filterValueList == null ){
			filterValueList = new ArrayList<Long>(0);
		}
		return filterValueList;
	}
	public String getFromDateStr() {
		return fromDateStr;
	}
	public void setFromDateStr(String fromDateStr) {
		this.fromDateStr = fromDateStr;
	}
	public String getToDateStr() {
		return toDateStr;
	}
	public void setToDateStr(String toDateStr) {
		this.toDateStr = toDateStr;
	}
	public Long getBoothInchargeEnrollmentId() {
		return boothInchargeEnrollmentId;
	}
	public void setBoothInchargeEnrollmentId(Long boothInchargeEnrollmentId) {
		this.boothInchargeEnrollmentId = boothInchargeEnrollmentId;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public String getResultType() {
		return resultType;
	}
	public void setResultType(String resultType) {
		this.resultType = resultType;
	}
	public List<Long> getBoothRoleIds() {
		if(boothRoleIds == null){
			boothRoleIds = new ArrayList<Long>(0);
		}
		return boothRoleIds;
	}
	public String getAccessType() {
		return accessType;
	}
	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}
	public Set<Long> getAccessValues() {
		if(accessValues == null){
			accessValues = new HashSet<Long>();
		}
		return accessValues;
	}
	
	public Long getAccessLevelId() {
		return accessLevelId;
	}
	public void setAccessLevelId(Long accessLevelId) {
		this.accessLevelId = accessLevelId;
	}
	public Long getActivityMemberId() {
		return activityMemberId;
	}
	public void setActivityMemberId(Long activityMemberId) {
		this.activityMemberId = activityMemberId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getSerialRangeId() {
		return serialRangeId;
	}
	public void setSerialRangeId(Long serialRangeId) {
		this.serialRangeId = serialRangeId;
	}
	public Long getBoothId() {
		return boothId;
	}
	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}
	public Long getOveralCount() {
		return overalCount;
	}
	public void setOveralCount(Long overalCount) {
		this.overalCount = overalCount;
	}
}
