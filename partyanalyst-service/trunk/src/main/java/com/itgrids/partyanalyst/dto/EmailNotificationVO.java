package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EmailNotificationVO implements Serializable {
   /**
	 * 
	 */
	private static final long serialVersionUID = -7354846330897302533L;
private int newspresent = 0;
   private int photopresent = 0;
   private int videopresent = 0;
   private Long id;
   private String name;
   
   private String title;   
   private Long fileGallaryId;
   private String gallaryName;
   private String source;
   private String language;
   private String description;

   
   private List<EmailNotificationVO> news = new ArrayList<EmailNotificationVO>();
   
   private List<EmailNotificationVO> photos = new ArrayList<EmailNotificationVO>();
   
   private List<EmailNotificationVO> videos = new ArrayList<EmailNotificationVO>();

public int getNewspresent() {
	return newspresent;
}

public void setNewspresent(int newspresent) {
	this.newspresent = newspresent;
}

public int getPhotopresent() {
	return photopresent;
}

public void setPhotopresent(int photopresent) {
	this.photopresent = photopresent;
}

public int getVideopresent() {
	return videopresent;
}

public void setVideopresent(int videopresent) {
	this.videopresent = videopresent;
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public Long getFileGallaryId() {
	return fileGallaryId;
}

public void setFileGallaryId(Long fileGallaryId) {
	this.fileGallaryId = fileGallaryId;
}

public String getSource() {
	return source;
}

public void setSource(String source) {
	this.source = source;
}

public String getLanguage() {
	return language;
}

public void setLanguage(String language) {
	this.language = language;
}

public List<EmailNotificationVO> getNews() {
	return news;
}

public void setNews(List<EmailNotificationVO> news) {
	this.news = news;
}

public List<EmailNotificationVO> getPhotos() {
	return photos;
}

public void setPhotos(List<EmailNotificationVO> photos) {
	this.photos = photos;
}

public List<EmailNotificationVO> getVideos() {
	return videos;
}

public void setVideos(List<EmailNotificationVO> videos) {
	this.videos = videos;
}

public String getGallaryName() {
	return gallaryName;
}

public void setGallaryName(String gallaryName) {
	this.gallaryName = gallaryName;
}

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
   
   
}
