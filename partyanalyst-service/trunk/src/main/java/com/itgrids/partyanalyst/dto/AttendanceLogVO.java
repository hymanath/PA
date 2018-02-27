package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.Date;

public class AttendanceLogVO implements Serializable{

	private static final long serialVersionUID = -1403587588885940722L;

	private Long deviceLogId;
	private Long deviceId;
	private String employeeCode;
	private Date logTime;

	public Long getDeviceLogId() {
		return deviceLogId;
	}

	public void setDeviceLogId(Long deviceLogId) {
		this.deviceLogId = deviceLogId;
	}

	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public Date getLogTime() {
		return logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}
	
}
