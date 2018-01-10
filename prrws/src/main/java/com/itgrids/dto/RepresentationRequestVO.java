package com.itgrids.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author sys
 *
 */
@SuppressWarnings({ "serial", "serial" })
public class RepresentationRequestVO implements java.io.Serializable{

	private Long id;
	private Long userId;
	private Long locationLevelId;
	private Long locationValue;
	private String representeeType;
	private String workName;
	private Long  noOfWorks;
	private Long estimationCost;
	private String date;
	private String subject;
	private String subSubject;	
	private String lead;
	private String briefLead;
	private String grant;
	private String status;
	private Long subjectId;
	private Long subSubjectId;
	private Long grantId;
	private Long statusId;
	private Long deptId;
	private String isPreviousPetition;
	private String previousPetitionRefNo;
	private String projectDescription;
	private Long petitionMemberId;
	private String candidateName;
	private String mobileNo;
	private Long age;
	private List<RepresentationRequestVO> worksList = new ArrayList<RepresentationRequestVO>(0);
	private List<MultipartFile> filesList  = new ArrayList<MultipartFile>();
	private List<MultipartFile> workFilesList =new ArrayList<MultipartFile>();
	private PetitionMemberVO petitionMemberVO;
	private PetitionMemberVO petitionRefCandidateVO;
	private AddressVO candidateAddressVO;
	private AddressVO candidateNativeAddressVO;
	private String refCode;
	private String designation;
	private Long designationId;
	private Long reffererId;
	private String refDesignation;
	private String reprType;
	private String reffererName;
	private Long referrerCandidateId;
	private String departrment;
	private Long departrmentId;
	private String district;
	private Long districtId;
	private String constituency;
	private Long constituencyId;
	private Long petitionLeadId;
	private Long briefLeadId;
	private Long petitionGrantId;
	private Long petitionStatusId;
	private String remarks;
	private List<Long> designationIds = null;
	private List<Long> statusIds = null;
	
	
	
	public List<Long> getStatusIds() {
		return statusIds;
	}

	public void setStatusIds(List<Long> statusIds) {
		this.statusIds = statusIds;
	}

	public List<Long> getDesignationIds() {
		return designationIds;
	}

	public void setDesignationIds(List<Long> designationIds) {
		this.designationIds = designationIds;
	}

	public String getReprType() {
		return reprType;
	}

	public void setReprType(String reprType) {
		this.reprType = reprType;
	}

	public String getRefDesignation() {
		return refDesignation;
	}

	public void setRefDesignation(String refDesignation) {
		this.refDesignation = refDesignation;
	}

	public String getDepartrment() {
		return departrment;
	}

	public void setDepartrment(String departrment) {
		this.departrment = departrment;
	}

	public Long getDepartrmentId() {
		return departrmentId;
	}

	public void setDepartrmentId(Long departrmentId) {
		this.departrmentId = departrmentId;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public String getConstituency() {
		return constituency;
	}

	public void setConstituency(String constituency) {
		this.constituency = constituency;
	}

	public Long getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}

	public PetitionMemberVO getPetitionRefCandidateVO() {
		return petitionRefCandidateVO;
	}

	public void setPetitionRefCandidateVO(PetitionMemberVO petitionRefCandidateVO) {
		this.petitionRefCandidateVO = petitionRefCandidateVO;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getReferrerCandidateId() {
		return referrerCandidateId;
	}

	public void setReferrerCandidateId(Long referrerCandidateId) {
		this.referrerCandidateId = referrerCandidateId;
	}

	public List<MultipartFile> getWorkFilesList() {
		return workFilesList;
	}

	public void setWorkFilesList(List<MultipartFile> workFilesList) {
		if(workFilesList != null && workFilesList.size()>0)
			this.workFilesList = workFilesList;
	}

	public List<MultipartFile> getFilesList() {
		return filesList;
	}

	public void setFilesList(List<MultipartFile> filesList) {
		if(filesList != null && filesList.size()>0)
			this.filesList = filesList;
	}

	public AddressVO getCandidateAddressVO() {
		return candidateAddressVO;
	}

	public void setCandidateAddressVO(AddressVO candidateAddressVO) {
		this.candidateAddressVO = candidateAddressVO;
	}

	public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

	public Long getNoOfWorks() {
		return noOfWorks;
	}

	public void setNoOfWorks(Long noOfWorks) {
		this.noOfWorks = noOfWorks;
	}

	public Long getEstimationCost() {
		return estimationCost;
	}

	public void setEstimationCost(Long estimationCost) {
		this.estimationCost = estimationCost;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getIsPreviousPetition() {
		return isPreviousPetition;
	}

	public void setIsPreviousPetition(String isPreviousPetition) {
		this.isPreviousPetition = isPreviousPetition;
	}

	public String getPreviousPetitionRefNo() {
		return previousPetitionRefNo;
	}

	public void setPreviousPetitionRefNo(String previousPetitionRefNo) {
		this.previousPetitionRefNo = previousPetitionRefNo;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public List<RepresentationRequestVO> getWorksList() {
		return worksList;
	}

	public void setWorksList(List<RepresentationRequestVO> worksList) {
		this.worksList = worksList;
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public PetitionMemberVO getPetitionMemberVO() {
		return petitionMemberVO;
	}

	public void setPetitionMemberVO(PetitionMemberVO petitionMemberVO) {
		this.petitionMemberVO = petitionMemberVO;
	}

	public Long getPetitionMemberId() {
		return petitionMemberId;
	}

	public void setPetitionMemberId(Long petitionMemberId) {
		this.petitionMemberId = petitionMemberId;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public String getRefCode() {
		return refCode;
	}

	public void setRefCode(String refCode) {
		this.refCode = refCode;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Long getDesignationId() {
		return designationId;
	}

	public void setDesignationId(Long designationId) {
		this.designationId = designationId;
	}

	public Long getReffererId() {
		return reffererId;
	}

	public void setReffererId(Long reffererId) {
		this.reffererId = reffererId;
	}

	public String getReffererName() {
		return reffererName;
	}

	public void setReffererName(String reffererName) {
		this.reffererName = reffererName;
	}

	public String getRepresenteeType() {
		return representeeType;
	}

	public void setRepresenteeType(String representeeType) {
		this.representeeType = representeeType;
	}

	public String getSubSubject() {
		return subSubject;
	}

	public void setSubSubject(String subSubject) {
		this.subSubject = subSubject;
	}

	public String getLead() {
		return lead;
	}

	public void setLead(String lead) {
		this.lead = lead;
	}

	public String getBriefLead() {
		return briefLead;
	}

	public void setBriefLead(String briefLead) {
		this.briefLead = briefLead;
	}

	public String getGrant() {
		return grant;
	}

	public void setGrant(String grant) {
		this.grant = grant;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Long getSubSubjectId() {
		return subSubjectId;
	}

	public void setSubSubjectId(Long subSubjectId) {
		this.subSubjectId = subSubjectId;
	}

	public Long getGrantId() {
		return grantId;
	}

	public void setGrantId(Long grantId) {
		this.grantId = grantId;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	public Long getPetitionLeadId() {
		return petitionLeadId;
	}

	public void setPetitionLeadId(Long petitionLeadId) {
		this.petitionLeadId = petitionLeadId;
	}

	public Long getBriefLeadId() {
		return briefLeadId;
	}

	public void setBriefLeadId(Long briefLeadId) {
		this.briefLeadId = briefLeadId;
	}

	public Long getPetitionGrantId() {
		return petitionGrantId;
	}

	public void setPetitionGrantId(Long petitionGrantId) {
		this.petitionGrantId = petitionGrantId;
	}

	public Long getPetitionStatusId() {
		return petitionStatusId;
	}

	public void setPetitionStatusId(Long petitionStatusId) {
		this.petitionStatusId = petitionStatusId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public AddressVO getCandidateNativeAddressVO() {
		return candidateNativeAddressVO;
	}

	public void setCandidateNativeAddressVO(AddressVO candidateNativeAddressVO) {
		this.candidateNativeAddressVO = candidateNativeAddressVO;
	}
}
