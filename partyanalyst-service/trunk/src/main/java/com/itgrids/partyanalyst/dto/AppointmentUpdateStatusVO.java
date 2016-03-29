package com.itgrids.partyanalyst.dto;

public class AppointmentUpdateStatusVO {
	private Long appointmentId;
	private String date;
	private String time;
	private boolean issmsChecked;
	private String smsText;
	private Long statusId;
	public Long getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public boolean isIssmsChecked() {
		return issmsChecked;
	}
	public void setIssmsChecked(boolean issmsChecked) {
		this.issmsChecked = issmsChecked;
	}
	public String getSmsText() {
		return smsText;
	}
	public void setSmsText(String smsText) {
		this.smsText = smsText;
	}
	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	
	

}
