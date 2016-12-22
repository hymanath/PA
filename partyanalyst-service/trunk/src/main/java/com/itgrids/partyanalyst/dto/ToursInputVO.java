package com.itgrids.partyanalyst.dto;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ToursInputVO {
	private Long candidateId; 
	private Long candidateDtlsId;
	private String month; 
	private Long year;   
	private Long ownLocationScopeId; 
	private Long ownLocationId; 
	private Long ownTours; 
	private Long inchargeLocationScopeId;
	private Long inchargeLocationId;
	private Long inchargeTours;
	private String remarks;
	private String filePath;
	private Long insertedBy;
	private Long updatedBy;
	private String insertedTimeStr;
	private String updatedTimeStr;
	private Date insertedTime;
	private Date updatedTime;
	private String fileExtension;
	private File file;
	private Long oldFileStatus;
	private Long tourCategoryId;
	private Long tourTypeId;
	
	private Map<File,String> files = new HashMap<File,String>();
	public Long getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public Long getYear() {
		return year;
	}
	public void setYear(Long year) {
		this.year = year;
	}
	public Long getOwnLocationScopeId() {
		return ownLocationScopeId;
	}
	public void setOwnLocationScopeId(Long ownLocationScopeId) {
		this.ownLocationScopeId = ownLocationScopeId;
	}
	public Long getOwnLocationId() {
		return ownLocationId;
	}
	public void setOwnLocationId(Long ownLocationId) {
		this.ownLocationId = ownLocationId;
	}
	public Long getOwnTours() {
		return ownTours;
	}
	public void setOwnTours(Long ownTours) {
		this.ownTours = ownTours;
	}
	public Long getInchargeLocationScopeId() {
		return inchargeLocationScopeId;
	}
	public void setInchargeLocationScopeId(Long inchargeLocationScopeId) {
		this.inchargeLocationScopeId = inchargeLocationScopeId;
	}
	public Long getInchargeLocationId() {
		return inchargeLocationId;
	}
	public void setInchargeLocationId(Long inchargeLocationId) {
		this.inchargeLocationId = inchargeLocationId;
	}
	public Long getInchargeTours() {
		return inchargeTours;
	}
	public void setInchargeTours(Long inchargeTours) {
		this.inchargeTours = inchargeTours;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Long getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
	}
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getInsertedTimeStr() {
		return insertedTimeStr;
	}
	public void setInsertedTimeStr(String insertedTimeStr) {
		this.insertedTimeStr = insertedTimeStr;
	}
	public String getUpdatedTimeStr() {
		return updatedTimeStr;
	}
	public void setUpdatedTimeStr(String updatedTimeStr) {
		this.updatedTimeStr = updatedTimeStr;
	}
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	public String getFileExtension() {
		return fileExtension;
	}
	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public Map<File, String> getFiles() {
		return files;
	}
	public void setFiles(Map<File, String> files) {    
		this.files = files;
	}
	public Long getCandidateDtlsId() {
		return candidateDtlsId;
	}
	public void setCandidateDtlsId(Long candidateDtlsId) {
		this.candidateDtlsId = candidateDtlsId;
	}
	public Long getOldFileStatus() {
		return oldFileStatus;
	}
	public void setOldFileStatus(Long oldFileStatus) {
		this.oldFileStatus = oldFileStatus;  
	}
	public Long getTourCategoryId() {
		return tourCategoryId;
	}
	public void setTourCategoryId(Long tourCategoryId) {
		this.tourCategoryId = tourCategoryId;
	}
	public Long getTourTypeId() {
		return tourTypeId;
	}
	public void setTourTypeId(Long tourTypeId) {
		this.tourTypeId = tourTypeId;
	}
	
	
};
