package com.itgrids.partyanalyst.model;

import java.util.Date;

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
@Table(name = "survey_user_tracking")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SurveyUserTracking
{

	private Long surveyUserTrackingId;
	private SurveyUser surveyUser;
	private Date date;
	private String longitude;
	private String latitude;
	private Date insertedTime;
	private Date updatedTime;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "survey_user_tracking_id", unique = true, nullable = false)
	public Long getSurveyUserTrackingId() {
		return surveyUserTrackingId;
	}
	public void setSurveyUserTrackingId(Long surveyUserTrackingId) {
		this.surveyUserTrackingId = surveyUserTrackingId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "survey_user_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public SurveyUser getSurveyUser() {
		return surveyUser;
	}
	public void setSurveyUser(SurveyUser surveyUser) {
		this.surveyUser = surveyUser;
	}
	
	@Column(name="date")
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Column(name="longitude")
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	@Column(name="latitude")
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@Column(name="updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	
	
}
