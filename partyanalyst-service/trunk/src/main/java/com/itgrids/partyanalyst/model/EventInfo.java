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
@Table(name = "event_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EventInfo implements Serializable{
	
	private Long eventInfoId;
	private Long reportLevelId;
	private Long locationValue;
	private Long totalAttendes;
	private Long invitees;
	private Long noninvitees;
	private Event event;
	private Long eventId;
	private Long stateId;
	private Date insertedTime;
	private Date date;
	private Long revisited;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "event_info_id", unique = true , nullable = false)
	public Long getEventInfoId() {
		return eventInfoId;
	}
	public void setEventInfoId(Long eventInfoId) {
		this.eventInfoId = eventInfoId;
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
	@Column(name="total_attendees")
	public Long getTotalAttendes() {
		return totalAttendes;
	}
	public void setTotalAttendes(Long totalAttendes) {
		this.totalAttendes = totalAttendes;
	}
	@Column(name="event_id")
	public Long getEventId() {
		return eventId;
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
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	@Column(name="state_id")
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "event_id",insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	@Column(name="date")
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Column(name="revisited")
	public Long getRevisited() {
		return revisited;
	}
	public void setRevisited(Long revisited) {
		this.revisited = revisited;
	}
	
	

}
