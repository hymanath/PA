package com.itgrids.partyanalyst.dto;

public class AppointmentScheduleVO {
private Long userId;
private Long tdpCadreId;
private String name;
private String subject;
private String mobileNo;
private String time;
private String appointmentStatus;
private String scheduleType;
private String designation;
private String createdBy;





public String getCreatedBy() {
	return createdBy;
}
public void setCreatedBy(String createdBy) {
	this.createdBy = createdBy;
}
public String getDesignation() {
	return designation;
}
public void setDesignation(String designation) {
	this.designation = designation;
}
public String getScheduleType() {
	return scheduleType;
}
public void setScheduleType(String scheduleType) {
	this.scheduleType = scheduleType;
}
public String getAppointmentStatus() {
	return appointmentStatus;
}
public void setAppointmentStatus(String appointmentStatus) {
	this.appointmentStatus = appointmentStatus;
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
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getSubject() {
	return subject;
}
public void setSubject(String subject) {
	this.subject = subject;
}
public String getMobileNo() {
	return mobileNo;
}
public void setMobileNo(String mobileNo) {
	this.mobileNo = mobileNo;
}
public String getTime() {
	return time;
}
public void setTime(String time) {
	this.time = time;
}


}
