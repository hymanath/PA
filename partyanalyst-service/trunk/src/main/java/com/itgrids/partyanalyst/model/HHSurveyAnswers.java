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
@Table(name="hh_survey_answers")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class HHSurveyAnswers extends BaseModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long hhSurveyAnswerId;
	private HouseHolds houseHold;
	private HHSurveyQuestion hhSurveyQuestion;
	private HHOptions hhOptions;
	private Long houseHoldId;
	private Long hhSurveyQuestionId;
	private Long hhOptionsId;
	private String remarks;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "hh_survey_answer_id", unique = true, nullable = false)
	public Long getHhSurveyAnswerId() {
		return hhSurveyAnswerId;
	}
	public void setHhSurveyAnswerId(Long hhSurveyAnswerId) {
		this.hhSurveyAnswerId = hhSurveyAnswerId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="house_hold_id",insertable = false ,updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public HouseHolds getHouseHold() {
		return houseHold;
	}
	public void setHouseHold(HouseHolds houseHold) {
		this.houseHold = houseHold;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="hh_survey_question_id",insertable = false ,updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public HHSurveyQuestion getHhSurveyQuestion() {
		return hhSurveyQuestion;
	}
	
	
	public void setHhSurveyQuestion(HHSurveyQuestion hhSurveyQuestion) {
		this.hhSurveyQuestion = hhSurveyQuestion;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="hh_options_id",insertable = false ,updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public HHOptions getHhOptions() {
		return hhOptions;
	}
	public void setHhOptions(HHOptions hhOptions) {
		this.hhOptions = hhOptions;
	}
	
	@Column(name = "house_hold_id")
	public Long getHouseHoldId() {
		return houseHoldId;
	}
	public void setHouseHoldId(Long houseHoldId) {
		this.houseHoldId = houseHoldId;
	}
	
	@Column(name = "hh_survey_question_id")
	public Long getHhSurveyQuestionId() {
		return hhSurveyQuestionId;
	}
	public void setHhSurveyQuestionId(Long hhSurveyQuestionId) {
		this.hhSurveyQuestionId = hhSurveyQuestionId;
	}
	
	@Column(name = "remarks", length=2000)
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	@Column(name = "hh_options_id")
	public Long getHhOptionsId() {
		return hhOptionsId;
	}
	public void setHhOptionsId(Long hhOptionsId) {
		this.hhOptionsId = hhOptionsId;
	}
	
	
	
	
	
}
