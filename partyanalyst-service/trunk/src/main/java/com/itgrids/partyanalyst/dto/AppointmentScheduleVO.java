package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class AppointmentScheduleVO {

private Long id;
private Long userId;
private Long tdpCadreId;
private String name;
private String subject;
private String mobileNo;
private String time;
private String appointmentStatus;
private String appointmentStatusColor;
private String scheduleType;
private String designation;
private String createdBy;
private Long statusId;
private Long appointmentId;
private String toTime;
private String fromDate;
private String toDate;
private String appointmentUniqueId;
private String date;
private String imageUrl;
private String formatDate;
private Long   apptTimeSlotId;


public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public String getFromDate() {
	return fromDate;
}

public void setFromDate(String fromDate) {
	this.fromDate = fromDate;
}
public String getToDate() {
	return toDate;
}
public void setToDate(String toDate) {
	this.toDate = toDate;
}
public String getToTime() {
	return toTime;
}
public void setToTime(String toTime) {
	this.toTime = toTime;
}
public Long getAppointmentId() {
	return appointmentId;
}
public void setAppointmentId(Long appointmentId) {
	this.appointmentId = appointmentId;
}
private List<AppointmentScheduleVO> subList = new ArrayList<AppointmentScheduleVO>();





public List<AppointmentScheduleVO> getSubList() {
	return subList;
}
public void setSubList(List<AppointmentScheduleVO> subList) {
	this.subList = subList;
}
public Long getStatusId() {
	return statusId;
}
public void setStatusId(Long statusId) {
	this.statusId = statusId;
}
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
public String getAppointmentStatusColor() {
	return appointmentStatusColor;
}
public void setAppointmentStatusColor(String appointmentStatusColor) {
	this.appointmentStatusColor = appointmentStatusColor;
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
public String getAppointmentUniqueId() {
	return appointmentUniqueId;
}
public void setAppointmentUniqueId(String appointmentUniqueId) {
	this.appointmentUniqueId = appointmentUniqueId;
}
public String getImageUrl() {
	return imageUrl;
}
public void setImageUrl(String imageUrl) {
	this.imageUrl = imageUrl;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getFormatDate() {
	return formatDate;
}
public void setFormatDate(String formatDate) {
	this.formatDate = formatDate;
}
public Long getApptTimeSlotId() {
	return apptTimeSlotId;
}
public void setApptTimeSlotId(Long apptTimeSlotId) {
	this.apptTimeSlotId = apptTimeSlotId;
}


}
