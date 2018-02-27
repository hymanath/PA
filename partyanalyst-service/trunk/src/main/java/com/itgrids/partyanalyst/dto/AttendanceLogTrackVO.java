package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class AttendanceLogTrackVO implements Serializable{

	private static final long serialVersionUID = 388094627530486811L;

	private Date startTime;
	private Date endTime;
	private Long totalRecords;
	private String status;
	private List<AttendanceLogVO> logData;

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Long getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(Long totalRecords) {
		this.totalRecords = totalRecords;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<AttendanceLogVO> getLogData() {
		return logData;
	}

	public void setLogData(List<AttendanceLogVO> logData) {
		this.logData = logData;
	}

}
