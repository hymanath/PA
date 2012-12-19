package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.Date;

public class UserProfileVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7901379330975717919L;

	private String pageType; //party,candidate,specialpage
	private String type; //news,photos,videos
	private String title; //file title
	private String description; //file desc
	private String headerTitle; 
	private String name;
	private Long id;
	private String imageUrl;
	private String fileLink;
	private String dateDiff;
	private Long fileGallaryId;
	private Date date;
	
	
	public String getPageType() {
		return pageType;
	}
	public void setPageType(String pageType) {
		this.pageType = pageType;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getHeaderTitle() {
		return headerTitle;
	}
	public void setHeaderTitle(String headerTitle) {
		this.headerTitle = headerTitle;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getFileLink() {
		return fileLink;
	}
	public void setFileLink(String fileLink) {
		this.fileLink = fileLink;
	}
	public String getDateDiff() {
		return dateDiff;
	}
	public void setDateDiff(String dateDiff) {
		this.dateDiff = dateDiff;
	}
	public Long getFileGallaryId() {
		return fileGallaryId;
	}
	public void setFileGallaryId(Long fileGallaryId) {
		this.fileGallaryId = fileGallaryId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
