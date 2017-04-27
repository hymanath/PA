package com.itgrids.partyanalyst.dto;


public class GovtOfficerSmsDetailsVO {
	private Long alertGovtOfficerSmsDetailsId;
	private Long userId;
	private Long govtOfficerId;
	private String mobileNo;
	
	private Long alertId;
	private Long alertStatusId;
	private String smsText;
	private String insertTime;
	private Long actionTypeId;
	private Long govtSubTaskId;
	public Long getAlertGovtOfficerSmsDetailsId() {
		return alertGovtOfficerSmsDetailsId;
	}
	public void setAlertGovtOfficerSmsDetailsId(Long alertGovtOfficerSmsDetailsId) {
		this.alertGovtOfficerSmsDetailsId = alertGovtOfficerSmsDetailsId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getGovtOfficerId() {
		return govtOfficerId;
	}
	public void setGovtOfficerId(Long govtOfficerId) {
		this.govtOfficerId = govtOfficerId;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public Long getAlertId() {
		return alertId;
	}
	public void setAlertId(Long alertId) {
		this.alertId = alertId;
	}
	public Long getAlertStatusId() {
		return alertStatusId;
	}
	public void setAlertStatusId(Long alertStatusId) {
		this.alertStatusId = alertStatusId;
	}
	public String getSmsText() {
		return smsText;
	}
	public void setSmsText(String smsText) {
		this.smsText = smsText;
	}
	public String getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}
	public Long getActionTypeId() {
		return actionTypeId;
	}
	public void setActionTypeId(Long actionTypeId) {
		this.actionTypeId = actionTypeId;
	}
	public Long getGovtSubTaskId() {
		return govtSubTaskId;
	}
	public void setGovtSubTaskId(Long govtSubTaskId) {
		this.govtSubTaskId = govtSubTaskId;
	}
	
	

}
