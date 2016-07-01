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
@Table(name = "age_wise_event_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AgeWiseEventInfo implements java.io.Serializable {
	

	private Long ageWiseEventInfoId;
	private Long eventId;
	private Long ageRangeId;
	private String ageRange;
	private Long attended;
	private Long invited;
	private Long inviteeAttended;
	private Long nonInviteeAttended;
	private Date date;
	private Date insertedTime;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "age_wise_event_info_id", unique = true, nullable = false)
	public Long getAgeWiseEventInfoId() {
		return ageWiseEventInfoId;
	}
	public void setAgeWiseEventInfoId(Long ageWiseEventInfoId) {
		this.ageWiseEventInfoId = ageWiseEventInfoId;
	}
	@Column(name = "event_id")
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	@Column(name = "age_range_id")
	public Long getAgeRangeId() {
		return ageRangeId;
	}
	public void setAgeRangeId(Long ageRangeId) {
		this.ageRangeId = ageRangeId;
	}
	@Column(name = "age_range")
	public String getAgeRange() {
		return ageRange;
	}
	public void setAgeRange(String ageRange) {
		this.ageRange = ageRange;
	}
	
	@Column(name = "attended")
	public Long getAttended() {
		return attended;
	}
	public void setAttended(Long attended) {
		this.attended = attended;
	}
	@Column(name = "invited")
	public Long getInvited() {
		return invited;
	}
	public void setInvited(Long invited) {
		this.invited = invited;
	}
	@Column(name = "inviteeAttended")
	public Long getInviteeAttended() {
		return inviteeAttended;
	}
	public void setInviteeAttended(Long inviteeAttended) {
		this.inviteeAttended = inviteeAttended;
	}
	@Column(name = "non_invitee_attended")
	public Long getNonInviteeAttended() {
		return nonInviteeAttended;
	}
	public void setNonInviteeAttended(Long nonInviteeAttended) {
		this.nonInviteeAttended = nonInviteeAttended;
	}
	@Column(name = "date")
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Column(name = "inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	
	
}
