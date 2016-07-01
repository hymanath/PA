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
@Table(name = "sub_caste_event_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SubCasteEventInfo implements java.io.Serializable {
	/*
	 * sub_caste_event_info_id,
	 * event_id,
	 * caste_id,
	 * caste_name,
	 * attended,
	 * invited,
	 * invitee_attended,
	 * non_invitee_attended,
	 * date,
	 * inserted_time
	 * 
	 */
	
	private Long subCasteEventInfoId;
	private Long eventId;
	private Long casteId;
	private String casteName;
	private Long attended;
	private Long invited;
	private Long inviteeAttended;
	private Long nonInviteeAttended;
	private Date date;
	private Date inserted_time;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sub_caste_event_info_id", unique = true, nullable = false)
	public Long getSubCasteEventInfoId() {
		return subCasteEventInfoId;
	}
	public void setSubCasteEventInfoId(Long subCasteEventInfoId) {
		this.subCasteEventInfoId = subCasteEventInfoId;
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
	public Date getInserted_time() {
		return inserted_time;
	}
	public void setInserted_time(Date inserted_time) {
		this.inserted_time = inserted_time;
	}

	
	
}
