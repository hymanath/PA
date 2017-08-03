package com.itgrids.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "light_monitoring")
public class LightMonitoring {

	public Long lightMonitoringId;
	public Long panchayatId;
	public Long panchayatName;
	public Long totalLights;
	public Long totalPoles;
	public Long totalPanels;
	public Long workingLights;
	public Long notWorkingLights;
	public Long onLights;
	public Long offLights;
	public Date surveyDate;
	public Date insertedTime;
	public String isDeleted;
	
	@Id
	@Column(name="light_monitoring_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getLightMonitoringId() {
		return lightMonitoringId;
	}
	public void setLightMonitoringId(Long lightMonitoringId) {
		this.lightMonitoringId = lightMonitoringId;
	}
	
	@Column(name="panchayat_id")
	public Long getPanchayatId() {
		return panchayatId;
	}
	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}
	
	@Column(name="panchayat_name")
	public Long getPanchayatName() {
		return panchayatName;
	}
	public void setPanchayatName(Long panchayatName) {
		this.panchayatName = panchayatName;
	}
	
	@Column(name="total_lights")
	public Long getTotalLights() {
		return totalLights;
	}
	public void setTotalLights(Long totalLights) {
		this.totalLights = totalLights;
	}
	
	@Column(name="total_poles")
	public Long getTotalPoles() {
		return totalPoles;
	}
	public void setTotalPoles(Long totalPoles) {
		this.totalPoles = totalPoles;
	}
	
	@Column(name="total_panels")
	public Long getTotalPanels() {
		return totalPanels;
	}
	public void setTotalPanels(Long totalPanels) {
		this.totalPanels = totalPanels;
	}
	
	@Column(name="working_lights")
	public Long getWorkingLights() {
		return workingLights;
	}
	public void setWorkingLights(Long workingLights) {
		this.workingLights = workingLights;
	}
	
	@Column(name="not_working_lights")
	public Long getNotWorkingLights() {
		return notWorkingLights;
	}
	public void setNotWorkingLights(Long notWorkingLights) {
		this.notWorkingLights = notWorkingLights;
	}
	
	@Column(name="on_lights")
	public Long getOnLights() {
		return onLights;
	}
	public void setOnLights(Long onLights) {
		this.onLights = onLights;
	}
	
	@Column(name="off_lights")
	public Long getOffLights() {
		return offLights;
	}
	public void setOffLights(Long offLights) {
		this.offLights = offLights;
	}
	
	@Column(name="survey_date")
	public Date getSurveyDate() {
		return surveyDate;
	}
	public void setSurveyDate(Date surveyDate) {
		this.surveyDate = surveyDate;
	}
	
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
}
