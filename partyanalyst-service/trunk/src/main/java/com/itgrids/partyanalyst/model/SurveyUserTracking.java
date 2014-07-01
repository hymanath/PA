package com.itgrids.partyanalyst.model;

import java.util.Date;

public class SurveyUserTracking
{

	private Long surveyUserTrackingId;
	private SurveyUser surveyUser;
	private Date date;
	private String longitude;
	private String latitude;
	private Date insertedTime;
	private Date updatedTime;
	public Long getSurveyUserTrackingId() {
		return surveyUserTrackingId;
	}
	public void setSurveyUserTrackingId(Long surveyUserTrackingId) {
		this.surveyUserTrackingId = surveyUserTrackingId;
	}
	public SurveyUser getSurveyUser() {
		return surveyUser;
	}
	public void setSurveyUser(SurveyUser surveyUser) {
		this.surveyUser = surveyUser;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	
	
}
