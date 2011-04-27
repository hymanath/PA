package com.itgrids.partyanalyst.dto;

import java.util.Date;
import java.util.List;

import com.itgrids.partyanalyst.model.Registration;

public class HomePageQuestionsVO {
	
	 private Long homePageQuestionId;	
	 private Registration registration;
	 private Date questionStartDate;
	 private Date questionEndDate;
	 private String question;
	 private String isDelete;
	 private List<QuestionsOptionsVO> answerOptionsVOs;
	public Long getHomePageQuestionId() {
		return homePageQuestionId;
	}
	public void setHomePageQuestionId(Long homePageQuestionId) {
		this.homePageQuestionId = homePageQuestionId;
	}
	public Registration getRegistration() {
		return registration;
	}
	public void setRegistration(Registration registration) {
		this.registration = registration;
	}
	public Date getQuestionStartDate() {
		return questionStartDate;
	}
	public void setQuestionStartDate(Date questionStartDate) {
		this.questionStartDate = questionStartDate;
	}
	public Date getQuestionEndDate() {
		return questionEndDate;
	}
	public void setQuestionEndDate(Date questionEndDate) {
		this.questionEndDate = questionEndDate;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	public void setAnswerOptionsVOs(List<QuestionsOptionsVO> answerOptionsVOs) {
		this.answerOptionsVOs = answerOptionsVOs;
	}
	public List<QuestionsOptionsVO> getAnswerOptionsVOs() {
		return answerOptionsVOs;
	}	

}
