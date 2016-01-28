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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "user_location_tracking")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserLocationTracking {
	
	private Long userLocationTrackingId;
	private String longitude;
	private String latitude;
	private Date surveyTime;
	private Date insertedTime;
	private Long userId;
	private MobileAppUser user;
	private String uniqueId;
	private Date gpsTime;
	private String imeiNo;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_location_tracking_id", unique=true, nullable=false)
	public Long getUserLocationTrackingId() {
		return userLocationTrackingId;
	}

	public void setUserLocationTrackingId(Long userLocationTrackingId) {
		this.userLocationTrackingId = userLocationTrackingId;
	}
	
	
	
	//@Temporal(TemporalType.TIMESTAMP)
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}

	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	

	//@Temporal(TemporalType.TIMESTAMP)
	@Column(name="survey_time")
	public Date getSurveyTime() {
		return surveyTime;
	}


	public void setSurveyTime(Date surveyTime) {
		this.surveyTime = surveyTime;
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
	
	
    @Column(name="user_id")
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="user_id" , insertable = false ,updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public MobileAppUser getUser() {
		return user;
	}

	public void setUser(MobileAppUser user) {
		this.user = user;
	}
	

    @Column(name="unique_id")
	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	@Column(name="gps_time")
	public Date getGpsTime() {
		return gpsTime;
	}

	public void setGpsTime(Date gpsTime) {
		this.gpsTime = gpsTime;
	}

	@Column(name="imei_no")
	public String getImeiNo() {
		return imeiNo;
	}

	public void setImeiNo(String imeiNo) {
		this.imeiNo = imeiNo;
	}
	
	
}
