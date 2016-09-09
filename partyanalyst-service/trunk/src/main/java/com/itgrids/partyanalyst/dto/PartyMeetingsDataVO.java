package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class PartyMeetingsDataVO implements Serializable{
	
	private Long   id;
	private String name;
	private String locationLevelName;
	private Long   totalCount = 0l;
	private Long   conductedCount=0l;
	
	private Double conductedPerc = 0.0;
	private String requiredName;
	
	private Long noOfMeetings=0l;
	private Long invitedCount=0l;
	private Long attendedCount=0l;
	private Long invitteeAttendedCount=0l;
	private Long notAttendedCount=0l;
	
	private Double invitedPerc=0.0;
	private Double attendedPerc=0.0;
	private Double inviteeAttendedPerc=0.0;
	private Double notAttendedPerc=0.0;
	
	
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
	public String getLocationLevelName() {
		return locationLevelName;
	}
	public void setLocationLevelName(String locationLevelName) {
		this.locationLevelName = locationLevelName;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getConductedCount() {
		return conductedCount;
	}
	public void setConductedCount(Long conductedCount) {
		this.conductedCount = conductedCount;
	}
	public Double getConductedPerc() {
		return conductedPerc;
	}
	public void setConductedPerc(Double conductedPerc) {
		this.conductedPerc = conductedPerc;
	}
	public String getRequiredName() {
		return requiredName;
	}
	public void setRequiredName(String requiredName) {
		this.requiredName = requiredName;
	}
	public Long getNoOfMeetings() {
		return noOfMeetings;
	}
	public void setNoOfMeetings(Long noOfMeetings) {
		this.noOfMeetings = noOfMeetings;
	}
	public Long getInvitedCount() {
		return invitedCount;
	}
	public void setInvitedCount(Long invitedCount) {
		this.invitedCount = invitedCount;
	}
	public Long getAttendedCount() {
		return attendedCount;
	}
	public void setAttendedCount(Long attendedCount) {
		this.attendedCount = attendedCount;
	}
	public Long getInvitteeAttendedCount() {
		return invitteeAttendedCount;
	}
	public void setInvitteeAttendedCount(Long invitteeAttendedCount) {
		this.invitteeAttendedCount = invitteeAttendedCount;
	}
	public Long getNotAttendedCount() {
		return notAttendedCount;
	}
	public void setNotAttendedCount(Long notAttendedCount) {
		this.notAttendedCount = notAttendedCount;
	}
	public Double getInvitedPerc() {
		return invitedPerc;
	}
	public void setInvitedPerc(Double invitedPerc) {
		this.invitedPerc = invitedPerc;
	}
	public Double getAttendedPerc() {
		return attendedPerc;
	}
	public void setAttendedPerc(Double attendedPerc) {
		this.attendedPerc = attendedPerc;
	}
	public Double getInviteeAttendedPerc() {
		return inviteeAttendedPerc;
	}
	public void setInviteeAttendedPerc(Double inviteeAttendedPerc) {
		this.inviteeAttendedPerc = inviteeAttendedPerc;
	}
	public Double getNotAttendedPerc() {
		return notAttendedPerc;
	}
	public void setNotAttendedPerc(Double notAttendedPerc) {
		this.notAttendedPerc = notAttendedPerc;
	}
	
}
