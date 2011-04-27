package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "homepage_question_answers")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class HomePageQuestionAnswers implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long homepageQuestionAnswerId; 
	private HomePageQuestion homePageQuestion;
	private String answerKey;
	private String answerValue;
	
	/** default constructor */
	public HomePageQuestionAnswers(){
		
	}
	
	/**  full constructor */
	
	public HomePageQuestionAnswers(HomePageQuestion homePageQuestion, String answerKey,
			String answerValue) {
		this.homePageQuestion = homePageQuestion;
		this.answerKey = answerKey;
		this.answerValue = answerValue;
	}


	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column (name = "homepage_question_answer_id")
	public Long getHomepageQuestionAnswerId() {
		return homepageQuestionAnswerId;
	}
	
	public void setHomepageQuestionAnswerId(Long homepageQuestionAnswerId) {
		this.homepageQuestionAnswerId = homepageQuestionAnswerId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
	@JoinColumn(name = "homepage_question_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public HomePageQuestion getHomePageQuestion() {
		return homePageQuestion;
	}
	
	public void setHomePageQuestion(HomePageQuestion homePageQuestion) {
		this.homePageQuestion = homePageQuestion;
	}
	
	@Column (name = "answer_key", length = 50)
	public String getAnswerKey() {
		return answerKey;
	}
	
	public void setAnswerKey(String answerKey) {
		this.answerKey = answerKey;
	}
	
	@Column (name = "answer_value", length = 200)
	public String getAnswerValue() {
		return answerValue;
	}
	
	public void setAnswerValue(String answerValue) {
		this.answerValue = answerValue;
	}
		
}
