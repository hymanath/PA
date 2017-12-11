package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class TrainingCampSurveyVO implements java.io.Serializable{

	private Long userId;
	private Long id;
	private String name;
	private String startDateStr;
	private String endDateStr;
	
	private Long poor;
	private Long average;
	private Long good;
	private Long veryGood;
	private Long excellent;
	
	private List<Long> surveyIdsList = new ArrayList<Long>(0);
	List<TrainingCampSurveyVO> programsList = new ArrayList<TrainingCampSurveyVO>();
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStartDateStr() {
		return startDateStr;
	}
	public void setStartDateStr(String startDateStr) {
		this.startDateStr = startDateStr;
	}
	public String getEndDateStr() {
		return endDateStr;
	}
	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}
	public List<Long> getSurveyIdsList() {
		return surveyIdsList;
	}
	public void setSurveyIdsList(List<Long> surveyIdsList) {
		this.surveyIdsList = surveyIdsList;
	}
	public List<TrainingCampSurveyVO> getProgramsList() {
		return programsList;
	}
	public void setProgramsList(List<TrainingCampSurveyVO> programsList) {
		this.programsList = programsList;
	}
	public Long getPoor() {
		return poor;
	}
	public void setPoor(Long poor) {
		this.poor = poor;
	}
	public Long getAverage() {
		return average;
	}
	public void setAverage(Long average) {
		this.average = average;
	}
	public Long getGood() {
		return good;
	}
	public void setGood(Long good) {
		this.good = good;
	}
	public Long getVeryGood() {
		return veryGood;
	}
	public void setVeryGood(Long veryGood) {
		this.veryGood = veryGood;
	}
	public Long getExcellent() {
		return excellent;
	}
	public void setExcellent(Long excellent) {
		this.excellent = excellent;
	}
	
}
