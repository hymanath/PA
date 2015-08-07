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
	
	private Long totalAssignedCount=0l;
	private Long dialedCallsCount;
	private Long todayAllocatedCalls=0l;
	
	
	private List<TrainingCampScheduleVO> trainingCampVOList;
	private List<TrainingCampScheduleVO> trainingCampScheduleVOList;
	
	
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
	public Long getTotalAssignedCount() {
		return totalAssignedCount;
	}
	public void setTotalAssignedCount(Long totalAssignedCount) {
		this.totalAssignedCount = totalAssignedCount;
	}
	public List<TrainingCampScheduleVO> getTrainingCampScheduleVOList() {
		return trainingCampScheduleVOList;
	}
	public void setTrainingCampScheduleVOList(
			List<TrainingCampScheduleVO> trainingCampScheduleVOList) {
		this.trainingCampScheduleVOList = trainingCampScheduleVOList;
	}
	public Long getDialedCallsCount() {
		return dialedCallsCount;
	}
	public void setDialedCallsCount(Long dialedCallsCount) {
		this.dialedCallsCount = dialedCallsCount;
	}
	public Long getTodayAllocatedCalls() {
		return todayAllocatedCalls;
	}
	public void setTodayAllocatedCalls(Long todayAllocatedCalls) {
		this.todayAllocatedCalls = todayAllocatedCalls;
	}
	
	
}
