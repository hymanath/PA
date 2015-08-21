package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

/*
 * DTO Used to PartyMeetings Summary Results
 * @Author  SASI
 * @Since	21st Aug 2015
 * 
 * */

public class PartyMeetingSummaryVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long totalCommittees;
	private Long plannedCommittees;
	private Long conducted;
	private Long inviteesAttended;
	private String inviteesAttendedPercent;
	
	private String meetingName;
	private Long meetingId;
	private String location;
	private Long locationId;
	private String scheduledOn;
	private Long totalInvitees;
	
	private Long totalAttended;
	private Long totalAbsent;
	private Long nonInviteesAttended;
	private String nonInviteesAttendedPercent;
	
	private boolean momTextExist;
	private boolean momFilesExist;
	private boolean atrTextExist;
	private boolean atrFilesExist;
	
	
	private List<PartyMeetingSummaryVO> partyMeetingsList;
	private String absentPercentage;
	private PartyMeetingSummaryVO attendanceInfo; 
	
	private Long momPointsCount;
	private Long momFilesCount;
	private Long atrFilesCount;
	private Long atrTextCount;
	private PartyMeetingSummaryVO docTxtInfo;
	
	public Long getTotalCommittees() {
		return totalCommittees;
	}
	public void setTotalCommittees(Long totalCommittees) {
		this.totalCommittees = totalCommittees;
	}
	public Long getPlannedCommittees() {
		return plannedCommittees;
	}
	public void setPlannedCommittees(Long plannedCommittees) {
		this.plannedCommittees = plannedCommittees;
	}
	public Long getConducted() {
		return conducted;
	}
	public void setConducted(Long conducted) {
		this.conducted = conducted;
	}
	public Long getInviteesAttended() {
		return inviteesAttended;
	}
	public void setInviteesAttended(Long inviteesAttended) {
		this.inviteesAttended = inviteesAttended;
	}
	public String getInviteesAttendedPercent() {
		return inviteesAttendedPercent;
	}
	public void setInviteesAttendedPercent(String inviteesAttendedPercent) {
		this.inviteesAttendedPercent = inviteesAttendedPercent;
	}
	public String getMeetingName() {
		return meetingName;
	}
	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
	}
	public Long getMeetingId() {
		return meetingId;
	}
	public void setMeetingId(Long meetingId) {
		this.meetingId = meetingId;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	public String getScheduledOn() {
		return scheduledOn;
	}
	public void setScheduledOn(String scheduledOn) {
		this.scheduledOn = scheduledOn;
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
	public Long getTotalAbsent() {
		return totalAbsent;
	}
	public void setTotalAbsent(Long totalAbsent) {
		this.totalAbsent = totalAbsent;
	}
	public Long getNonInviteesAttended() {
		return nonInviteesAttended;
	}
	public void setNonInviteesAttended(Long nonInviteesAttended) {
		this.nonInviteesAttended = nonInviteesAttended;
	}
	public String getNonInviteesAttendedPercent() {
		return nonInviteesAttendedPercent;
	}
	public void setNonInviteesAttendedPercent(String nonInviteesAttendedPercent) {
		this.nonInviteesAttendedPercent = nonInviteesAttendedPercent;
	}
	public boolean isMomTextExist() {
		return momTextExist;
	}
	public void setMomTextExist(boolean momTextExist) {
		this.momTextExist = momTextExist;
	}
	public boolean isMomFilesExist() {
		return momFilesExist;
	}
	public void setMomFilesExist(boolean momFilesExist) {
		this.momFilesExist = momFilesExist;
	}
	public boolean isAtrTextExist() {
		return atrTextExist;
	}
	public void setAtrTextExist(boolean atrTextExist) {
		this.atrTextExist = atrTextExist;
	}
	public boolean isAtrFilesExist() {
		return atrFilesExist;
	}
	public void setAtrFilesExist(boolean atrFilesExist) {
		this.atrFilesExist = atrFilesExist;
	}
	public List<PartyMeetingSummaryVO> getPartyMeetingsList() {
		return partyMeetingsList;
	}
	public void setPartyMeetingsList(List<PartyMeetingSummaryVO> partyMeetingsList) {
		this.partyMeetingsList = partyMeetingsList;
	}
	public String getAbsentPercentage() {
		return absentPercentage;
	}
	public void setAbsentPercentage(String absentPercentage) {
		this.absentPercentage = absentPercentage;
	}
	public PartyMeetingSummaryVO getAttendanceInfo() {
		return attendanceInfo;
	}
	public void setAttendanceInfo(PartyMeetingSummaryVO attendanceInfo) {
		this.attendanceInfo = attendanceInfo;
	}
	public Long getMomPointsCount() {
		return momPointsCount;
	}
	public void setMomPointsCount(Long momPointsCount) {
		this.momPointsCount = momPointsCount;
	}
	public Long getMomFilesCount() {
		return momFilesCount;
	}
	public void setMomFilesCount(Long momFilesCount) {
		this.momFilesCount = momFilesCount;
	}
	public Long getAtrFilesCount() {
		return atrFilesCount;
	}
	public void setAtrFilesCount(Long atrFilesCount) {
		this.atrFilesCount = atrFilesCount;
	}
	public Long getAtrTextCount() {
		return atrTextCount;
	}
	public void setAtrTextCount(Long atrTextCount) {
		this.atrTextCount = atrTextCount;
	}
	public PartyMeetingSummaryVO getDocTxtInfo() {
		return docTxtInfo;
	}
	public void setDocTxtInfo(PartyMeetingSummaryVO docTxtInfo) {
		this.docTxtInfo = docTxtInfo;
	}
	
	
	
}
