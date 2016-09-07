package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity
@Table(name = "party_meeting_main_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PartyMeetingMainType {
	
	private Long partyMeetingMainTypeId;
	private String meetingType;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="party_meeting_main_type_id", unique=true, nullable=false)
	public Long getPartyMeetingMainTypeId() {
		return partyMeetingMainTypeId;
	}
	public void setPartyMeetingMainTypeId(Long partyMeetingMainTypeId) {
		this.partyMeetingMainTypeId = partyMeetingMainTypeId;
	}
	@Column(name="meeting_type")
	public String getMeetingType() {
		return meetingType;
	}
	public void setMeetingType(String meetingType) {
		this.meetingType = meetingType;
	}
	
}
