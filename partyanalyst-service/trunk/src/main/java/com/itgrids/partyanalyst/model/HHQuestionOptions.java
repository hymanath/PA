package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;

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
@Table(name="hh_question_options")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class HHQuestionOptions extends BaseModel implements Serializable {
	  
	private Long questionOptionsId;
	//private Long surveyQuestionId;
	private HHSurveyQuestion hhSurveyQuestion;
	//private Long optionsId;
	private HHOptions hhOptions;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "question_options_id", unique = true, nullable = false)
	public Long getQuestionOptionsId() {
		return questionOptionsId;
	}
	public void setQuestionOptionsId(Long questionOptionsId) {
		this.questionOptionsId = questionOptionsId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="survey_question_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public HHSurveyQuestion getHhSurveyQuestion() {
		return hhSurveyQuestion;
	}
	public void setHhSurveyQuestion(HHSurveyQuestion hhSurveyQuestion) {
		this.hhSurveyQuestion = hhSurveyQuestion;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="options_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public HHOptions getHhOptions() {
		return hhOptions;
	}
	public void setHhOptions(HHOptions hhOptions) {
		this.hhOptions = hhOptions;
	}
		
	
}
