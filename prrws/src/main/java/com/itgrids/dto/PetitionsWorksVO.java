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
	private Double estimateCost;
	private Long eOfficeId;
	private String remarks;
	private List<PetitionsWorksVO> subWorksList = new ArrayList<PetitionsWorksVO>();
	private List<MultipartFile> fileList = new ArrayList<MultipartFile>();
	private Long workTypeId;
	
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
