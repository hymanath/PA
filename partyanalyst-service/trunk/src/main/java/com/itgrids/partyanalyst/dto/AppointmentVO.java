package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class AppointmentVO {
	
	private Long appointmentPrioprityId;
	private String reason;
	private Long appointmentStatusId;
	private String appointmentPreferableTimeType;
	private String appointmentDates;
	private List<AppointmentBasicInfoVO> basicInfoList = new ArrayList<AppointmentBasicInfoVO>();
	private Long appointmentUserId;
	private String uniqueCode;
	
	public Long getAppointmentPrioprityId() {
		return appointmentPrioprityId;
	}
	public void setAppointmentPrioprityId(Long appointmentPrioprityId) {
		this.appointmentPrioprityId = appointmentPrioprityId;
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
}
