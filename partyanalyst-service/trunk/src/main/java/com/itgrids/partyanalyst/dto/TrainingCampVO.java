package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class TrainingCampVO implements java.io.Serializable{

	private Long id;
	private String name;
	private String trainingCampName;
	private String trainerName;
	private String scheduledDate;
	private Long interestedCount;
	private Long notInterestedCount;
	private Long nextBatchInterestedCount;
	private Long assignedCount;
	private Long availableMembersCount;
	private Long totalProgrammesCount;
	private Long dialedCallsCount;
	private Long todayAllocatedCount;
	private Long allocatedCallsCount;
	private Long filledCount;
	private Long scheduleConfirmationCount;
	private Long batchConfirmationCount;
	private Long assignedCalls;
	private Long completedCalls;
	private Long pendingCalls;
	private String scheduleName;
	private String memberStatus;
	private String startDateStr;
	private String endDateStr;
	private Long locationTypeId;
	private List<TrainingCampVO> trainingCampVOList = new ArrayList<TrainingCampVO>(0);
	
	private Long schdlStatusId;
	private String schdlStatus;
	private Long othersCount;
	private Long conformLaterCount;
	private Long batchId;
	private Long allocatedCalls;
	private Long dialedCount;
	private Long unDialedCount;
	private String status;
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getAllocatedCalls() {
		return allocatedCalls;
	}
	public void setAllocatedCalls(Long allocatedCalls) {
		this.allocatedCalls = allocatedCalls;
	}
	public Long getDialedCount() {
		return dialedCount;
	}
	public void setDialedCount(Long dialedCount) {
		this.dialedCount = dialedCount;
	}
	public Long getUnDialedCount() {
		return unDialedCount;
	}
	public void setUnDialedCount(Long unDialedCount) {
		this.unDialedCount = unDialedCount;
	}
	public Long getLocationTypeId() {
		return locationTypeId;
	}
	public void setLocationTypeId(Long locationTypeId) {
		this.locationTypeId = locationTypeId;
	}
	public Long getBatchId() {
		return batchId;
	}
	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}
	public Long getConformLaterCount() {
		return conformLaterCount;
	}
	public void setConformLaterCount(Long conformLaterCount) {
		this.conformLaterCount = conformLaterCount;
	}
	public Long getOthersCount() {
		return othersCount;
	}
	public void setOthersCount(Long othersCount) {
		this.othersCount = othersCount;
	}
	public Long getSchdlStatusId() {
		return schdlStatusId;
	}
	public void setSchdlStatusId(Long schdlStatusId) {
		this.schdlStatusId = schdlStatusId;
	}
	public String getSchdlStatus() {
		return schdlStatus;
	}
	public void setSchdlStatus(String schdlStatus) {
		this.schdlStatus = schdlStatus;
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
	public String getMemberStatus() {
		return memberStatus;
	}
	public void setMemberStatus(String memberStatus) {
		this.memberStatus = memberStatus;
	}
	public String getScheduleName() {
		return scheduleName;
	}
	public void setScheduleName(String scheduleName) {
		this.scheduleName = scheduleName;
	}
	public List<TrainingCampVO> getTrainingCampVOList() {
		return trainingCampVOList;
	}
	public void setTrainingCampVOList(List<TrainingCampVO> trainingCampVOList) {
		this.trainingCampVOList = trainingCampVOList;
	}
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getTrainingCampName() {
		return trainingCampName;
	}
	public String getTrainerName() {
		return trainerName;
	}
	public String getScheduledDate() {
		return scheduledDate;
	}
	public Long getInterestedCount() {
		return interestedCount;
	}
	public Long getNotInterestedCount() {
		return notInterestedCount;
	}
	public Long getNextBatchInterestedCount() {
		return nextBatchInterestedCount;
	}
	public Long getAssignedCount() {
		return assignedCount;
	}
	public Long getAvailableMembersCount() {
		return availableMembersCount;
	}
	public Long getTotalProgrammesCount() {
		return totalProgrammesCount;
	}
	public Long getDialedCallsCount() {
		return dialedCallsCount;
	}
	public Long getTodayAllocatedCount() {
		return todayAllocatedCount;
	}
	public Long getAllocatedCallsCount() {
		return allocatedCallsCount;
	}
	public Long getFilledCount() {
		return filledCount;
	}
	public Long getScheduleConfirmationCount() {
		return scheduleConfirmationCount;
	}
	public Long getBatchConfirmationCount() {
		return batchConfirmationCount;
	}
	public Long getAssignedCalls() {
		return assignedCalls;
	}
	public Long getCompletedCalls() {
		return completedCalls;
	}
	public Long getPendingCalls() {
		return pendingCalls;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setTrainingCampName(String trainingCampName) {
		this.trainingCampName = trainingCampName;
	}
	public void setTrainerName(String trainerName) {
		this.trainerName = trainerName;
	}
	public void setScheduledDate(String scheduledDate) {
		this.scheduledDate = scheduledDate;
	}
	public void setInterestedCount(Long interestedCount) {
		this.interestedCount = interestedCount;
	}
	public void setNotInterestedCount(Long notInterestedCount) {
		this.notInterestedCount = notInterestedCount;
	}
	public void setNextBatchInterestedCount(Long nextBatchInterestedCount) {
		this.nextBatchInterestedCount = nextBatchInterestedCount;
	}
	public void setAssignedCount(Long assignedCount) {
		this.assignedCount = assignedCount;
	}
	public void setAvailableMembersCount(Long availableMembersCount) {
		this.availableMembersCount = availableMembersCount;
	}
	public void setTotalProgrammesCount(Long totalProgrammesCount) {
		this.totalProgrammesCount = totalProgrammesCount;
	}
	public void setDialedCallsCount(Long dialedCallsCount) {
		this.dialedCallsCount = dialedCallsCount;
	}
	public void setTodayAllocatedCount(Long todayAllocatedCount) {
		this.todayAllocatedCount = todayAllocatedCount;
	}
	public void setAllocatedCallsCount(Long allocatedCallsCount) {
		this.allocatedCallsCount = allocatedCallsCount;
	}
	public void setFilledCount(Long filledCount) {
		this.filledCount = filledCount;
	}
	public void setScheduleConfirmationCount(Long scheduleConfirmationCount) {
		this.scheduleConfirmationCount = scheduleConfirmationCount;
	}
	public void setBatchConfirmationCount(Long batchConfirmationCount) {
		this.batchConfirmationCount = batchConfirmationCount;
	}
	public void setAssignedCalls(Long assignedCalls) {
		this.assignedCalls = assignedCalls;
	}
	public void setCompletedCalls(Long completedCalls) {
		this.completedCalls = completedCalls;
	}
	public void setPendingCalls(Long pendingCalls) {
		this.pendingCalls = pendingCalls;
	}
	
	
	
	
}
