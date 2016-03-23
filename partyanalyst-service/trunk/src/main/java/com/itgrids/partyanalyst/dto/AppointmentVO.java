package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class AppointmentVO {
	
	private Long appointmentId;
	private Long appointmentPriorityId;
	private String reason;
	private Long appointmentStatusId;
	private String appointmentPreferableTimeType;
	private String appointmentDates;
	private List<AppointmentBasicInfoVO> basicInfoList = new ArrayList<AppointmentBasicInfoVO>();
	private Long appointmentUserId;
	private String uniqueCode;
	private String priority;
	private String status;
	
	public Long getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}
	public Long getAppointmentPriorityId() {
		return appointmentPriorityId;
	}
	public void setAppointmentPriorityId(Long appointmentPriorityId) {
		this.appointmentPriorityId = appointmentPriorityId;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Long getAppointmentStatusId() {
		return appointmentStatusId;
	}
	public void setAppointmentStatusId(Long appointmentStatusId) {
		this.appointmentStatusId = appointmentStatusId;
	}
	public String getAppointmentPreferableTimeType() {
		return appointmentPreferableTimeType;
	}
	public void setAppointmentPreferableTimeType(
			String appointmentPreferableTimeType) {
		this.appointmentPreferableTimeType = appointmentPreferableTimeType;
	}
	public String getAppointmentDates() {
		return appointmentDates;
	}
	public void setAppointmentDates(String appointmentDates) {
		this.appointmentDates = appointmentDates;
	}
	public List<AppointmentBasicInfoVO> getBasicInfoList() {
		return basicInfoList;
	}
	public void setBasicInfoList(List<AppointmentBasicInfoVO> basicInfoList) {
		this.basicInfoList = basicInfoList;
	}
	public Long getAppointmentUserId() {
		return appointmentUserId;
	}
	public void setAppointmentUserId(Long appointmentUserId) {
		this.appointmentUserId = appointmentUserId;
	}
	public String getUniqueCode() {
		return uniqueCode;
	}
	public void setUniqueCode(String uniqueCode) {
		this.uniqueCode = uniqueCode;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
