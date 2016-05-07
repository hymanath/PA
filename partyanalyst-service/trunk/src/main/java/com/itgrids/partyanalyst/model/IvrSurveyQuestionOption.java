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
	private Long insertedBy;
	private Long updatedBy;
	private Date insertedTime;
	private Date updatedTime;
	private IvrOption ivrOption;
	private IvrSurveyQuestion ivrSurveyQuestion;
	private User insertedUser;
	private User updatedUser;

	
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
	@Column(name="ivr_option_id")
	public Long getIvrOptionId() {
		return ivrOptionId;
	}
	public void setIvrOptionId(Long ivrOptionId) {
		this.ivrOptionId = ivrOptionId;
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
	@Column(name = "inserted_by")
	public Long getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
	}
	@Column(name = "updated_by")
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
	@Column(name = "inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	@Column(name = "updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="inserted_by", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getInsertedUser() {
		return insertedUser;
	}
	public void setInsertedUser(User insertedUser) {
		this.insertedUser = insertedUser;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="updated_by", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUpdatedUser() {
		return updatedUser;
	}
	public void setUpdatedUser(User updatedUser) {
		this.updatedUser = updatedUser;
	}	
}

