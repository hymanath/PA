package com.itgrids.partyanalyst.model;

import java.util.Date;

/**
 * 
 * @author Prasad Thiragabathina
 *
 *This Model Describes About Survey user tab details
 */
public class SurveyUserTabAssign
{
	private Long surveyUserTabAssignId;
	private SurveyUser surveyUser;
	private String tabNo;
	private String activeStatus;
	private Date date;
	private String remarks;
	private Date insertedTime;
	private Date updatedTime;
	public Long getSurveyUserTabAssignId() {
		return surveyUserTabAssignId;
	}
	public void setSurveyUserTabAssignId(Long surveyUserTabAssignId) {
		this.surveyUserTabAssignId = surveyUserTabAssignId;
	}
	public SurveyUser getSurveyUser() {
		return surveyUser;
	}
	public void setSurveyUser(SurveyUser surveyUser) {
		this.surveyUser = surveyUser;
	}
	public String getTabNo() {
		return tabNo;
	}
	public void setTabNo(String tabNo) {
		this.tabNo = tabNo;
	}
	public String getActiveStatus() {
		return activeStatus;
	}
	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
