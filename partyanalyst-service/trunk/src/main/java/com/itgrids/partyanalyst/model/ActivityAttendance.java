package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

import java.util.Date;


/**
 * The persistent class for the activity_attendance database table.
 * 
 */
@Entity
@Table(name="activity_attendance")
public class ActivityAttendance implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long activityAttendanceId;
	private Date activityDate;
	private Long activityLocationInfoId;
	private ActivityLocationInfo activityLocationInfo;
	private ActivityScope activityScope;
	private Long activityScopeId;
	private Date attendedTime;
	private Long day;
	private Long insertedBy;
	private Date insertedTime;
	private String isAttended;
	private Long tdpCadreId;

	public ActivityAttendance() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="activity_attendance_id", unique=true, nullable=false)
	public Long getActivityAttendanceId() {
		return this.activityAttendanceId;
	}

	public void setActivityAttendanceId(Long activityAttendanceId) {
		this.activityAttendanceId = activityAttendanceId;
	}

	@Column(name="activity_date")
	public Date getActivityDate() {
		return this.activityDate;
	}

	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}


	@Column(name="activity_location_info_id")
	public Long getActivityLocationInfoId() {
		return this.activityLocationInfoId;
	}

	public void setActivityLocationInfoId(Long activityLocationInfoId) {
		this.activityLocationInfoId = activityLocationInfoId;
	}


	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "activity_location_info_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ActivityLocationInfo getActivityLocationInfo() {
		return activityLocationInfo;
	}


	public void setActivityLocationInfo(ActivityLocationInfo activityLocationInfo) {
		this.activityLocationInfo = activityLocationInfo;
	}


	@Column(name="activity_scope_id")
	public Long getActivityScopeId() {
		return this.activityScopeId;
	}

	public void setActivityScopeId(Long activityScopeId) {
		this.activityScopeId = activityScopeId;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "activity_scope_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ActivityScope getActivityScope() {
		return activityScope;
	}
	public void setActivityScope(ActivityScope activityScope) {
		this.activityScope = activityScope;
	}
	@Column(name="attended_time")
	public Date getAttendedTime() {
		return this.attendedTime;
	}

	public void setAttendedTime(Date attendedTime) {
		this.attendedTime = attendedTime;
	}


	public Long getDay() {
		return this.day;
	}

	public void setDay(Long day) {
		this.day = day;
	}


	@Column(name="inserted_by")
	public Long getInsertedBy() {
		return this.insertedBy;
	}

	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
	}

	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return this.insertedTime;
	}

	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}


	@Column(name="is_attended", length=45)
	public String getIsAttended() {
		return this.isAttended;
	}

	public void setIsAttended(String isAttended) {
		this.isAttended = isAttended;
	}


	@Column(name="tdp_cadre_id")
	public Long getTdpCadreId() {
		return this.tdpCadreId;
	}

	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}

}