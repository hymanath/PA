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
/**
 * 
 * @author Srishailam Pittala
 *
 */

@Entity
@Table(name = "event")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Event implements java.io.Serializable{
	
	private Long eventId;
	private String name;
	private String description;
	private Date eventStartTime;
	private Date eventEndTime;
	private Long parentEventId;
	private String isActive;
	private Date insertedTime;
	
	private String startTime; // programme start Time
	private String endTime; // programme end Time
	private String isInviteeExist;
	
	private Long entryLimit;
	private String serverWorkMode;
	private String tabWorkMode;
	
	private Integer orderId;
	private String syncType;
	private Long eventTypeId;
	private EventType eventType;
	private Date eventEndDate;
	
	private String isVisible;
	private String attendanceEnrolmentYear;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "event_id", unique = true, nullable = false)
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="start_time")
	public Date getEventStartTime() {
		return eventStartTime;
	}
	public void setEventStartTime(Date eventStartTime) {
		this.eventStartTime = eventStartTime;
	}
	
	@Column(name="end_time")
	public Date getEventEndTime() {
		return eventEndTime;
	}
	public void setEventEndTime(Date eventEndTime) {
		this.eventEndTime = eventEndTime;
	}
	
	@Column(name="parent_event_id")
	public Long getParentEventId() {
		return parentEventId;
	}
	public void setParentEventId(Long parentEventId) {
		this.parentEventId = parentEventId;
	}	
	
	
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	@Column(name="insertion_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	
	/*
	 @Column(name="is_enabled")
	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}
	 public String getIsEnabled() {
		return isEnabled;
	}
	
	
	*/
	@Column(name="event_start_time")
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	@Column(name="event_end_time")
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	@Column(name="is_invitee_exist")
	public String getIsInviteeExist() {
		return isInviteeExist;
	}
	public void setIsInviteeExist(String isInviteeExist) {
		this.isInviteeExist = isInviteeExist;
	}

	@Column(name="entry_limit")
	public Long getEntryLimit() {
		return entryLimit;
	}
	public void setEntryLimit(Long entryLimit) {
		this.entryLimit = entryLimit;
	}
	

	@Column(name="server_work_mode")
	public String getServerWorkMode() {
		return serverWorkMode;
	}

	public void setServerWorkMode(String serverWorkMode) {
		this.serverWorkMode = serverWorkMode;
	}

	@Column(name="tab_work_mode")
	public String getTabWorkMode() {
		return tabWorkMode;
	}

	public void setTabWorkMode(String tabWorkMode) {
		this.tabWorkMode = tabWorkMode;
	}
	
	@Column(name="is_active")
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	
	@Column(name="order_id")
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	@Column(name="sync_type")
	public String getSyncType() {
		return syncType;
	}

	public void setSyncType(String syncType) {
		this.syncType = syncType;
	}
	
	@Column(name="event_type_id")
	public Long getEventTypeId() {
		return eventTypeId;
	}
	
	public void setEventTypeId(Long eventTypeId) {
		this.eventTypeId = eventTypeId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "event_type_id",insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public EventType getEventType() {
		return eventType;
	}
	
	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}
	
	@Column(name="is_visible")
	public String getIsVisible() {
		return isVisible;
	}
	public void setIsVisible(String isVisible) {
		this.isVisible = isVisible;
	}
	
	@Column(name="event_end_date")
	public Date getEventEndDate() {
		return eventEndDate;
	}
	public void setEventEndDate(Date eventEndDate) {
		this.eventEndDate = eventEndDate;
	}
	
	@Column(name="attendance_enrolment_year")
	public String getAttendanceEnrolmentYear() {
		return attendanceEnrolmentYear;
	}
	public void setAttendanceEnrolmentYear(String attendanceEnrolmentYear) {
		this.attendanceEnrolmentYear = attendanceEnrolmentYear;
	}
	
	
	
}
