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
@Table(name = "survey_completed_locations_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SurveyCompletedLocationsDetails {
	
	private Long surveyLocaltionStatusId;
	private Long locationValue;
	private Long locationScopeId;
	private String isCompleted;
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "survey_completed_locations_details_id", unique = true, nullable = false)
	public Long getSurveyLocaltionStatusId() {
		return surveyLocaltionStatusId;
	}
	public void setSurveyLocaltionStatusId(Long surveyLocaltionStatusId) {
		this.surveyLocaltionStatusId = surveyLocaltionStatusId;
	}
	
	@Column(name="location_value")
	public Long getLocationValue() {
		return locationValue;
	}
	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
	
	@Column(name="location_scope_id")
	public Long getLocationScopeId() {
		return locationScopeId;
	}
	public void setLocationScopeId(Long locationScopeId) {
		this.locationScopeId = locationScopeId;
	}
	
	@Column(name="is_completed")
	public String getIsCompleted() {
		return isCompleted;
	}
	public void setIsCompleted(String isCompleted) {
		this.isCompleted = isCompleted;
	}
	
	
	
	
	
}
