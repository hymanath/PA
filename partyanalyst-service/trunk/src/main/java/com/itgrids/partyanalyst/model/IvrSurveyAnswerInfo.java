package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
@Entity
@Table(name = "ivr_survey_answer_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class IvrSurveyAnswerInfo extends BaseModel implements Serializable {

	private Long ivrSurveyAnswerInfoId;
	private Long ivrResponseTypeId;
	private Long ivrSurveyId;
	private Long ivrSurveyRoundId;
	private String mobileNo;
	private String keyPressed;
	private String callDuration;
	private Date callTime;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ivr_survey_answer_info_id", unique = true, nullable = false)
	public Long getIvrSurveyAnswerInfoId() {
		return ivrSurveyAnswerInfoId;
	}
	public void setIvrSurveyAnswerInfoId(Long ivrSurveyAnswerInfoId) {
		this.ivrSurveyAnswerInfoId = ivrSurveyAnswerInfoId;
	}
	
	@Column(name = "ivr_response_type_id")
	public Long getIvrResponseTypeId() {
		return ivrResponseTypeId;
	}
	public void setIvrResponseTypeId(Long ivrResponseTypeId) {
		this.ivrResponseTypeId = ivrResponseTypeId;
	}
	
	@Column(name = "ivr_survey_id")
	public Long getIvrSurveyId() {
		return ivrSurveyId;
	}
	public void setIvrSurveyId(Long ivrSurveyId) {
		this.ivrSurveyId = ivrSurveyId;
	}
	
	@Column(name = "ivr_survey_round_id")
	public Long getIvrSurveyRoundId() {
		return ivrSurveyRoundId;
	}
	public void setIvrSurveyRoundId(Long ivrSurveyRoundId) {
		this.ivrSurveyRoundId = ivrSurveyRoundId;
	}
	
	@Column(name = "mobile_no")
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	@Column(name = "key_pressed")
	public String getKeyPressed() {
		return keyPressed;
	}
	public void setKeyPressed(String keyPressed) {
		this.keyPressed = keyPressed;
	}
	
	@Column(name = "call_duration")
	public String getCallDuration() {
		return callDuration;
	}
	public void setCallDuration(String callDuration) {
		this.callDuration = callDuration;
	}
	
	@Column(name = "call_time")
	public Date getCallTime() {
		return callTime;
	}
	public void setCallTime(Date callTime) {
		this.callTime = callTime;
	}
	
}
