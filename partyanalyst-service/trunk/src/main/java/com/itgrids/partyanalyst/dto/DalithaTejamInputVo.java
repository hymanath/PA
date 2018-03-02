package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class DalithaTejamInputVo {

	private List<Long> locationScopeIds= new ArrayList<Long>();
	private List<Long> locationValues= new ArrayList<Long>();
	private String fromDate;
	private String toDate;
	private Long activityId;
	private Long locationScopeId;
	private Long locationValue;
	
	
	public List<Long> getLocationScopeIds() {
		return locationScopeIds;
	}
	public void setLocationScopeIds(List<Long> locationScopeIds) {
		this.locationScopeIds = locationScopeIds;
	}
	public List<Long> getLocationValues() {
		return locationValues;
	}
	public void setLocationValues(List<Long> locationValues) {
		this.locationValues = locationValues;
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
	public Long getActivityId() {
		return activityId;
	}
	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}
	public Long getLocationScopeId() {
		return locationScopeId;
	}
	public void setLocationScopeId(Long locationScopeId) {
		this.locationScopeId = locationScopeId;
	}
	public Long getLocationValue() {
		return locationValue;
	}
	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
	
	
}
