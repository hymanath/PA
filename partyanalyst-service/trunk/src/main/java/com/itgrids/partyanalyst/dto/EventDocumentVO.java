package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class EventDocumentVO {
	
	private String path;
	private String name;
	private String id;
	private Long day;
	private Long locationScopeId;
	private Long locationValue;
	private Long activityLevel;
	private Long activityId;
	private List<EventDocumentVO> subList = new ArrayList<EventDocumentVO>();
	
	
	
	
	public List<EventDocumentVO> getSubList() {
		return subList;
	}
	public void setSubList(List<EventDocumentVO> subList) {
		this.subList = subList;
	}
	public Long getActivityId() {
		return activityId;
	}
	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}
	public Long getActivityLevel() {
		return activityLevel;
	}
	public void setActivityLevel(Long activityLevel) {
		this.activityLevel = activityLevel;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public Long getDay() {
		return day;
	}
	public void setDay(Long day) {
		this.day = day;
	}

	
	

}
