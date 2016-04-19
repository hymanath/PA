package com.itgrids.partyanalyst.dto;

public class AppointmentCountVO {
	private Long requestedCnt=0l;
	private Long scheduledCnt=0l;
	private Long uniqueRequestedCnt=0l;
	private Long uniqueScheduledCnt=0l;
	private Long total=0l;
	private Long uniquecnt=0l;
	private Long roleId;
	private String role;
	public Long getRequestedCnt() {
		return requestedCnt;
	}
	public void setRequestedCnt(Long requestedCnt) {
		this.requestedCnt = requestedCnt;
	}
	public Long getScheduledCnt() {
		return scheduledCnt;
	}
	public void setScheduledCnt(Long scheduledCnt) {
		this.scheduledCnt = scheduledCnt;
	}
	public Long getUniqueRequestedCnt() {
		return uniqueRequestedCnt;
	}
	public void setUniqueRequestedCnt(Long uniqueRequestedCnt) {
		this.uniqueRequestedCnt = uniqueRequestedCnt;
	}
	public Long getUniqueScheduledCnt() {
		return uniqueScheduledCnt;
	}
	public void setUniqueScheduledCnt(Long uniqueScheduledCnt) {
		this.uniqueScheduledCnt = uniqueScheduledCnt;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public Long getUniquecnt() {
		return uniquecnt;
	}
	public void setUniquecnt(Long uniquecnt) {
		this.uniquecnt = uniquecnt;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	

}
