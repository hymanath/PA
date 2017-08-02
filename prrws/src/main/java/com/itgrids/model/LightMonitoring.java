package com.itgrids.model;



import java.util.Date;

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
/*
 * Date : 02/08/2017
 * Author :Swapna
 * @description : saveRealtimeStatusByVillages
 */
@Entity
@Table(name = "light_monitoring")
public class LightMonitoring  {
	
	
	private Long lightMonitoringId;	
	private Panchayat panchayat;
	private Long totalLights;
	private Long totalPanels;
	private Long totalPoles;
    private Long workingLights;
	private Long onLights;
	private Long offLights;
	private Long notWorkingLights;
	private String isDeleted;
	private Date insertredTime;
	private Date surveyDate;
	
	private Long panchayatId;
	
	@Id
	@Column(name = "light_monitoring_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getLightMonitoringId() {
		return lightMonitoringId;
	}
	
	public void setLightMonitoringId(Long lightMonitoringId) {
		this.lightMonitoringId = lightMonitoringId;
	}
	
	@Column(name="total_lights")
	public Long getTotalLights() {
		return totalLights;
	}
	public void setTotalLights(Long totalLights) {
		this.totalLights = totalLights;
	}
	
	@Column(name="total_panels")
	public Long getTotalPanels() {
		return totalPanels;
	}
	public void setTotalPanels(Long totalPanels) {
		this.totalPanels = totalPanels;
	}
	
	@Column(name="total_poles")
	public Long getTotalPoles() {
		return totalPoles;
	}
	public void setTotalPoles(Long totalPoles) {
		this.totalPoles = totalPoles;
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
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "panchayat_id", insertable = false, updatable = false)
	public Panchayat getPanchayat() {
		return panchayat;
	}

	public void setPanchayat(Panchayat panchayat) {
		this.panchayat = panchayat;
	}

	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Column(name="inserted_time")
	public Date getInsertredTime() {
		return insertredTime;
	}

	public void setInsertredTime(Date insertredTime) {
		this.insertredTime = insertredTime;
	}

	@Column(name="survey_date")
	public Date getSurveyDate() {
		return surveyDate;
	}

	public void setSurveyDate(Date surveyDate) {
		this.surveyDate = surveyDate;
	}

	@Column(name="panchayat_id")
	public Long getPanchayatId() {
		return panchayatId;
	}

	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}
	
}
