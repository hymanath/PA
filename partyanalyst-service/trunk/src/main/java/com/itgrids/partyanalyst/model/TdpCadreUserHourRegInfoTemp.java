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
@Table(name = " tdp_cadre_user_hour_reg_info_temp")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdpCadreUserHourRegInfoTemp implements Serializable{
	
	private Long 	tdpCadreUserHourRegInfoTempId;
	private Long 	cadreSurveyUserId;
	private Long 	tabUserInfoId;
	private Date 	surveyDate;
	private Long 	hour;
	private Long 	regCount; 
	private Date 	insertedTime; 
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tdp_cadre_user_hour_reg_info_temp_id", unique = true, nullable = false)
	public Long getTdpCadreUserHourRegInfoTempId() {
		return tdpCadreUserHourRegInfoTempId;
	}
	public void setTdpCadreUserHourRegInfoTempId(Long tdpCadreUserHourRegInfoTempId) {
		this.tdpCadreUserHourRegInfoTempId = tdpCadreUserHourRegInfoTempId;
	}
	
	@Column(name="cadre_survey_user_id")
	public Long getCadreSurveyUserId() {
		return cadreSurveyUserId;
	}
	public void setCadreSurveyUserId(Long cadreSurveyUserId) {
		this.cadreSurveyUserId = cadreSurveyUserId;
	}
	@Column(name="tab_user_info_id")
	public Long getTabUserInfoId() {
		return tabUserInfoId;
	}
	public void setTabUserInfoId(Long tabUserInfoId) {
		this.tabUserInfoId = tabUserInfoId;
	}
	@Column(name="survey_date")
	public Date getSurveyDate() {
		return surveyDate;
	}
	public void setSurveyDate(Date surveyDate) {
		this.surveyDate = surveyDate;
	}
	@Column(name="hour")
	public Long getHour() {
		return hour;
	}
	public void setHour(Long hour) {
		this.hour = hour;
	}
	@Column(name="reg_count")
	public Long getRegCount() {
		return regCount;
	}
	public void setRegCount(Long regCount) {
		this.regCount = regCount;
	}
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	

}
