package com.itgrids.partyanalyst.dto;

public class AppointmentStatusVO {

	private Long appointmentStatusId;
	private String status;
	private Long statusCount;
	private String updatedTime;
	
	public Long getAppointmentStatusId() {
		return appointmentStatusId;
	}
	public void setAppointmentStatusId(Long appointmentStatusId) {
		this.appointmentStatusId = appointmentStatusId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getStatusCount() {
		return statusCount;
	}
	public void setStatusCount(Long statusCount) {
		this.statusCount = statusCount;
	}
	public String getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}
}
