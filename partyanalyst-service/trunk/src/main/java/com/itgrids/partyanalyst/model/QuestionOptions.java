package com.itgrids.partyanalyst.model;

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
@Table(name = "question_options")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class QuestionOptions extends BaseModel implements java.io.Serializable{

	private long questionOptionsId;
	private SurveyQuestion surveyQuestion;
	private Option options;
	private UpdationDetails updationDetails;
	
	public QuestionOptions() {
	}
	
	public QuestionOptions(long questionOptionsId,SurveyQuestion surveyQuestion,Option options,UpdationDetails updationDetails) {
		this.questionOptionsId = questionOptionsId;
		this.surveyQuestion = surveyQuestion;
		this.options = options;
		this.updationDetails = updationDetails;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "question_options_id", unique = true, nullable = false)
	public long getQuestionOptionsId() {
		return questionOptionsId;
	}

	public void setQuestionOptionsId(long questionOptionsId) {
		this.questionOptionsId = questionOptionsId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="survey_question_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public SurveyQuestion getSurveyQuestion() {
		return surveyQuestion;
	}

	public void setSurveyQuestion(SurveyQuestion surveyQuestion) {
		this.surveyQuestion = surveyQuestion;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="options_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Option getOptions() {
		return options;
	}

	public void setOptions(Option options) {
		this.options = options;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="updation_details_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UpdationDetails getUpdationDetails() {
		return updationDetails;
	}

	public void setUpdationDetails(UpdationDetails updationDetails) {
		this.updationDetails = updationDetails;
	}
	
	
}
