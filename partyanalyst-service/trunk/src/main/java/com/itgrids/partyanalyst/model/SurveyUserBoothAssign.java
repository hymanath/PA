package com.itgrids.partyanalyst.model;

import java.util.Date;

/**
 * 
 * @author Prasad Thiragabathina
 * 
 * This Model Describes About Survey User Booth Details Means In Wich Booth He Is Collecting The Voter Details
 *
 */
public class SurveyUserBoothAssign 
{

	private Long surveyUserBoothAssignId;
	private SurveyUser surveyUser;
	private Constituency constituency;
	private Panchayat Panchayat;
	private Booth booth;
	private Date insertedTime;
	private Date updatedTime;
	public Long getSurveyUserBoothAssignId() {
		return surveyUserBoothAssignId;
	}
	public void setSurveyUserBoothAssignId(Long surveyUserBoothAssignId) {
		this.surveyUserBoothAssignId = surveyUserBoothAssignId;
	}
	public SurveyUser getSurveyUser() {
		return surveyUser;
	}
	public void setSurveyUser(SurveyUser surveyUser) {
		this.surveyUser = surveyUser;
	}
	public Constituency getConstituency() {
		return constituency;
	}
	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}
	public Panchayat getPanchayat() {
		return Panchayat;
	}
	public void setPanchayat(Panchayat panchayat) {
		Panchayat = panchayat;
	}
	public Booth getBooth() {
		return booth;
	}
	public void setBooth(Booth booth) {
		this.booth = booth;
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
