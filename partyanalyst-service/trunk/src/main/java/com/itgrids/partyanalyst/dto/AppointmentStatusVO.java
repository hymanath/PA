package com.itgrids.partyanalyst.dto;

import java.util.List;

public class AppointmentStatusVO {

	private Long appointmentStatusId;
	private String status;
	private Long statusCount;
	private String updatedTime;
	private Long   membersCount;
	
	private List<Long> clickIds;
	private List<AppointmentStatusVO> subList;
	
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
	public Long getMembersCount() {
		return membersCount;
	}
	public void setMembersCount(Long membersCount) {
		this.membersCount = membersCount;
	}
	public List<Long> getClickIds() {
		return clickIds;
	}
	public void setClickIds(List<Long> clickIds) {
		this.clickIds = clickIds;
	}
	public List<AppointmentStatusVO> getSubList() {
		return subList;
	}
	public void setSubList(List<AppointmentStatusVO> subList) {
		this.subList = subList;
	}
	
}
