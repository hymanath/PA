package com.itgrids.partyanalyst.dto;

import java.util.List;

public class ProblemBeanVO {
	private Long problemId;
	private Long problemLocationId;
	private Long problemHistoryId;
	private Long userID;
	private Long hamletId;
	private Long problemSourceScopeId;
	private Long problemClassificationId;
	private Long problemStatusId;
	private Long probSourceId;
	private Long assignedProblemProgressId;
	private Long probAssignedDeptId;
	private String problem;
	private String description;
	private String state;
	private String district;
	private String constituency;
	private String tehsil;
	private String village;
	private String hamlet;
	private String reportedDate;
	private String existingFrom;
	private String name;
	private String email;
	private String phone;
	private String mobile;
	private String address;
	private String probSource;
	private String year;
	private String problemSourceScope;
	private String problemType;
	private String problemClassification;
	private String department;
	private String departmentConcernedPersonName;
	private String designation;
	private String contactNo;
	private String reasonForPending;
	private List<SelectOptionVO> departments;
	private String departmentConcernedPersonPhoneNumber;
	private String comments;
	private String updatedDate;
	
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getProblemClassification() {
		return problemClassification;
	}

	public void setProblemClassification(String problemClassification) {
		this.problemClassification = problemClassification;
	}
	
	public List<SelectOptionVO> getDepartments() {
		return departments;
	}

	public void setDepartments(List<SelectOptionVO> departments) {
		this.departments = departments;
	}

	public String getProblemType() {
		return problemType;
	}

	public void setProblemType(String problemType) {
		this.problemType = problemType;
	}

	public String getProblemSourceScope() {
		return problemSourceScope;
	}

	public void setProblemSourceScope(String problemSourceScope) {
		this.problemSourceScope = problemSourceScope;
	}

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReportedDate() {
		return reportedDate;
	}

	public void setReportedDate(String reportedDate) {
		this.reportedDate = reportedDate;
	}

	public String getExistingFrom() {
		return existingFrom;
	}

	public void setExistingFrom(String existingFrom) {
		this.existingFrom = existingFrom;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getUserID() {
		return userID;
	}

	public Long getProbSourceId() {
		return probSourceId;
	}

	public void setProbSourceId(Long probSourceId) {
		this.probSourceId = probSourceId;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
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

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public String getHamlet() {
		return hamlet;
	}

	public void setHamlet(String hamlet) {
		this.hamlet = hamlet;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
	public Long getProblemLocationId() {
		return problemLocationId;
	}

	public void setProblemLocationId(Long problemLocationId) {
		this.problemLocationId = problemLocationId;
	}

	public Long getProblemId() {
		return problemId;
	}

	public void setProblemId(Long problemId) {
		this.problemId = problemId;
	}

	public Long getHamletId() {
		return hamletId;
	}

	public void setHamletId(Long hamletId) {
		this.hamletId = hamletId;
	}

	public Long getProblemSourceScopeId() {
		return problemSourceScopeId;
	}

	public void setProblemSourceScopeId(Long problemSourceScopeId) {
		this.problemSourceScopeId = problemSourceScopeId;
	}

	public Long getProblemClassificationId() {
		return problemClassificationId;
	}

	public void setProblemClassificationId(Long problemClassificationId) {
		this.problemClassificationId = problemClassificationId;
	}

	public Long getProblemStatusId() {
		return problemStatusId;
	}

	public void setProblemStatusId(Long problemStatusId) {
		this.problemStatusId = problemStatusId;
	}

	public String getProbSource() {
		return probSource;
	}

	public void setProbSource(String probSource) {
		this.probSource = probSource;
	}

	public Long getProblemHistoryId() {
		return problemHistoryId;
	}

	public void setProblemHistoryId(Long problemHistoryId) {
		this.problemHistoryId = problemHistoryId;
	}

	public Long getAssignedProblemProgressId() {
		return assignedProblemProgressId;
	}

	public void setAssignedProblemProgressId(Long assignedProblemProgressId) {
		this.assignedProblemProgressId = assignedProblemProgressId;
	}

	public String getDepartmentConcernedPersonName() {
		return departmentConcernedPersonName;
	}

	public void setDepartmentConcernedPersonName(
			String departmentConcernedPersonName) {
		this.departmentConcernedPersonName = departmentConcernedPersonName;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getReasonForPending() {
		return reasonForPending;
	}

	public void setReasonForPending(String reasonForPending) {
		this.reasonForPending = reasonForPending;
	}

	public Long getProbAssignedDeptId() {
		return probAssignedDeptId;
	}

	public void setProbAssignedDeptId(Long probAssignedDeptId) {
		this.probAssignedDeptId = probAssignedDeptId;
	}

	public String getDepartmentConcernedPersonPhoneNumber() {
		return departmentConcernedPersonPhoneNumber;
	}

	public void setDepartmentConcernedPersonPhoneNumber(String departmentConcernedPersonPhoneNumber) {
		this.departmentConcernedPersonPhoneNumber = departmentConcernedPersonPhoneNumber;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	
}
