package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;

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
@Table(name="party_meeting_status_temp2")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PartyMeetingStatusTemp implements Serializable{
	
	private Long partyMeetingStatusTempId;
	
	private Long partyMeetingId;
	private String partyOfficeStatus;
	private String ivrStatus;
	private String thirdPartyStatus;
	private String mettingStatus;
	private Date insertedTime;
	
	private PartyMeeting partyMeeting;
	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="party_meeting_status_temp_id", unique=true, nullable=false)
	public Long getPartyMeetingStatusTempId() {
		return partyMeetingStatusTempId;
	}
	public void setPartyMeetingStatusTempId(Long partyMeetingStatusTempId) {
		this.partyMeetingStatusTempId = partyMeetingStatusTempId;
	}
	
	@Column(name="party_meeting_id")
	public Long getPartyMeetingId() {
		return partyMeetingId;
	}

	public void setPartyMeetingId(Long partyMeetingId) {
		this.partyMeetingId = partyMeetingId;
	}
	@Column(name="party_office_status")
	public String getPartyOfficeStatus() {
		return partyOfficeStatus;
	}

	public void setPartyOfficeStatus(String partyOfficeStatus) {
		this.partyOfficeStatus = partyOfficeStatus;
	}
	@Column(name="ivr_status")
	public String getIvrStatus() {
		return ivrStatus;
	}

	public void setIvrStatus(String ivrStatus) {
		this.ivrStatus = ivrStatus;
	}
	@Column(name="meeting_status")
	public String getMettingStatus() {
		return mettingStatus;
	}

	public void setMettingStatus(String mettingStatus) {
		this.mettingStatus = mettingStatus;
	}
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}

	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="party_meeting_id",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public PartyMeeting getPartyMeeting() {
		return partyMeeting;
	}

	public void setPartyMeeting(PartyMeeting partyMeeting) {
		this.partyMeeting = partyMeeting;
	}
	
	@Column(name="third_party_status")
	public String getThirdPartyStatus() {
		return thirdPartyStatus;
	}
	public void setThirdPartyStatus(String thirdPartyStatus) {
		this.thirdPartyStatus = thirdPartyStatus;
	}
}
