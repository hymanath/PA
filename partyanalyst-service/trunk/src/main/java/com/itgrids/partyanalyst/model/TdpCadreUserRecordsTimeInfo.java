/**
 * 
 */
package com.itgrids.partyanalyst.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * @author sys
 *
 */
@Entity
@Table(name = "tdp_cadre_user_records_time_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

public class TdpCadreUserRecordsTimeInfo {
	
	private Long 						tdpCadreUserRecordsTimeInfoId;
	private Long 						cadreSurveyUserId;
	private Long 						tabUserInfoId;
	private Date 						surveyDate;
	private Date 						firstRecordTime;
	private Date 						lastRecordTime;
	private Date 						insertedTime;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tdp_cadre_user_records_time_info_id", unique = true, nullable = false)
	public Long getTdpCadreUserRecordsTimeInfoId() {
		return tdpCadreUserRecordsTimeInfoId;
	}
	public void setTdpCadreUserRecordsTimeInfoId(Long tdpCadreUserRecordsTimeInfoId) {
		this.tdpCadreUserRecordsTimeInfoId = tdpCadreUserRecordsTimeInfoId;
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
	@Column(name="first_record_time")
	public Date getFirstRecordTime() {
		return firstRecordTime;
	}
	public void setFirstRecordTime(Date firstRecordTime) {
		this.firstRecordTime = firstRecordTime;
	}
	@Column(name="last_record_time")
	public Date getLastRecordTime() {
		return lastRecordTime;
	}
	public void setLastRecordTime(Date lastRecordTime) {
		this.lastRecordTime = lastRecordTime;
	}
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	

}
