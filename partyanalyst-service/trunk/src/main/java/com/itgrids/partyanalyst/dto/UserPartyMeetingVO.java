package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class UserPartyMeetingVO implements Serializable{

	private static final long serialVersionUID = -6030650863848834561L;

	private Long partyMeetingId;
	private String meetingName;
	private Long meetingLevelId;
	private Long meetingLevelValue;
	private String meetingLevelName;
	private String startDate;
	private String endDate;
	private String dailyStartTime;
	private String dailyEndTime;
	private Long userId;
	private Long partyMeetingTypeId;
	private Long partyMeetingStatusId;
	private PartyMeetingLocationVO partyMeetingLocation;
	
	public PartyMeetingLocationVO getPartyMeetingLocation() {
		return partyMeetingLocation;
	}
	public void setPartyMeetingLocation(PartyMeetingLocationVO partyMeetingLocation) {
		this.partyMeetingLocation = partyMeetingLocation;
	}
	public Long getPartyMeetingId() {
		return partyMeetingId;
	}
	public void setPartyMeetingId(Long partyMeetingId) {
		this.partyMeetingId = partyMeetingId;
	}
	public String getMeetingName() {
		return meetingName;
	}
	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
	}
	public Long getMeetingLevelId() {
		return meetingLevelId;
	}
	public void setMeetingLevelId(Long meetingLevelId) {
		this.meetingLevelId = meetingLevelId;
	}
	public Long getMeetingLevelValue() {
		return meetingLevelValue;
	}
	public void setMeetingLevelValue(Long meetingLevelValue) {
		this.meetingLevelValue = meetingLevelValue;
	}

	public String getMeetingLevelName() {
		return meetingLevelName;
	}
	public void setMeetingLevelName(String meetingLevelName) {
		this.meetingLevelName = meetingLevelName;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getDailyStartTime() {
		return dailyStartTime;
	}
	public void setDailyStartTime(String dailyStartTime) {
		this.dailyStartTime = dailyStartTime;
	}
	public String getDailyEndTime() {
		return dailyEndTime;
	}
	public void setDailyEndTime(String dailyEndTime) {
		this.dailyEndTime = dailyEndTime;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getPartyMeetingTypeId() {
		return partyMeetingTypeId;
	}
	public void setPartyMeetingTypeId(Long partyMeetingTypeId) {
		this.partyMeetingTypeId = partyMeetingTypeId;
	}
	public Long getPartyMeetingStatusId() {
		return partyMeetingStatusId;
	}
	public void setPartyMeetingStatusId(Long partyMeetingStatusId) {
		this.partyMeetingStatusId = partyMeetingStatusId;
	}
	
}
