package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class SurveyAnalysisVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3704529337578877546L;

	private Long   id;
	private String name;
	private Long   count = 0l;
	private String question;
	private Long   questionId;
	private Double percentage;
	private List<SurveyAnalysisVO> subList;
	
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
	
	public Long getCount() {
		return count;
	}
	
	public void setCount(Long count) {
		this.count = count;
	}
	
	public String getQuestion() {
		return question;
	}
	
	public void setQuestion(String question) {
		this.question = question;
	}
	
	public Long getQuestionId() {
		return questionId;
	}
	
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public List<SurveyAnalysisVO> getSubList() {
		return subList;
	}

	public void setSubList(List<SurveyAnalysisVO> subList) {
		this.subList = subList;
	}

	public Double getPercentage() {
		return percentage;
	}

	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}
	
	
}
