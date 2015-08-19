package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.itgrids.partyanalyst.model.PartyMeetingMinute;

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
	private Long partyMeetingTypeId;
	private String partyMeetingType;
	private Long meetingLevelId;
	private String meetingLevel;
	private Long locationValue;
	private Date startDate;
	private Date endDate;
	private List<PartyMeetingVO> minutesDetails;
	private List<PartyMeetingVO> atrDetails;
	private Long partyMeetingMinuteId;
	private String minutePoint;
	private Long insertedById;
	private String insertedBy;
	private Long updatedById;
	private String updatedBy;
	private String insertedTime;
	private String updatedTime;
	private Long partyMeetingAtrPointId;
	private String request;
	private String actionTaken;
	private String requestFrom;
	private Long locationScopeId;
	private String raisedBy;
	private List<CallTrackingVO> atrDocuments;
	private List<CallTrackingVO> minutesDocuments;
	private String locationName;
	
	
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public List<CallTrackingVO> getAtrDocuments() {
		return atrDocuments;
	}
	public void setAtrDocuments(List<CallTrackingVO> atrDocuments) {
		this.atrDocuments = atrDocuments;
	}
	public List<CallTrackingVO> getMinutesDocuments() {
		return minutesDocuments;
	}
	public void setMinutesDocuments(List<CallTrackingVO> minutesDocuments) {
		this.minutesDocuments = minutesDocuments;
	}
	public List<PartyMeetingVO> getAtrDetails() {
		return atrDetails;
	}
	public void setAtrDetails(List<PartyMeetingVO> atrDetails) {
		this.atrDetails = atrDetails;
	}
	public String getRaisedBy() {
		return raisedBy;
	}
	public void setRaisedBy(String raisedBy) {
		this.raisedBy = raisedBy;
	}
	public Long getLocationScopeId() {
		return locationScopeId;
	}
	public void setLocationScopeId(Long locationScopeId) {
		this.locationScopeId = locationScopeId;
	}
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	public String getActionTaken() {
		return actionTaken;
	}
	public void setActionTaken(String actionTaken) {
		this.actionTaken = actionTaken;
	}
	public String getRequestFrom() {
		return requestFrom;
	}
	public void setRequestFrom(String requestFrom) {
		this.requestFrom = requestFrom;
	}
	public Long getPartyMeetingAtrPointId() {
		return partyMeetingAtrPointId;
	}
	public void setPartyMeetingAtrPointId(Long partyMeetingAtrPointId) {
		this.partyMeetingAtrPointId = partyMeetingAtrPointId;
	}
	public String getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}
	public String getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(String insertedTime) {
		this.insertedTime = insertedTime;
	}
	public Long getUpdatedById() {
		return updatedById;
	}
	public void setUpdatedById(Long updatedById) {
		this.updatedById = updatedById;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Long getInsertedById() {
		return insertedById;
	}
	public void setInsertedById(Long insertedById) {
		this.insertedById = insertedById;
	}
	public String getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(String insertedBy) {
		this.insertedBy = insertedBy;
	}
	public String getMinutePoint() {
		return minutePoint;
	}
	public void setMinutePoint(String minutePoint) {
		this.minutePoint = minutePoint;
	}
	public Long getPartyMeetingMinuteId() {
		return partyMeetingMinuteId;
	}
	public void setPartyMeetingMinuteId(Long partyMeetingMinuteId) {
		this.partyMeetingMinuteId = partyMeetingMinuteId;
	}
	public List<PartyMeetingVO> getMinutesDetails() {
		return minutesDetails;
	}
	public void setMinutesDetails(List<PartyMeetingVO> minutesDetails) {
		this.minutesDetails = minutesDetails;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Long getLocationValue() {
		return locationValue;
	}
	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
	public Long getMeetingLevelId() {
		return meetingLevelId;
	}
	public void setMeetingLevelId(Long meetingLevelId) {
		this.meetingLevelId = meetingLevelId;
	}
	public String getMeetingLevel() {
		return meetingLevel;
	}
	public void setMeetingLevel(String meetingLevel) {
		this.meetingLevel = meetingLevel;
	}
	public Long getPartyMeetingTypeId() {
		return partyMeetingTypeId;
	}
	public void setPartyMeetingTypeId(Long partyMeetingTypeId) {
		this.partyMeetingTypeId = partyMeetingTypeId;
	}
	public String getPartyMeetingType() {
		return partyMeetingType;
	}
	public void setPartyMeetingType(String partyMeetingType) {
		this.partyMeetingType = partyMeetingType;
	}
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
