package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
@Entity
@Table(name = "survey_completed_constituency_status")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SurveyCompletedConstituencyStatus {
	
	private Long surveyCompletedConstituencyStatusId;
	private String status;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "survey_completed_constituency_status_id", unique = true, nullable = false)
	public Long getSurveyCompletedConstituencyStatusId() {
		return surveyCompletedConstituencyStatusId;
	}
	public void setSurveyCompletedConstituencyStatusId(
			Long surveyCompletedConstituencyStatusId) {
		this.surveyCompletedConstituencyStatusId = surveyCompletedConstituencyStatusId;
	}
	
	@Column(name="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
