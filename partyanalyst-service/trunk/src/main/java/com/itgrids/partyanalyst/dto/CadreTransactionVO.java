package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class CadreTransactionVO {

	private Long id;
	private String name;
	private Long constituencyId;
	private Long sinkedRecords =0l;
	private Long pendingRecords =0l;
	private Long totalAmount =0l;
	private Long paidAmount =0l;
	private Long pendingAmount =0l;
	private String insertedTime;
	private String updatedTime;
	private String surveyTime;
	private String txnStatus;
	private String completeStatus;
	private String uniqueKey;
	private String mobileNo;
	private Long userId;
	private String txnNo;
	private String message;
	private String otpNo;
	private String refNo;
	
	private List<CadreRegistrationVO> cadreRegistrationVOList = new ArrayList<CadreRegistrationVO>();
	
				
	public List<CadreRegistrationVO> getCadreRegistrationVOList() {
		return cadreRegistrationVOList;
	}
	public void setCadreRegistrationVOList(
			List<CadreRegistrationVO> cadreRegistrationVOList) {
		this.cadreRegistrationVOList = cadreRegistrationVOList;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}
	public String getSurveyTime() {
		return surveyTime;
	}
	public void setSurveyTime(String surveyTime) {
		this.surveyTime = surveyTime;
	}
	public String getTxnStatus() {
		return txnStatus;
	}
	public void setTxnStatus(String txnStatus) {
		this.txnStatus = txnStatus;
	}
	public String getCompleteStatus() {
		return completeStatus;
	}
	public void setCompleteStatus(String completeStatus) {
		this.completeStatus = completeStatus;
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
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getTxnNo() {
		return txnNo;
	}
	public void setTxnNo(String txnNo) {
		this.txnNo = txnNo;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getOtpNo() {
		return otpNo;
	}
	public void setOtpNo(String otpNo) {
		this.otpNo = otpNo;
	}
	public String getRefNo() {
		return refNo;
	}
	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}
	
	
	
}
