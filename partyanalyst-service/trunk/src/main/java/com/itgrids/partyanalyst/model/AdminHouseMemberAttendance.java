package com.itgrids.partyanalyst.model;

import java.io.Serializable;
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
@Table(name = "admin_house_member_attendance")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AdminHouseMemberAttendance extends BaseModel implements Serializable{
	
	private Long adminHouseMemberAttendanceId;
	private Long adminHouseSessionDayId;
	private String isAttended;
	private Date inTime;
	private Date outTime;
	private String remarks;
	private String isDeleted;
	
	private AdminHouseSessionDay adminHouseSessionDay;


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "admin_house_member_attendance_id", unique = true, nullable = false)
	public Long getAdminHouseMemberAttendanceId() {
		return adminHouseMemberAttendanceId;
	}

	public void setAdminHouseMemberAttendanceId(Long adminHouseMemberAttendanceId) {
		this.adminHouseMemberAttendanceId = adminHouseMemberAttendanceId;
	}
	@Column(name = "admin_house_session_day_id")
	public Long getAdminHouseSessionDayId() {
		return adminHouseSessionDayId;
	}

	public void setAdminHouseSessionDayId(Long adminHouseSessionDayId) {
		this.adminHouseSessionDayId = adminHouseSessionDayId;
	}
	@Column(name = "is_attended")
	public String getIsAttended() {
		return isAttended;
	}

	public void setIsAttended(String isAttended) {
		this.isAttended = isAttended;
	}
	@Column(name = "in_time")
	public Date getInTime() {
		return inTime;
	}
	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}
	@Column(name = "out_time")
	public Date getOutTime() {
		return outTime;
	}
	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}
	@Column(name = "remarks")
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "admin_house_session_day_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AdminHouseSessionDay getAdminHouseSessionDay() {
		return adminHouseSessionDay;
	}
	public void setAdminHouseSessionDay(AdminHouseSessionDay adminHouseSessionDay) {
		this.adminHouseSessionDay = adminHouseSessionDay;
	}
}
