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
	
	private Long momPointsCount =0l;
	private Long momFilesCount =0l;
	private Long atrFilesCount =0l;
	private Long atrTextCount =0l;
	private PartyMeetingSummaryVO docTxtInfo;
	
	private int meetingsCount;
	private int plannedMeetings;
	private int conductedMeetings;
	private int averageInviteesAttended;
	private String averageInviteesAttendedPercent;
	private String conductedMeetingsPercent;
	
	private int bothCount;
	private int onlyFileCount;
	private int onlyTxtCount;
	private int nothingCount;
	
	private PartyMeetingSummaryVO atrDocTxtInfo;
	private PartyMeetingSummaryVO momDocTxtInfo;
	
	private Long stateId;
	private Long districtId;
	private Long constituencyId;
	private String stateName;
	private String districtName;
	private String constituencyName;
	
	private Long momUpdatedMeetings;
	private Long atrUpdatedMeetings;
	private Long assemblyNo;
	
	
	public Long getAssemblyNo() {
		return assemblyNo;
	}
	public void setAssemblyNo(Long assemblyNo) {
		this.assemblyNo = assemblyNo;
	}
	public Long getMomUpdatedMeetings() {
		return momUpdatedMeetings;
	}
	public void setMomUpdatedMeetings(Long momUpdatedMeetings) {
		this.momUpdatedMeetings = momUpdatedMeetings;
	}
	public Long getAtrUpdatedMeetings() {
		return atrUpdatedMeetings;
	}
	public void setAtrUpdatedMeetings(Long atrUpdatedMeetings) {
		this.atrUpdatedMeetings = atrUpdatedMeetings;
	}
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	public String getAverageInviteesAttendedPercent() {
		return averageInviteesAttendedPercent;
	}
	public void setAverageInviteesAttendedPercent(
			String averageInviteesAttendedPercent) {
		this.averageInviteesAttendedPercent = averageInviteesAttendedPercent;
	}
	public PartyMeetingSummaryVO getAtrDocTxtInfo() {
		return atrDocTxtInfo;
	}
	public void setAtrDocTxtInfo(PartyMeetingSummaryVO atrDocTxtInfo) {
		this.atrDocTxtInfo = atrDocTxtInfo;
	}
	public PartyMeetingSummaryVO getMomDocTxtInfo() {
		return momDocTxtInfo;
	}
	public void setMomDocTxtInfo(PartyMeetingSummaryVO momDocTxtInfo) {
		this.momDocTxtInfo = momDocTxtInfo;
	}
	public int getBothCount() {
		return bothCount;
	}
	public void setBothCount(int bothCount) {
		this.bothCount = bothCount;
	}
	public int getOnlyFileCount() {
		return onlyFileCount;
	}
	public void setOnlyFileCount(int onlyFileCount) {
		this.onlyFileCount = onlyFileCount;
	}
	public int getOnlyTxtCount() {
		return onlyTxtCount;
	}
	public void setOnlyTxtCount(int onlyTxtCount) {
		this.onlyTxtCount = onlyTxtCount;
	}
	public int getNothingCount() {
		return nothingCount;
	}
	public void setNothingCount(int nothingCount) {
		this.nothingCount = nothingCount;
	}
	public String getConductedMeetingsPercent() {
		return conductedMeetingsPercent;
	}
	public void setConductedMeetingsPercent(String conductedMeetingsPercent) {
		this.conductedMeetingsPercent = conductedMeetingsPercent;
	}
	public int getMeetingsCount() {
		return meetingsCount;
	}
	public void setMeetingsCount(int meetingsCount) {
		this.meetingsCount = meetingsCount;
	}
	public int getPlannedMeetings() {
		return plannedMeetings;
	}
	public void setPlannedMeetings(int plannedMeetings) {
		this.plannedMeetings = plannedMeetings;
	}
	public int getConductedMeetings() {
		return conductedMeetings;
	}
	public void setConductedMeetings(int conductedMeetings) {
		this.conductedMeetings = conductedMeetings;
	}
	public int getAverageInviteesAttended() {
		return averageInviteesAttended;
	}
	public void setAverageInviteesAttended(int averageInviteesAttended) {
		this.averageInviteesAttended = averageInviteesAttended;
	}
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
