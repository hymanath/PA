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
@Table(name = "event_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EventInfo implements Serializable{
	
	private Long EventInfoId;
	private String locationType;
	private Long locationValue;
	private Long NoOfInvitees;
	private Long eventId;
	private Long stateId;
	private Date insertedTime;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "event_info_id", unique = true , nullable = false)
	public Long getEventInfoId() {
		return EventInfoId;
	}
	public void setEventInfoId(Long eventInfoId) {
		EventInfoId = eventInfoId;
	}
	@Column(name="location_type")
	public String getLocationType() {
		return locationType;
	}
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
	@Column(name="location_value")
	public Long getLocationValue() {
		return locationValue;
	}
	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
	@Column(name="no_of_invitees")
	public Long getNoOfInvitees() {
		return NoOfInvitees;
	}
	public void setNoOfInvitees(Long noOfInvitees) {
		NoOfInvitees = noOfInvitees;
	}
	@Column(name="event_id")
	public Long getEventId() {
		return eventId;
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
	
	

}
