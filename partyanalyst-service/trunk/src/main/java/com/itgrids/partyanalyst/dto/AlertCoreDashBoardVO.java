package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Administrator
 *
 */
public class AlertCoreDashBoardVO implements Serializable{

	private Long id;
	private String name;
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
	private String createdTime;
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
	
	private List<KeyValueVO> subLevels = new ArrayList<KeyValueVO>();
	
	private List<AlertCoreDashBoardVO> subList = new ArrayList<AlertCoreDashBoardVO>(0);
	private List<AlertCoreDashBoardVO> subList1 = new ArrayList<AlertCoreDashBoardVO>(0);
	private Set<Long> setList = new HashSet<Long>(0);

	
	public List<KeyValueVO> getSubLevels() {
		return subLevels;
	}

	public void setSubLevels(List<KeyValueVO> subLevels) {
		this.subLevels = subLevels;
	}

	public String getCallerStr() {
		return callerStr;
	}

	public void setCallerStr(String callerStr) {
		this.callerStr = callerStr;
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

	public List<AlertCoreDashBoardVO> getSubList() {
		return subList;
	}

	public void setSubList(List<AlertCoreDashBoardVO> subList) {
		this.subList = subList;
	}

	public Double getCountPerc() {
		return countPerc;
	}

	public void setCountPerc(Double countPerc) {
		this.countPerc = countPerc;
	}

	public Set<Long> getSetList() {
		return setList;
	}

	public void setSetList(Set<Long> setList) {
		this.setList = setList;
	}

	public List<AlertCoreDashBoardVO> getSubList1() {
		return subList1;
	}

	public void setSubList1(List<AlertCoreDashBoardVO> subList1) {
		this.subList1 = subList1;
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

	public List<String> getAttachments() {
		if(attachments == null){
			attachments = new ArrayList<String>();
		}
		return attachments;
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

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
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

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
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

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}	
	
    	
}  
