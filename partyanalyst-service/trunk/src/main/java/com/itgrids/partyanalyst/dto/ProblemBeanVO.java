package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class ProblemBeanVO extends ResultStatus implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long problemId;
	private Long problemLocationId;
	private String problemLocation;
	private Long problemHistoryId;
	private Long userID;
	private Long tehsilId;
	private Long hamletId;
	private Long problemSourceScopeId;
	private Long problemClassificationId;
	private Long problemStatusId;
	private Long probSourceId;
	private Long assignedProblemProgressId;
	private Long probAssignedDeptId;
	private Long departmentId;
	private String deptName;
	private String problem;
	private String description;
	private String state;
	private String district;
	private String constituency;
	private String tehsil;
	private String village;
	private String hamlet;
	private String localBody;
	private String postedDate;
	private String reportedDate;
	private String existingFrom;
	private String name;
	private String postedPersonName;
	private String email;
	private String phone;
	private String mobile;
	private String address;
	private String probSource;
	private String year;
	private String problemSourceScope;
	private String problemType;
	private Long   problemTypeId;
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
	private String status;
	private Long problemAndProblemSourceId;
	private Boolean isAssigned;
	private String impactLevel;
	private String problemPostedBy;
	private Long problemImpactLevelId;
	private Long problemImpactLevelValue;
	private String isApproved;
	private String isDeleted;
	private String booth;
	private String ward;
	private List<ProblemHistoryVO> problemHistories;
	private String totalResultsCount;
	private String acceptedCount;
	private String rejectedCount;
	private List<ApprovalInfoVO> problemApproovals;
	private Boolean isParliament;
	private Long pConstituencyId;
	private List<ProblemDetailsVO> problemsInfo;
	private Long problemsCount;
	private Long cadreId;
	private String cadreName;
	private String problemStatus;
	private String recentActivity;
	private List<Object> statusList;
	private String problemScope;
	private String problemRefNum;
	private Long subUserId;
	private String mandal;
	private FileVO fileVO;
	

	public FileVO getFileVO() {
		return fileVO;
	}

	public void setFileVO(FileVO fileVO) {
		this.fileVO = fileVO;
	}

	public Long getSubUserId() {
		return subUserId;
	}

	public void setSubUserId(Long subUserId) {
		this.subUserId = subUserId;
	}

	public String getProblemScope() {
		return problemScope;
	}

	public void setProblemScope(String problemScope) {
		this.problemScope = problemScope;
	}

	public List<Object> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<Object> statusList) {
		this.statusList = statusList;
	}

	public String getRecentActivity() {
		return recentActivity;
	}

	public void setRecentActivity(String recentActivity) {
		this.recentActivity = recentActivity;
	}

	public Long getCadreId() {
		return cadreId;
	}

	public void setCadreId(Long cadreId) {
		this.cadreId = cadreId;
	}

	public String getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(String postedDate) {
		this.postedDate = postedDate;
	}

	public String getImpactLevel() {
		return impactLevel;
	}

	public void setImpactLevel(String impactLevel) {
		this.impactLevel = impactLevel;
	}

	public List<ProblemHistoryVO> getProblemHistories() {
		return problemHistories;
	}

	public void setProblemHistories(List<ProblemHistoryVO> problemHistories) {
		this.problemHistories = problemHistories;
	}

	public Long getTehsilId() {
		return tehsilId;
	}

	public void setTehsilId(Long tehsilId) {
		this.tehsilId = tehsilId;
	}
	public String getPostedPersonName() {
		return postedPersonName;
	}

	public void setPostedPersonName(String postedPersonName) {
		this.postedPersonName = postedPersonName;
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

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

	public Long getProblemAndProblemSourceId() {
		return problemAndProblemSourceId;
	}

	public void setProblemAndProblemSourceId(Long problemAndProblemSourceId) {
		this.problemAndProblemSourceId = problemAndProblemSourceId;
	}

	public Boolean getIsAssigned() {
		return isAssigned;
	}

	public void setIsAssigned(Boolean isAssigned) {
		this.isAssigned = isAssigned;
	}

	public String getProblemPostedBy() {
		return problemPostedBy;
	}

	public void setProblemPostedBy(String problemPostedBy) {
		this.problemPostedBy = problemPostedBy;
	}

	public Long getProblemImpactLevelId() {
		return problemImpactLevelId;
	}

	public void setProblemImpactLevelId(Long problemImpactLevelId) {
		this.problemImpactLevelId = problemImpactLevelId;
	}

	public Long getProblemImpactLevelValue() {
		return problemImpactLevelValue;
	}

	public void setProblemImpactLevelValue(Long problemImpactLevelValue) {
		this.problemImpactLevelValue = problemImpactLevelValue;
	}

	public String getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getProblemLocation() {
		return problemLocation;
	}

	public void setProblemLocation(String problemLocation) {
		this.problemLocation = problemLocation;
	}
	
	public String getBooth() {
		return booth;
	}

	public void setBooth(String booth) {
		this.booth = booth;
	}

	public String getLocalBody() {
		return localBody;
	}

	public void setLocalBody(String localBody) {
		this.localBody = localBody;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}
	
	public String getTotalResultsCount() {
		return totalResultsCount;
	}

	public void setTotalResultsCount(String totalResultsCount) {
		this.totalResultsCount = totalResultsCount;
	}

	public String getAcceptedCount() {
		return acceptedCount;
	}

	public void setAcceptedCount(String acceptedCount) {
		this.acceptedCount = acceptedCount;
	}

	public String getRejectedCount() {
		return rejectedCount;
	}

	public void setRejectedCount(String rejectedCount) {
		this.rejectedCount = rejectedCount;
	}

	public List<ApprovalInfoVO> getProblemApproovals() {
		return problemApproovals;
	}

	public void setProblemApproovals(List<ApprovalInfoVO> problemApproovals) {
		this.problemApproovals = problemApproovals;
	}

	public Boolean getIsParliament() {
		return isParliament;
	}

	public void setIsParliament(Boolean isParliament) {
		this.isParliament = isParliament;
	}

	public Long getPConstituencyId() {
		return pConstituencyId;
	}

	public void setPConstituencyId(Long constituencyId) {
		pConstituencyId = constituencyId;
	}
	
	
	

	public List<ProblemDetailsVO> getProblemsInfo() {
		return problemsInfo;
	}

	public void setProblemsInfo(List<ProblemDetailsVO> problemsInfo) {
		this.problemsInfo = problemsInfo;
	}

	public Long getProblemsCount() {
		return problemsCount;
	}

	public void setProblemsCount(Long problemsCount) {
		this.problemsCount = problemsCount;
	}

	public String getCadreName() {
		return cadreName;
	}

	public void setCadreName(String cadreName) {
		this.cadreName = cadreName;
	}

	public String getProblemStatus() {
		return problemStatus;
	}

	public void setProblemStatus(String problemStatus) {
		this.problemStatus = problemStatus;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public void setProblemRefNum(String problemRefNum) {
		this.problemRefNum = problemRefNum;
	}

	public String getProblemRefNum() {
		return problemRefNum;
	}

	public void setProblemTypeId(Long problemTypeId) {
		this.problemTypeId = problemTypeId;
	}

	public Long getProblemTypeId() {
		return problemTypeId;
	}

	public void setMandal(String mandal) {
		this.mandal = mandal;
	}

	public String getMandal() {
		return mandal;
	}
	
	
}
