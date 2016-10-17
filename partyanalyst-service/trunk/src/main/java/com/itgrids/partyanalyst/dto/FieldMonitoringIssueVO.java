package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class FieldMonitoringIssueVO implements Serializable{
	
	private Long issueTypeId;
	private Long cadreSurveyUserId;
	private Long tabUserInfoId;
	
	private Long  constituencyId;
	private String description;
	private Long  loginUserId;
	
	
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
	
	
}
