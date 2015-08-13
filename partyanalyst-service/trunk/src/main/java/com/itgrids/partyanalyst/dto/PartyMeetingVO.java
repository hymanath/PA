package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class PartyMeetingVO implements java.io.Serializable{

	private Long id;
	private String name;
	private String meetingType;
	private String subName;
	private String location;
	private Long locationId =0L;
	private Long invitedCount=0L;
	private Long attendedCount=0L;
	private Long nonInviteeCount=0L;
	private Long absentCount=0L;
	private String startDateStr;
	private String endDateStr;
	private String meetingLevelStr;	
	private Long minutsCount=0L;
	private Long atrPointsCount=0L;
	private Long documentsCount=0L;
	private String memberStatus;
	public List<PartyMeetingVO> partyMeetingVOList = new ArrayList<PartyMeetingVO>(0);
	
	public String getMemberStatus() {
		return memberStatus;
	}
	public void setMemberStatus(String memberStatus) {
		this.memberStatus = memberStatus;
	}
	public Long getMinutsCount() {
		return minutsCount;
	}
	public Long getAtrPointsCount() {
		return atrPointsCount;
	}
	public Long getDocumentsCount() {
		return documentsCount;
	}
	public void setMinutsCount(Long minutsCount) {
		this.minutsCount = minutsCount;
	}
	public void setAtrPointsCount(Long atrPointsCount) {
		this.atrPointsCount = atrPointsCount;
	}
	public void setDocumentsCount(Long documentsCount) {
		this.documentsCount = documentsCount;
	}
	public String getMeetingLevelStr() {
		return meetingLevelStr;
	}
	public void setMeetingLevelStr(String meetingLevelStr) {
		this.meetingLevelStr = meetingLevelStr;
	}
	public String getStartDateStr() {
		return startDateStr;
	}
	public String getEndDateStr() {
		return endDateStr;
	}
	public void setStartDateStr(String startDateStr) {
		this.startDateStr = startDateStr;
	}
	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}
	public Long getAbsentCount() {
		return absentCount;
	}
	public void setAbsentCount(Long absentCount) {
		this.absentCount = absentCount;
	}
	public List<PartyMeetingVO> getPartyMeetingVOList() {
		return partyMeetingVOList;
	}
	public void setPartyMeetingVOList(List<PartyMeetingVO> partyMeetingVOList) {
		this.partyMeetingVOList = partyMeetingVOList;
	}
	
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
