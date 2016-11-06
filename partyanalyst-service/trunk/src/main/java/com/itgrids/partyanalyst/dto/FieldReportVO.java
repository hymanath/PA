package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class FieldReportVO implements Serializable {
	private Long order = 0l;
	private String label;
	private Long lastDayTotalReg = 0l;
	private Long todayTotalReg = 0l;
	private Long lastDayTotalUsers = 0l;
	private Long todayTotalUsers = 0l;
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Long getLastDayTotalReg() {
		return lastDayTotalReg;
	}
	public void setLastDayTotalReg(Long lastDayTotalReg) {
		this.lastDayTotalReg = lastDayTotalReg;
	}
	public Long getTodayTotalReg() {
		return todayTotalReg;
	}
	public void setTodayTotalReg(Long todayTotalReg) {
		this.todayTotalReg = todayTotalReg;
	}
	public Long getLastDayTotalUsers() {
		return lastDayTotalUsers;
	}
	public void setLastDayTotalUsers(Long lastDayTotalUsers) {
		this.lastDayTotalUsers = lastDayTotalUsers;
	}
	public Long getTodayTotalUsers() {
		return todayTotalUsers;
	}
	public void setTodayTotalUsers(Long todayTotalUsers) {
		this.todayTotalUsers = todayTotalUsers;
	}
	public Long getOrder() {
		return order;
	}
	public void setOrder(Long order) {
		this.order = order;
	}
}
