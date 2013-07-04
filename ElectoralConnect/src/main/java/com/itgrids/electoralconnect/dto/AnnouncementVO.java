package com.itgrids.electoralconnect.dto;

import java.io.Serializable;
import java.util.Date;

public class AnnouncementVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3570674128096766897L;
	private String name;
	private String description;
	private String updatedBy;
	private String fileDate;
	private Date updatedDate;
	private Long announcementType;
	private String fileTitle;
	private String fileDescription;
	private String filePath;
	private String fileName;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getFileDate() {
		return fileDate;
	}
	public void setFileDate(String fileDate) {
		this.fileDate = fileDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public Long getAnnouncementType() {
		return announcementType;
	}
	public void setAnnouncementType(Long announcementType) {
		this.announcementType = announcementType;
	}
	public String getFileTitle() {
		return fileTitle;
	}
	public void setFileTitle(String fileTitle) {
		this.fileTitle = fileTitle;
	}
	public String getFileDescription() {
		return fileDescription;
	}
	public void setFileDescription(String fileDescription) {
		this.fileDescription = fileDescription;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
	
}
