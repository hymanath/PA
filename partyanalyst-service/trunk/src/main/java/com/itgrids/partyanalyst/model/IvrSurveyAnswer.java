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
@Table(name = "ivr_survey_answer")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class IvrSurveyAnswer extends BaseModel implements Serializable {
	
	
	private Long ivrSurveyAnswerId;
	private Long ivrSurveyId;
	private Long ivrSurveyRoundId;
	private Long ivrSurveyQuestionId;
	private Long ivrOptionId;
	private Long ivrRespondentId;
	private String isValid;
	
	
	
	private IvrOption ivrOption;
	private IvrRespondent ivrRespondent;
	private IvrSurvey ivrSurvey;
	private IvrSurveyQuestion ivrSurveyQuestion;
	private IvrSurveyRound ivrSurveyRound;
	
	@Column(name="ivr_survey_answer_id")
	public Long getIvrSurveyAnswerId() {
		return ivrSurveyAnswerId;
	}
	public void setIvrSurveyAnswerId(Long ivrSurveyAnswerId) {
		this.ivrSurveyAnswerId = ivrSurveyAnswerId;
	}
	@Column(name="ivr_survey_id")
	public Long getIvrSurveyId() {
		return ivrSurveyId;
	}
	public void setIvrSurveyId(Long ivrSurveyId) {
		this.ivrSurveyId = ivrSurveyId;
	}
	@Column(name="ivr_survey_round_id")
	public Long getIvrSurveyRoundId() {
		return ivrSurveyRoundId;
	}
	public void setIvrSurveyRoundId(Long ivrSurveyRoundId) {
		this.ivrSurveyRoundId = ivrSurveyRoundId;
	}
	@Column(name="ivr_survey_question_id")
	public Long getIvrSurveyQuestionId() {
		return ivrSurveyQuestionId;
	}
	public void setIvrSurveyQuestionId(Long ivrSurveyQuestionId) {
		this.ivrSurveyQuestionId = ivrSurveyQuestionId;
	}
	@Column(name="ivr_option_id")
	public Long getIvrOptionId() {
		return ivrOptionId;
	}
	public void setIvrOptionId(Long ivrOptionId) {
		this.ivrOptionId = ivrOptionId;
	}
	@Column(name="ivr_respondent_id")
	public Long getIvrRespondentId() {
		return ivrRespondentId;
	}
	public void setIvrRespondentId(Long ivrRespondentId) {
		this.ivrRespondentId = ivrRespondentId;
	}
	@Column(name="is_valid")
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="ivr_option_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public IvrOption getIvrOption() {
		return ivrOption;
	}
	public void setIvrOption(IvrOption ivrOption) {
		this.ivrOption = ivrOption;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="ivr_respondent_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public IvrRespondent getIvrRespondent() {
		return ivrRespondent;
	}
	public void setIvrRespondent(IvrRespondent ivrRespondent) {
		this.ivrRespondent = ivrRespondent;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="ivr_survey_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public IvrSurvey getIvrSurvey() {
		return ivrSurvey;
	}
	public void setIvrSurvey(IvrSurvey ivrSurvey) {
		this.ivrSurvey = ivrSurvey;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="ivr_survey_question_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public IvrSurveyQuestion getIvrSurveyQuestion() {
		return ivrSurveyQuestion;
	}
	public void setIvrSurveyQuestion(IvrSurveyQuestion ivrSurveyQuestion) {
		this.ivrSurveyQuestion = ivrSurveyQuestion;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="ivr_survey_round_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public IvrSurveyRound getIvrSurveyRound() {
		return ivrSurveyRound;
	}
	public void setIvrSurveyRound(IvrSurveyRound ivrSurveyRound) {
		this.ivrSurveyRound = ivrSurveyRound;
	}
	
}
