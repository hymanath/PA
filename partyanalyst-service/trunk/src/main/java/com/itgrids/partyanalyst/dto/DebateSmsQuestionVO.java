package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class DebateSmsQuestionVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6162660528806418300L;
	private Long questionId;
	private String question;
	
	private Long option1Id;
	private Double perc1;
	private String option1;
	
	private Long option2Id;
	private Double perc2;
	private String option2;
	public Long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public Long getOption1Id() {
		return option1Id;
	}
	public void setOption1Id(Long option1Id) {
		this.option1Id = option1Id;
	}
	public Double getPerc1() {
		return perc1;
	}
	public void setPerc1(Double perc1) {
		this.perc1 = perc1;
	}
	public String getOption1() {
		return option1;
	}
	public void setOption1(String option1) {
		this.option1 = option1;
	}
	public Long getOption2Id() {
		return option2Id;
	}
	public void setOption2Id(Long option2Id) {
		this.option2Id = option2Id;
	}
	public Double getPerc2() {
		return perc2;
	}
	public void setPerc2(Double perc2) {
		this.perc2 = perc2;
	}
	public String getOption2() {
		return option2;
	}
	public void setOption2(String option2) {
		this.option2 = option2;
	}
	
	
}
