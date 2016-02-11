package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "ivr_survey_question_option")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class IvrSurveyQuestionOption extends BaseModel implements Serializable{
	
	private Long ivrSurveyQuestionOptionId;
	private Long ivrSurveyQuestionId;
	private Long ivrOptionId;
	private Long ivrSurveyRoundId;
	private String isDeleted;
	private String key;
	private String orderNo;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ivr_survey_question_option_id", unique = true, nullable = false)
	public Long getIvrSurveyQuestionOptionId() {
		return ivrSurveyQuestionOptionId;
	}
	public void setIvrSurveyQuestionOptionId(Long ivrSurveyQuestionOptionId) {
		this.ivrSurveyQuestionOptionId = ivrSurveyQuestionOptionId;
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
	@Column(name="ivr_survey_round_id")
	public Long getIvrSurveyRoundId() {
		return ivrSurveyRoundId;
	}
	public void setIvrSurveyRoundId(Long ivrSurveyRoundId) {
		this.ivrSurveyRoundId = ivrSurveyRoundId;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Column(name="key")
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	@Column(name="order_no")
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}	
}

