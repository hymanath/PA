package com.itgrids.partyanalyst.dto;

public class CadreEventsVO {
	public Long id;
	public String name;
	public Long invitedCount;
	public Long attendedCount;
	public Long lateCount;
	public String attendedTime;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getInvitedCount() {
		return invitedCount;
	}
	public void setInvitedCount(Long invitedCount) {
		this.invitedCount = invitedCount;
	}
	public Long getAttendedCount() {
		return attendedCount;
	}
	public void setAttendedCount(Long attendedCount) {
		this.attendedCount = attendedCount;
	}
	public Long getLateCount() {
		return lateCount;
	}
	public void setLateCount(Long lateCount) {
		this.lateCount = lateCount;
	}
	public Long getAttendedTime() {
		return attendedTime;
	}
	public void setAttendedTime(Long attendedTime) {
		this.attendedTime = attendedTime;
	}
}
