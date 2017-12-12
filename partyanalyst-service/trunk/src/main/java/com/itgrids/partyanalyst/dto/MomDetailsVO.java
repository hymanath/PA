package com.itgrids.partyanalyst.dto;

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
	
	private List<MomDetailsVO> commentList;
	private List<String> documentList;
	
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
	
	
	
}
