package com.itgrids.partyanalyst.dto;

import java.util.Date;
import java.util.List;

public class ProblemsCountByStatus {
	private Long statusId;
	private String date;
	private Date dateVal;
	private String status;
	private int count;
	private int fixedCount;
	private int newCount;
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Date getDateVal() {
		return dateVal;
	}
	public void setDateVal(Date dateVal) {
		this.dateVal = dateVal;
	}
	public int getFixedCount() {
		return fixedCount;
	}
	public void setFixedCount(int fixedCount) {
		this.fixedCount = fixedCount;
	}
	public int getNewCount() {
		return newCount;
	}
	public void setNewCount(int newCount) {
		this.newCount = newCount;
	}
	
	
}
