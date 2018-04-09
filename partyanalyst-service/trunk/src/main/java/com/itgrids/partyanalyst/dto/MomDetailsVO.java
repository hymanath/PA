package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class MomDetailsVO {

	private Long meetingId;
	private String momPoints;
	private Long momPointsId;
	private String meetingName;
	private Long createdLocationScopeId;
	private Long createdLocationScopeValue;
	private Long assignedLocationScopeId;
	private Long assignedLocationScopeValue;
	private String createdLocation;
	private String assignedLocation;
	private String status;
	private String priority;
	private String date;
	private String comment;
	private String isEditable;
	private Long createdAddressId;
	
	private List<MomDetailsVO> commentList;
	private List<String> documentList;
	private Long locationLevel;
	
	private Long meetingLevelId;
	private String meetingLevel;
	private String searchType;
	public String startDate;
	public String endDate;
	public List<Long> partyMeetingLevelIdsList = new ArrayList<Long>(0);
	public Long locationLevelId;
	public List<Long> locationValuesList = new ArrayList<Long>(0);
	public Long sourceTypeId;
	private String sourceName;
	public String momType; // isactionable or not or general
	public List<MomDetailsVO> minutesList = new ArrayList<MomDetailsVO>(0);
	public List<KeyValueVO> filesList = new ArrayList<KeyValueVO>(0);
	public AddressVO addressVO;

	public List<KeyValueVO> getFilesList() {
		return filesList;
	}
	public void setFilesList(List<KeyValueVO> filesList) {
		this.filesList = filesList;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public List<MomDetailsVO> getMinutesList() {
		return minutesList;
	}
	public void setMinutesList(List<MomDetailsVO> minutesList) {
		this.minutesList = minutesList;
	}
	public AddressVO getAddressVO() {
		return addressVO;
	}
	public void setAddressVO(AddressVO addressVO) {
		this.addressVO = addressVO;
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
	public List<Long> getPartyMeetingLevelIdsList() {
		return partyMeetingLevelIdsList;
	}
	public void setPartyMeetingLevelIdsList(List<Long> partyMeetingLevelIdsList) {
		this.partyMeetingLevelIdsList = partyMeetingLevelIdsList;
	}
	public Long getLocationLevelId() {
		return locationLevelId;
	}
	public void setLocationLevelId(Long locationLevelId) {
		this.locationLevelId = locationLevelId;
	}
	public List<Long> getLocationValuesList() {
		return locationValuesList;
	}
	public void setLocationValuesList(List<Long> locationValuesList) {
		this.locationValuesList = locationValuesList;
	}
	public Long getSourceTypeId() {
		return sourceTypeId;
	}
	public void setSourceTypeId(Long sourceTypeId) {
		this.sourceTypeId = sourceTypeId;
	}
	public String getMomType() {
		return momType;
	}
	public void setMomType(String momType) {
		this.momType = momType;
	}
	public String getMomPoints() {
		return momPoints;
	}
	public void setMomPoints(String momPoints) {
		this.momPoints = momPoints;
	}
	public String getCreatedLocation() {
		return createdLocation;
	}
	public void setCreatedLocation(String createdLocation) {
		this.createdLocation = createdLocation;
	}
	public String getAssignedLocation() {
		return assignedLocation;
	}
	public void setAssignedLocation(String assignedLocation) {
		this.assignedLocation = assignedLocation;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public List<MomDetailsVO> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<MomDetailsVO> commentList) {
		this.commentList = commentList;
	}
	
	public List<String> getDocumentList() {
		return documentList;
	}
	public void setDocumentList(List<String> documentList) {
		this.documentList = documentList;
	}
	public String getMeetingName() {
		return meetingName;
	}
	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Long getMeetingId() {
		return meetingId;
	}
	public void setMeetingId(Long meetingId) {
		this.meetingId = meetingId;
	}
	public String getIsEditable() {
		return isEditable;
	}
	public void setIsEditable(String isEditable) {
		this.isEditable = isEditable;
	}
	public Long getMomPointsId() {
		return momPointsId;
	}
	public void setMomPointsId(Long momPointsId) {
		this.momPointsId = momPointsId;
	}
	public Long getCreatedLocationScopeId() {
		return createdLocationScopeId;
	}
	public void setCreatedLocationScopeId(Long createdLocationScopeId) {
		this.createdLocationScopeId = createdLocationScopeId;
	}
	public Long getCreatedLocationScopeValue() {
		return createdLocationScopeValue;
	}
	public void setCreatedLocationScopeValue(Long createdLocationScopeValue) {
		this.createdLocationScopeValue = createdLocationScopeValue;
	}
	public Long getAssignedLocationScopeId() {
		return assignedLocationScopeId;
	}
	public void setAssignedLocationScopeId(Long assignedLocationScopeId) {
		this.assignedLocationScopeId = assignedLocationScopeId;
	}
	public Long getAssignedLocationScopeValue() {
		return assignedLocationScopeValue;
	}
	public void setAssignedLocationScopeValue(Long assignedLocationScopeValue) {
		this.assignedLocationScopeValue = assignedLocationScopeValue;
	}
	public Long getLocationLevel() {
		return locationLevel;
	}
	public void setLocationLevel(Long locationLevel) {
		this.locationLevel = locationLevel;
	}
	public Long getCreatedAddressId() {
		return createdAddressId;
	}
	public void setCreatedAddressId(Long createdAddressId) {
		this.createdAddressId = createdAddressId;
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
	public String getSourceName() {
		return sourceName;
	}
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
}
