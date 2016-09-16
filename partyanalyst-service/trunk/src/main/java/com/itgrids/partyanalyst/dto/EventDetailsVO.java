package com.itgrids.partyanalyst.dto;

public class EventDetailsVO {

	private Long id;
	private String name;
	private Long inviteeCount=0l;
	private Long inviteeAttendedCount=0l;
	private Long inviteeNotAttendedCount=0l;
	private Long attendedCount=0l;
	private Long nonInviteeAttendedCount=0l;
	
	private Double inviteeAttendedCounPer=0.0d;
	private Double inviteeNotAttendedCountPer=0.0d;
	private Double attendedCountPer=0.0d;
	private Double nonInviteeAttendedCountPer=0.0d;
	
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
	public Long getInviteeCount() {
		return inviteeCount;
	}
	public void setInviteeCount(Long inviteeCount) {
		this.inviteeCount = inviteeCount;
	}
	public Long getInviteeAttendedCount() {
		return inviteeAttendedCount;
	}
	public void setInviteeAttendedCount(Long inviteeAttendedCount) {
		this.inviteeAttendedCount = inviteeAttendedCount;
	}
	public Long getInviteeNotAttendedCount() {
		return inviteeNotAttendedCount;
	}
	public void setInviteeNotAttendedCount(Long inviteeNotAttendedCount) {
		this.inviteeNotAttendedCount = inviteeNotAttendedCount;
	}
	public Long getAttendedCount() {
		return attendedCount;
	}
	public void setAttendedCount(Long attendedCount) {
		this.attendedCount = attendedCount;
	}
	public Long getNonInviteeAttendedCount() {
		return nonInviteeAttendedCount;
	}
	public void setNonInviteeAttendedCount(Long nonInviteeAttendedCount) {
		this.nonInviteeAttendedCount = nonInviteeAttendedCount;
	}
	public Double getInviteeAttendedCounPer() {
		return inviteeAttendedCounPer;
	}
	public void setInviteeAttendedCounPer(Double inviteeAttendedCounPer) {
		this.inviteeAttendedCounPer = inviteeAttendedCounPer;
	}
	public Double getInviteeNotAttendedCountPer() {
		return inviteeNotAttendedCountPer;
	}
	public void setInviteeNotAttendedCountPer(Double inviteeNotAttendedCountPer) {
		this.inviteeNotAttendedCountPer = inviteeNotAttendedCountPer;
	}
	public Double getAttendedCountPer() {
		return attendedCountPer;
	}
	public void setAttendedCountPer(Double attendedCountPer) {
		this.attendedCountPer = attendedCountPer;
	}
	public Double getNonInviteeAttendedCountPer() {
		return nonInviteeAttendedCountPer;
	}
	public void setNonInviteeAttendedCountPer(Double nonInviteeAttendedCountPer) {
		this.nonInviteeAttendedCountPer = nonInviteeAttendedCountPer;
	}

}
