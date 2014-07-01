package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.Date;

public class SurveyResponceVO implements Serializable 
{

	
	private static final long serialVersionUID = -7553249000409612768L;
	private Long surveyUserId;
	private Long surveyorId;
	private Long voterId;
	private Long casteId;
	private Long hamletId;
	private String casteName;
	private String hamletName;
	
	private Date date;
	
	private String longitude;
	private String latitude;
	public Long getSurveyUserId() {
		return surveyUserId;
	}
	public void setSurveyUserId(Long surveyUserId) {
		this.surveyUserId = surveyUserId;
	}
	public Long getSurveyorId() {
		return surveyorId;
	}
	public void setSurveyorId(Long surveyorId) {
		this.surveyorId = surveyorId;
	}
	public Long getVoterId() {
		return voterId;
	}
	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}
	public Long getCasteId() {
		return casteId;
	}
	public void setCasteId(Long casteId) {
		this.casteId = casteId;
	}
	public Long getHamletId() {
		return hamletId;
	}
	public void setHamletId(Long hamletId) {
		this.hamletId = hamletId;
	}
	public String getCasteName() {
		return casteName;
	}
	public void setCasteName(String casteName) {
		this.casteName = casteName;
	}
	public String getHamletName() {
		return hamletName;
	}
	public void setHamletName(String hamletName) {
		this.hamletName = hamletName;
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
	
	
	
}
