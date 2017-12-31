package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class ActivityDetailsVO {

	private Long locationId;
	private String name;
	private String conductedDate;
	private String plannedDate;
	private Long activityScopeId;
	private String status;
	private Long activityLocationInfoId;
	private Long activityQuestionnaireId;
	private Long activityDaywiseQuestionnaireId;
	private String activityStarteDate;
	private String activityEndDate;
	private String conductedStatus;
	
	private Long questionId;
	private String question;
	private Long optionTypeId;
	private String optionType;
	private String hasRemarks;
	private Long optionId;
	private Long tabDetailsId;
	private Long answerOptionId;
	private String answerText;
	private String locationType;
	
	
    private String imei;
    private String uniqueKey;
    private String latitude;
    private String longitude;
    private Long itdpAppUserId;
    private String syncSource;
    private String imageBase64String;
	private Long id;
	private String appVersion;
	private String isMandatory;
	private String questionType;
	private String activityDate;
    
	private List<ActivityOptionVO> optionList;
	private List<ActivityDetailsVO> subList = new ArrayList<ActivityDetailsVO>();
	
	
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getConductedDate() {
		return conductedDate;
	}
	public void setConductedDate(String conductedDate) {
		this.conductedDate = conductedDate;
	}
	public String getPlannedDate() {
		return plannedDate;
	}
	public void setPlannedDate(String plannedDate) {
		this.plannedDate = plannedDate;
	}
	
	public Long getActivityScopeId() {
		return activityScopeId;
	}
	public void setActivityScopeId(Long activityScopeId) {
		this.activityScopeId = activityScopeId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public Long getOptionTypeId() {
		return optionTypeId;
	}
	public void setOptionTypeId(Long optionTypeId) {
		this.optionTypeId = optionTypeId;
	}
	public String getOptionType() {
		return optionType;
	}
	public void setOptionType(String optionType) {
		this.optionType = optionType;
	}
	public List<ActivityOptionVO> getOptionList() {
		return optionList;
	}
	public void setOptionList(List<ActivityOptionVO> optionList) {
		this.optionList = optionList;
	}
	public String getHasRemarks() {
		return hasRemarks;
	}
	public void setHasRemarks(String hasRemarks) {
		this.hasRemarks = hasRemarks;
	}
	public Long getActivityLocationInfoId() {
		return activityLocationInfoId;
	}
	public void setActivityLocationInfoId(Long activityLocationInfoId) {
		this.activityLocationInfoId = activityLocationInfoId;
	}
	public Long getActivityQuestionnaireId() {
		return activityQuestionnaireId;
	}
	public void setActivityQuestionnaireId(Long activityQuestionnaireId) {
		this.activityQuestionnaireId = activityQuestionnaireId;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getUniqueKey() {
		return uniqueKey;
	}
	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public Long getItdpAppUserId() {
		return itdpAppUserId;
	}
	public void setItdpAppUserId(Long itdpAppUserId) {
		this.itdpAppUserId = itdpAppUserId;
	}
	public String getSyncSource() {
		return syncSource;
	}
	public void setSyncSource(String syncSource) {
		this.syncSource = syncSource;
	}
	public Long getOptionId() {
		return optionId;
	}
	public void setOptionId(Long optionId) {
		this.optionId = optionId;
	}
	public List<ActivityDetailsVO> getSubList() {
		return subList;
	}
	public void setSubList(List<ActivityDetailsVO> subList) {
		this.subList = subList;
	}
	public Long getTabDetailsId() {
		return tabDetailsId;
	}
	public void setTabDetailsId(Long tabDetailsId) {
		this.tabDetailsId = tabDetailsId;
	}
	public Long getAnswerOptionId() {
		return answerOptionId;
	}
	public void setAnswerOptionId(Long answerOptionId) {
		this.answerOptionId = answerOptionId;
	}
	public String getAnswerText() {
		return answerText;
	}
	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}
	public String getImageBase64String() {
		return imageBase64String;
	}
	public void setImageBase64String(String imageBase64String) {
		this.imageBase64String = imageBase64String;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAppVersion() {
		return appVersion;
	}
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	public String getIsMandatory() {
		return isMandatory;
	}
	public void setIsMandatory(String isMandatory) {
		this.isMandatory = isMandatory;
	}
	public Long getActivityDaywiseQuestionnaireId() {
		return activityDaywiseQuestionnaireId;
	}
	public void setActivityDaywiseQuestionnaireId(
			Long activityDaywiseQuestionnaireId) {
		this.activityDaywiseQuestionnaireId = activityDaywiseQuestionnaireId;
	}
	public String getQuestionType() {
		return questionType;
	}
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	public String getActivityDate() {
		return activityDate;
	}
	public void setActivityDate(String activityDate) {
		this.activityDate = activityDate;
	}
	public String getActivityStarteDate() {
		return activityStarteDate;
	}
	public void setActivityStarteDate(String activityStarteDate) {
		this.activityStarteDate = activityStarteDate;
	}
	public String getActivityEndDate() {
		return activityEndDate;
	}
	public void setActivityEndDate(String activityEndDate) {
		this.activityEndDate = activityEndDate;
	}
	public String getConductedStatus() {
		return conductedStatus;
	}
	public void setConductedStatus(String conductedStatus) {
		this.conductedStatus = conductedStatus;
	}
	public String getLocationType() {
		return locationType;
	}
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
	
	
	
	
}
