package com.itgrids.partyanalyst.model;

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
@Table(name="party_meeting_ivr_status")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PartyMeetingIvrStatus implements java.io.Serializable{

	private Long partyMeetingIvrStatusId;
	private Long partyMeetingId;
	private PartyMeeting partyMeeting;
	private String isConductedByIvr;
	private String isConductedByPC; // program committee (PC)
	private Long conductedIvrCount;
	private String isAttendanceAvailable;
	private Long noOfattendents;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="party_meeting_ivr_status_id", unique=true, nullable=false)
	public Long getPartyMeetingIvrStatusId() {
		return partyMeetingIvrStatusId;
	}
	public void setPartyMeetingIvrStatusId(Long partyMeetingIvrStatusId) {
		this.partyMeetingIvrStatusId = partyMeetingIvrStatusId;
	}
	
	@Column(name="party_meeting_id")
	public Long getPartyMeetingId() {
		return partyMeetingId;
	}
	public void setPartyMeetingId(Long partyMeetingId) {
		this.partyMeetingId = partyMeetingId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="party_meeting_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public PartyMeeting getPartyMeeting() {
		return partyMeeting;
	}
	public void setPartyMeeting(PartyMeeting partyMeeting) {
		this.partyMeeting = partyMeeting;
	}
	
	@Column(name="is_conducted_by_ivr")
	public String getIsConductedByIvr() {
		return isConductedByIvr;
	}
	public void setIsConductedByIvr(String isConductedByIvr) {
		this.isConductedByIvr = isConductedByIvr;
	}
	
	@Column(name="is_conducted_by_pc")
	public String getIsConductedByPC() {
		return isConductedByPC;
	}
	public void setIsConductedByPC(String isConductedByPC) {
		this.isConductedByPC = isConductedByPC;
	}
	
	@Column(name="conducted_ivr_count")
	public Long getConductedIvrCount() {
		return conductedIvrCount;
	}
	public void setConductedIvrCount(Long conductedIvrCount) {
		this.conductedIvrCount = conductedIvrCount;
	}
	
	@Column(name="is_attendents_available")
	public String getIsAttendanceAvailable() {
		return isAttendanceAvailable;
	}
	public void setIsAttendanceAvailable(String isAttendanceAvailable) {
		this.isAttendanceAvailable = isAttendanceAvailable;
	}
	
	@Column(name="no_of_attendants")
	public Long getNoOfattendents() {
		return noOfattendents;
	}
	public void setNoOfattendents(Long noOfattendents) {
		this.noOfattendents = noOfattendents;
	}
	
	
}
