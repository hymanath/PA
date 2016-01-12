/**
 * @author Sasi
 * Jan 11, 2016
 * ActivityLocationVO.java
 * PA - SERV - com.itgrids.partyanalyst.dto
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

/**
 * @author Sasi
 * @date Jan 11, 2016
 */
public class ActivityLocationVO implements Serializable{

	private Long 	activityLocationInfoId;
	private Long 	activityScopeId;
	private Long 	locationLevel;
	private Long 	locationValue;
	private String 	plannedStartDate;
	private String 	plannedEndDate;
	private String 	conductedStartDate;
	private String 	conductedEndDate;
	
	
	public Long getActivityLocationInfoId() {
		return activityLocationInfoId;
	}
	public void setActivityLocationInfoId(Long activityLocationInfoId) {
		this.activityLocationInfoId = activityLocationInfoId;
	}
	public Long getActivityScopeId() {
		return activityScopeId;
	}
	public void setActivityScopeId(Long activityScopeId) {
		this.activityScopeId = activityScopeId;
	}
	public Long getLocationLevel() {
		return locationLevel;
	}
	public void setLocationLevel(Long locationLevel) {
		this.locationLevel = locationLevel;
	}
	public Long getLocationValue() {
		return locationValue;
	}
	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
	public String getPlannedStartDate() {
		return plannedStartDate;
	}
	public void setPlannedStartDate(String plannedStartDate) {
		this.plannedStartDate = plannedStartDate;
	}
	public String getPlannedEndDate() {
		return plannedEndDate;
	}
	public void setPlannedEndDate(String plannedEndDate) {
		this.plannedEndDate = plannedEndDate;
	}
	public String getConductedStartDate() {
		return conductedStartDate;
	}
	public void setConductedStartDate(String conductedStartDate) {
		this.conductedStartDate = conductedStartDate;
	}
	public String getConductedEndDate() {
		return conductedEndDate;
	}
	public void setConductedEndDate(String conductedEndDate) {
		this.conductedEndDate = conductedEndDate;
	}
}
