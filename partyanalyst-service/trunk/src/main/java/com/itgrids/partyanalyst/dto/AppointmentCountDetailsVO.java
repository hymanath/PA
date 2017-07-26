package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class AppointmentCountDetailsVO implements Serializable{

	
	private Long appointmentId;
	private Long  todayCount=0l;
	private Long monthCount=0l;
	private String name;
	private String designation;
	private String image;
	private String appointmentTime;
	private Long uniqueCount =0l;
	private Long todayUniqueCount =0l;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Long getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Long getTodayCount() {
		return todayCount;
	}
	public void setTodayCount(Long todayCount) {
		this.todayCount = todayCount;
	}
	public Long getMonthCount() {
		return monthCount;
	}
	public void setMonthCount(Long monthCount) {
		this.monthCount = monthCount;
	}
	public String getAppointmentTime() {
		return appointmentTime;
	}
	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}
	public Long getUniqueCount() {
		return uniqueCount;
	}
	public void setUniqueCount(Long uniqueCount) {
		this.uniqueCount = uniqueCount;
	}
	public Long getTodayUniqueCount() {
		return todayUniqueCount;
	}
	public void setTodayUniqueCount(Long todayUniqueCount) {
		this.todayUniqueCount = todayUniqueCount;
	}
	
}
