package com.itgrids.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class PetitionsWorksVO {

	private Long petitionId;
	private String endorsmentNo;
	private String endorsmentDate;
	private Long pmSubWorkDetailsId;
	private Long pmDocumentId;
	private Long pmRepresenteeRefDetailsId;
	private Long  pmRepresenteeRefDocumentId;
	private Long workId;
	private String workName;
	private Long noOfWorks;
	private String grievanceDescription;
	private Long deptId;
	private String deptName; 
	private Long subjectId;
	private String subject;
	private Long subSubjectId;
	private String subSubject;
	private Long leadId;
	private String leadName;
	private Long briefLeadId;
	private String briefLeadName;
	private Long grantId;
	private String grantName;
	private String estimateCost;
	private String estimateCostStr;
	private String eOfficeId;
	private String remarks;
	private String isPreviousPetition;
	private String previousPetitionNo;
	private List<PetitionsWorksVO> subWorksList = new ArrayList<PetitionsWorksVO>();
	private List<MultipartFile> fileList = new ArrayList<MultipartFile>();
	private List<KeyValueVO> subjectsList = new ArrayList<KeyValueVO>(0);
	private List<KeyValueVO> subSubjectsList = new ArrayList<KeyValueVO>(0);
	private List<PetitionFileVO> reportTypeFilesList = new ArrayList<PetitionFileVO>(0);
	private Long workTypeId;
	private String workType;
	private String status;
	private Long statusId;
	private AddressVO addressVO;
	private Long locationScopeId;
	private Long locationValue;
	private Long uiSeriesNo;
	private List<PetitionHistoryVO> historyList = new ArrayList<PetitionHistoryVO>(0);
	
	public List<PetitionHistoryVO> getHistoryList() {
		return historyList;
	}
	public void setHistoryList(List<PetitionHistoryVO> historyList) {
		this.historyList = historyList;
	}
	public Long getUiSeriesNo() {
		return uiSeriesNo;
	}
	public void setUiSeriesNo(Long uiSeriesNo) {
		this.uiSeriesNo = uiSeriesNo;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getSubSubject() {
		return subSubject;
	}
	public void setSubSubject(String subSubject) {
		this.subSubject = subSubject;
	}
	public String getLeadName() {
		return leadName;
	}
	public void setLeadName(String leadName) {
		this.leadName = leadName;
	}
	public String getBriefLeadName() {
		return briefLeadName;
	}
	public void setBriefLeadName(String briefLeadName) {
		this.briefLeadName = briefLeadName;
	}
	public String getGrantName() {
		return grantName;
	}
	public void setGrantName(String grantName) {
		this.grantName = grantName;
	}
	public String getWorkType() {
		return workType;
	}
	public void setWorkType(String workType) {
		this.workType = workType;
	}
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
	public String getEstimateCost() {
		return estimateCost;
	}
	public void setEstimateCost(String estimateCost) {
		this.estimateCost = estimateCost;
	}
	public String geteOfficeId() {
		return eOfficeId;
	}
	public void seteOfficeId(String eOfficeId) {
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
	public Long getPmSubWorkDetailsId() {
		return pmSubWorkDetailsId;
	}
	public void setPmSubWorkDetailsId(Long pmSubWorkDetailsId) {
		this.pmSubWorkDetailsId = pmSubWorkDetailsId;
	}
	public Long getPmDocumentId() {
		return pmDocumentId;
	}
	public void setPmDocumentId(Long pmDocumentId) {
		this.pmDocumentId = pmDocumentId;
	}
	public Long getPmRepresenteeRefDetailsId() {
		return pmRepresenteeRefDetailsId;
	}
	public void setPmRepresenteeRefDetailsId(Long pmRepresenteeRefDetailsId) {
		this.pmRepresenteeRefDetailsId = pmRepresenteeRefDetailsId;
	}
	public Long getPmRepresenteeRefDocumentId() {
		return pmRepresenteeRefDocumentId;
	}
	public void setPmRepresenteeRefDocumentId(Long pmRepresenteeRefDocumentId) {
		this.pmRepresenteeRefDocumentId = pmRepresenteeRefDocumentId;
	}
	
	public Long getPetitionId() {
		return petitionId;
	}
	public void setPetitionId(Long petitionId) {
		this.petitionId = petitionId;
	}
	public String getEndorsmentNo() {
		return endorsmentNo;
	}
	public void setEndorsmentNo(String endorsmentNo) {
		this.endorsmentNo = endorsmentNo;
	}
	public String getEndorsmentDate() {
		return endorsmentDate;
	}
	public void setEndorsmentDate(String endorsmentDate) {
		this.endorsmentDate = endorsmentDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	public List<KeyValueVO> getSubjectsList() {
		return subjectsList;
	}
	public void setSubjectsList(List<KeyValueVO> subjectsList) {
		this.subjectsList = subjectsList;
	}
	public List<KeyValueVO> getSubSubjectsList() {
		return subSubjectsList;
	}
	public void setSubSubjectsList(List<KeyValueVO> subSubjectsList) {
		this.subSubjectsList = subSubjectsList;
	}
	public List<PetitionFileVO> getReportTypeFilesList() {
		return reportTypeFilesList;
	}
	public void setReportTypeFilesList(List<PetitionFileVO> reportTypeFilesList) {
		this.reportTypeFilesList = reportTypeFilesList;
	}
	public String getEstimateCostStr() {
		return estimateCostStr;
	}
	public void setEstimateCostStr(String estimateCostStr) {
		this.estimateCostStr = estimateCostStr;
	}
}
