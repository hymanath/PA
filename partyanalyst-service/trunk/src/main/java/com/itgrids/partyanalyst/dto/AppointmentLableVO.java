package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.Date;

public class AppointmentLableVO implements Serializable{
	private long appointmentLabelId;
	private String labelName;
	private String Date;
	private String insertedTime;
	private String updatedTime;
	private String insertedBy;
	private long insertedById;
	private String updatedBy;
	private long updatedById;
	public long getAppointmentLabelId() {
		return appointmentLabelId;
	}
	public void setAppointmentLabelId(long appointmentLabelId) {
		this.appointmentLabelId = appointmentLabelId;
	}
	public String getLabelName() {
		return labelName;
	}
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public String getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(String insertedTime) {
		this.insertedTime = insertedTime;
	}
	public String getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}
	public String getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(String insertedBy) {
		this.insertedBy = insertedBy;
	}
	public long getInsertedById() {
		return insertedById;
	}
	public void setInsertedById(long insertedById) {
		this.insertedById = insertedById;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public long getUpdatedById() {
		return updatedById;
	}
	public void setUpdatedById(long updatedById) {
		this.updatedById = updatedById;
	}
	
	}
