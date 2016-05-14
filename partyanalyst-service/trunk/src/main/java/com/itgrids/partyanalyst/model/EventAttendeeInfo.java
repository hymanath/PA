package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "event_attendee_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EventAttendeeInfo extends BaseModel implements Serializable{
	
	
	private Long eventAttendeeInfoId;
	private Long eventId;
	private Long reportLevelId;
	private Long locationValue;
	private Long stateId;
	private Long invitees;
	private Long noninvitees;
	private Long totalAttendes;
	private Date insertedTime;
	private Date fromDate;
	private Date toDate;
	private String isMainEvent;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "event_attendee_info_id", unique = true , nullable = false)
	public Long getEventAttendeeInfoId() {
		return eventAttendeeInfoId;
	}
	public void setEventAttendeeInfoId(Long eventAttendeeInfoId) {
		this.eventAttendeeInfoId = eventAttendeeInfoId;
	}
	
	@Column(name="event_id")
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	
	@Column(name="report_level_id")
	public Long getReportLevelId() {
		return reportLevelId;
	}
	public void setReportLevelId(Long reportLevelId) {
		this.reportLevelId = reportLevelId;
	}
	
	@Column(name="location_value")
	public Long getLocationValue() {
		return locationValue;
	}
	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
	
	@Column(name="state_id")
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	
	@Column(name="invitees")
	public Long getInvitees() {
		return invitees;
	}
	public void setInvitees(Long invitees) {
		this.invitees = invitees;
	}
	
	@Column(name="noninvitees")
	public Long getNoninvitees() {
		return noninvitees;
	}
	public void setNoninvitees(Long noninvitees) {
		this.noninvitees = noninvitees;
	}
	
	@Column(name="total_attendees")
	public Long getTotalAttendes() {
		return totalAttendes;
	}
	public void setTotalAttendes(Long totalAttendes) {
		this.totalAttendes = totalAttendes;
	}
	
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@Column(name="from_date")
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	
	@Column(name="to_date")
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	
	@Column(name="is_main_event")
	public String getIsMainEvent() {
		return isMainEvent;
	}
	public void setIsMainEvent(String isMainEvent) {
		this.isMainEvent = isMainEvent;
	}
	
	
}
