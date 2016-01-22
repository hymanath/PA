package com.itgrids.partyanalyst.model;

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
@Table(name = "ivr_survey_round_question")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class IvrSurveyRoundQuestion {
	private Long ivrSurveyRoundQuestionId;
	private Long communicationMediaRoundId;
	private Long activityQuestionId;
	private Long activityOptionTypeId;
	private String isDeleted;
	private Long insertedBy;
	private Long updatedBy;
	private Date insertedTime;
	private Date updatedTime;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ivr_survey_round_question_id", unique=true, nullable=false)
	public Long getIvrSurveyRoundQuestionId() {
		return ivrSurveyRoundQuestionId;
	}
	public void setIvrSurveyRoundQuestionId(Long ivrSurveyRoundQuestionId) {
		this.ivrSurveyRoundQuestionId = ivrSurveyRoundQuestionId;
	}
	@Column(name="communication_media_round_id")
	public Long getCommunicationMediaRoundId() {
		return communicationMediaRoundId;
	}
	public void setCommunicationMediaRoundId(Long communicationMediaRoundId) {
		this.communicationMediaRoundId = communicationMediaRoundId;
	}
	@Column(name="activity_question_id")
	public Long getActivityQuestionId() {
		return activityQuestionId;
	}
	public void setActivityQuestionId(Long activityQuestionId) {
		this.activityQuestionId = activityQuestionId;
	}
	@Column(name="activity_option_type_id")
	public Long getActivityOptionTypeId() {
		return activityOptionTypeId;
	}
	public void setActivityOptionTypeId(Long activityOptionTypeId) {
		this.activityOptionTypeId = activityOptionTypeId;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Column(name="inserted_by")
	public Long getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
	}
	@Column(name="updated_by")
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	@Column(name="updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	
	

}
