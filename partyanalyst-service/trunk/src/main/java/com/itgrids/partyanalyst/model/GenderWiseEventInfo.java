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
@Table(name = "gender_wise_event_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GenderWiseEventInfo implements Serializable {
  /*
   * gender_wise_event_info_id,
   * event_id,
   * gender_id,
   * gender,
   */
    private Long genderWiseEventInfoId;
    private Long eventId;
    private Long genderId;
    private String gender;
    private Long attended;
	private Long invited;
	private Long inviteeAttended;
	private Long nonInviteeAttended;
	private Date date;
	private Date insertedTime;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "gender_wise_event_info_id", unique = true, nullable = false)
	public Long getGenderWiseEventInfoId() {
		return genderWiseEventInfoId;
	}
	public void setGenderWiseEventInfoId(Long genderWiseEventInfoId) {
		this.genderWiseEventInfoId = genderWiseEventInfoId;
	}
	@Column(name = "event_id")
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	@Column(name = "gender_id")
	public Long getGenderId() {
		return genderId;
	}
	public void setGenderId(Long genderId) {
		this.genderId = genderId;
	}
	@Column(name = "gender")
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
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
