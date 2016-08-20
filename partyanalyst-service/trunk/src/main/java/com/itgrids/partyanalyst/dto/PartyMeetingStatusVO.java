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
	
	private String district;
	private String parliament;
	private String constituency;
	private String tehsil;
	private String panchayat;
	private String localElectionBody;
	private String electionType;
	private String ward;

	
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
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getParliament() {
		return parliament;
	}
	public void setParliament(String parliament) {
		this.parliament = parliament;
	}
	public String getConstituency() {
		return constituency;
	}
	public void setConstituency(String constituency) {
		this.constituency = constituency;
	}
	public String getTehsil() {
		return tehsil;
	}
	public void setTehsil(String tehsil) {
		this.tehsil = tehsil;
	}
	public String getPanchayat() {
		return panchayat;
	}
	public void setPanchayat(String panchayat) {
		this.panchayat = panchayat;
	}
	public String getLocalElectionBody() {
		return localElectionBody;
	}
	public void setLocalElectionBody(String localElectionBody) {
		this.localElectionBody = localElectionBody;
	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	public String getElectionType() {
		return electionType;
	}
	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}
	
	
}
