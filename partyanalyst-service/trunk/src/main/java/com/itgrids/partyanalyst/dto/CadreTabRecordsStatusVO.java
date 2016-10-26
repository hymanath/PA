package com.itgrids.partyanalyst.dto;

import java.io.Serializable;


public class CadreTabRecordsStatusVO implements Serializable{

	private Long cadreSurveyUserId;
	private Long tabUserInfoId;
	private String imeiNo;
	private String lattitude;
	private String longittude;
	private Long totalRecords;
	private Long sync;
	private Long pending;
	private String surveyDate;
	private String minRecordTime;
	private String maxRecordTime;
	private Long kafkaPending;
	private Long serverPending;
	
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
	public Long getServerPending() {
		return serverPending;
	}
	public void setServerPending(Long serverPending) {
		this.serverPending = serverPending;
	}
	
	
	
}
