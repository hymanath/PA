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
@Table(name = "event_caste_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EventCasteInfo extends BaseModel implements java.io.Serializable {
	
	
	private Long eventCasteInfoId;
	private Long eventId;
	private Long casteId;
	private String casteName;
	private Date fromDate;
	private Date toDate;
	private Long attended;
	private String attendedPercantage;
	private Long invitee;
	private Long inviteeAttended;
	private String inviteeAttendedPercantage;
	private Long nonInviteeAttended;
	private String nonInviteeAttendedPercantage;
	private Date insertedTime;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "event_caste_category_day_info_id", unique = true, nullable = false)
	public Long getEventCasteInfoId() {
		return eventCasteInfoId;
	}
	public void setEventCasteInfoId(Long eventCasteInfoId) {
		this.eventCasteInfoId = eventCasteInfoId;
	}
	@Column(name = "event_id")
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	@Column(name = "caste_id")
	public Long getCasteId() {
		return casteId;
	}
	public void setCasteId(Long casteId) {
		this.casteId = casteId;
	}
	@Column(name = "caste_name")
	public String getCasteName() {
		return casteName;
	}
	public void setCasteName(String casteName) {
		this.casteName = casteName;
	}
	
	@Column(name = "from_date")
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	@Column(name = "to_date")
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	@Column(name = "attended")
	public Long getAttended() {
		return attended;
	}
	public void setAttended(Long attended) {
		this.attended = attended;
	}
	@Column(name = "attended_percantage")
	public String getAttendedPercantage() {
		return attendedPercantage;
	}
	public void setAttendedPercantage(String attendedPercantage) {
		this.attendedPercantage = attendedPercantage;
	}
	@Column(name = "invitee")
	public Long getInvitee() {
		return invitee;
	}
	public void setInvitee(Long invitee) {
		this.invitee = invitee;
	}
	@Column(name = "invitee_attended")
	public Long getInviteeAttended() {
		return inviteeAttended;
	}
	public void setInviteeAttended(Long inviteeAttended) {
		this.inviteeAttended = inviteeAttended;
	}
	@Column(name = "invitee_attended_percantage")
	public String getInviteeAttendedPercantage() {
		return inviteeAttendedPercantage;
	}
	public void setInviteeAttendedPercantage(String inviteeAttendedPercantage) {
		this.inviteeAttendedPercantage = inviteeAttendedPercantage;
	}
	@Column(name = "non_invitee_attended")
	public Long getNonInviteeAttended() {
		return nonInviteeAttended;
	}
	public void setNonInviteeAttended(Long nonInviteeAttended) {
		this.nonInviteeAttended = nonInviteeAttended;
	}
	@Column(name = "non_invitee_attended_percantage")
	public String getNonInviteeAttendedPercantage() {
		return nonInviteeAttendedPercantage;
	}
	public void setNonInviteeAttendedPercantage(String nonInviteeAttendedPercantage) {
		this.nonInviteeAttendedPercantage = nonInviteeAttendedPercantage;
	}
	@Column(name = "inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}

}
