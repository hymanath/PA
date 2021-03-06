package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class FieldMonitoringIssueVO implements Serializable{
	
	private Long issueTypeId;
	private String issueType;
	private Long cadreSurveyUserId;
	private Long tabUserInfoId;
	
	private Long  constituencyId;
	private String description;
	private Long  loginUserId;
	
	private Long cadreRegIssueId;
	private String dateStr;
	
	private Long issueStatusId;
	private String issueStatus;
	private String firstname;
	private String lastname;
	private Long count;
	private String userName;
	private String updatedTime;
	private String mandalId;
	private String mobileNo;
	
	public Long getIssueTypeId() {
		return issueTypeId;
	}
	public void setIssueTypeId(Long issueTypeId) {
		this.issueTypeId = issueTypeId;
	}
	public Long getCadreSurveyUserId() {
		return cadreSurveyUserId;
	}
	public void setCadreSurveyUserId(Long cadreSurveyUserId) {
		this.cadreSurveyUserId = cadreSurveyUserId;
	}
	public Long getTabUserInfoId() {
		return tabUserInfoId;
	}
	public void setTabUserInfoId(Long tabUserInfoId) {
		this.tabUserInfoId = tabUserInfoId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Long getLoginUserId() {
		return loginUserId;
	}
	public void setLoginUserId(Long loginUserId) {
		this.loginUserId = loginUserId;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public Long getCadreRegIssueId() {
		return cadreRegIssueId;
	}
	public void setCadreRegIssueId(Long cadreRegIssueId) {
		this.cadreRegIssueId = cadreRegIssueId;
	}
	public String getDateStr() {
		return dateStr;
	}
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
	public String getIssueType() {
		return issueType;
	}
	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}
	public Long getIssueStatusId() {
		return issueStatusId;
	}
	public void setIssueStatusId(Long issueStatusId) {
		this.issueStatusId = issueStatusId;
	}
	public String getIssueStatus() {
		return issueStatus;
	}
	public void setIssueStatus(String issueStatus) {
		this.issueStatus = issueStatus;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}
	public String getMandalId() {
		return mandalId;
	}
	public void setMandalId(String mandalId) {
		this.mandalId = mandalId;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	
}
