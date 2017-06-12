package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

//Please Don't add fields without confirmation of App
public class AmsDataVO implements Serializable {

	private Long id;
	private String name;
	
	private List<AmsKeyValueVO> newsPaperList;
	private List<Long> newsPaperIdsList;
	private List<AmsKeyValueVO> chanelList;
	private List<Long> chanelIdsList;
	private List<AmsKeyValueVO> chanelListNew;
	private List<Long> chanelIdsListNew;
	private List<AmsKeyValueVO> deptList;
	private List<Long> deptIdsList;
	private List<AmsKeyValueVO> deptListNew;
	private List<Long> deptIdsListNew;
	
	private List<AmsKeyValueVO> socailMediaTypeList;
	private List<Long> socailMediaTypeIdsList;
	private List<AmsKeyValueVO> alertCallCenterTypeIdList;
	private List<Long> alertCallCenterTypeIdsList;
	private List<AmsKeyValueVO> mondayGrievanceTypeList;
	private List<Long> mondayGrievanceTypeIdsList;
	private List<AmsKeyValueVO> janmabhoomiTypeList;
	private List<Long> janmabhoomiTypeIdsList;
	private List<AmsKeyValueVO> specialGrievanceTypeList;
	private List<Long> specialGrievanceTypeIdsList;
	private List<AmsKeyValueVO> generalGrievanceTypeList;
	private List<Long> generalGrievanceTypeIdsList;
	
	private List<AmsKeyValueVO> alertSeverityList;	
	private List<AmsKeyValueVO> alertStatusList;
	private List<AmsKeyValueVO> govtAlertSubTaksStatusList;
	
	private List<Long> alertSeverityIdsList;
	private List<Long> alertStatusIdsList;
	private List<Long> govtAlertSubTaksStatusIdsList;
	
	private String fromDate = null,toDate = null;
	
	//AlertCoreDashBoardVo
	
	private String title;
	private String desc;
	private String email;
	private String officerMobileNo;
	
	private Long totalCount=0l;
	private Long count=0l;
	
	private Double countPerc=0.0;
	private Long stateId=0l;
	private Long categoryId;
	private String category;
	
	private String createdDate;
	private String updatedDate;
	private Long statusId;
	private String status;
	private Long interval;
	private String alertLevel;
	private String location;
	private String source;
	private String severtyColor;
	private Long subTaskCount = 0L;
	private String statusColor;
	private List<String> attachments;
	private String callerDetails;
	private String department;
	private String impactLevel;
	private String designation;
	private String mobileNo;
	private String imagePath;
	private String loginTime;
	private String logoutTime;
	private String totalHours;
	private Long noOfAlertCreated = 0L;
	private String relatedTo;
	private String problem;
	private Long pendingCount = 0L;
	private Long reopenCount = 0l;
	private Long totalAgent = 0L;
	private String totalTime;
	private Long totalAlert = 0L;
	private Long attendedCount = 0L;
	private String range;
	private String callerStr;
	private Long grandTotal = 0L;
	
	private String levelHeading;
	private Long departmentId;
	private Long designationId;
	private Long locationId;
	private Long orderNo;
	
	private String countType;
	private String alertType;
	
	
	public List<Long> getAlertStatusIdsList() {
		return alertStatusIdsList;
	}
	public void setAlertStatusIdsList(List<Long> alertStatusIdsList) {
		this.alertStatusIdsList = alertStatusIdsList;
	}
	public List<Long> getAlertSeverityIdsList() {
		return alertSeverityIdsList;
	}
	public void setAlertSeverityIdsList(List<Long> alertSeverityIdsList) {
		this.alertSeverityIdsList = alertSeverityIdsList;
	}
	public List<Long> getNewsPaperIdsList() {
		return newsPaperIdsList;
	}
	public void setNewsPaperIdsList(List<Long> newsPaperIdsList) {
		this.newsPaperIdsList = newsPaperIdsList;
	}
	public List<Long> getChanelIdsList() {
		return chanelIdsList;
	}
	public void setChanelIdsList(List<Long> chanelIdsList) {
		this.chanelIdsList = chanelIdsList;
	}
	public List<Long> getChanelIdsListNew() {
		return chanelIdsListNew;
	}
	public void setChanelIdsListNew(List<Long> chanelIdsListNew) {
		this.chanelIdsListNew = chanelIdsListNew;
	}
	public List<Long> getDeptIdsList() {
		return deptIdsList;
	}
	public void setDeptIdsList(List<Long> deptIdsList) {
		this.deptIdsList = deptIdsList;
	}
	public List<Long> getDeptIdsListNew() {
		return deptIdsListNew;
	}
	public void setDeptIdsListNew(List<Long> deptIdsListNew) {
		this.deptIdsListNew = deptIdsListNew;
	}
	public List<Long> getSocailMediaTypeIdsList() {
		return socailMediaTypeIdsList;
	}
	public void setSocailMediaTypeIdsList(List<Long> socailMediaTypeIdsList) {
		this.socailMediaTypeIdsList = socailMediaTypeIdsList;
	}
	public List<Long> getAlertCallCenterTypeIdsList() {
		return alertCallCenterTypeIdsList;
	}
	public void setAlertCallCenterTypeIdsList(List<Long> alertCallCenterTypeIdsList) {
		this.alertCallCenterTypeIdsList = alertCallCenterTypeIdsList;
	}
	public List<Long> getMondayGrievanceTypeIdsList() {
		return mondayGrievanceTypeIdsList;
	}
	public void setMondayGrievanceTypeIdsList(List<Long> mondayGrievanceTypeIdsList) {
		this.mondayGrievanceTypeIdsList = mondayGrievanceTypeIdsList;
	}
	public List<Long> getJanmabhoomiTypeIdsList() {
		return janmabhoomiTypeIdsList;
	}
	public void setJanmabhoomiTypeIdsList(List<Long> janmabhoomiTypeIdsList) {
		this.janmabhoomiTypeIdsList = janmabhoomiTypeIdsList;
	}
	public List<Long> getSpecialGrievanceTypeIdsList() {
		return specialGrievanceTypeIdsList;
	}
	public void setSpecialGrievanceTypeIdsList(
			List<Long> specialGrievanceTypeIdsList) {
		this.specialGrievanceTypeIdsList = specialGrievanceTypeIdsList;
	}
	public List<Long> getGeneralGrievanceTypeIdsList() {
		return generalGrievanceTypeIdsList;
	}
	public void setGeneralGrievanceTypeIdsList(
			List<Long> generalGrievanceTypeIdsList) {
		this.generalGrievanceTypeIdsList = generalGrievanceTypeIdsList;
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
	public List<AmsKeyValueVO> getNewsPaperList() {
		return newsPaperList;
	}
	public void setNewsPaperList(List<AmsKeyValueVO> newsPaperList) {
		this.newsPaperList = newsPaperList;
	}
	public List<AmsKeyValueVO> getChanelList() {
		return chanelList;
	}
	public void setChanelList(List<AmsKeyValueVO> chanelList) {
		this.chanelList = chanelList;
	}
	public List<AmsKeyValueVO> getChanelListNew() {
		return chanelListNew;
	}
	public void setChanelListNew(List<AmsKeyValueVO> chanelListNew) {
		this.chanelListNew = chanelListNew;
	}
	public List<AmsKeyValueVO> getDeptList() {
		return deptList;
	}
	public void setDeptList(List<AmsKeyValueVO> deptList) {
		this.deptList = deptList;
	}
	public List<AmsKeyValueVO> getDeptListNew() {
		return deptListNew;
	}
	public void setDeptListNew(List<AmsKeyValueVO> deptListNew) {
		this.deptListNew = deptListNew;
	}
	public List<AmsKeyValueVO> getSocailMediaTypeList() {
		return socailMediaTypeList;
	}
	public void setSocailMediaTypeList(List<AmsKeyValueVO> socailMediaTypeList) {
		this.socailMediaTypeList = socailMediaTypeList;
	}
	public List<AmsKeyValueVO> getAlertCallCenterTypeIdList() {
		return alertCallCenterTypeIdList;
	}
	public void setAlertCallCenterTypeIdList(
			List<AmsKeyValueVO> alertCallCenterTypeIdList) {
		this.alertCallCenterTypeIdList = alertCallCenterTypeIdList;
	}
	public List<AmsKeyValueVO> getMondayGrievanceTypeList() {
		return mondayGrievanceTypeList;
	}
	public void setMondayGrievanceTypeList(
			List<AmsKeyValueVO> mondayGrievanceTypeList) {
		this.mondayGrievanceTypeList = mondayGrievanceTypeList;
	}
	public List<AmsKeyValueVO> getJanmabhoomiTypeList() {
		return janmabhoomiTypeList;
	}
	public void setJanmabhoomiTypeList(List<AmsKeyValueVO> janmabhoomiTypeList) {
		this.janmabhoomiTypeList = janmabhoomiTypeList;
	}
	public List<AmsKeyValueVO> getSpecialGrievanceTypeList() {
		return specialGrievanceTypeList;
	}
	public void setSpecialGrievanceTypeList(
			List<AmsKeyValueVO> specialGrievanceTypeList) {
		this.specialGrievanceTypeList = specialGrievanceTypeList;
	}
	public List<AmsKeyValueVO> getGeneralGrievanceTypeList() {
		return generalGrievanceTypeList;
	}
	public void setGeneralGrievanceTypeList(
			List<AmsKeyValueVO> generalGrievanceTypeList) {
		this.generalGrievanceTypeList = generalGrievanceTypeList;
	}
	public List<AmsKeyValueVO> getAlertSeverityList() {
		return alertSeverityList;
	}
	public void setAlertSeverityList(List<AmsKeyValueVO> alertSeverityList) {
		this.alertSeverityList = alertSeverityList;
	}
	public List<AmsKeyValueVO> getAlertStatusList() {
		return alertStatusList;
	}
	public void setAlertStatusList(List<AmsKeyValueVO> alertStatusList) {
		this.alertStatusList = alertStatusList;
	}
	public List<AmsKeyValueVO> getGovtAlertSubTaksStatusList() {
		return govtAlertSubTaksStatusList;
	}
	public void setGovtAlertSubTaksStatusList(
			List<AmsKeyValueVO> govtAlertSubTaksStatusList) {
		this.govtAlertSubTaksStatusList = govtAlertSubTaksStatusList;
	}
	public List<Long> getGovtAlertSubTaksStatusIdsList() {
		return govtAlertSubTaksStatusIdsList;
	}
	public void setGovtAlertSubTaksStatusIdsList(
			List<Long> govtAlertSubTaksStatusIdsList) {
		this.govtAlertSubTaksStatusIdsList = govtAlertSubTaksStatusIdsList;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOfficerMobileNo() {
		return officerMobileNo;
	}
	public void setOfficerMobileNo(String officerMobileNo) {
		this.officerMobileNo = officerMobileNo;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Double getCountPerc() {
		return countPerc;
	}
	public void setCountPerc(Double countPerc) {
		this.countPerc = countPerc;
	}
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
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
	public Long getInterval() {
		return interval;
	}
	public void setInterval(Long interval) {
		this.interval = interval;
	}
	public String getAlertLevel() {
		return alertLevel;
	}
	public void setAlertLevel(String alertLevel) {
		this.alertLevel = alertLevel;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getSevertyColor() {
		return severtyColor;
	}
	public void setSevertyColor(String severtyColor) {
		this.severtyColor = severtyColor;
	}
	public Long getSubTaskCount() {
		return subTaskCount;
	}
	public void setSubTaskCount(Long subTaskCount) {
		this.subTaskCount = subTaskCount;
	}
	public String getStatusColor() {
		return statusColor;
	}
	public void setStatusColor(String statusColor) {
		this.statusColor = statusColor;
	}
	public List<String> getAttachments() {
		return attachments;
	}
	public void setAttachments(List<String> attachments) {
		this.attachments = attachments;
	}
	public String getCallerDetails() {
		return callerDetails;
	}
	public void setCallerDetails(String callerDetails) {
		this.callerDetails = callerDetails;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getImpactLevel() {
		return impactLevel;
	}
	public void setImpactLevel(String impactLevel) {
		this.impactLevel = impactLevel;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	public String getLogoutTime() {
		return logoutTime;
	}
	public void setLogoutTime(String logoutTime) {
		this.logoutTime = logoutTime;
	}
	public String getTotalHours() {
		return totalHours;
	}
	public void setTotalHours(String totalHours) {
		this.totalHours = totalHours;
	}
	public Long getNoOfAlertCreated() {
		return noOfAlertCreated;
	}
	public void setNoOfAlertCreated(Long noOfAlertCreated) {
		this.noOfAlertCreated = noOfAlertCreated;
	}
	public String getRelatedTo() {
		return relatedTo;
	}
	public void setRelatedTo(String relatedTo) {
		this.relatedTo = relatedTo;
	}
	public String getProblem() {
		return problem;
	}
	public void setProblem(String problem) {
		this.problem = problem;
	}
	public Long getPendingCount() {
		return pendingCount;
	}
	public void setPendingCount(Long pendingCount) {
		this.pendingCount = pendingCount;
	}
	public Long getReopenCount() {
		return reopenCount;
	}
	public void setReopenCount(Long reopenCount) {
		this.reopenCount = reopenCount;
	}
	public Long getTotalAgent() {
		return totalAgent;
	}
	public void setTotalAgent(Long totalAgent) {
		this.totalAgent = totalAgent;
	}
	public String getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
	}
	public Long getTotalAlert() {
		return totalAlert;
	}
	public void setTotalAlert(Long totalAlert) {
		this.totalAlert = totalAlert;
	}
	public Long getAttendedCount() {
		return attendedCount;
	}
	public void setAttendedCount(Long attendedCount) {
		this.attendedCount = attendedCount;
	}
	public String getRange() {
		return range;
	}
	public void setRange(String range) {
		this.range = range;
	}
	public String getCallerStr() {
		return callerStr;
	}
	public void setCallerStr(String callerStr) {
		this.callerStr = callerStr;
	}
	public Long getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(Long grandTotal) {
		this.grandTotal = grandTotal;
	}
	public String getLevelHeading() {
		return levelHeading;
	}
	public void setLevelHeading(String levelHeading) {
		this.levelHeading = levelHeading;
	}
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public Long getDesignationId() {
		return designationId;
	}
	public void setDesignationId(Long designationId) {
		this.designationId = designationId;
	}
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	public String getCountType() {
		return countType;
	}
	public void setCountType(String countType) {
		this.countType = countType;
	}
	public String getAlertType() {
		return alertType;
	}
	public void setAlertType(String alertType) {
		this.alertType = alertType;
	}
		
	
	
}
