package com.itgrids.partyanalyst.model;

import java.util.Date;


/**
 * 
 * @author Prasad Thiragabathina
 * 
 * This Model Describes About Survey User Comes Under Which Categoery(Data Collector,Data Verifier etc.....)
 *
 */
public class SurveyUserType
{

	private Long surveyUsertypeId;
	private String description;
	private Date insertedTime;
	private Date updatedTime;
	public Long getSurveyUsertypeId() {
		return surveyUsertypeId;
	}
	public void setSurveyUsertypeId(Long surveyUsertypeId) {
		this.surveyUsertypeId = surveyUsertypeId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
