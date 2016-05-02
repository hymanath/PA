package com.itgrids.partyanalyst.dto;

public class AppointmentMemberInputVO {
	
	private Long roleId;
	private String scheduleType;
	private String cntType;
	private String memberType;
	private Long aptUserId;
	
	
	
	
	
	
	
	
	public Long getAptUserId() {
		return aptUserId;
	}
	public void setAptUserId(Long aptUserId) {
		this.aptUserId = aptUserId;
	}
	public String getMemberType() {
		return memberType;
	}
	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getScheduleType() {
		return scheduleType;
	}
	public void setScheduleType(String scheduleType) {
		this.scheduleType = scheduleType;
	}
	public String getCntType() {
		return cntType;
	}
	public void setCntType(String cntType) {
		this.cntType = cntType;
	}
	 
	
	

}
