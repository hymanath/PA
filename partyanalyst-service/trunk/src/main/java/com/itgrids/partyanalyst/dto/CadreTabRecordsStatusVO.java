package com.itgrids.partyanalyst.dto;

import java.io.Serializable;


public class CadreTabRecordsStatusVO implements java.io.Serializable{

	private Long cadreSurveyUserId;
	private Long tabUserInfoId;
	private String imeiNo;
	private String lattitude;
	private String longittude;
	private Long totalRecords;
	private Long sync;
	private Long pending;
	private String surveyDate;
	private String name;
	private String mobileNo;
	private String firstRecord;
	private String lastRecord;
	private Long totalAmount;
	private String minRecordTime;
	private String maxRecordTime;
	private Long kafkaPending;
	private Long kafkaSync;
	private String totalImeiNo;
	private Long sumRecords;
	private Long totalSyn;
	private Long totalPending;
	
	private Long actualCount;

	
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
	public String getImeiNo() {
		return imeiNo;
	}
	public void setImeiNo(String imeiNo) {
		this.imeiNo = imeiNo;
	}
	public String getLattitude() {
		return lattitude;
	}
	public void setLattitude(String lattitude) {
		this.lattitude = lattitude;
	}
	public String getLongittude() {
		return longittude;
	}
	public void setLongittude(String longittude) {
		this.longittude = longittude;
	}
	public Long getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(Long totalRecords) {
		this.totalRecords = totalRecords;
	}
	public Long getSync() {
		return sync;
	}
	public void setSync(Long sync) {
		this.sync = sync;
	}
	public Long getPending() {
		return pending;
	}
	public void setPending(Long pending) {
		this.pending = pending;
	}
	public String getSurveyDate() {
		return surveyDate;
	}
	public void setSurveyDate(String surveyDate) {
		this.surveyDate = surveyDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getFirstRecord() {
		return firstRecord;
	}
	public void setFirstRecord(String firstRecord) {
		this.firstRecord = firstRecord;
	}
	public String getLastRecord() {
		return lastRecord;
	}
	public void setLastRecord(String lastRecord) {
		this.lastRecord = lastRecord;
	}
	public Long getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public String getMinRecordTime() {
		return minRecordTime;
	}
	public void setMinRecordTime(String minRecordTime) {
		this.minRecordTime = minRecordTime;
	}
	public String getMaxRecordTime() {
		return maxRecordTime;
	}
	public void setMaxRecordTime(String maxRecordTime) {
		this.maxRecordTime = maxRecordTime;
	}
	public Long getKafkaPending() {
		return kafkaPending;
	}
	public void setKafkaPending(Long kafkaPending) {
		this.kafkaPending = kafkaPending;
	}	
	public Long getKafkaSync() {
		return kafkaSync;
	}
	public void setKafkaSync(Long kafkaSync) {
		this.kafkaSync = kafkaSync;
	}
	public String getTotalImeiNo() {
		return totalImeiNo;
	}
	public void setTotalImeiNo(String totalImeiNo) {
		this.totalImeiNo = totalImeiNo;
	}
	public Long getSumRecords() {
		return sumRecords;
	}
	public void setSumRecords(Long sumRecords) {
		this.sumRecords = sumRecords;
	}
	public Long getTotalSyn() {
		return totalSyn;
	}
	public void setTotalSyn(Long totalSyn) {
		this.totalSyn = totalSyn;
	}
	public Long getTotalPending() {
		return totalPending;
	}
	public void setTotalPending(Long totalPending) {
		this.totalPending = totalPending;
	}
	public Long getActualCount() {
		return actualCount;
	}
	public void setActualCount(Long actualCount) {
		this.actualCount = actualCount;
	}
	
	
	
}
