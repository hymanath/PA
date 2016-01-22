package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "ivr_survey_round_question_option")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class IvrSurveyRoundQuestionOption {

	private Long ivrSurveyRoundQuestionOptionId;
	private Long ivrSurveyRoundQuestionId;
	private Long activityOptionId;
	private String isDeleted;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ivr_survey_round_question_option_id", unique=true, nullable=false)
	public Long getIvrSurveyRoundQuestionOptionId() {
		return ivrSurveyRoundQuestionOptionId;
	}
	public void setIvrSurveyRoundQuestionOptionId(
			Long ivrSurveyRoundQuestionOptionId) {
		this.ivrSurveyRoundQuestionOptionId = ivrSurveyRoundQuestionOptionId;
	}
	@Column(name="ivr_survey_round_question_id")
	public Long getIvrSurveyRoundQuestionId() {
		return ivrSurveyRoundQuestionId;
	}
	public void setIvrSurveyRoundQuestionId(Long ivrSurveyRoundQuestionId) {
		this.ivrSurveyRoundQuestionId = ivrSurveyRoundQuestionId;
	}
	@Column(name="activity_option_id")
	public Long getActivityOptionId() {
		return activityOptionId;
	}
	public void setActivityOptionId(Long activityOptionId) {
		this.activityOptionId = activityOptionId;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
	
	
}
