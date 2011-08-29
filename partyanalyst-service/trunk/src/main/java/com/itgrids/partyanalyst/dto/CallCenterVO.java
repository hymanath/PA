package com.itgrids.partyanalyst.dto;

import java.util.Date;

public class CallCenterVO {
	
	private String name ;
	private String refNum ;
	private String mobileNum ;
	private String emailId ;
	private Date fromDate ;
	private Date toDate;
	private Long userId;
	
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

}
