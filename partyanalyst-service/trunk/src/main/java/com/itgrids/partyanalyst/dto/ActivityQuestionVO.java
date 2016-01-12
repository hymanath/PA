package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class ActivityQuestionVO implements Serializable{
	private Long		questionId;
	private String		question;
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
	
}
