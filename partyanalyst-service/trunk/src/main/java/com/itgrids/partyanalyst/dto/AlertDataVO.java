package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 *
 */
public class AlertDataVO {
	private String severity;
	private String desc;
	private String alertType;
	private Long count=0l;
	private String userType;
	private String date;
	private String status;
	private Long id;
	private LocationVO locationVO;
	private Long regionScopeId;
	private String regionScope;
	private Long statusId;
	private String name;
	private String impact;
	private String image;
	private Long impactId;
	private String mobileNo;
	private List<AlertDataVO> subList = new ArrayList<AlertDataVO>(0);
	private Long voterId;
	private String committeeLocation;
	private String committeePosition;
	private String committeeName;
	private String electionType;
	private Long alertCategoryId;
	private String alertCategoryName;
	private String title;
	private String statusColor;
	
	private Long categoryId;
	private String category;
	private String imageUrl;
	private Long alertCategoryTypeId;
	private String organization;
	
	private String comment;
	private String membershipNo;
	private String alertSource;
	private List<String> documentList = new ArrayList<String>(0);
	private List<String> documentNameList = new ArrayList<String>(0);
	private Long verificationStatusId;
	private String verificationStatus;
	private String designation="";
	
	private String department;
	private String dueDate;
	private Long severityId;
	private List<AlertTrackingVO> subList1 = new ArrayList<AlertTrackingVO>(0);
	private List<AlertVO> rejinderDocList = new ArrayList<AlertVO>(0);
	
	public List<AlertTrackingVO> getSubList1() {
		return subList1;
	}
	public void setSubList1(List<AlertTrackingVO> subList1) {
		this.subList1 = subList1;
	}
	public Long getSeverityId() {
		return severityId;
	}
	public void setSeverityId(Long severityId) {
		this.severityId = severityId;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getStatusColor() {
		return statusColor;
	}
	public void setStatusColor(String statusColor) {
		this.statusColor = statusColor;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getAlertCategoryId() {
		return alertCategoryId;
	}
	public void setAlertCategoryId(Long alertCategoryId) {
		this.alertCategoryId = alertCategoryId;
	}
	public String getAlertCategoryName() {
		return alertCategoryName;
	}
	public void setAlertCategoryName(String alertCategoryName) {
		this.alertCategoryName = alertCategoryName;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public List<AlertDataVO> getSubList() {
		return subList;
	}
	public void setSubList(List<AlertDataVO> subList) {
		this.subList = subList;
	}
	public Long getImpactId() {
		return impactId;
	}
	public void setImpactId(Long impactId) {
		this.impactId = impactId;
	}
	public String getImpact() {
		return impact;
	}
	public void setImpact(String impact) {
		this.impact = impact;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	public String getSeverity() {
		return severity;
	}
	public void setSeverity(String severity) {
		this.severity = severity;
	}
	public String getDesc() {
		return desc;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getAlertType() {
		return alertType;
	}
	public void setAlertType(String alertType) {
		this.alertType = alertType;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocationVO getLocationVO() {
		return locationVO;
	}
	public void setLocationVO(LocationVO locationVO) {
		this.locationVO = locationVO;
	}
	public Long getRegionScopeId() {
		return regionScopeId;
	}
	public void setRegionScopeId(Long regionScopeId) {
		this.regionScopeId = regionScopeId;
	}
	public String getRegionScope() {
		return regionScope;
	}
	public void setRegionScope(String regionScope) {
		this.regionScope = regionScope;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Long getVoterId() {
		return voterId;
	}
	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}
	public String getCommitteeLocation() {
		return committeeLocation;
	}
	public void setCommitteeLocation(String committeeLocation) {
		this.committeeLocation = committeeLocation;
	}
	public String getCommitteePosition() {
		return committeePosition;
	}
	public void setCommitteePosition(String committeePosition) {
		this.committeePosition = committeePosition;
	}
	public String getCommitteeName() {
		return committeeName;
	}
	public void setCommitteeName(String committeeName) {
		this.committeeName = committeeName;
	}
	public String getElectionType() {
		return electionType;
	}
	public void setElectionType(String electionType) {
		this.electionType = electionType;
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
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Long getAlertCategoryTypeId() {
		return alertCategoryTypeId;
	}
	public void setAlertCategoryTypeId(Long alertCategoryTypeId) {
		this.alertCategoryTypeId = alertCategoryTypeId;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getMembershipNo() {
		return membershipNo;
	}
	public void setMembershipNo(String membershipNo) {
		this.membershipNo = membershipNo;
	}
	public String getAlertSource() {
		return alertSource;
	}
	public void setAlertSource(String alertSource) {
		this.alertSource = alertSource;
	}
	public List<String> getDocumentList() {
		return documentList;
	}
	public void setDocumentList(List<String> documentList) {
		this.documentList = documentList;
	}
	
	public Long getVerificationStatusId() {
		return verificationStatusId;
	}
	public void setVerificationStatusId(Long verificationStatusId) {
		this.verificationStatusId = verificationStatusId;
	}
	public String getVerificationStatus() {
		return verificationStatus;
	}
	public void setVerificationStatus(String verificationStatus) {
		this.verificationStatus = verificationStatus;
	}
	public List<String> getDocumentNameList() {
		return documentNameList;
	}
	public void setDocumentNameList(List<String> documentNameList) {
		this.documentNameList = documentNameList;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public List<AlertVO> getRejinderDocList() {
		return rejinderDocList;
	}
	public void setRejinderDocList(List<AlertVO> rejinderDocList) {
		this.rejinderDocList = rejinderDocList;
	}
	
}
