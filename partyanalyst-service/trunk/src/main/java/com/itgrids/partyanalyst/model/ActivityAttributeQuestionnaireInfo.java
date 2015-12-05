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
@Table(name = "activity_attribute_questionnaire_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ActivityAttributeQuestionnaireInfo extends BaseModel implements Serializable{
	
	private Long activityAttributeQuestionnaireInfoId;
	private Long activityAttributeId;
	private Long activityQuestionnaireId;
	private ActivityAttribute activityAttribute;
	
	private ActivityQuestionnaire activityQuestionnaire;

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "activity_attribute_questionnaire_info_id", unique = true, nullable = false)
	public Long getActivityAttributeQuestionnaireInfoId() {
		return activityAttributeQuestionnaireInfoId;
	}

	public void setActivityAttributeQuestionnaireInfoId(
			Long activityAttributeQuestionnaireInfoId) {
		this.activityAttributeQuestionnaireInfoId = activityAttributeQuestionnaireInfoId;
	}

	@Column(name="activity_attribute_id")
	public Long getActivityAttributeId() {
		return activityAttributeId;
	}

	public void setActivityAttributeId(Long activityAttributeId) {
		this.activityAttributeId = activityAttributeId;
	}

	
	@Column(name="activity_questionnaire_id")
	public Long getActivityQuestionnaireId() {
		return activityQuestionnaireId;
	}
	
	public void setActivityQuestionnaireId(Long activityQuestionnaireId) {
		this.activityQuestionnaireId = activityQuestionnaireId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="activity_attribute_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ActivityAttribute getActivityAttribute() {
		return activityAttribute;
	}

	public void setActivityAttribute(ActivityAttribute activityAttribute) {
		this.activityAttribute = activityAttribute;
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
}
