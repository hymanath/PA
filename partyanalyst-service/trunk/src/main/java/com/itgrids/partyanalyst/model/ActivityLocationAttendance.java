package com.itgrids.partyanalyst.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

public class ActivityLocationAttendance {
	private Long activityLocationAttendanceId;
	private Long activityLocationInfoId;
	private Long attendanceId;
	private Attendance attendance;
	private ActivityLocationInfo activityLocationInfo;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "activity_location_attendance_id", unique = true, nullable = false)
	public Long getActivityLocationAttendanceId() {
		return activityLocationAttendanceId;
	}
	public void setActivityLocationAttendanceId(Long activityLocationAttendanceId) {
		this.activityLocationAttendanceId = activityLocationAttendanceId;
	}
	@Column(name = "activity_location_info_id")
	public Long getActivityLocationInfoId() {
		return activityLocationInfoId;
	}
	
	public void setActivityLocationInfoId(Long activityLocationInfoId) {
		this.activityLocationInfoId = activityLocationInfoId;
	}
	@Column(name = "attendance_id")
	public Long getAttendanceId() {
		return attendanceId;
	}
	public void setAttendanceId(Long attendanceId) {
		this.attendanceId = attendanceId;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="attendance_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Attendance getAttendance() {
		return attendance;
	}
	public void setAttendance(Attendance attendance) {
		this.attendance = attendance;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="activity_location_info_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ActivityLocationInfo getActivityLocationInfo() {
		return activityLocationInfo;
	}
	public void setActivityLocationInfo(ActivityLocationInfo activityLocationInfo) {
		this.activityLocationInfo = activityLocationInfo;
	}
	
	
	

}
