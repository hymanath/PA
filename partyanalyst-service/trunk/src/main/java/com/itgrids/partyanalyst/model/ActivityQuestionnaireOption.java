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
@Table(name="activity_questionnaire_option")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ActivityQuestionnaireOption extends BaseModel implements Serializable{
	
	private Long activityQuestionnaireOptionId;
	private Long activityQuestionnaireId;
	private Long activityOptionId;
	
	private Long orderNo;
	private String isDeleted;
	private Long insertedBy;
	private Long updatedBy;
	private Date insertedTime;
	private Date updatedTime;

	private ActivityQuestionnaire activityQuestionnaire;
	private ActivityOption activityOption;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "activity_questionnaire_option_id", unique = true, nullable = false)
	public Long getActivityQuestionnaireOptionId() {
		return activityQuestionnaireOptionId;
	}
	public void setActivityQuestionnaireOptionId(Long activityQuestionnaireOptionId) {
		this.activityQuestionnaireOptionId = activityQuestionnaireOptionId;
	}
	
	@Column(name = "activity_questionnaire_id")
	public Long getActivityQuestionnaireId() {
		return activityQuestionnaireId;
	}
	public void setActivityQuestionnaireId(Long activityQuestionnaireId) {
		this.activityQuestionnaireId = activityQuestionnaireId;
	}
	
	@Column(name = "activity_option_id")
	public Long getActivityOptionId() {
		return activityOptionId;
	}
	public void setActivityOptionId(Long activityOptionId) {
		this.activityOptionId = activityOptionId;
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
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="activity_questionnaire_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ActivityQuestionnaire getActivityQuestionnaire() {
		return activityQuestionnaire;
	}
	public void setActivityQuestionnaire(ActivityQuestionnaire activityQuestionnaire) {
		this.activityQuestionnaire = activityQuestionnaire;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="activity_option_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ActivityOption getActivityOption() {
		return activityOption;
	}
	public void setActivityOption(ActivityOption activityOption) {
		this.activityOption = activityOption;
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
}
