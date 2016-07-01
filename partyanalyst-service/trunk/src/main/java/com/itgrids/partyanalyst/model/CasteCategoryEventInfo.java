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
@Table(name = "caste_category_event_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CasteCategoryEventInfo implements Serializable {
	
	private Long casteCategoryEventInfoId;
	private Long eventId;
	private Long casteCategoryId;
	private String casteCategoryName;
	private Long attended;
	private Long invited;
	private Long inviteeAttended;
	private Long nonInviteeAttended;
	private Date date;
	private Date insertedTime;
	
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "caste_category_event_info_id", unique = true, nullable = false)
	public Long getCasteCategoryEventInfoId() {
		return casteCategoryEventInfoId;
	}
	public void setCasteCategoryEventInfoId(Long casteCategoryEventInfoId) {
		this.casteCategoryEventInfoId = casteCategoryEventInfoId;
	}
	@Column(name = "event_id")
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	@Column(name = "caste_category_id")
	public Long getCasteCategoryId() {
		return casteCategoryId;
	}
	public void setCasteCategoryId(Long casteCategoryId) {
		this.casteCategoryId = casteCategoryId;
	}
	@Column(name = "caste_category_name")
	public String getCasteCategoryName() {
		return casteCategoryName;
	}
	public void setCasteCategoryName(String casteCategoryName) {
		this.casteCategoryName = casteCategoryName;
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
	@Column(name = "invitee_attended")
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
