package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class TrainingCampVO implements java.io.Serializable{

	private Long id;
	private String name;
	private String trainingCampName;
	private String trainerName;
	private String scheduledDate;
	private Long userBusyCount;
	private Long busyCount;
	private Long acceptedCount;
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
	private List<SimpleVO> simpleVoList = new ArrayList<SimpleVO>();
	private Long feedBackProgramCount;
	private Long leaderFeedBackCount;
	private Long feedBackQuizCount;
	private Double attendedPercentage;
	
	
	private Long schdlStatusId;
	private String schdlStatus;
	private Long othersCount;
	private Long conformLaterCount;
	private Long batchId;
	private Long allocatedCalls;
	private Long dialedCount;
	private Long unDialedCount;
	private String status;
	private Long laterCount;
	private List<TrainingCampVO> batchDetails;
	private List<TrainingCampVO> campDetails;
	private List<TrainingCampVO> programDetails;
	private List<TrainingCampVO> scheduleDetails;
	private List<TrainingCampVO> committeLvlDetails;
	private List<TrainingCampVO> roles;
	private String batchName;
	private Long campId;
	private Long programId;
	private String programName;
	private String campName;
	private Long scheduleId;
	private String scheduleCode;
	
	private Long completedMemberCount=0l;
	private Long runningMemberCount=0l;
	private Long upCommingMemberCount=0l;
	
	private Long completedMemberAttendeeCount=0l;
	
	private int totalTrainingCenters=0;
	private String batchDates;
	private String scheduleDates;
	
	private List<Long> completedBatchIds;
	private List<Long> runningBatchIds;
	private List<Long> upComingBatchIds;
	private Long cmpBatchCount=1l;
	private List<TrainingCampVO> programWiseDetails;	
	
	private Long completedBatches=0l;
	private Long runningBatches=0l;
	private Long upComingBatches =0l;
	private Long memberCount = 0l;
	
	private Long memberCountAttendee = 0l;
	
	private List<TrainingCampVO> completedDetails=new ArrayList<TrainingCampVO>(0);
	private List<TrainingCampVO> runningDetails=new ArrayList<TrainingCampVO>(0);
	private List<TrainingCampVO> upcomingDetails=new ArrayList<TrainingCampVO>(0);
	private Long runningAttendenceMemberCount=0l;
	private Long wrongMobileNo;
	private Long invalidMobileNo;
	private Long switchOffCount;
	
	private Long completedAttendenceNonIn;
	private Long runningAttendenceNonIn;
	private Long memberCountNonIn = 0l;
	
	private Long thirdId;
	private String thirdName;
	private Long districtCount=0l;
	private Long committeeLvlId;
	private String committeeLvlName;
	private Long roleId;
	private String role;
	
	public Double getAttendedPercentage() {
		return attendedPercentage;
	}
	public void setAttendedPercentage(Double attendedPercentage) {
		this.attendedPercentage = attendedPercentage;
	}
	public Long getFeedBackProgramCount() {
		return feedBackProgramCount;
	}
	public void setFeedBackProgramCount(Long feedBackProgramCount) {
		this.feedBackProgramCount = feedBackProgramCount;
	}
	public Long getLeaderFeedBackCount() {
		return leaderFeedBackCount;
	}
	public void setLeaderFeedBackCount(Long leaderFeedBackCount) {
		this.leaderFeedBackCount = leaderFeedBackCount;
	}
	public Long getFeedBackQuizCount() {
		return feedBackQuizCount;
	}
	public void setFeedBackQuizCount(Long feedBackQuizCount) {
		this.feedBackQuizCount = feedBackQuizCount;
	}
	public Long getDistrictCount() {
		return districtCount;
	}
	public void setDistrictCount(Long districtCount) {
		this.districtCount = districtCount;
	}
	public Long getThirdId() {
		return thirdId;
	}
	public void setThirdId(Long thirdId) {
		this.thirdId = thirdId;
	}	
	public String getThirdName() {
		return thirdName;
	}
	public void setThirdName(String thirdName) {
		this.thirdName = thirdName;
	}
	public Long getMemberCountNonIn() {
		return memberCountNonIn;
	}
	public void setMemberCountNonIn(Long memberCountNonIn) {
		this.memberCountNonIn = memberCountNonIn;
	}
	public Long getCompletedAttendenceNonIn() {
		return completedAttendenceNonIn;
	}
	public void setCompletedAttendenceNonIn(Long completedAttendenceNonIn) {
		this.completedAttendenceNonIn = completedAttendenceNonIn;
	}
	public Long getRunningAttendenceNonIn() {
		return runningAttendenceNonIn;
	}
	public void setRunningAttendenceNonIn(Long runningAttendenceNonIn) {
		this.runningAttendenceNonIn = runningAttendenceNonIn;
	}
	public List<SimpleVO> getSimpleVoList() {
		return simpleVoList;
	}
	public void setSimpleVoList(List<SimpleVO> simpleVoList) {
		this.simpleVoList = simpleVoList;
	}
	public Long getMemberCountAttendee() {
		return memberCountAttendee;
	}
	public void setMemberCountAttendee(Long memberCountAttendee) {
		this.memberCountAttendee = memberCountAttendee;
	}
	public Long getCompletedMemberAttendeeCount() {
		return completedMemberAttendeeCount;
	}
	public void setCompletedMemberAttendeeCount(Long completedMemberAttendeeCount) {
		this.completedMemberAttendeeCount = completedMemberAttendeeCount;
	}
	public Long getUserBusyCount() {
		return userBusyCount;
	}
	public void setUserBusyCount(Long userBusyCount) {
		this.userBusyCount = userBusyCount;
	}
	public Long getSwitchOffCount() {
		return switchOffCount;
	}
	public void setSwitchOffCount(Long switchOffCount) {
		this.switchOffCount = switchOffCount;
	}
	public Long getInvalidMobileNo() {
		return invalidMobileNo;
	}
	public void setInvalidMobileNo(Long invalidMobileNo) {
		this.invalidMobileNo = invalidMobileNo;
	}
	public Long getWrongMobileNo() {
		return wrongMobileNo;
	}
	public void setWrongMobileNo(Long wrongMobileNo) {
		this.wrongMobileNo = wrongMobileNo;
	}
	public Long getLaterCount() {
		return laterCount;
	}
	public void setLaterCount(Long laterCount) {
		this.laterCount = laterCount;
	}
	public Long getRunningAttendenceMemberCount() {
		return runningAttendenceMemberCount;
	}
	public void setRunningAttendenceMemberCount(Long runningAttendenceMemberCount) {
		this.runningAttendenceMemberCount = runningAttendenceMemberCount;
	}
	public List<TrainingCampVO> getCompletedDetails() {
		return completedDetails;
	}
	public void setCompletedDetails(List<TrainingCampVO> completedDetails) {
		this.completedDetails = completedDetails;
	}
	public List<TrainingCampVO> getRunningDetails() {
		return runningDetails;
	}
	public void setRunningDetails(List<TrainingCampVO> runningDetails) {
		this.runningDetails = runningDetails;
	}
	public List<TrainingCampVO> getUpcomingDetails() {
		return upcomingDetails;
	}
	public void setUpcomingDetails(List<TrainingCampVO> upcomingDetails) {
		this.upcomingDetails = upcomingDetails;
	}
	public Long getMemberCount() {
		return memberCount;
	}
	public void setMemberCount(Long memberCount) {
		this.memberCount = memberCount;
	}
	public Long getCompletedBatches() {
		return completedBatches;
	}
	public void setCompletedBatches(Long completedBatches) {
		this.completedBatches = completedBatches;
	}
	public Long getRunningBatches() {
		return runningBatches;
	}
	public void setRunningBatches(Long runningBatches) {
		this.runningBatches = runningBatches;
	}
	public Long getUpComingBatches() {
		return upComingBatches;
	}
	public void setUpComingBatches(Long upComingBatches) {
		this.upComingBatches = upComingBatches;
	}
	public List<TrainingCampVO> getProgramWiseDetails() {
		return programWiseDetails;
	}
	public void setProgramWiseDetails(List<TrainingCampVO> programWiseDetails) {
		this.programWiseDetails = programWiseDetails;
	}
	public Long getCmpBatchCount() {
		return cmpBatchCount;
	}
	public void setCmpBatchCount(Long cmpBatchCount) {
		this.cmpBatchCount = cmpBatchCount;
	}
	public List<Long> getCompletedBatchIds() {
		return completedBatchIds;
	}
	public void setCompletedBatchIds(List<Long> completedBatchIds) {
		this.completedBatchIds = completedBatchIds;
	}
	public List<Long> getRunningBatchIds() {
		return runningBatchIds;
	}
	public void setRunningBatchIds(List<Long> runningBatchIds) {
		this.runningBatchIds = runningBatchIds;
	}
	public List<Long> getUpComingBatchIds() {
		return upComingBatchIds;
	}
	public void setUpComingBatchIds(List<Long> upComingBatchIds) {
		this.upComingBatchIds = upComingBatchIds;
	}
	public String getBatchDates() {
		return batchDates;
	}
	public void setBatchDates(String batchDates) {
		this.batchDates = batchDates;
	}
	public String getScheduleDates() {
		return scheduleDates;
	}
	public void setScheduleDates(String scheduleDates) {
		this.scheduleDates = scheduleDates;
	}
	public Long getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(Long scheduleId) {
		this.scheduleId = scheduleId;
	}
	public String getScheduleCode() {
		return scheduleCode;
	}
	public void setScheduleCode(String scheduleCode) {
		this.scheduleCode = scheduleCode;
	}
	public List<TrainingCampVO> getScheduleDetails() {
		return scheduleDetails;
	}
	public void setScheduleDetails(List<TrainingCampVO> scheduleDetails) {
		this.scheduleDetails = scheduleDetails;
	}
	public int getTotalTrainingCenters() {
		return totalTrainingCenters;
	}
	public void setTotalTrainingCenters(int totalTrainingCenters) {
		this.totalTrainingCenters = totalTrainingCenters;
	}
	public Long getCompletedMemberCount() {
		return completedMemberCount;
	}
	public void setCompletedMemberCount(Long completedMemberCount) {
		this.completedMemberCount = completedMemberCount;
	}
	public Long getRunningMemberCount() {
		return runningMemberCount;
	}
	public void setRunningMemberCount(Long runningMemberCount) {
		this.runningMemberCount = runningMemberCount;
	}
	public Long getUpCommingMemberCount() {
		return upCommingMemberCount;
	}
	public void setUpCommingMemberCount(Long upCommingMemberCount) {
		this.upCommingMemberCount = upCommingMemberCount;
	}
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public String getCampName() {
		return campName;
	}
	public void setCampName(String campName) {
		this.campName = campName;
	}
	public String getBatchName() {
		return batchName;
	}
	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}
	public Long getCampId() {
		return campId;
	}
	public void setCampId(Long campId) {
		this.campId = campId;
	}
	public Long getProgramId() {
		return programId;
	}
	public void setProgramId(Long programId) {
		this.programId = programId;
	}
	public List<TrainingCampVO> getCampDetails() {
		return campDetails;
	}
	public void setCampDetails(List<TrainingCampVO> campDetails) {
		this.campDetails = campDetails;
	}
	
	public List<TrainingCampVO> getProgramDetails() {
		return programDetails;
	}
	public void setProgramDetails(List<TrainingCampVO> programDetails) {
		this.programDetails = programDetails;
	}
	public List<TrainingCampVO> getBatchDetails() {
		return batchDetails;
	}
	public void setBatchDetails(List<TrainingCampVO> batchDetails) {
		this.batchDetails = batchDetails;
	}
	public Long getBusyCount() {
		return busyCount;
	}
	public void setBusyCount(Long busyCount) {
		this.busyCount = busyCount;
	}
	public Long getAcceptedCount() {
		return acceptedCount;
	}
	public void setAcceptedCount(Long acceptedCount) {
		this.acceptedCount = acceptedCount;
	}
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
	public List<TrainingCampVO> getCommitteLvlDetails() {
		return committeLvlDetails;
	}
	public void setCommitteLvlDetails(List<TrainingCampVO> committeLvlDetails) {
		this.committeLvlDetails = committeLvlDetails;
	}
	public List<TrainingCampVO> getRoles() {
		return roles;
	}
	public void setRoles(List<TrainingCampVO> roles) {
		this.roles = roles;
	}
	public Long getCommitteeLvlId() {
		return committeeLvlId;
	}
	public void setCommitteeLvlId(Long committeeLvlId) {
		this.committeeLvlId = committeeLvlId;
	}
	public String getCommitteeLvlName() {
		return committeeLvlName;
	}
	public void setCommitteeLvlName(String committeeLvlName) {
		this.committeeLvlName = committeeLvlName;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
	
	
}
