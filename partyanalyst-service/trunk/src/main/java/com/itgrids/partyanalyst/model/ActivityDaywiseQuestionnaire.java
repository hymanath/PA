package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;

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
@Table(name="activity_daywise_questionnaire")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ActivityDaywiseQuestionnaire extends BaseModel implements Serializable {
	
	private Long activityDaywiseQuestionnaireId;
	private Date activityDate;
	private Long day;
	private String isDeleted ;
	private Long activityQuestionnaireId;
	private ActivityQuestionnaire activityQuestionnaire;


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "activity_daywise_questionnaire_id", unique = true, nullable = false)
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
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Column(name = "activity_questionnaire_id")
	public Long getActivityQuestionnaireId() {
		return activityQuestionnaireId;
	}
	public void setActivityQuestionnaireId(Long activityQuestionnaireId) {
		this.activityQuestionnaireId = activityQuestionnaireId;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "activity_questionnaire_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ActivityQuestionnaire getActivityQuestionnaire() {
		return activityQuestionnaire;
	}
	public void setActivityQuestionnaire(ActivityQuestionnaire activityQuestionnaire) {
		this.activityQuestionnaire = activityQuestionnaire;
	}
	

}
