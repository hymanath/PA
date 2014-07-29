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
@Table(name = "survey_completed_locations")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SurveyCompletedLocations {
	
	private Long surveyCompletedLocationsId;
	private Long locationScopeId;
	private Long locationValue;
	private Long statusId;
	
	private RegionScopes regionScopes;
	private SurveyCompletedStatus surveyCompletedStatus;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "survey_completed_locations_id", unique = true, nullable = false)
	public Long getSurveyCompletedLocationsId() {
		return surveyCompletedLocationsId;
	}
	public void setSurveyCompletedLocationsId(Long surveyCompletedLocationsId) {
		this.surveyCompletedLocationsId = surveyCompletedLocationsId;
	}
	
	@Column(name="location_scope_id")
	public Long getLocationScopeId() {
		return locationScopeId;
	}
	public void setLocationScopeId(Long locationScopeId) {
		this.locationScopeId = locationScopeId;
	}
	
	@Column(name="location_value")
	public Long getLocationValue() {
		return locationValue;
	}
	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
	
	@Column(name="status_id")
	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "location_scope_id",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public RegionScopes getRegionScopes() {
		return regionScopes;
	}
	public void setRegionScopes(RegionScopes regionScopes) {
		this.regionScopes = regionScopes;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_id",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public SurveyCompletedStatus getSurveyCompletedStatus() {
		return surveyCompletedStatus;
	}
	public void setSurveyCompletedStatus(SurveyCompletedStatus surveyCompletedStatus) {
		this.surveyCompletedStatus = surveyCompletedStatus;
	}
	
}
