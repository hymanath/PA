package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class SurveyStatusVO {
	
	private Long id;
	private String name;
	private Long totalBooths = 0l;
	private Long surveyprocessTotal = 0l;
	private Long surveyprocessCompleted = 0l;
	private Long redoBoothsTotal = 0l;
	private Long redoBoothsCompleted =0l;
	private Long surveyCompletedBooths =0l;
	private List<SurveyStatusVO> subList = new ArrayList<SurveyStatusVO>();
	
	public List<SurveyStatusVO> getSubList() {
		return subList;
	}
	public void setSubList(List<SurveyStatusVO> subList) {
		this.subList = subList;
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
	public Long getTotalBooths() {
		return totalBooths;
	}
	public void setTotalBooths(Long totalBooths) {
		this.totalBooths = totalBooths;
	}
	public Long getSurveyprocessTotal() {
		return surveyprocessTotal;
	}
	public void setSurveyprocessTotal(Long surveyprocessTotal) {
		this.surveyprocessTotal = surveyprocessTotal;
	}
	public Long getSurveyprocessCompleted() {
		return surveyprocessCompleted;
	}
	public void setSurveyprocessCompleted(Long surveyprocessCompleted) {
		this.surveyprocessCompleted = surveyprocessCompleted;
	}
	
	
	public Long getSurveyCompletedBooths() {
		return surveyCompletedBooths;
	}
	public void setSurveyCompletedBooths(Long surveyCompletedBooths) {
		this.surveyCompletedBooths = surveyCompletedBooths;
	}
	public Long getRedoBoothsTotal() {
		return redoBoothsTotal;
	}
	public void setRedoBoothsTotal(Long redoBoothsTotal) {
		this.redoBoothsTotal = redoBoothsTotal;
	}
	public Long getRedoBoothsCompleted() {
		return redoBoothsCompleted;
	}
	public void setRedoBoothsCompleted(Long redoBoothsCompleted) {
		this.redoBoothsCompleted = redoBoothsCompleted;
	}

	
	

}
