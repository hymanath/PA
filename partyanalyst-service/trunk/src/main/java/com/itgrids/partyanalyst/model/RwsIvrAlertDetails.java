package com.itgrids.partyanalyst.model;

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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "rws_ivr_alert_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RwsIvrAlertDetails {

	private Long rwsIvrAlertDetailsId;
	private Long alertId;
	private Long rwsIvrSurveyId;
	private Long rwsIvrTypeId;
	private String ivrSatisfiedStatus;
	private Long locationLevel;
	private Long locationValue;
	private String isDeleted;
	private Long insertedBy;
	private Date insertedTime;
	
	private RwsIvrType rwsIvrType;
	private Alert alert;
	private RwsIvrSurvey rwsIvrSurvey;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "rws_ivr_alert_details_id", unique = true, nullable = false)
	public Long getRwsIvrAlertDetailsId() {
		return rwsIvrAlertDetailsId;
	}
	public void setRwsIvrAlertDetailsId(Long rwsIvrAlertDetailsId) {
		this.rwsIvrAlertDetailsId = rwsIvrAlertDetailsId;
	}
	@Column(name = "alert_id")
	public Long getAlertId() {
		return alertId;
	}
	public void setAlertId(Long alertId) {
		this.alertId = alertId;
	}
	@Column(name = "rws_ivr_survey_id")
	public Long getRwsIvrSurveyId() {
		return rwsIvrSurveyId;
	}
	public void setRwsIvrSurveyId(Long rwsIvrSurveyId) {
		this.rwsIvrSurveyId = rwsIvrSurveyId;
	}
	@Column(name = "ivr_satisfied_status")
	public String getIvrSatisfiedStatus() {
		return ivrSatisfiedStatus;
	}
	public void setIvrSatisfiedStatus(String ivrSatisfiedStatus) {
		this.ivrSatisfiedStatus = ivrSatisfiedStatus;
	}
	@Column(name = "location_level")
	public Long getLocationLevel() {
		return locationLevel;
	}
	public void setLocationLevel(Long locationLevel) {
		this.locationLevel = locationLevel;
	}
	@Column(name = "location_value")
	public Long getLocationValue() {
		return locationValue;
	}
	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Column(name = "inserted_by")
	public Long getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
	}
	@Column(name = "inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "rws_ivr_type_id",insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public RwsIvrType getRwsIvrType() {
		return rwsIvrType;
	}
	public void setRwsIvrType(RwsIvrType rwsIvrType) {
		this.rwsIvrType = rwsIvrType;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "alert_id",insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Alert getAlert() {
		return alert;
	}
	public void setAlert(Alert alert) {
		this.alert = alert;
	}
	@Column(name = "rws_ivr_type_id")
	public Long getRwsIvrTypeId() {
		return rwsIvrTypeId;
	}
	public void setRwsIvrTypeId(Long rwsIvrTypeId) {
		this.rwsIvrTypeId = rwsIvrTypeId;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "rws_ivr_survey_id",insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public RwsIvrSurvey getRwsIvrSurvey() {
		return rwsIvrSurvey;
	}
	public void setRwsIvrSurvey(RwsIvrSurvey rwsIvrSurvey) {
		this.rwsIvrSurvey = rwsIvrSurvey;
	}
	
}
