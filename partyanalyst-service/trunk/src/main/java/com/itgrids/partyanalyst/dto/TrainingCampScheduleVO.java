package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class TrainingCampScheduleVO implements Serializable{
	
	private Long id;
	private String name;
	private Long count=0l;
	private Long totalCount=0l;
	private Long statusId;
	private String status;
	
	private Long assignedCallsCount=0l;
	private Long completedCallsCount=0l;
	private Long pendingCallsCount=0l;
	
	private Long attendingCount=0l;
	private Long invitedCount=0l;
	private Long notNowCount=0l;
	private Long notInterestedCount=0l;
	private Long callBackCount=0l;
	private Long wrongMobileNoCount=0l;
	private Long invalidMobileNoCount=0l;
	
	public Long getNotInterestedCount() {
		return notInterestedCount;
	}
	public void setNotInterestedCount(Long notInterestedCount) {
		this.notInterestedCount = notInterestedCount;
	}
	
	private List<TrainingCampScheduleVO> trainingCampVOList;
	
	
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
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<TrainingCampScheduleVO> getTrainingCampVOList() {
		return trainingCampVOList;
	}
	public void setTrainingCampVOList(List<TrainingCampScheduleVO> trainingCampVOList) {
		this.trainingCampVOList = trainingCampVOList;
	}
	public Long getAssignedCallsCount() {
		return assignedCallsCount;
	}
	public void setAssignedCallsCount(Long assignedCallsCount) {
		this.assignedCallsCount = assignedCallsCount;
	}
	public Long getCompletedCallsCount() {
		return completedCallsCount;
	}
	public void setCompletedCallsCount(Long completedCallsCount) {
		this.completedCallsCount = completedCallsCount;
	}
	public Long getPendingCallsCount() {
		return pendingCallsCount;
	}
	public void setPendingCallsCount(Long pendingCallsCount) {
		this.pendingCallsCount = pendingCallsCount;
	}
	public Long getAttendingCount() {
		return attendingCount;
	}
	public void setAttendingCount(Long attendingCount) {
		this.attendingCount = attendingCount;
	}
	public Long getInvitedCount() {
		return invitedCount;
	}
	public void setInvitedCount(Long invitedCount) {
		this.invitedCount = invitedCount;
	}
	public Long getNotNowCount() {
		return notNowCount;
	}
	public void setNotNowCount(Long notNowCount) {
		this.notNowCount = notNowCount;
	}
	public Long getCallBackCount() {
		return callBackCount;
	}
	public void setCallBackCount(Long callBackCount) {
		this.callBackCount = callBackCount;
	}
	public Long getWrongMobileNoCount() {
		return wrongMobileNoCount;
	}
	public void setWrongMobileNoCount(Long wrongMobileNoCount) {
		this.wrongMobileNoCount = wrongMobileNoCount;
	}
	public Long getInvalidMobileNoCount() {
		return invalidMobileNoCount;
	}
	public void setInvalidMobileNoCount(Long invalidMobileNoCount) {
		this.invalidMobileNoCount = invalidMobileNoCount;
	}
	
	
	
}
