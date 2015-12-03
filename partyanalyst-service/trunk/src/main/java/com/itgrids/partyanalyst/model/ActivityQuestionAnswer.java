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
@Table(name="activity_question_answer")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ActivityQuestionAnswer extends BaseModel implements Serializable{
	
	private Long activityQuestionAnswerId;
	private Long activityQuestionnaireId;
	private Long activityLocationInfoId;
	private Long activityOptionId;
	private String optionTxt;
	private String isDeleted;
	
	private ActivityQuestionnaire activityQuestionnaire;
	private ActivityLocationInfo activityLocationInfo;
	private ActivityOption activityOption;
	
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
}
