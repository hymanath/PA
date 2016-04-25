package com.itgrids.partyanalyst.model;

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
@Table(name = "activity_status_questionnaire")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ActivityStatusQuestionnaire {

	private Long activityStatusQuestionnaireId;
	private Long activityScopeId;
	private Long activityQuestionnaireId;
	private String isDeleted;

	private ActivityScope activityScope;
	private ActivityQuestionnaire activityQuestionnaire;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "activity_status_questionnaire_id", unique = true, nullable = false)
	public Long getActivityStatusQuestionnaireId() {
		return activityStatusQuestionnaireId;
	}
	public void setActivityStatusQuestionnaireId(Long activityStatusQuestionnaireId) {
		this.activityStatusQuestionnaireId = activityStatusQuestionnaireId;
	}
	@Column(name="activity_scope_id")
	public Long getActivityScopeId() {
		return activityScopeId;
	}
	public void setActivityScopeId(Long activityScopeId) {
		this.activityScopeId = activityScopeId;
	}
	@Column(name="activity_questionnaire_id")
	public Long getActivityQuestionnaireId() {
		return activityQuestionnaireId;
	}
	public void setActivityQuestionnaireId(Long activityQuestionnaireId) {
		this.activityQuestionnaireId = activityQuestionnaireId;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
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
