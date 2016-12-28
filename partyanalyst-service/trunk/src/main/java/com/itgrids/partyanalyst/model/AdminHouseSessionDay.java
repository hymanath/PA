package com.itgrids.partyanalyst.model;

import java.io.Serializable;
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
@Table(name = "admin_house_session_day")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AdminHouseSessionDay extends BaseModel implements Serializable{
	
	private Long adminHouseSessionDayId;
	private Long adminHouseSessionId;
	private Date sessionDate;
	private Long day;
	private Date startTime;
	private Date endTime;
	private String isDeleted;
	
	private AdminHouseSession adminHouseSession;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "admin_house_session_day_id", unique = true, nullable = false)
	public Long getAdminHouseSessionDayId() {
		return adminHouseSessionDayId;
	}
	public void setAdminHouseSessionDayId(Long adminHouseSessionDayId) {
		this.adminHouseSessionDayId = adminHouseSessionDayId;
	}
	@Column(name = "session_date")
	public Date getSessionDate() {
		return sessionDate;
	}
	public void setSessionDate(Date sessionDate) {
		this.sessionDate = sessionDate;
	}
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Column(name = "start_time")
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	@Column(name = "end_time")
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	@Column(name = "admin_house_session_id")
	public Long getAdminHouseSessionId() {
		return adminHouseSessionId;
	}
	public void setAdminHouseSessionId(Long adminHouseSessionId) {
		this.adminHouseSessionId = adminHouseSessionId;
	}
	@Column(name = "day")
	public Long getDay() {
		return day;
	}
	public void setDay(Long day) {
		this.day = day;
	}
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "admin_house_session_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AdminHouseSession getAdminHouseSession() {
		return adminHouseSession;
	}
	public void setAdminHouseSession(AdminHouseSession adminHouseSession) {
		this.adminHouseSession = adminHouseSession;
	}
	
}
