package com.itgrids.partyanalyst.dto;

public class AlertInputVO {
	private Long levelId;
	private Long statusId;
	private String fromDate;
	private String toDate;
	private Long levelValue;
	private Long categoryId;	
	private Long assignId;
	private String searchTypeStr;
	
	
	public String getSearchTypeStr() {
		return searchTypeStr;
	}
	public void setSearchTypeStr(String searchTypeStr) {
		this.searchTypeStr = searchTypeStr;
	}
	public Long getLevelValue() {
		return levelValue;
	}
	public void setLevelValue(Long levelValue) {
		this.levelValue = levelValue;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public Long getAssignId() {
		return assignId;
	}
	public void setAssignId(Long assignId) {
		this.assignId = assignId;
	}
	public Long getLevelId() {
		return levelId;
	}
	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}
	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	

}
