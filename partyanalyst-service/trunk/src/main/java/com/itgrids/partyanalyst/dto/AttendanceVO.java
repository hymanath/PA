package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class AttendanceVO implements Serializable{

	private static final long serialVersionUID = -6548396594726688141L;
	
	private Long partyMeetingId;
	private Long trainingCampScheduleId;
	private Long trainingCampBatchId;
	private Long trainingCampTopicId;
	private Long trainingProgramId;
	private String membershipId;
	private String attendedTime;
	private String rfid;
	private String imei;
	private String uniqueKey;
	private String latitude;
	private String longitude;
	private Long tabUserId;
	private Long currentTabUserId;
	private String errorDescription;
	private String syncSource;
	private String type;
	private String status;
	private Long tdpCadreId;
	private Long tabPrimaryKey;
	private Long serverPrimaryKey;
	private Long sessionId;
	
	public Long getSessionId() {
		return sessionId;
	}
	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}
	public Long getTabPrimaryKey() {
		return tabPrimaryKey;
	}
	public void setTabPrimaryKey(Long tabPrimaryKey) {
		this.tabPrimaryKey = tabPrimaryKey;
	}
	public Long getServerPrimaryKey() {
		return serverPrimaryKey;
	}
	public void setServerPrimaryKey(Long serverPrimaryKey) {
		this.serverPrimaryKey = serverPrimaryKey;
	}
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSyncSource() {
		return syncSource;
	}
	public void setSyncSource(String syncSource) {
		this.syncSource = syncSource;
	}
	public String getErrorDescription() {
		return errorDescription;
	}
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
	public Long getPartyMeetingId() {
		return partyMeetingId;
	}
	public void setPartyMeetingId(Long partyMeetingId) {
		this.partyMeetingId = partyMeetingId;
	}
	public Long getTrainingCampScheduleId() {
		return trainingCampScheduleId;
	}
	public void setTrainingCampScheduleId(Long trainingCampScheduleId) {
		this.trainingCampScheduleId = trainingCampScheduleId;
	}
	public Long getTrainingCampBatchId() {
		return trainingCampBatchId;
	}
	public void setTrainingCampBatchId(Long trainingCampBatchId) {
		this.trainingCampBatchId = trainingCampBatchId;
	}
	public Long getTrainingCampTopicId() {
		return trainingCampTopicId;
	}
	public void setTrainingCampTopicId(Long trainingCampTopicId) {
		this.trainingCampTopicId = trainingCampTopicId;
	}
	public String getMembershipId() {
		return membershipId;
	}
	public void setMembershipId(String membershipId) {
		this.membershipId = membershipId;
	}
	public String getAttendedTime() {
		return attendedTime;
	}
	public void setAttendedTime(String attendedTime) {
		this.attendedTime = attendedTime;
	}
	public String getRfid() {
		return rfid;
	}
	public void setRfid(String rfid) {
		this.rfid = rfid;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getUniqueKey() {
		return uniqueKey;
	}
	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public Long getTabUserId() {
		return tabUserId;
	}
	public void setTabUserId(Long tabUserId) {
		this.tabUserId = tabUserId;
	}
	public Long getCurrentTabUserId() {
		return currentTabUserId;
	}
	public void setCurrentTabUserId(Long currentTabUserId) {
		this.currentTabUserId = currentTabUserId;
	}
	public Long getTrainingProgramId() {
		return trainingProgramId;
	}
	public void setTrainingProgramId(Long trainingProgramId) {
		this.trainingProgramId = trainingProgramId;
	}
}
