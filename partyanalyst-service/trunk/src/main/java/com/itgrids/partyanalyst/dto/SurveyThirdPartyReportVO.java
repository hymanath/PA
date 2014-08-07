package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class SurveyThirdPartyReportVO implements Serializable {
	private Long boothId;
	private String partNo;
	private Long totalVoters;
	private Long thirdPartyStatusId;
	private String thirdPartyStatus;
	private List<SurveyThirdPartyReportVO> boothsList;
	private List<SurveyThirdPartyReportVO> statusList;
	
	private Long statusCount;
	private String statusPercentage;
	private Long userCollected;
	
	private Long statusId;
	private String statusName;
	
	private String userName;
	private Long userId;
	private String mobileNo;
	
	private List<SurveyThirdPartyReportVO> usersList;
	private SurveyThirdPartyReportVO users;
	
	private String boothType;
	
	
	
	public String getBoothType() {
		return boothType;
	}
	public void setBoothType(String boothType) {
		this.boothType = boothType;
	}
	public Long getBoothId() {
		return boothId;
	}
	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}
	public String getPartNo() {
		return partNo;
	}
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}
	public Long getTotalVoters() {
		return totalVoters;
	}
	public void setTotalVoters(Long totalVoters) {
		this.totalVoters = totalVoters;
	}
	public Long getThirdPartyStatusId() {
		return thirdPartyStatusId;
	}
	public void setThirdPartyStatusId(Long thirdPartyStatusId) {
		this.thirdPartyStatusId = thirdPartyStatusId;
	}
	public String getThirdPartyStatus() {
		return thirdPartyStatus;
	}
	public void setThirdPartyStatus(String thirdPartyStatus) {
		this.thirdPartyStatus = thirdPartyStatus;
	}
	public List<SurveyThirdPartyReportVO> getBoothsList() {
		return boothsList;
	}
	public void setBoothsList(List<SurveyThirdPartyReportVO> boothsList) {
		this.boothsList = boothsList;
	}
	public List<SurveyThirdPartyReportVO> getStatusList() {
		return statusList;
	}
	public void setStatusList(List<SurveyThirdPartyReportVO> statusList) {
		this.statusList = statusList;
	}
	
	public Long getStatusCount() {
		return statusCount;
	}
	public void setStatusCount(Long statusCount) {
		this.statusCount = statusCount;
	}
	public String getStatusPercentage() {
		return statusPercentage;
	}
	public void setStatusPercentage(String statusPercentage) {
		this.statusPercentage = statusPercentage;
	}
	
	public Long getUserCollected() {
		return userCollected;
	}
	public void setUserCollected(Long userCollected) {
		this.userCollected = userCollected;
	}
	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public List<SurveyThirdPartyReportVO> getUsersList() {
		return usersList;
	}
	public void setUsersList(List<SurveyThirdPartyReportVO> usersList) {
		this.usersList = usersList;
	}
	public SurveyThirdPartyReportVO getUsers() {
		return users;
	}
	public void setUsers(SurveyThirdPartyReportVO users) {
		this.users = users;
	}
	
	
	
	
	
}
