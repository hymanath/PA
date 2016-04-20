package com.itgrids.partyanalyst.dto;

public class AppointmentUpdateStatusVO {
	private Long appointmentId;
	private String date;
	private String time;
	private boolean issmsChecked;
	private String smsText;
	private Long statusId;
	private String appointmentType;
	private String commented;
	private Long userId;
	private Long appointmentStatusId;
	private Long apptUserId;
	
	public Long getAppointmentStatusId() {
		return appointmentStatusId;
	}
	public void setAppointmentStatusId(Long appointmentStatusId) {
		this.appointmentStatusId = appointmentStatusId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public String getCommented() {
		return commented;
	}
	public void setCommented(String commented) {
		this.commented = commented;
	}
	public String getAppointmentType() {
		return appointmentType;
	}
	public void setAppointmentType(String appointmentType) {
		this.appointmentType = appointmentType;
	}
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
	public Long getApptUserId() {
		return apptUserId;
	}
	public void setApptUserId(Long apptUserId) {
		this.apptUserId = apptUserId;
	}
	
	

}
