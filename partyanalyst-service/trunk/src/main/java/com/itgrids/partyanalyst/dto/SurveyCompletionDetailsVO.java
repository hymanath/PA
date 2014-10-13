package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
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
	private List<SurveyCompletionDetailsVO>  completedList = new ArrayList<SurveyCompletionDetailsVO>();
	
	private  List<SurveyCompletionDetailsVO>  verificationList = new ArrayList<SurveyCompletionDetailsVO>();
	private  List<SurveyCompletionDetailsVO>  surveyList = new ArrayList<SurveyCompletionDetailsVO>();
	private Long id;
	private String name;
	private Long noOfBooths = 0l;
	private Long completedBooths = 0l;
	private Long totalVoters = 0l;
	private Long casteAssignedVoters = 0l;
	private Long casteNotAssignedVoters;
	private Long districtId;
	private String district;
	
	
	
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
	public Long getNoOfBooths() {
		return noOfBooths;
	}
	public void setNoOfBooths(Long noOfBooths) {
		this.noOfBooths = noOfBooths;
	}
	public Long getCompletedBooths() {
		return completedBooths;
	}
	public void setCompletedBooths(Long completedBooths) {
		this.completedBooths = completedBooths;
	}
	public Long getTotalVoters() {
		return totalVoters;
	}
	public void setTotalVoters(Long totalVoters) {
		this.totalVoters = totalVoters;
	}
	public Long getCasteAssignedVoters() {
		return casteAssignedVoters;
	}
	public void setCasteAssignedVoters(Long casteAssignedVoters) {
		this.casteAssignedVoters = casteAssignedVoters;
	}
	public Long getCasteNotAssignedVoters() {
		return casteNotAssignedVoters;
	}
	public void setCasteNotAssignedVoters(Long casteNotAssignedVoters) {
		this.casteNotAssignedVoters = casteNotAssignedVoters;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public List<SurveyCompletionDetailsVO> getCompletedList() {
		return completedList;
	}
	public void setCompletedList(List<SurveyCompletionDetailsVO> completedList) {
		this.completedList = completedList;
	}
	public List<SurveyCompletionDetailsVO> getVerificationList() {
		return verificationList;
	}
	public void setVerificationList(List<SurveyCompletionDetailsVO> verificationList) {
		this.verificationList = verificationList;
	}
	public List<SurveyCompletionDetailsVO> getSurveyList() {
		return surveyList;
	}
	public void setSurveyList(List<SurveyCompletionDetailsVO> surveyList) {
		this.surveyList = surveyList;
	}
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
