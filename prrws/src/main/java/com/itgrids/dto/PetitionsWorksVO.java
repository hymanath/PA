package com.itgrids.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class PetitionsWorksVO {

	private Long workId;
	private String workName;
	private Long noOfWorks;
	private String grievanceDescription;
	private Long deptId;
	private Long subjectId;
	private Long subSubjectId;
	private Long leadId;
	private Long briefLeadId;
	private Long grantId;
	private Double estimateCost;
	private Long eOfficeId;
	private String remarks;
	private String isPreviousPetition;
	private String previousPetitionNo;
	private List<PetitionsWorksVO> subWorksList = new ArrayList<PetitionsWorksVO>();
	private List<MultipartFile> fileList = new ArrayList<MultipartFile>();
	private Long workTypeId;
	
	private AddressVO addressVO;
	private Long locationScopeId;
	private Long locationValue;
	
	public AddressVO getAddressVO() {
		return addressVO;
	}
	public void setAddressVO(AddressVO addressVO) {
		this.addressVO = addressVO;
	}
	public Long getLocationScopeId() {
		return locationScopeId;
	}
	public void setLocationScopeId(Long locationScopeId) {
		this.locationScopeId = locationScopeId;
	}
	public Long getLocationValue() {
		return locationValue;
	}
	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
	public Long getLeadId() {
		return leadId;
	}
	public void setLeadId(Long leadId) {
		this.leadId = leadId;
	}
	public Long getBriefLeadId() {
		return briefLeadId;
	}
	public void setBriefLeadId(Long briefLeadId) {
		this.briefLeadId = briefLeadId;
	}
	public Long getGrantId() {
		return grantId;
	}
	public void setGrantId(Long grantId) {
		this.grantId = grantId;
	}
	public String getIsPreviousPetition() {
		return isPreviousPetition;
	}
	public void setIsPreviousPetition(String isPreviousPetition) {
		this.isPreviousPetition = isPreviousPetition;
	}
	public String getPreviousPetitionNo() {
		return previousPetitionNo;
	}
	public void setPreviousPetitionNo(String previousPetitionNo) {
		this.previousPetitionNo = previousPetitionNo;
	}
	public Long getWorkId() {
		return workId;
	}
	public void setWorkId(Long workId) {
		this.workId = workId;
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
	public String getGrievanceDescription() {
		return grievanceDescription;
	}
	public void setGrievanceDescription(String grievanceDescription) {
		this.grievanceDescription = grievanceDescription;
	}
	public Long getDeptId() {
		return deptId;
	}
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	public Long getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}
	public Long getSubSubjectId() {
		return subSubjectId;
	}
	public void setSubSubjectId(Long subSubjectId) {
		this.subSubjectId = subSubjectId;
	}
	public Double getEstimateCost() {
		return estimateCost;
	}
	public void setEstimateCost(Double estimateCost) {
		this.estimateCost = estimateCost;
	}
	public Long geteOfficeId() {
		return eOfficeId;
	}
	public void seteOfficeId(Long eOfficeId) {
		this.eOfficeId = eOfficeId;
	}
	
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public List<PetitionsWorksVO> getSubWorksList() {
		return subWorksList;
	}
	public void setSubWorksList(List<PetitionsWorksVO> subWorksList) {
		this.subWorksList = subWorksList;
	}
	public List<MultipartFile> getFileList() {
		return fileList;
	}
	public void setFileList(List<MultipartFile> fileList) {
		this.fileList = fileList;
	}
	public Long getWorkTypeId() {
		return workTypeId;
	}
	public void setWorkTypeId(Long workTypeId) {
		this.workTypeId = workTypeId;
	}
	
	
}
