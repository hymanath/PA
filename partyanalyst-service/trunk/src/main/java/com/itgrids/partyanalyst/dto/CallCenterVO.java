package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CallCenterVO {
	
	private String name ;
	private String refNum ;
	private String mobileNum ;
	private String emailId ;
	private Date fromDate ;
	private Date toDate;
	private Long userId;
	private String firstName;
	private String userName;
	
	
	private String imagePath;
	private String loginTime;
	private String logoutTime;
	private String totalHours;
	private Long noOfAlertCreated;
	
	
	private Long totalAgent;
	private String totalTime;
	private Long totalAlert;
	private Long attendedCount;
	
	private CallCenterVO callCenterVO;
	private List<CallCenterVO> callCenterVOList = null;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRefNum() {
		return refNum;
	}
	public void setRefNum(String refNum) {
		this.refNum = refNum;
	}
	public String getMobileNum() {
		return mobileNum;
	}
	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getUserId() {
		return userId;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	public String getLogoutTime() {
		return logoutTime;
	}
	public void setLogoutTime(String logoutTime) {
		this.logoutTime = logoutTime;
	}
	public String getTotalHours() {
		return totalHours;
	}
	public void setTotalHours(String totalHours) {
		this.totalHours = totalHours;
	}
	public Long getNoOfAlertCreated() {
		return noOfAlertCreated;
	}
	public void setNoOfAlertCreated(Long noOfAlertCreated) {
		this.noOfAlertCreated = noOfAlertCreated;
	}
	public Long getTotalAgent() {
		return totalAgent;
	}
	public void setTotalAgent(Long totalAgent) {
		this.totalAgent = totalAgent;
	}
	public String getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
	}
	public Long getTotalAlert() {
		return totalAlert;
	}
	public void setTotalAlert(Long totalAlert) {
		this.totalAlert = totalAlert;
	}
	public CallCenterVO getCallCenterVO() {
		return callCenterVO;
	}
	public void setCallCenterVO(CallCenterVO callCenterVO) {
		this.callCenterVO = callCenterVO;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<CallCenterVO> getCallCenterVOList() {
		if(callCenterVOList == null){
			callCenterVOList = new ArrayList<CallCenterVO>(); 
		}
		return callCenterVOList;
	}
	public Long getAttendedCount() {
		return attendedCount;
	}
	public void setAttendedCount(Long attendedCount) {
		this.attendedCount = attendedCount;
	}
	
	
}
