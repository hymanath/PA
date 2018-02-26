package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class DalithaTejamInputVo {

	private List<Long> locationScopeId= new ArrayList<Long>();
	private List<Long> locationValue= new ArrayList<Long>();
	private String fromDate;
	private String toDate;
	private Long activityId;
	
	
	public List<Long> getLocationScopeId() {
		return locationScopeId;
	}
	public void setLocationScopeId(List<Long> locationScopeId) {
		this.locationScopeId = locationScopeId;
	}
	public List<Long> getLocationValue() {
		return locationValue;
	}
	public void setLocationValue(List<Long> locationValue) {
		this.locationValue = locationValue;
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
	
	
}
