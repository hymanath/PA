package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class GrievanceAlertVO {

	private Long alertTypeId;
	private Long alertImpactId;
	private Long informationSourceId;
	private Long severityId;
	private Long locationLevelId;
	private Long locationValue;
	private Long stateId;
	private Long districtId;
	private Long constituencyId;
	private Long mandalId;
	private Long panchayatId;
	private Long hamletId;
	
	private String alertTitle;
	private String complaintNo;
	private String description;
	private Long callerTypeId;
	private Long issueTypeId;
	private Long entrySourceId;
	private Long alertIssueSubTypeId;
	
	private String name;
	private String address;
	private String mobileNo;
	private String emailId;
	
	private Long departmentId;
	private Long levelId;
	private Long levelValue;
	private Long designationId;
	private Long govtOfficerId;
	private String date;
	private String time;
	private String locationName;
	private String title;
	private String relatedTo;
	private String problem;
	private String status;
	private String createdBy;
	
	private String state;
	private String district;
	private String assembly;
	private String tehsil;
	private String panchayat;
	private String hamlet;
	private String leb;
	private String ward;
	private Long alertId;
	private String locationTypeStr;
	private Long socialMediaTypeId;
	private String accountId;
	
	private Long alertCallCenterTypeId; 
	private List<GrievanceAlertVO> subList = new ArrayList<GrievanceAlertVO>();
	private String callerDuplicate;
	
	
	public String getCallerDuplicate() {
		return callerDuplicate;
	}
	public void setCallerDuplicate(String callerDuplicate) {
		this.callerDuplicate = callerDuplicate;
	}
	public List<GrievanceAlertVO> getSubList() {
		return subList;
	}
	public void setSubList(List<GrievanceAlertVO> subList) {
		this.subList = subList;
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
	public String getAssembly() {
		return assembly;
	}
	public void setAssembly(String assembly) {
		this.assembly = assembly;
	}
	public String getTehsil() {
		return tehsil;
	}
	public void setTehsil(String tehsil) {
		this.tehsil = tehsil;
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
	public String getLeb() {
		return leb;
	}
	public void setLeb(String leb) {
		this.leb = leb;
	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getAlertTypeId() {
		return alertTypeId;
	}
	public void setAlertTypeId(Long alertTypeId) {
		this.alertTypeId = alertTypeId;
	}
	public Long getAlertImpactId() {
		return alertImpactId;
	}
	public void setAlertImpactId(Long alertImpactId) {
		this.alertImpactId = alertImpactId;
	}
	public Long getInformationSourceId() {
		return informationSourceId;
	}
	public void setInformationSourceId(Long informationSourceId) {
		this.informationSourceId = informationSourceId;
	}
	public Long getSeverityId() {
		return severityId;
	}
	public void setSeverityId(Long severityId) {
		this.severityId = severityId;
	}
	public Long getLocationLevelId() {
		return locationLevelId;
	}
	public void setLocationLevelId(Long locationLevelId) {
		this.locationLevelId = locationLevelId;
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
	public Long getMandalId() {
		return mandalId;
	}
	public void setMandalId(Long mandalId) {
		this.mandalId = mandalId;
	}
	public Long getPanchayatId() {
		return panchayatId;
	}
	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}
	public Long getHamletId() {
		return hamletId;
	}
	public void setHamletId(Long hamletId) {
		this.hamletId = hamletId;
	}
	public String getAlertTitle() {
		return alertTitle;
	}
	public void setAlertTitle(String alertTitle) {
		this.alertTitle = alertTitle;
	}
	public String getComplaintNo() {
		return complaintNo;
	}
	public void setComplaintNo(String complaintNo) {
		this.complaintNo = complaintNo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getCallerTypeId() {
		return callerTypeId;
	}
	public void setCallerTypeId(Long callerTypeId) {
		this.callerTypeId = callerTypeId;
	}
	public Long getIssueTypeId() {
		return issueTypeId;
	}
	public void setIssueTypeId(Long issueTypeId) {
		this.issueTypeId = issueTypeId;
	}
	public Long getEntrySourceId() {
		return entrySourceId;
	}
	public void setEntrySourceId(Long entrySourceId) {
		this.entrySourceId = entrySourceId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public Long getLevelId() {
		return levelId;
	}
	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}
	public Long getLevelValue() {
		return levelValue;
	}
	public void setLevelValue(Long levelValue) {
		this.levelValue = levelValue;
	}
	public Long getDesignationId() {
		return designationId;
	}
	public void setDesignationId(Long designationId) {
		this.designationId = designationId;
	}
	public Long getGovtOfficerId() {
		return govtOfficerId;
	}
	public void setGovtOfficerId(Long govtOfficerId) {
		this.govtOfficerId = govtOfficerId;
	}
	public Long getLocationValue() {
		return locationValue;
	}
	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
	public Long getAlertIssueSubTypeId() {
		return alertIssueSubTypeId;
	}
	public void setAlertIssueSubTypeId(Long alertIssueSubTypeId) {
		this.alertIssueSubTypeId = alertIssueSubTypeId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Long getAlertId() {
		return alertId;
	}
	public void setAlertId(Long alertId) {
		this.alertId = alertId;
	}
	public String getLocationTypeStr() {
		return locationTypeStr;
	}
	public void setLocationTypeStr(String locationTypeStr) {
		this.locationTypeStr = locationTypeStr;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public Long getSocialMediaTypeId() {
		return socialMediaTypeId;
	}
	public void setSocialMediaTypeId(Long socialMediaTypeId) {
		this.socialMediaTypeId = socialMediaTypeId;
	}
	public Long getAlertCallCenterTypeId() {
		return alertCallCenterTypeId;
	}
	public void setAlertCallCenterTypeId(Long alertCallCenterTypeId) {
		this.alertCallCenterTypeId = alertCallCenterTypeId;
	}
	
	
}
