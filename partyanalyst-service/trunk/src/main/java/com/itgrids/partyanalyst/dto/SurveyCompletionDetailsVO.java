package com.itgrids.partyanalyst.dto;

import java.util.List;

public class SurveyCompletionDetailsVO {
	
	private Long locationScopeId;
	private Long locationValue;
	private Long userTypeId;
	
	private boolean dataCollectorCompleted;
	private boolean verifierCompleted;
	private String locationName;
	
	private List<Long> collectorCompletedList;
	private List<Long> verifierCompletedList;
	
	
	public List<Long> getCollectorCompletedList() {
		return collectorCompletedList;
	}
	public void setCollectorCompletedList(List<Long> collectorCompletedList) {
		this.collectorCompletedList = collectorCompletedList;
	}
	public List<Long> getVerifierCompletedList() {
		return verifierCompletedList;
	}
	public void setVerifierCompletedList(List<Long> verifierCompletedList) {
		this.verifierCompletedList = verifierCompletedList;
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
	public Long getUserTypeId() {
		return userTypeId;
	}
	public void setUserTypeId(Long userTypeId) {
		this.userTypeId = userTypeId;
	}
	public boolean isDataCollectorCompleted() {
		return dataCollectorCompleted;
	}
	public void setDataCollectorCompleted(boolean dataCollectorCompleted) {
		this.dataCollectorCompleted = dataCollectorCompleted;
	}
	public boolean isVerifierCompleted() {
		return verifierCompleted;
	}
	public void setVerifierCompleted(boolean verifierCompleted) {
		this.verifierCompleted = verifierCompleted;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

}
