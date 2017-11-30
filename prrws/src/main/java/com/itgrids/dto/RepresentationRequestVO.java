package com.itgrids.dto;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({ "serial", "serial" })
public class RepresentationRequestVO implements java.io.Serializable{

	private Long id;
	private String name;
	
	private String memberType;
	private String endorsmentDate;
	private String representationDate;
	private String mobileNo;
	private String emailId;
	private String voterCardNo;
	
	private AddressVO candidateAddressVO;

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
	
	private List<RepresentationRequestVO> worksList = new ArrayList<RepresentationRequestVO>(0);

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

	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}

	public String getEndorsmentDate() {
		return endorsmentDate;
	}

	public void setEndorsmentDate(String endorsmentDate) {
		this.endorsmentDate = endorsmentDate;
	}

	public String getRepresentationDate() {
		return representationDate;
	}

	public void setRepresentationDate(String representationDate) {
		this.representationDate = representationDate;
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

	public String getVoterCardNo() {
		return voterCardNo;
	}

	public void setVoterCardNo(String voterCardNo) {
		this.voterCardNo = voterCardNo;
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
	
}
