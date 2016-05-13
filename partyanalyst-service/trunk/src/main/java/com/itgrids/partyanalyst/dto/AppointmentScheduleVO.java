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

//prefferable Dates Scenario
private Long dateTypeId;
private String dateType;
private String  apptpreferableDates;
private Long   minDateCheck=0l;
private String minDate;
private String maxDate;
private String requestedDate;

private Long   apptCandiTypeId;
private String candDesignation;
private String constituency;

private Long totalRequestedAppCount;
private Long totalCompletedAppCount;
private String candidateLastVisitDate;
private String candidateLastUpdatedStatus;

public Long getTotalRequestedAppCount() {
	return totalRequestedAppCount;
}
public void setTotalRequestedAppCount(Long totalRequestedAppCount) {
	this.totalRequestedAppCount = totalRequestedAppCount;
}
public Long getTotalCompletedAppCount() {
	return totalCompletedAppCount;
}
public void setTotalCompletedAppCount(Long totalCompletedAppCount) {
	this.totalCompletedAppCount = totalCompletedAppCount;
}
public String getCandidateLastVisitDate() {
	return candidateLastVisitDate;
}
public void setCandidateLastVisitDate(String candidateLastVisitDate) {
	this.candidateLastVisitDate = candidateLastVisitDate;
}
public String getCandidateLastUpdatedStatus() {
	return candidateLastUpdatedStatus;
}
public void setCandidateLastUpdatedStatus(String candidateLastUpdatedStatus) {
	this.candidateLastUpdatedStatus = candidateLastUpdatedStatus;
}
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
public Long getDateTypeId() {
	return dateTypeId;
}
public void setDateTypeId(Long dateTypeId) {
	this.dateTypeId = dateTypeId;
}
public String getDateType() {
	return dateType;
}
public void setDateType(String dateType) {
	this.dateType = dateType;
}
public String getApptpreferableDates() {
	return apptpreferableDates;
}
public void setApptpreferableDates(String apptpreferableDates) {
	this.apptpreferableDates = apptpreferableDates;
}
public Long getMinDateCheck() {
	return minDateCheck;
}
public void setMinDateCheck(Long minDateCheck) {
	this.minDateCheck = minDateCheck;
}
public String getMinDate() {
	return minDate;
}
public void setMinDate(String minDate) {
	this.minDate = minDate;
}
public String getMaxDate() {
	return maxDate;
}
public void setMaxDate(String maxDate) {
	this.maxDate = maxDate;
}
public String getRequestedDate() {
	return requestedDate;
}
public void setRequestedDate(String requestedDate) {
	this.requestedDate = requestedDate;
}
public String getCandDesignation() {
	return candDesignation;
}
public void setCandDesignation(String candDesignation) {
	this.candDesignation = candDesignation;
}
public String getConstituency() {
	return constituency;
}
public void setConstituency(String constituency) {
	this.constituency = constituency;
}
public Long getApptCandiTypeId() {
	return apptCandiTypeId;
}
public void setApptCandiTypeId(Long apptCandiTypeId) {
	this.apptCandiTypeId = apptCandiTypeId;
}


}
