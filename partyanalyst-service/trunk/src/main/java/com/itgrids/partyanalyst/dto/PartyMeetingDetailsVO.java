package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class PartyMeetingDetailsVO implements Serializable{

	private static final long serialVersionUID = -3473087678212505817L;
	
	private String meetingName;
	private String location;
	private String scheduleDate;
	private Long totalInvitees;
	private Long totalAttended;
	private Long inviteesAttended;
	private Long nonInviteesAttended;
	private Long absent;
	private String inviteesAttendedPercentage;
	private String nonInviteesAttendedPercentage;
	private String absentPercentage;

	public String getMeetingName() {
		return meetingName;
	}

	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(String scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	public Long getTotalInvitees() {
		return totalInvitees;
	}

	public void setTotalInvitees(Long totalInvitees) {
		this.totalInvitees = totalInvitees;
	}

	public Long getTotalAttended() {
		return totalAttended;
	}

	public void setTotalAttended(Long totalAttended) {
		this.totalAttended = totalAttended;
	}

	public Long getInviteesAttended() {
		return inviteesAttended;
	}

	public void setInviteesAttended(Long inviteesAttended) {
		this.inviteesAttended = inviteesAttended;
	}

	public Long getNonInviteesAttended() {
		return nonInviteesAttended;
	}

	public void setNonInviteesAttended(Long nonInviteesAttended) {
		this.nonInviteesAttended = nonInviteesAttended;
	}

	public Long getAbsent() {
		return absent;
	}

	public void setAbsent(Long absent) {
		this.absent = absent;
	}

	public String getInviteesAttendedPercentage() {
		return inviteesAttendedPercentage;
	}

	public void setInviteesAttendedPercentage(String inviteesAttendedPercentage) {
		this.inviteesAttendedPercentage = inviteesAttendedPercentage;
	}

	public String getNonInviteesAttendedPercentage() {
		return nonInviteesAttendedPercentage;
	}

	public void setNonInviteesAttendedPercentage(
			String nonInviteesAttendedPercentage) {
		this.nonInviteesAttendedPercentage = nonInviteesAttendedPercentage;
	}

	public String getAbsentPercentage() {
		return absentPercentage;
	}

	public void setAbsentPercentage(String absentPercentage) {
		this.absentPercentage = absentPercentage;
	}

}
