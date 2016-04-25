package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class AppointmentStatusFlowVO implements Serializable{

	private Long statusId;
	private String status;
	
	private Long typeId;
	private String type;
	
	private Long toStatusId;
	private String toStatus;
	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getTypeId() {
		return typeId;
	}
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getToStatusId() {
		return toStatusId;
	}
	public void setToStatusId(Long toStatusId) {
		this.toStatusId = toStatusId;
	}
	public String getToStatus() {
		return toStatus;
	}
	public void setToStatus(String toStatus) {
		this.toStatus = toStatus;
	}
	
	
}
