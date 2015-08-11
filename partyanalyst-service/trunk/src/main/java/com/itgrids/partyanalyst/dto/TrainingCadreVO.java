package com.itgrids.partyanalyst.dto;

public class TrainingCadreVO {
	private Long batchId;
	private Long programId;
	private Long scheduleId;
	private Long campId;
	private String ramarks;
	private Long callStatusId;
	private Long scheduleStatusId;
	private String status;
	private Long tdpCadreId;
	private Long userId;
	private Long invitteId;
	private Long inviteeCallerId;
	private String callBackTime;
	private String callBackDate;
	
	
	
	
	public String getCallBackDate() {
		return callBackDate;
	}
	public void setCallBackDate(String callBackDate) {
		this.callBackDate = callBackDate;
	}
	
	
	public String getCallBackTime() {
		return callBackTime;
	}
	public void setCallBackTime(String callBackTime) {
		this.callBackTime = callBackTime;
	}
	public Long getInvitteId() {
		return invitteId;
	}
	public void setInvitteId(Long invitteId) {
		this.invitteId = invitteId;
	}
	public Long getInviteeCallerId() {
		return inviteeCallerId;
	}
	public void setInviteeCallerId(Long inviteeCallerId) {
		this.inviteeCallerId = inviteeCallerId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	public Long getBatchId() {
		return batchId;
	}
	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}
	public Long getProgramId() {
		return programId;
	}
	public void setProgramId(Long programId) {
		this.programId = programId;
	}
	public Long getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(Long scheduleId) {
		this.scheduleId = scheduleId;
	}
	public Long getCampId() {
		return campId;
	}
	public void setCampId(Long campId) {
		this.campId = campId;
	}
	public String getRamarks() {
		return ramarks;
	}
	public void setRamarks(String ramarks) {
		this.ramarks = ramarks;
	}
	public Long getCallStatusId() {
		return callStatusId;
	}
	public void setCallStatusId(Long callStatusId) {
		this.callStatusId = callStatusId;
	}
	public Long getScheduleStatusId() {
		return scheduleStatusId;
	}
	public void setScheduleStatusId(Long scheduleStatusId) {
		this.scheduleStatusId = scheduleStatusId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	

}
