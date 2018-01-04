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
	private Long alertTypeId;
	private Long alertImpactScopeId;
	private Long actionTypeId;
	private Long actionTypeStatusId;
	private String fromDate2;
	private String toDate2;
	private String searchType;
	private String verificationUserType;
	public Long getAlertTypeId() {
		return alertTypeId;
	}
	public void setAlertTypeId(Long alertTypeId) {
		this.alertTypeId = alertTypeId;
	}
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
	public Long getAlertImpactScopeId() {
		return alertImpactScopeId;
	}
	public void setAlertImpactScopeId(Long alertImpactScopeId) {
		this.alertImpactScopeId = alertImpactScopeId;
	}
	public Long getActionTypeId() {
		return actionTypeId;
	}
	public void setActionTypeId(Long actionTypeId) {
		this.actionTypeId = actionTypeId;
	}
	public Long getActionTypeStatusId() {
		return actionTypeStatusId;
	}
	public void setActionTypeStatusId(Long actionTypeStatusId) {
		this.actionTypeStatusId = actionTypeStatusId;
	}
	public String getFromDate2() {
		return fromDate2;
	}
	public void setFromDate2(String fromDate2) {
		this.fromDate2 = fromDate2;
	}
	public String getToDate2() {
		return toDate2;
	}
	public void setToDate2(String toDate2) {
		this.toDate2 = toDate2;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getVerificationUserType() {
		return verificationUserType;
	}
	public void setVerificationUserType(String verificationUserType) {
		this.verificationUserType = verificationUserType;
	}
	

}
