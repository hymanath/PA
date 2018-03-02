package com.itgrids.dto;

import java.util.ArrayList;
import java.util.List;

public class EMeetingsVO {
	
	private Long id;
	private String name;
	private Long totalPanchayats = 0L;
	private Long conductedPanchayats = 0L;
	private Long notConductedPanchayts = 0L;
	private Long totalMeetings = 0L;
	private Long conductedMeetings = 0L;
	private Long notConductedMeetings = 0L;
	private Long totalAgendaPts = 0L;
	private Long approvedAgendaPts = 0L;
	private Long notApprovedAgendaPts = 0L;
	private Long generalMeetings = 0L;
	private Long reqMeetings = 0L;
	private Long EmergencyMeetings = 0L;
	private Long beyond60Days = 0L;
	private Long beyond90Days = 0L;
	private Long beyond120Days = 0L;
	private Long exceptedMeetings = 0L;
	private Long districtId;
	private String districtName;
	private Long constituencyId;
	private String constituencyName;
	private Long mandalId;
	private String mandalName;
	private Long panchayatId;
	private String panchayatName;
	private Long parliamentId;
	private String parliamentName;
	private Long memberId;
	private String memberName;
	private String designation;
	private String mobileNo;
	private String invitationStatus;
	private String attendance;
	private String vote;
	private String remark;
	private String meetingType;
	private String meetingDate;
	private Long totalMembers = 0L;
	private Long attendedMembers = 0L;
	private Long absentMembers = 0L;
	private String minutesOfMeeting;
	private String meetingDuration;
	private List<EMeetingsVO> subList = new ArrayList<EMeetingsVO>(0);
	
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
	public Long getTotalPanchayats() {
		return totalPanchayats;
	}
	public void setTotalPanchayats(Long totalPanchayats) {
		this.totalPanchayats = totalPanchayats;
	}
	public Long getConductedPanchayats() {
		return conductedPanchayats;
	}
	public void setConductedPanchayats(Long conductedPanchayats) {
		this.conductedPanchayats = conductedPanchayats;
	}
	public Long getNotConductedPanchayts() {
		return notConductedPanchayts;
	}
	public void setNotConductedPanchayts(Long notConductedPanchayts) {
		this.notConductedPanchayts = notConductedPanchayts;
	}
	public Long getTotalMeetings() {
		return totalMeetings;
	}
	public void setTotalMeetings(Long totalMeetings) {
		this.totalMeetings = totalMeetings;
	}
	public Long getConductedMeetings() {
		return conductedMeetings;
	}
	public void setConductedMeetings(Long conductedMeetings) {
		this.conductedMeetings = conductedMeetings;
	}
	public Long getNotConductedMeetings() {
		return notConductedMeetings;
	}
	public void setNotConductedMeetings(Long notConductedMeetings) {
		this.notConductedMeetings = notConductedMeetings;
	}
	public Long getTotalAgendaPts() {
		return totalAgendaPts;
	}
	public void setTotalAgendaPts(Long totalAgendaPts) {
		this.totalAgendaPts = totalAgendaPts;
	}
	public Long getApprovedAgendaPts() {
		return approvedAgendaPts;
	}
	public void setApprovedAgendaPts(Long approvedAgendaPts) {
		this.approvedAgendaPts = approvedAgendaPts;
	}
	public Long getNotApprovedAgendaPts() {
		return notApprovedAgendaPts;
	}
	public void setNotApprovedAgendaPts(Long notApprovedAgendaPts) {
		this.notApprovedAgendaPts = notApprovedAgendaPts;
	}
	public Long getGeneralMeetings() {
		return generalMeetings;
	}
	public void setGeneralMeetings(Long generalMeetings) {
		this.generalMeetings = generalMeetings;
	}
	public Long getReqMeetings() {
		return reqMeetings;
	}
	public void setReqMeetings(Long reqMeetings) {
		this.reqMeetings = reqMeetings;
	}
	public Long getEmergencyMeetings() {
		return EmergencyMeetings;
	}
	public void setEmergencyMeetings(Long emergencyMeetings) {
		EmergencyMeetings = emergencyMeetings;
	}
	public Long getBeyond60Days() {
		return beyond60Days;
	}
	public void setBeyond60Days(Long beyond60Days) {
		this.beyond60Days = beyond60Days;
	}
	public Long getBeyond90Days() {
		return beyond90Days;
	}
	public void setBeyond90Days(Long beyond90Days) {
		this.beyond90Days = beyond90Days;
	}
	public Long getBeyond120Days() {
		return beyond120Days;
	}
	public void setBeyond120Days(Long beyond120Days) {
		this.beyond120Days = beyond120Days;
	}
	public Long getExceptedMeetings() {
		return exceptedMeetings;
	}
	public void setExceptedMeetings(Long exceptedMeetings) {
		this.exceptedMeetings = exceptedMeetings;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	public Long getMandalId() {
		return mandalId;
	}
	public void setMandalId(Long mandalId) {
		this.mandalId = mandalId;
	}
	public String getMandalName() {
		return mandalName;
	}
	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}
	public Long getPanchayatId() {
		return panchayatId;
	}
	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}
	public String getPanchayatName() {
		return panchayatName;
	}
	public void setPanchayatName(String panchayatName) {
		this.panchayatName = panchayatName;
	}
	public Long getParliamentId() {
		return parliamentId;
	}
	public void setParliamentId(Long parliamentId) {
		this.parliamentId = parliamentId;
	}
	public String getParliamentName() {
		return parliamentName;
	}
	public void setParliamentName(String parliamentName) {
		this.parliamentName = parliamentName;
	}
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getInvitationStatus() {
		return invitationStatus;
	}
	public void setInvitationStatus(String invitationStatus) {
		this.invitationStatus = invitationStatus;
	}
	public String getAttendance() {
		return attendance;
	}
	public void setAttendance(String attendance) {
		this.attendance = attendance;
	}
	public String getVote() {
		return vote;
	}
	public void setVote(String vote) {
		this.vote = vote;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getMeetingType() {
		return meetingType;
	}
	public void setMeetingType(String meetingType) {
		this.meetingType = meetingType;
	}
	public String getMeetingDate() {
		return meetingDate;
	}
	public void setMeetingDate(String meetingDate) {
		this.meetingDate = meetingDate;
	}
	public Long getTotalMembers() {
		return totalMembers;
	}
	public void setTotalMembers(Long totalMembers) {
		this.totalMembers = totalMembers;
	}
	public Long getAttendedMembers() {
		return attendedMembers;
	}
	public void setAttendedMembers(Long attendedMembers) {
		this.attendedMembers = attendedMembers;
	}
	public Long getAbsentMembers() {
		return absentMembers;
	}
	public void setAbsentMembers(Long absentMembers) {
		this.absentMembers = absentMembers;
	}
	public String getMinutesOfMeeting() {
		return minutesOfMeeting;
	}
	public void setMinutesOfMeeting(String minutesOfMeeting) {
		this.minutesOfMeeting = minutesOfMeeting;
	}
	public String getMeetingDuration() {
		return meetingDuration;
	}
	public void setMeetingDuration(String meetingDuration) {
		this.meetingDuration = meetingDuration;
	}
	public List<EMeetingsVO> getSubList() {
		return subList;
	}
	public void setSubList(List<EMeetingsVO> subList) {
		this.subList = subList;
	}
}
