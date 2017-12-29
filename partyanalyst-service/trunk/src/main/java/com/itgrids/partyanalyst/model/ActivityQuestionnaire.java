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
@Table(name="activity_questionnaire")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ActivityQuestionnaire extends BaseModel implements Serializable{
	private Long activityQuestionnaireId;
	private Long activityScopeId;
	private Long activityQuestionId;
	private Long activityOptionTypeId;
	private Long parentActivityQuestionnaireId;
	private Long parentActivityOptionId;
	private Long orderNo;
	private String isDeleted;
	private Long insertedBy;
	private Long updatedBy;
	private Date insertedTime;
	private Date updatedTime;
	private String hasRemark;
	private String isMandatory;
	
	private ActivityScope activityScope;
	private ActivityQuestion activityQuestion;
	private ActivityOptionType activityOptionType;
	private ActivityOption activityOption;
	
	private Long				respondentTypeId;
	private RespondentType		respondentType;
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "activity_questionnaire_id", unique = true, nullable = false)
	public Long getActivityQuestionnaireId() {
		return activityQuestionnaireId;
	}
	public void setActivityQuestionnaireId(Long activityQuestionnaireId) {
		this.activityQuestionnaireId = activityQuestionnaireId;
	}
	
	@Column(name = "activity_scope_id")
	public Long getActivityScopeId() {
		return activityScopeId;
	}
	public void setActivityScopeId(Long activityScopeId) {
		this.activityScopeId = activityScopeId;
	}
	
	@Column(name = "activity_question_id")
	public Long getActivityQuestionId() {
		return activityQuestionId;
	}
	public void setActivityQuestionId(Long activityQuestionId) {
		this.activityQuestionId = activityQuestionId;
	}
	
	@Column(name = "activity_option_type_id")
	public Long getActivityOptionTypeId() {
		return activityOptionTypeId;
	}
	
	@Column(name = "has_remark")
	public String getHasRemark() {
		return hasRemark;
	}
	public void setHasRemark(String hasRemark) {
		this.hasRemark = hasRemark;
	}
	public void setActivityOptionTypeId(Long activityOptionTypeId) {
		this.activityOptionTypeId = activityOptionTypeId;
	}
	
	@Column(name = "parent_activity_questionnaire_id")
	public Long getParentActivityQuestionnaireId() {
		return parentActivityQuestionnaireId;
	}
	public void setParentActivityQuestionnaireId(Long parentActivityQuestionnaireId) {
		this.parentActivityQuestionnaireId = parentActivityQuestionnaireId;
	}
	
	@Column(name = "order_no")
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Column(name ="parent_activity_option_id")
	public Long getParentActivityOptionId() {
		return parentActivityOptionId;
	}
	public void setParentActivityOptionId(Long parentActivityOptionId) {
		this.parentActivityOptionId = parentActivityOptionId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="parent_activity_option_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ActivityOption getActivityOption() {
		return activityOption;
	}
	public void setActivityOption(ActivityOption activityOption) {
		this.activityOption = activityOption;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="activity_scope_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ActivityScope getActivityScope() {
		return activityScope;
	}
	public void setActivityScope(ActivityScope activityScope) {
		this.activityScope = activityScope;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="activity_question_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ActivityQuestion getActivityQuestion() {
		return activityQuestion;
	}
	public void setActivityQuestion(ActivityQuestion activityQuestion) {
		this.activityQuestion = activityQuestion;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="activity_option_type_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ActivityOptionType getActivityOptionType() {
		return activityOptionType;
	}
	public void setActivityOptionType(ActivityOptionType activityOptionType) {
		this.activityOptionType = activityOptionType;
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
	
	@Column(name = "respondent_type_id")
	public Long getRespondentTypeId() {
		return respondentTypeId;
	}
	public void setRespondentTypeId(Long respondentTypeId) {
		this.respondentTypeId = respondentTypeId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="respondent_type_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public RespondentType getRespondentType() {
		return respondentType;
	}
	public void setRespondentType(RespondentType respondentType) {
		this.respondentType = respondentType;
	}
	@Column(name = "is_mandatory")
	public String getIsMandatory() {
		return isMandatory;
	}
	public void setIsMandatory(String isMandatory) {
		this.isMandatory = isMandatory;
	}
	
	
}
