package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class PartyMeetingStatusVO implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String dateString;
	private String location;
	private Long   partyMeetinglevelId;
	
	private String ivrStatus;
	private String dpoStatus;
	private String meetingStatus;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDateString() {
		return dateString;
	}
	public void setDateString(String dateString) {
		this.dateString = dateString;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getIvrStatus() {
		return ivrStatus;
	}
	public void setIvrStatus(String ivrStatus) {
		this.ivrStatus = ivrStatus;
	}
	public String getDpoStatus() {
		return dpoStatus;
	}
	public void setDpoStatus(String dpoStatus) {
		this.dpoStatus = dpoStatus;
	}
	public String getMeetingStatus() {
		return meetingStatus;
	}
	public void setMeetingStatus(String meetingStatus) {
		this.meetingStatus = meetingStatus;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Long getPartyMeetinglevelId() {
		return partyMeetinglevelId;
	}
	public void setPartyMeetinglevelId(Long partyMeetinglevelId) {
		this.partyMeetinglevelId = partyMeetinglevelId;
	}
	
	
	
}
