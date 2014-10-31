package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class ReconciliationVO implements Serializable
{

	
	private static final long serialVersionUID = -5014645641468536800L;
	private Long 		cadreSurveyUserId;
	private Long 		constituencyId;
	private Long 		sinkedRecords;
	private Long 		pendingRecords;
	private Long 		totalAmount;
	private Long 		paidAmount;
	private Long 		pendingAmount;
	private String 		insertedTime; 
	private String		uniqueKey;
	private String 		mobileNo;
	
	
	public Long getCadreSurveyUserId() {
		return cadreSurveyUserId;
	}
	public void setCadreSurveyUserId(Long cadreSurveyUserId) {
		this.cadreSurveyUserId = cadreSurveyUserId;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public Long getSinkedRecords() {
		return sinkedRecords;
	}
	public void setSinkedRecords(Long sinkedRecords) {
		this.sinkedRecords = sinkedRecords;
	}
	public Long getPendingRecords() {
		return pendingRecords;
	}
	public void setPendingRecords(Long pendingRecords) {
		this.pendingRecords = pendingRecords;
	}
	public Long getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Long getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(Long paidAmount) {
		this.paidAmount = paidAmount;
	}
	public Long getPendingAmount() {
		return pendingAmount;
	}
	public void setPendingAmount(Long pendingAmount) {
		this.pendingAmount = pendingAmount;
	}
	public String getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(String insertedTime) {
		this.insertedTime = insertedTime;
	}
	public String getUniqueKey() {
		return uniqueKey;
	}
	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	
	
}
