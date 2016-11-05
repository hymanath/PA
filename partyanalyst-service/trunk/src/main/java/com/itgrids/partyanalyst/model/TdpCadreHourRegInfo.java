package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "tdp_cadre_hour_reg_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdpCadreHourRegInfo extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 992536127053470977L;
	
	private Long tdpCadreHourRegInfoId;
	private Long stateId;
	private Date surveyDate;
	private Long hour;
	private Long totalRegistrations;
	private Long cadreSurveyUsers;
	private Date insertedTime;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tdp_cadre_hour_reg_info_id", unique = true, nullable = false)
	public Long getTdpCadreHourRegInfoId() {
		return tdpCadreHourRegInfoId;
	}
	public void setTdpCadreHourRegInfoId(Long tdpCadreHourRegInfoId) {
		this.tdpCadreHourRegInfoId = tdpCadreHourRegInfoId;
	}
	
	@Column(name = "state_id")
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	@Column(name = "survey_date")
	public Date getSurveyDate() {
		return surveyDate;
	}
	public void setSurveyDate(Date surveyDate) {
		this.surveyDate = surveyDate;
	}
	@Column(name = "hour")
	public Long getHour() {
		return hour;
	}
	public void setHour(Long hour) {
		this.hour = hour;
	}
	
	@Column(name = "total_registrations")
	public Long getTotalRegistrations() {
		return totalRegistrations;
	}
	public void setTotalRegistrations(Long totalRegistrations) {
		this.totalRegistrations = totalRegistrations;
	}
	
	@Column(name = "cadre_survey_users")
	public Long getCadreSurveyUsers() {
		return cadreSurveyUsers;
	}
	public void setCadreSurveyUsers(Long cadreSurveyUsers) {
		this.cadreSurveyUsers = cadreSurveyUsers;
	}
	
	@Column(name = "inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	
}
