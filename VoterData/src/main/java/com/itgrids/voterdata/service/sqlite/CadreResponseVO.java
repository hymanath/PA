package com.itgrids.voterdata.service.sqlite;

import java.io.Serializable;

public class CadreResponseVO implements Serializable{
	
	private static final long serialVersionUID = 2389557435175002120L;
	
	private Long backupId;
	private String refNo;
	private String dataSourceType;
	private String surveyTime;
	private String exceptionText;
	private String responseJson;
	private Long tdpCadreId;
	private Long voterId;
	private Long familyVoterId;
	private Long tabUserId;
	private Long tabUserInfoId;
	private Long webUserId;
	private Long serverPrimaryKey;
	private String userStatus;
	private String uniqueKey;
	private String saveStatus;
	private String imageSaveStatus;
	private String imageIssue;
	private Long tabPrimaryKey;
	private String requestedTime;
	private String synkType;
	private String imei;
	private String deviceName;
	private String appVersion;
	
	private String simSerialNo;
	private String memberShipNo;
	
	
	public String getSimSerialNo() {
		return simSerialNo;
	}
	public void setSimSerialNo(String simSerialNo) {
		this.simSerialNo = simSerialNo;
	}
	public String getMemberShipNo() {
		return memberShipNo;
	}
	public void setMemberShipNo(String memberShipNo) {
		this.memberShipNo = memberShipNo;
	}
	public Long getTabPrimaryKey() {
		return tabPrimaryKey;
	}
	public void setTabPrimaryKey(Long tabPrimaryKey) {
		this.tabPrimaryKey = tabPrimaryKey;
	}
	public String getRefNo() {
		return refNo;
	}
	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}
	public String getDataSourceType() {
		return dataSourceType;
	}
	public void setDataSourceType(String dataSourceType) {
		this.dataSourceType = dataSourceType;
	}
	public String getSurveyTime() {
		return surveyTime;
	}
	public void setSurveyTime(String surveyTime) {
		this.surveyTime = surveyTime;
	}
	public String getExceptionText() {
		return exceptionText;
	}
	public void setExceptionText(String exceptionText) {
		this.exceptionText = exceptionText;
	}
	public String getResponseJson() {
		return responseJson;
	}
	public void setResponseJson(String responseJson) {
		this.responseJson = responseJson;
	}
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	public Long getVoterId() {
		return voterId;
	}
	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}
	public Long getFamilyVoterId() {
		return familyVoterId;
	}
	public void setFamilyVoterId(Long familyVoterId) {
		this.familyVoterId = familyVoterId;
	}
	public Long getTabUserId() {
		return tabUserId;
	}
	public void setTabUserId(Long tabUserId) {
		this.tabUserId = tabUserId;
	}
	public Long getTabUserInfoId() {
		return tabUserInfoId;
	}
	public void setTabUserInfoId(Long tabUserInfoId) {
		this.tabUserInfoId = tabUserInfoId;
	}
	public Long getWebUserId() {
		return webUserId;
	}
	public void setWebUserId(Long webUserId) {
		this.webUserId = webUserId;
	}
	public Long getServerPrimaryKey() {
		return serverPrimaryKey;
	}
	public void setServerPrimaryKey(Long serverPrimaryKey) {
		this.serverPrimaryKey = serverPrimaryKey;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public String getUniqueKey() {
		return uniqueKey;
	}
	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
	public String getSaveStatus() {
		return saveStatus;
	}
	public void setSaveStatus(String saveStatus) {
		this.saveStatus = saveStatus;
	}
	public String getImageSaveStatus() {
		return imageSaveStatus;
	}
	public void setImageSaveStatus(String imageSaveStatus) {
		this.imageSaveStatus = imageSaveStatus;
	}
	public String getImageIssue() {
		return imageIssue;
	}
	public void setImageIssue(String imageIssue) {
		this.imageIssue = imageIssue;
	}
	public Long getBackupId() {
		return backupId;
	}
	public void setBackupId(Long backupId) {
		this.backupId = backupId;
	}
	
	public String getRequestedTime() {
		return requestedTime;
	}
	public void setRequestedTime(String requestedTime) {
		this.requestedTime = requestedTime;
	}
	public String getSynkType() {
		return synkType;
	}
	public void setSynkType(String synkType) {
		this.synkType = synkType;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getAppVersion() {
		return appVersion;
	}
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	
}
