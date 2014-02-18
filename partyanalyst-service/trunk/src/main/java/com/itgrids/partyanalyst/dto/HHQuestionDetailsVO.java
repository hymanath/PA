package com.itgrids.partyanalyst.dto;

public class HHQuestionDetailsVO {
	
	Long surveyTypeId ;
	Long surveySubTypeId ;
	
	String question ;
	String options ;
	Long optnTypeId ;
	boolean isRemarks;
	
	
	public boolean isRemarks() {
		return isRemarks;
	}
	public void setRemarks(boolean isRemarks) {
		this.isRemarks = isRemarks;
	}
	public Long getSurveyTypeId() {
		return surveyTypeId;
	}
	public void setSurveyTypeId(Long surveyTypeId) {
		this.surveyTypeId = surveyTypeId;
	}
	public Long getSurveySubTypeId() {
		return surveySubTypeId;
	}
	public void setSurveySubTypeId(Long surveySubTypeId) {
		this.surveySubTypeId = surveySubTypeId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getOptions() {
		return options;
	}
	public void setOptions(String options) {
		this.options = options;
	}
	public Long getOptnTypeId() {
		return optnTypeId;
	}
	public void setOptnTypeId(Long optnTypeId) {
		this.optnTypeId = optnTypeId;
	}

}
