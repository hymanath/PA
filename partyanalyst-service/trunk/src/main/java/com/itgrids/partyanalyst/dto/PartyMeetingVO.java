package com.itgrids.partyanalyst.dto;

public class PartyMeetingVO implements java.io.Serializable{

	private Long id;
	private String name;
	private String meetingType;
	private String subName;
	private String location;
	private Long locationId;
	private Long invitedCount;
	private Long attendedCount;
	private Long nonInviteeCount;
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getMeetingType() {
		return meetingType;
	}
	public String getSubName() {
		return subName;
	}
	public String getLocation() {
		return location;
	}
	public Long getLocationId() {
		return locationId;
	}
	public Long getInvitedCount() {
		return invitedCount;
	}
	public Long getAttendedCount() {
		return attendedCount;
	}
	public Long getNonInviteeCount() {
		return nonInviteeCount;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setMeetingType(String meetingType) {
		this.meetingType = meetingType;
	}
	public void setSubName(String subName) {
		this.subName = subName;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	public void setInvitedCount(Long invitedCount) {
		this.invitedCount = invitedCount;
	}
	public void setAttendedCount(Long attendedCount) {
		this.attendedCount = attendedCount;
	}
	public void setNonInviteeCount(Long nonInviteeCount) {
		this.nonInviteeCount = nonInviteeCount;
	}
	
}
