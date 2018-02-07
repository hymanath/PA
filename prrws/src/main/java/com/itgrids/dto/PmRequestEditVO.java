package com.itgrids.dto;

import java.util.ArrayList;
import java.util.List;

public class PmRequestEditVO {

	private Long petitionId;
	private String endorsmentNo;
	private String endorsmentDate;
	private String representationType;
	private String representationdate;
	private Long workId;
	private String workName;
	private Long noOfWorks;
	private String estimateCost;
	private String estimateCostStr;
	private String grievanceDescription;
	private String isOldData;
	private List<KeyValueVO> coveringLetterPathsList = new ArrayList<KeyValueVO>(0);
	private List<KeyValueVO> fileList = new ArrayList<KeyValueVO>();
	private List<KeyValueVO> allFileList = new ArrayList<KeyValueVO>();
	private List<PmRequestVO> representeeDetailsList = new ArrayList<PmRequestVO>(0);	
	private List<PmRequestVO> referDetailsList = new ArrayList<PmRequestVO>(0);
	private List<PetitionsWorksVO> subWorksList = new ArrayList<PetitionsWorksVO>();
	private List<PetitionFileVO> reportTypeFilesList = new ArrayList<PetitionFileVO>(0);
	private List<KeyValueVO> statusList = new ArrayList<KeyValueVO>(0);
	private List<KeyValueVO> deptList = new ArrayList<KeyValueVO>(0);
	private List<PetitionHistoryVO> historyList = new ArrayList<PetitionHistoryVO>(0);
	private Long statusId;
	private String actionType;
	private String worksStatus;
	
	public String getWorksStatus() {
		return worksStatus;
	}
	public void setWorksStatus(String worksStatus) {
		this.worksStatus = worksStatus;
	}
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public List<KeyValueVO> getAllFileList() {
		return allFileList;
	}
	public void setAllFileList(List<KeyValueVO> allFileList) {
		this.allFileList = allFileList;
	}
	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	public List<PetitionHistoryVO> getHistoryList() {
		return historyList;
	}
	public void setHistoryList(List<PetitionHistoryVO> historyList) {
		this.historyList = historyList;
	}
	public List<KeyValueVO> getDeptList() {
		return deptList;
	}
	public void setDeptList(List<KeyValueVO> deptList) {
		this.deptList = deptList;
	}
	public List<KeyValueVO> getStatusList() {
		return statusList;
	}
	public void setStatusList(List<KeyValueVO> statusList) {
		this.statusList = statusList;
	}
	
	public List<PetitionFileVO> getReportTypeFilesList() {
		return reportTypeFilesList;
	}
	public void setReportTypeFilesList(List<PetitionFileVO> reportTypeFilesList) {
		this.reportTypeFilesList = reportTypeFilesList;
	}
	public List<KeyValueVO> getCoveringLetterPathsList() {
		return coveringLetterPathsList;
	}
	public void setCoveringLetterPathsList(List<KeyValueVO> coveringLetterPathsList) {
		this.coveringLetterPathsList = coveringLetterPathsList;
	}
	public String getEstimateCost() {
		return estimateCost;
	}
	public void setEstimateCost(String estimateCost) {
		this.estimateCost = estimateCost;
	}
	public String getRepresentationType() {
		return representationType;
	}
	public void setRepresentationType(String representationType) {
		this.representationType = representationType;
	}
	public String getRepresentationdate() {
		return representationdate;
	}
	public void setRepresentationdate(String representationdate) {
		this.representationdate = representationdate;
	}
	public List<PmRequestVO> getRepresenteeDetailsList() {
		return representeeDetailsList;
	}
	public void setRepresenteeDetailsList(List<PmRequestVO> representeeDetailsList) {
		this.representeeDetailsList = representeeDetailsList;
	}
	public List<PetitionsWorksVO> getSubWorksList() {
		return subWorksList;
	}
	public void setSubWorksList(List<PetitionsWorksVO> subWorksList) {
		this.subWorksList = subWorksList;
	}
	public List<KeyValueVO> getFileList() {
		return fileList;
	}
	public void setFileList(List<KeyValueVO> fileList) {
		this.fileList = fileList;
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
	public List<PmRequestVO> getReferDetailsList() {
		return referDetailsList;
	}
	public void setReferDetailsList(List<PmRequestVO> referDetailsList) {
		this.referDetailsList = referDetailsList;
	}
	public String getEstimateCostStr() {
		return estimateCostStr;
	}
	public void setEstimateCostStr(String estimateCostStr) {
		this.estimateCostStr = estimateCostStr;
	}
	public String getIsOldData() {
		return isOldData;
	}
	public void setIsOldData(String isOldData) {
		this.isOldData = isOldData;
	}
}
