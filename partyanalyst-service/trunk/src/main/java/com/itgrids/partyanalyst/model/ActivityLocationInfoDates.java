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

@Entity
@Table(name = "activity_location_info_dates")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ActivityLocationInfoDates implements java.io.Serializable {
	private Long activityLocationInfoDatesId;
	private ActivityLocationInfo activityLocationInfo;
	private Long activityLocationInfoId;
	private ActivityDateType activityDateType;
	private Long activityDateTypeId;
	private Date activityDate;
	private Long day;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "activity_location_info_dates_id", nullable = false, unique = true)
	public Long getActivityLocationInfoDatesId() {
		return activityLocationInfoDatesId;
	}

	public void setActivityLocationInfoDatesId(Long activityLocationInfoDatesId) {
		this.activityLocationInfoDatesId = activityLocationInfoDatesId;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "activity_location_info_id",insertable = false, updatable = false)
	public ActivityLocationInfo getActivityLocationInfo() {
		return activityLocationInfo;
	}

	public void setActivityLocationInfo(
			ActivityLocationInfo activityLocationInfo) {
		this.activityLocationInfo = activityLocationInfo;
	}

	@Column(name = "activity_location_info_id")
	public Long getActivityLocationInfoId() {
		return activityLocationInfoId;
	}

	public void setActivityLocationInfoId(Long activityLocationInfoId) {
		this.activityLocationInfoId = activityLocationInfoId;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "activity_date_type_id", insertable = false, updatable = false)
	public ActivityDateType getActivityDateType() {
		return activityDateType;
	}

	public void setActivityDateType(ActivityDateType activityDateType) {
		this.activityDateType = activityDateType;
	}

	@Column(name = "activity_date_type_id")
	public Long getActivityDateTypeId() {
		return activityDateTypeId;
	}

	public void setActivityDateTypeId(Long activityDateTypeId) {
		this.activityDateTypeId = activityDateTypeId;
	}

	@Column(name = "activity_date")
	public Date getActivityDate() {
		return activityDate;
	}

	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}
	@Column(name = "day")
	public Long getDay() {
		return day;
	}

	public void setDay(Long day) {
		this.day = day;
	}

	
	
}
