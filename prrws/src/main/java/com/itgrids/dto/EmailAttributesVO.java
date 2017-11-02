package com.itgrids.dto;

import java.io.Serializable;
import java.util.List;

public class EmailAttributesVO implements Serializable{
	
	private List<String> pdfNames;
	private List<String> images;
	private List<String> emailIds;
	private String       time;
	private String       subject;
	private String       bodyText;
	private String       day;
	private String 		 date;
	private String 		fileName;
	private String 		filePath;
	private Long 		emailId;
	
	public List<String> getPdfNames() {
		return pdfNames;
	}
	public void setPdfNames(List<String> pdfNames) {
		this.pdfNames = pdfNames;
	}
	public List<String> getImages() {
		return images;
	}
	public void setImages(List<String> images) {
		this.images = images;
	}
	public List<String> getEmailIds() {
		return emailIds;
	}
	public void setEmailIds(List<String> emailIds) {
		this.emailIds = emailIds;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBodyText() {
		return bodyText;
	}
	public void setBodyText(String bodyText) {
		this.bodyText = bodyText;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Long getEmailId() {
		return emailId;
	}
	public void setEmailId(Long emailId) {
		this.emailId = emailId;
	}
	
	
	
	
}
