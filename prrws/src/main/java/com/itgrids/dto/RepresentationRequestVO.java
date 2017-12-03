package com.itgrids.dto;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

@SuppressWarnings({ "serial", "serial" })
public class RepresentationRequestVO implements java.io.Serializable{

	private Long userId;
	private Long locationLevelId;
	private Long locationValue;
	private Long referrerCandidateId;	
	private String workName;
	private Long  noOfWorks;
	private Long estimationCost;
	private String subject;
	private Long subjectId;
	private Long deptId;
	private String isPreviousPetition;
	private String previousPetitionRefNo;
	private String projectDescription;
	private Long petitionMemberId;
	private String candidateName;
	private String mobileNo;
	private Long age;
	private List<RepresentationRequestVO> worksList = new ArrayList<RepresentationRequestVO>(0);
	private List<MultipartFile> filesList;
	private List<MultipartFile> workFilesList;
	private PetitionMemberVO petitionMemberVO;
	private AddressVO candidateAddressVO;
	private String refCode;
	private String designation;
	private Long designationId;
	private Long reffererId;
	private String reffererName;
	
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

	public Long getReferrerCandidateId() {
		return referrerCandidateId;
	}

	public void setReferrerCandidateId(Long referrerCandidateId) {
		this.referrerCandidateId = referrerCandidateId;
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
	
	
}
