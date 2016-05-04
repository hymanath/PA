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
@Table(name = "ivr_survey_candidate_question")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class IvrSurveyCandidateQuestion extends BaseModel implements Serializable {
    
	private Long candidateSurveyQuestionId;
	private Long candidateId;
	private Long ivrSurveyQuestionId;
	private Long ivrOptionsId;
	private String isLocationScope;
	private Long locationScopeId;
	private Long locationValue;
	
	private Candidate  candidate;
	private IvrOption  ivrOption;
	private IvrSurveyQuestion ivrSurveyQuestion;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "candidate_survey_question_id", unique = true, nullable = false)
	public Long getCandidateSurveyQuestionId() {
		return candidateSurveyQuestionId;
	}
	public void setCandidateSurveyQuestionId(Long candidateSurveyQuestionId) {
		this.candidateSurveyQuestionId = candidateSurveyQuestionId;
	}
	@Column(name = "candidate_id")
	public Long getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}
	@Column(name = "ivr_survey_question_id")
	public Long getIvrSurveyQuestionId() {
		return ivrSurveyQuestionId;
	}
	public void setIvrSurveyQuestionId(Long ivrSurveyQuestionId) {
		this.ivrSurveyQuestionId = ivrSurveyQuestionId;
	}
	@Column(name = "ivr_options_id")
	public Long getIvrOptionsId() {
		return ivrOptionsId;
	}
	public void setIvrOptionsId(Long ivrOptionsId) {
		this.ivrOptionsId = ivrOptionsId;
	}
	@Column(name = "is_location_scope")
	public String getIsLocationScope() {
		return isLocationScope;
	}
	public void setIsLocationScope(String isLocationScope) {
		this.isLocationScope = isLocationScope;
	}
	@Column(name = "location_scope_id")
	public Long getLocationScopeId() {
		return locationScopeId;
	}
	public void setLocationScopeId(Long locationScopeId) {
		this.locationScopeId = locationScopeId;
	}
	@Column(name = "location_value")
	public Long getLocationValue() {
		return locationValue;
	}
	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="candidate_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
    public Candidate getCandidate() {
		return candidate;
	}
	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="ivr_options_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
     public IvrOption getIvrOption() {
		return ivrOption;
	}
	public void setIvrOption(IvrOption ivrOption) {
		this.ivrOption = ivrOption;
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
	
}
