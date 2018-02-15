package com.itgrids.dto;

import java.util.ArrayList;

import java.util.List;

public class AlertVO implements java.io.Serializable{

	private Long alertTypeId;
	private String alertTypeName;
	private Long severity;
	private Long locationLevelId;
	private Long locationValue;
	private String desc;
	private String candidateId;
	private Long alertImpactId;
	private Long alertSourceId;
	private String color;
	
	private Long stateId;
	private Long districtId;
	private Long constituencyId;
	private Long constituencyNo;
	private Long tehsilId;
	private Long localBodyId;
	private Long panchayatId;
	private Long hamletId;
	private Long wardId;
	
	
	private String state;
	private String district;
	private String constituency;
	private String tehsil;
	private String localBody;
	private String panchayat;
	private String hamlet;
	private String ward;
	private Long alertCallerId;
	
	private Long tdpCadreId;
	private Long id;
	private String name;
	private Long statusId;
	private String status;
	private Long count = 0l;
	private String category;
	private Long categoryId;
	private Long categoryCount = 0l;
	private Long locationId;
	private String locationName;
	
	private List<AlertVO> subList1 = new ArrayList<AlertVO>(0);
	private List<AlertVO> subList2 = new ArrayList<AlertVO>(0);
	private String title;
	private String date1;
	private String date2;
	private Long noOfDays = 0l;
	private String comment;
	private Long alertId;
	private List<String> filePthList = new ArrayList<String>(0);
	private String clarificationRequired;
	private String fileIdList;
	private Double statusPercent = 0.0d;
	private String severityStr;
	private String assignedDate;
	private Double percentage = 0.0d;
	private Long alertCnt = 0l;
	private Long subTaskId;
	private String dueDate;
	private String imageUrl;
	private String createdTime;
	private String address;
	private String mobileNo;
	private Long feedBackStatusId;
	private String deptName;
	private String callCenterSource;
	private String issueType;
	private String issueSubType;
	private String feedbackStatus;
	private String userName;
	private Long smTypeId;
	private String smType;
	private String verifyStatus;
	
	private Long newAlertStatusId;
	private String callerDuplicate;
    private Long proposalAmount=0l;
    private Long approvedAmount = 0l;
    
    private String year;
    private String fromDate;
    private String toDate;
    private Long deptId;
    private List<Long> statusIds = new ArrayList<Long>(0);
    private int startIndex;
	private int endIndex;
	private String type;
	
	private Long satisfiedCount=0l;
	private Long unSatisfiedCount=0l;
	private Long locationCnt=0l;
	private Long statusCount=0l;
	
	private Long notifiedCount=0l;
	private Long actionInProgressCount=0l;
	private List<AlertVO> list = new ArrayList<AlertVO>(0);
	private Long count1 = 0l;
	
	
	public List<AlertVO> getList() {
		return list;
	}
	public void setList(List<AlertVO> list) {
		this.list = list;
	}
	public Long getNotifiedCount() {
		return notifiedCount;
	}
	public void setNotifiedCount(Long notifiedCount) {
		this.notifiedCount = notifiedCount;
	}
	public Long getActionInProgressCount() {
		return actionInProgressCount;
	}
	public void setActionInProgressCount(Long actionInProgressCount) {
		this.actionInProgressCount = actionInProgressCount;
	}
	public String getCallerDuplicate() {
		return callerDuplicate;
	}
	public void setCallerDuplicate(String callerDuplicate) {
		this.callerDuplicate = callerDuplicate;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getConstituency() {
		return constituency;
	}
	public void setConstituency(String constituency) {
		this.constituency = constituency;
	}
	public String getTehsil() {
		return tehsil;
	}
	public void setTehsil(String tehsil) {
		this.tehsil = tehsil;
	}
	public String getLocalBody() {
		return localBody;
	}
	public void setLocalBody(String localBody) {
		this.localBody = localBody;
	}
	public String getPanchayat() {
		return panchayat;
	}
	public void setPanchayat(String panchayat) {
		this.panchayat = panchayat;
	}
	public String getHamlet() {
		return hamlet;
	}
	public void setHamlet(String hamlet) {
		this.hamlet = hamlet;
	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	public String getFeedbackStatus() {
		return feedbackStatus;
	}
	public void setFeedbackStatus(String feedbackStatus) {
		this.feedbackStatus = feedbackStatus;
	}
	public String getIssueSubType() {
		return issueSubType;
	}
	public void setIssueSubType(String issueSubType) {
		this.issueSubType = issueSubType;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getCallCenterSource() {
		return callCenterSource;
	}
	public void setCallCenterSource(String callCenterSource) {
		this.callCenterSource = callCenterSource;
	}
	public String getIssueType() {
		return issueType;
	}
	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public Long getSubTaskId() {
		return subTaskId;
	}
	public void setSubTaskId(Long subTaskId) {
		this.subTaskId = subTaskId;
	}
	private List<Long> deptIdList = new ArrayList<Long>();
	
	public Long getConstituencyNo() {
		return constituencyNo;
	}
	public void setConstituencyNo(Long constituencyNo) {
		this.constituencyNo = constituencyNo;
	}
	public List<Long> getDeptIdList() {
		return deptIdList;
	}
	public void setDeptIdList(List<Long> deptIdList) {
		this.deptIdList = deptIdList;
	}
	public String getAssignedDate() {
		return assignedDate;
	}
	public void setAssignedDate(String assignedDate) {
		this.assignedDate = assignedDate;
	}
	public String getSeverityStr() {
		return severityStr;
	}
	public void setSeverityStr(String severityStr) {
		this.severityStr = severityStr;
	}
	public Long getNoOfDays() {
		return noOfDays;
	}
	public void setNoOfDays(Long noOfDays) {
		this.noOfDays = noOfDays;
	}
	public String getDate1() {
		return date1;
	}
	public void setDate1(String date1) {
		this.date1 = date1;
	}
	public String getDate2() {
		return date2;
	}
	public void setDate2(String date2) {
		this.date2 = date2;
	}
	public String getAlertTypeName() {
		return alertTypeName;
	}
	public void setAlertTypeName(String alertTypeName) {
		this.alertTypeName = alertTypeName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
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
	public Long getTehsilId() {
		return tehsilId;
	}
	public void setTehsilId(Long tehsilId) {
		this.tehsilId = tehsilId;
	}
	public Long getLocalBodyId() {
		return localBodyId;
	}
	public void setLocalBodyId(Long localBodyId) {
		this.localBodyId = localBodyId;
	}
	public Long getPanchayatId() {
		return panchayatId;
	}
	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}
	public Long getWardId() {
		return wardId;
	}
	public void setWardId(Long wardId) {
		this.wardId = wardId;
	}
	
	public Long getAlertImpactId() {
		return alertImpactId;
	}
	public void setAlertImpactId(Long alertImpactId) {
		this.alertImpactId = alertImpactId;
	}
	public Long getAlertTypeId() {
		return alertTypeId;
	}
	public void setAlertTypeId(Long alertTypeId) {
		this.alertTypeId = alertTypeId;
	}
	public Long getSeverity() {
		return severity;
	}
	public void setSeverity(Long severity) {
		this.severity = severity;
	}
	public Long getLocationLevelId() {
		return locationLevelId;
	}
	public void setLocationLevelId(Long locationLevelId) {
		this.locationLevelId = locationLevelId;
	}
	public Long getLocationValue() {
		return locationValue;
	}
	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}
	public Long getAlertSourceId() {
		return alertSourceId;
	}
	public void setAlertSourceId(Long alertSourceId) {
		this.alertSourceId = alertSourceId;
	}
	public Long getCount() {
		return count;
	}
	public Long getSatisfiedCount() {
		return satisfiedCount;
	}
	public void setSatisfiedCount(Long satisfiedCount) {
		this.satisfiedCount = satisfiedCount;
	}
	public Long getUnSatisfiedCount() {
		return unSatisfiedCount;
	}
	public void setUnSatisfiedCount(Long unSatisfiedCount) {
		this.unSatisfiedCount = unSatisfiedCount;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public List<AlertVO> getSubList1() {
		return subList1;
	}
	public void setSubList1(List<AlertVO> subList1) {
		this.subList1 = subList1;
	}
	public Long getCategoryCount() {
		return categoryCount;
	}
	public void setCategoryCount(Long categoryCount) {
		this.categoryCount = categoryCount;
	}
	public List<AlertVO> getSubList2() {
		return subList2;
	}
	public void setSubList2(List<AlertVO> subList2) {
		this.subList2 = subList2;
	}
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Long getAlertId() {
		return alertId;
	}
	public void setAlertId(Long alertId) {
		this.alertId = alertId;
	}
	public List<String> getFilePthList() {
		return filePthList;
	}
	public void setFilePthList(List<String> filePthList) {
		this.filePthList = filePthList;
	}
	public String getClarificationRequired() {  
		return clarificationRequired;
	}
	public void setClarificationRequired(String clarificationRequired) {
		this.clarificationRequired = clarificationRequired;
	}
	public String getFileIdList() {
		return fileIdList;
	}
	public void setFileIdList(String fileIdList) {
		this.fileIdList = fileIdList;
	}
	public Double getStatusPercent() {
		return statusPercent;
	}
	public void setStatusPercent(Double statusPercent) {
		this.statusPercent = statusPercent;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPercentage() {
		return percentage;
	}
	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}
	public Long getAlertCnt() {
		return alertCnt;
	}
	public void setAlertCnt(Long alertCnt) {
		this.alertCnt = alertCnt;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public Long getFeedBackStatusId() {
		return feedBackStatusId;
	}
	public void setFeedBackStatusId(Long feedBackStatusId) {
		this.feedBackStatusId = feedBackStatusId;
	}
	public Long getSmTypeId() {
		return smTypeId;
	}
	public void setSmTypeId(Long smTypeId) {
		this.smTypeId = smTypeId;
	}
	public String getSmType() {
		return smType;
	}
	public void setSmType(String smType) {
		this.smType = smType;
	}
	public Long getNewAlertStatusId() {
		return newAlertStatusId;
	}
	public void setNewAlertStatusId(Long newAlertStatusId) {
		this.newAlertStatusId = newAlertStatusId;
	}
	public String getVerifyStatus() {
		return verifyStatus;
	}
	public void setVerifyStatus(String verifyStatus) {
		this.verifyStatus = verifyStatus;
	}
	public Long getAlertCallerId() {
		return alertCallerId;
	}
	public void setAlertCallerId(Long alertCallerId) {
		this.alertCallerId = alertCallerId;
	}
	public Long getProposalAmount() {
		return proposalAmount;
	}
	public void setProposalAmount(Long proposalAmount) {
		this.proposalAmount = proposalAmount;
	}
	public Long getApprovedAmount() {
		return approvedAmount;
	}
	public void setApprovedAmount(Long approvedAmount) {
		this.approvedAmount = approvedAmount;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public Long getDeptId() {
		return deptId;
	}
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public List<Long> getStatusIds() {
		return statusIds;
	}
	public void setStatusIds(List<Long> statusIds) {
		this.statusIds = statusIds;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getLocationCnt() {
		return locationCnt;
	}
	public void setLocationCnt(Long locationCnt) {
		this.locationCnt = locationCnt;
	}
	public Long getStatusCount() {
		return statusCount;
	}
	public void setStatusCount(Long statusCount) {
		this.statusCount = statusCount;
	}
	public Long getHamletId() {
		return hamletId;
	}
	public void setHamletId(Long hamletId) {
		this.hamletId = hamletId;
	}
	
}
