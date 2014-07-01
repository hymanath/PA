package com.itgrids.partyanalyst.model;

import java.util.Date;

/**
 * 
 * @author Prasad Thiragabathina
 * 
 * This Model Describes About Survey User Relation , means whis user comes under which leader.
 *
 */
public class SurveyUserRelation 
{

	private Long surveyUserRelationId;
	private SurveyUserType surveyUserType;
	private SurveyUser surveyUser;
	private SurveyUser surveyLeader;
	private Constituency constituency;
	private String activeStatus;
	private Date insertedTime;
	private Date updatedTime;
	public Long getSurveyUserRelationId() {
		return surveyUserRelationId;
	}
	public void setSurveyUserRelationId(Long surveyUserRelationId) {
		this.surveyUserRelationId = surveyUserRelationId;
	}
	public SurveyUserType getSurveyUserType() {
		return surveyUserType;
	}
	public void setSurveyUserType(SurveyUserType surveyUserType) {
		this.surveyUserType = surveyUserType;
	}
	public SurveyUser getSurveyUser() {
		return surveyUser;
	}
	public void setSurveyUser(SurveyUser surveyUser) {
		this.surveyUser = surveyUser;
	}
	public SurveyUser getSurveyLeader() {
		return surveyLeader;
	}
	public void setSurveyLeader(SurveyUser surveyLeader) {
		this.surveyLeader = surveyLeader;
	}
	public Constituency getConstituency() {
		return constituency;
	}
	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}
	public String getActiveStatus() {
		return activeStatus;
	}
	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
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
