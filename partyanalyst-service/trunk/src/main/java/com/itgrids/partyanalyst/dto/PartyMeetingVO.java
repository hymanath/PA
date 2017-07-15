package com.itgrids.partyanalyst.dto;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
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
	private Long partyMeetingTypeId;
	private Long partyMeetingSubTypeId;
	public Long getPartyMeetingSubTypeId() {
		return partyMeetingSubTypeId;
	}
	public void setPartyMeetingSubTypeId(Long partyMeetingSubTypeId) {
		this.partyMeetingSubTypeId = partyMeetingSubTypeId;
	}
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
	private List<CallTrackingVO> meetingDocs;
	private List<String> minutes;
	private List<String> atrPoints;
	private String path;
	private List<PartyMeetingVO> docsList;
	private Long partyMeetingId;
	private String partyMeetingName;
	private String mobileNo;
	private List<String> docmentsList = new ArrayList<String>();
	private Long count = 0l;
	private Long conductedCount = 0l;
	private Long notConductedCount = 0l;
	private List<PartyMeetingsDataVO> sessionList = new ArrayList<PartyMeetingsDataVO>(0);
	private List<Long> attendedList = new ArrayList<Long>(0);
	private List<Long> invitedList = new ArrayList<Long>(0);
	private List<Long> inviteeAttendedList = new ArrayList<Long>(0);
	private List<Long> nonInviteeAttendedList = new ArrayList<Long>(0);
	private List<Long> absentList = new ArrayList<Long>(0);
	private Long inviteeAttendedCount = 0l;
	private Double inviteeAttendedPerc = 0.00;
	private Double inviteeAbscentPerc = 0.00;
	private Double nonInviteePerc = 0.00;
	private String distName;
	private String constNmae;
	private String meetingTime;
	private String partyInvaitedChecking;
	private File file;
	private Long districtId=0L;
	private Long constituencyId=0L;
	private Long mandalId=0L;
	private Long villageId=0L;
	private String teshilName;
	private Long sessionId;
	private Long meetingMainType;
	private Long tehsilId;
	private Long stateId;
	private List<SessionVO> sessionVOList=new ArrayList<SessionVO>(0);
	private List<Long> tdpCadreIds;
	
	 
	 private List<Long> sessionTypeId= new ArrayList<Long>(0);
	 private List<String> startTimeList = new ArrayList<String>(0);
	 private List<String> endTimeList = new ArrayList<String>(0);
	 private List<String> lateTimeList = new ArrayList<String>(0);
	    
	
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public Long getMeetingMainType() {
		return meetingMainType;
	}
	public void setMeetingMainType(Long meetingMainType) {
		this.meetingMainType = meetingMainType;
	}
	public Long getSessionId() {
		return sessionId;
	}
	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}
	public String getTeshilName() {
		return teshilName;
	}
	public void setTeshilName(String teshilName) {
		this.teshilName = teshilName;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public Long getMandalId() {
		return mandalId;
	}
	public void setMandalId(Long mandalId) {
		this.mandalId = mandalId;
	}
	public Long getVillageId() {
		return villageId;
	}
	public void setVillageId(Long villageId) {
		this.villageId = villageId;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public Double getInviteeAttendedPerc() {
		return inviteeAttendedPerc;
	}
	public void setInviteeAttendedPerc(Double inviteeAttendedPerc) {
		this.inviteeAttendedPerc = inviteeAttendedPerc;
	}
	public Double getInviteeAbscentPerc() {
		return inviteeAbscentPerc;
	}
	public void setInviteeAbscentPerc(Double inviteeAbscentPerc) {
		this.inviteeAbscentPerc = inviteeAbscentPerc;
	}
	public Double getNonInviteePerc() {
		return nonInviteePerc;
	}
	public void setNonInviteePerc(Double nonInviteePerc) {
		this.nonInviteePerc = nonInviteePerc;
	}
	public Long getInviteeAttendedCount() {
		return inviteeAttendedCount;
	}
	public void setInviteeAttendedCount(Long inviteeAttendedCount) {
		this.inviteeAttendedCount = inviteeAttendedCount;
	}
	public List<Long> getAbsentList() {
		return absentList;
	}
	public void setAbsentList(List<Long> absentList) {
		this.absentList = absentList;
	}
	public List<Long> getAttendedList() {
		return attendedList;
	}
	public void setAttendedList(List<Long> attendedList) {
		this.attendedList = attendedList;
	}
	public List<Long> getInvitedList() {
		return invitedList;
	}
	public void setInvitedList(List<Long> invitedList) {
		this.invitedList = invitedList;
	}
	public List<Long> getInviteeAttendedList() {
		return inviteeAttendedList;
	}
	public void setInviteeAttendedList(List<Long> inviteeAttendedList) {
		this.inviteeAttendedList = inviteeAttendedList;
	}
	public List<Long> getNonInviteeAttendedList() {
		return nonInviteeAttendedList;
	}
	public void setNonInviteeAttendedList(List<Long> nonInviteeAttendedList) {
		this.nonInviteeAttendedList = nonInviteeAttendedList;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Long getConductedCount() {
		return conductedCount;
	}
	public void setConductedCount(Long conductedCount) {
		this.conductedCount = conductedCount;
	}
	public Long getNotConductedCount() {
		return notConductedCount;
	}
	public void setNotConductedCount(Long notConductedCount) {
		this.notConductedCount = notConductedCount;
	}
	public List<PartyMeetingsDataVO> getSessionList() {
		return sessionList;
	}
	public void setSessionList(List<PartyMeetingsDataVO> sessionList) {
		this.sessionList = sessionList;
	}
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public List<PartyMeetingVO> getDocsList() {
		return docsList;
	}
	public void setDocsList(List<PartyMeetingVO> docsList) {
		this.docsList = docsList;
	}
	public List<String> getMinutes() {
		return minutes;
	}
	public void setMinutes(List<String> minutes) {
		this.minutes = minutes;
	}
	public List<String> getAtrPoints() {
		return atrPoints;
	}
	public void setAtrPoints(List<String> atrPoints) {
		this.atrPoints = atrPoints;
	}
	
	public List<CallTrackingVO> getMeetingDocs() {
		return meetingDocs;
	}
	public void setMeetingDocs(List<CallTrackingVO> meetingDocs) {
		this.meetingDocs = meetingDocs;
	}
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
	public Long getPartyMeetingId() {
		return partyMeetingId;
	}
	public void setPartyMeetingId(Long partyMeetingId) {
		this.partyMeetingId = partyMeetingId;
	}
	public String getPartyMeetingName() {
		return partyMeetingName;
	}
	public void setPartyMeetingName(String partyMeetingName) {
		this.partyMeetingName = partyMeetingName;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public List<String> getDocmentsList() {
		return docmentsList;
	}
	public void setDocmentsList(List<String> docmentsList) {
		this.docmentsList = docmentsList;
	}
	public String getDistName() {
		return distName;
	}
	public void setDistName(String distName) {
		this.distName = distName;
	}
	public String getConstNmae() {
		return constNmae;
	}
	public void setConstNmae(String constNmae) {
		this.constNmae = constNmae;
	}
	public String getMeetingTime() {
		return meetingTime;
	}
	public void setMeetingTime(String meetingTime) {
		this.meetingTime = meetingTime;
	}
	public String getPartyInvaitedChecking() {
		return partyInvaitedChecking;
	}
	public void setPartyInvaitedChecking(String partyInvaitedChecking) {
		this.partyInvaitedChecking = partyInvaitedChecking;
	}
	public void setTehsilId(Long tehsilId) {
		this.tehsilId = tehsilId;
	}
	public Long getTehsilId() {
		return tehsilId;
	}
	public List<SessionVO> getSessionVOList() {
		return sessionVOList;
	}
	public void setSessionVOList(List<SessionVO> sessionVOList) {
		this.sessionVOList = sessionVOList;
	}
	public List<Long> getTdpCadreIds() {
		return tdpCadreIds;
	}
	public void setTdpCadreIds(List<Long> tdpCadreIds) {
		this.tdpCadreIds = tdpCadreIds;
	}
	public List<Long> getSessionTypeId() {
		return sessionTypeId;
	}
	public void setSessionTypeId(List<Long> sessionTypeId) {
		this.sessionTypeId = sessionTypeId;
	}
	public List<String> getStartTimeList() {
		return startTimeList;
	}
	public void setStartTimeList(List<String> startTimeList) {
		this.startTimeList = startTimeList;
	}
	public List<String> getEndTimeList() {
		return endTimeList;
	}
	public void setEndTimeList(List<String> endTimeList) {
		this.endTimeList = endTimeList;
	}
	public List<String> getLateTimeList() {
		return lateTimeList;
	}
	public void setLateTimeList(List<String> lateTimeList) {
		this.lateTimeList = lateTimeList;
	}

	
	
}
