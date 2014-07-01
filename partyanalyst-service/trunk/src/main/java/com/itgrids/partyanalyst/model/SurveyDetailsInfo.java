package com.itgrids.partyanalyst.model;

import java.util.Date;

/**
 * 
 * @author Prasad Thiragabathina
 * 
 * This Model Describes About Stores all survey details .
 *
 */
public class SurveyDetailsInfo
{

	private Long surveyDetailsInfoId;
	private SurveyUser surveyUser;
	private SurveySurveyorType surveySurveyorType;
	private Voter voter;
	private String mobileNumber;
	private String isCadre;
	private String isInfluencingPeople;
	private CasteState caste;
	private Hamlet hamlet;
	private String localArea;
	private String casteName;
	private String hamletName;
	private Date date;
	private String longitude;
	private String latitude;
	private Date insertedTime;
	private Date updatedTime;
	public Long getSurveyDetailsInfoId() {
		return surveyDetailsInfoId;
	}
	public void setSurveyDetailsInfoId(Long surveyDetailsInfoId) {
		this.surveyDetailsInfoId = surveyDetailsInfoId;
	}
	public SurveyUser getSurveyUser() {
		return surveyUser;
	}
	public void setSurveyUser(SurveyUser surveyUser) {
		this.surveyUser = surveyUser;
	}
	public SurveySurveyorType getSurveySurveyorType() {
		return surveySurveyorType;
	}
	public void setSurveySurveyorType(SurveySurveyorType surveySurveyorType) {
		this.surveySurveyorType = surveySurveyorType;
	}
	public Voter getVoter() {
		return voter;
	}
	public void setVoter(Voter voter) {
		this.voter = voter;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getIsCadre() {
		return isCadre;
	}
	public void setIsCadre(String isCadre) {
		this.isCadre = isCadre;
	}
	public String getIsInfluencingPeople() {
		return isInfluencingPeople;
	}
	public void setIsInfluencingPeople(String isInfluencingPeople) {
		this.isInfluencingPeople = isInfluencingPeople;
	}

	public CasteState getCaste() {
		return caste;
	}
	public void setCaste(CasteState caste) {
		this.caste = caste;
	}
	public Hamlet getHamlet() {
		return hamlet;
	}
	public void setHamlet(Hamlet hamlet) {
		this.hamlet = hamlet;
	}
	public String getLocalArea() {
		return localArea;
	}
	public void setLocalArea(String localArea) {
		this.localArea = localArea;
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
