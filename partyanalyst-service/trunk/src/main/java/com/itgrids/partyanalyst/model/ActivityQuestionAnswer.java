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
@Table(name="activity_question_answer")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ActivityQuestionAnswer extends BaseModel implements Serializable{
	
	private Long activityQuestionAnswerId;
	private Long activityQuestionnaireId;
	private Long activityLocationInfoId;
	private Long activityOptionId;
	private String optionTxt;
	private String isDeleted;
	private Long count;
	private Long insertedBy;
	private Long updatedBy;
	private Date insertedTime;
	private Date updatedTime;
	private Long tabDetailsId;
	
	private ActivityQuestionnaire activityQuestionnaire;
	private ActivityLocationInfo activityLocationInfo;
	private ActivityOption activityOption;
	private TabDetails tabDetails;
	private ActivityDaywiseQuestionnaire activityDaywiseQuestionnaire;
	private Long activityDaywiseQuestionnaireId;
	private Date activityDate ;
	private Long day;
	private String sourceType;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "activity_question_answer_id", unique = true, nullable = false)
	public Long getActivityQuestionAnswerId() {
		return activityQuestionAnswerId;
	}
	public void setActivityQuestionAnswerId(Long activityQuestionAnswerId) {
		this.activityQuestionAnswerId = activityQuestionAnswerId;
	}
	
	@Column(name = "activity_questionnaire_id")
	public Long getActivityQuestionnaireId() {
		return activityQuestionnaireId;
	}
	public void setActivityQuestionnaireId(Long activityQuestionnaireId) {
		this.activityQuestionnaireId = activityQuestionnaireId;
	}
	
	@Column(name = "activity_location_info_id")
	public Long getActivityLocationInfoId() {
		return activityLocationInfoId;
	}
	public void setActivityLocationInfoId(Long activityLocationInfoId) {
		this.activityLocationInfoId = activityLocationInfoId;
	}
	
	@Column(name = "activity_option_id")
	public Long getActivityOptionId() {
		return activityOptionId;
	}
	public void setActivityOptionId(Long activityOptionId) {
		this.activityOptionId = activityOptionId;
	}
	
	@Column(name = "option_txt")
	public String getOptionTxt() {
		return optionTxt;
	}
	public void setOptionTxt(String optionTxt) {
		this.optionTxt = optionTxt;
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
	@JoinColumn(name="activity_location_info_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ActivityLocationInfo getActivityLocationInfo() {
		return activityLocationInfo;
	}
	public void setActivityLocationInfo(ActivityLocationInfo activityLocationInfo) {
		this.activityLocationInfo = activityLocationInfo;
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
	
	@Column(name = "count")
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
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
	
	@Column(name = "tab_details_id")
	public Long getTabDetailsId() {
		return tabDetailsId;
	}
	public void setTabDetailsId(Long tabDetailsId) {
		this.tabDetailsId = tabDetailsId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="tab_details_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TabDetails getTabDetails() {
		return tabDetails;
	}
	public void setTabDetails(TabDetails tabDetails) {
		this.tabDetails = tabDetails;
	}
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "activity_daywise_questionnaire_id",insertable=false,updatable=false)
	public ActivityDaywiseQuestionnaire getActivityDaywiseQuestionnaire() {
		return activityDaywiseQuestionnaire;
	}
	public void setActivityDaywiseQuestionnaire(
			ActivityDaywiseQuestionnaire activityDaywiseQuestionnaire) {
		this.activityDaywiseQuestionnaire = activityDaywiseQuestionnaire;
	}
	@Column(name = "activity_daywise_questionnaire_id")
	public Long getActivityDaywiseQuestionnaireId() {
		return activityDaywiseQuestionnaireId;
	}
	public void setActivityDaywiseQuestionnaireId(
			Long activityDaywiseQuestionnaireId) {
		this.activityDaywiseQuestionnaireId = activityDaywiseQuestionnaireId;
	}
	@Column(name = "activity_date")
	public Date getActivityDate() {
		return activityDate;
	}
	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}
	@Column(name = "day")
	public Long getDay() {
		return day;
	}
	public void setDay(Long day) {
		this.day = day;
	}
	@Column(name = "source_type")
	public String getSourceType() {
		return sourceType;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	
	
	
}
