package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.Date;

public class PdfGenerationVO implements Serializable{
	
	
	
	private static final long serialVersionUID = 1L;
	
	
	private Long gallaryId ;
	private String gallaryName ;
	private Long sourceId ;
	private Long categoryId ;
	private Long impactLevelId ;
	private Long importanceId ;
	private Long languageId ;
	private String betweenDates ;
	private String startDate ;
	private String endDate ;
	private String allFiles;
	private String filePathToSave;
	private String pdfPath;
	private String pdfName;
	private String noFilesExist;
	private String imagePath;
	
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getNoFilesExist() {
		return noFilesExist;
	}
	public void setNoFilesExist(String noFilesExist) {
		this.noFilesExist = noFilesExist;
	}
	public String getPdfPath() {
		return pdfPath;
	}
	public void setPdfPath(String pdfPath) {
		this.pdfPath = pdfPath;
	}
	public String getPdfName() {
		return pdfName;
	}
	public void setPdfName(String pdfName) {
		this.pdfName = pdfName;
	}
	public String getAllFiles() {
		return allFiles;
	}
	public void setAllFiles(String allFiles) {
		this.allFiles = allFiles;
	}
	private Date startDateInDateFormat;
	private Date endDateInDateFormat;
	
	
	public Long getGallaryId() {
		return gallaryId;
	}
	public void setGallaryId(Long gallaryId) {
		this.gallaryId = gallaryId;
	}
	public String getGallaryName() {
		return gallaryName;
	}
	public void setGallaryName(String gallaryName) {
		this.gallaryName = gallaryName;
	}
	public Long getSourceId() {
		return sourceId;
	}
	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public Long getImpactLevelId() {
		return impactLevelId;
	}
	public void setImpactLevelId(Long impactLevelId) {
		this.impactLevelId = impactLevelId;
	}
	public Long getImportanceId() {
		return importanceId;
	}
	public void setImportanceId(Long importanceId) {
		this.importanceId = importanceId;
	}
	public Long getLanguageId() {
		return languageId;
	}
	public void setLanguageId(Long languageId) {
		this.languageId = languageId;
	}
	public String getBetweenDates() {
		return betweenDates;
	}
	public void setBetweenDates(String betweenDates) {
		this.betweenDates = betweenDates;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Date getStartDateInDateFormat() {
		return startDateInDateFormat;
	}
	public void setStartDateInDateFormat(Date startDateInDateFormat) {
		this.startDateInDateFormat = startDateInDateFormat;
	}
	public Date getEndDateInDateFormat() {
		return endDateInDateFormat;
	}
	public void setEndDateInDateFormat(Date endDateInDateFormat) {
		this.endDateInDateFormat = endDateInDateFormat;
	}
	public String getFilePathToSave() {
		return filePathToSave;
	}
	public void setFilePathToSave(String filePathToSave) {
		this.filePathToSave = filePathToSave;
	}

}
