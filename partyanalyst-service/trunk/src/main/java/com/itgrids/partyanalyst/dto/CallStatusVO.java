package com.itgrids.partyanalyst.dto;

public class CallStatusVO {
private Long id;
private String status;
private String locationLevel;
private Long locationLevelId;
private Long locationId;
private String meetingType;
private Long meetingTypeId;
private Long locationValue;
private String location;
private String startTime;
private String endTime;
private String meetingName;
private Long partyMeetingId;



public Long getPartyMeetingId() {
	return partyMeetingId;
}
public void setPartyMeetingId(Long partyMeetingId) {
	this.partyMeetingId = partyMeetingId;
}
public Long getLocationLevelId() {
	return locationLevelId;
}
public void setLocationLevelId(Long locationLevelId) {
	this.locationLevelId = locationLevelId;
}
public String getMeetingName() {
	return meetingName;
}
public void setMeetingName(String meetingName) {
	this.meetingName = meetingName;
}
public Long getMeetingTypeId() {
	return meetingTypeId;
}
public void setMeetingTypeId(Long meetingTypeId) {
	this.meetingTypeId = meetingTypeId;
}
public Long getLocationValue() {
	return locationValue;
}
public void setLocationValue(Long locationValue) {
	this.locationValue = locationValue;
}
public String getLocation() {
	return location;
}
public void setLocation(String location) {
	this.location = location;
}
public String getStartTime() {
	return startTime;
}
public void setStartTime(String startTime) {
	this.startTime = startTime;
}
public String getEndTime() {
	return endTime;
}
public void setEndTime(String endTime) {
	this.endTime = endTime;
}
public String getMeetingType() {
	return meetingType;
}
public void setMeetingType(String meetingType) {
	this.meetingType = meetingType;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getLocationLevel() {
	return locationLevel;
}
public void setLocationLevel(String locationLevel) {
	this.locationLevel = locationLevel;
}
public Long getLocationId() {
	return locationId;
}
public void setLocationId(Long locationId) {
	this.locationId = locationId;
}


}
