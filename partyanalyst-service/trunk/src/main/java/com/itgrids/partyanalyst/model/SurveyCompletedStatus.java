package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "survey_completed_status")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SurveyCompletedStatus implements Serializable {
	

	private Long surveyCompletedStatusId;
	private String statusCode;
	
	private String description;
	private String thirdPartyStatus;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "survey_completed_status_id", unique = true, nullable = false)
	public Long getSurveyCompletedStatusId() {
		return surveyCompletedStatusId;
	}
	public void setSurveyCompletedStatusId(Long surveyCompletedStatusId) {
		this.surveyCompletedStatusId = surveyCompletedStatusId;
	}
	
	@Column(name="status_code")
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	@Column(name="third_party_status")
	public String getThirdPartyStatus() {
		return thirdPartyStatus;
	}
	public void setThirdPartyStatus(String thirdPartyStatus) {
		this.thirdPartyStatus = thirdPartyStatus;
	}

}
