package com.itgrids.partyanalyst.model;

/**
 * 
 * @author Prasad Thiragabathina
 * 
 * This Model Describes About Surveyor Type (Data Collector, Verifier , etc....)
 *
 */
public class SurveySurveyorType 
{

	private Long surveySurveyorTypeId;
	private String description;
	public Long getSurveySurveyorTypeId() {
		return surveySurveyorTypeId;
	}
	public void setSurveySurveyorTypeId(Long surveySurveyorTypeId) {
		this.surveySurveyorTypeId = surveySurveyorTypeId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
