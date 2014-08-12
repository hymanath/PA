package com.itgrids.partyanalyst.model;

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
@Table(name = "survey_completed_constituency")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SurveyCompletedConstituency {
	
	private Long surveyCompletedConstituencyId;
	private Long constituencyId;
	private Long surveyCompletedConstituencyStatusId;
	private String queryComment;
	
	private Constituency constituency;
	private SurveyCompletedConstituencyStatus surveyCompletedConstituencyStatus;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "survey_completed_constituency_id", unique = true, nullable = false)
	public Long getSurveyCompletedConstituencyId() {
		return surveyCompletedConstituencyId;
	}
	public void setSurveyCompletedConstituencyId(Long surveyCompletedConstituencyId) {
		this.surveyCompletedConstituencyId = surveyCompletedConstituencyId;
	}
	
	
	@Column(name="constituency_id")
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	
	@Column(name="survey_completed_constituency_status_id")
	public Long getSurveyCompletedConstituencyStatusId() {
		return surveyCompletedConstituencyStatusId;
	}
	public void setSurveyCompletedConstituencyStatusId(
			Long surveyCompletedConstituencyStatusId) {
		this.surveyCompletedConstituencyStatusId = surveyCompletedConstituencyStatusId;
	}
	
	@Column(name="query_comment")
	public String getQueryComment() {
		return queryComment;
	}

	public void setQueryComment(String queryComment) {
		this.queryComment = queryComment;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "constituency_id",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Constituency getConstituency() {
		return constituency;
	}
	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "survey_completed_constituency_status_id",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public SurveyCompletedConstituencyStatus getSurveyCompletedConstituencyStatus() {
		return surveyCompletedConstituencyStatus;
	}
	
	public void setSurveyCompletedConstituencyStatus(
			SurveyCompletedConstituencyStatus surveyCompletedConstituencyStatus) {
		this.surveyCompletedConstituencyStatus = surveyCompletedConstituencyStatus;
	}
	
}
