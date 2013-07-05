package com.itgrids.electoralconnect.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
	private Date date;
	private String announcementName;
	private List<NotificationVO> notificationsList;
	private List<PressReleaseVO> pressReleasesList;
	private String fileName;
	private Long announcementId;
	private Long fileId;
	private Long announcementFileId;
	private String announcementTypeName;
	private int allAnnouncementsCount;
	private String dateString;
	private String title;
	private Long id;
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
	public List<NotificationVO> getNotificationsList() {
		return notificationsList;
	}
	public void setNotificationsList(List<NotificationVO> notificationsList) {
		this.notificationsList = notificationsList;
	}
	public List<PressReleaseVO> getPressReleasesList() {
		return pressReleasesList;
	}
	public void setPressReleasesList(List<PressReleaseVO> pressReleasesList) {
		this.pressReleasesList = pressReleasesList;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getAnnouncementName() {
		return announcementName;
	}
	public void setAnnouncementName(String announcementName) {
		this.announcementName = announcementName;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Long getAnnouncementId() {
		return announcementId;
	}
	public void setAnnouncementId(Long announcementId) {
		this.announcementId = announcementId;
	}
	public Long getFileId() {
		return fileId;
	}
	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}
	public Long getAnnouncementFileId() {
		return announcementFileId;
	}
	public void setAnnouncementFileId(Long announcementFileId) {
		this.announcementFileId = announcementFileId;
	}
	public String getAnnouncementTypeName() {
		return announcementTypeName;
	}
	public void setAnnouncementTypeName(String announcementTypeName) {
		this.announcementTypeName = announcementTypeName;
	}
	public int getAllAnnouncementsCount() {
		return allAnnouncementsCount;
	}
	public void setAllAnnouncementsCount(int allAnnouncementsCount) {
		this.allAnnouncementsCount = allAnnouncementsCount;
	}
	public String getDateString() {
		return dateString;
	}
	public void setDateString(String dateString) {
		this.dateString = dateString;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
		
	
	
}
