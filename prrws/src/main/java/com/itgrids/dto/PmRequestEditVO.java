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
	private String grievanceDescription;
	
	private List<KeyValueVO> fileList = new ArrayList<KeyValueVO>();
	private List<PmRequestVO> representeeDetailsList = new ArrayList<PmRequestVO>(0);	
	private List<PmRequestVO> referDetailsList = new ArrayList<PmRequestVO>(0);
	private List<PetitionsWorksVO> subWorksList = new ArrayList<PetitionsWorksVO>();
	
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
}
