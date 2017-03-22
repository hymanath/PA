package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MeetingDtlsVO implements Serializable {
	private Long locationId = 0L;
	private String locationName;
	private Long invitieCount = 0L;
	private Long inviteAbsentCount = 0L;  
	private Long attendedCount = 0L;
	private Long lateAttendedCount = 0L;
	
	private Long total = 0l;
	private Long conductedCnt = 0l;
	private Long notConductedCnt = 0l;
	private String name;
	private String designation;
	private String phone;
	private Long allSession;
	private List<String> sessionList;
	private Long meetingId;
	private Long sessionId;
	private List<Long> locLevelIdList = new ArrayList<Long>();
	private List<SessionVO> sessionVOs = new ArrayList<SessionVO>(0);
	
	
	public List<SessionVO> getSessionVOs() {
		return sessionVOs;
	}
	public void setSessionVOs(List<SessionVO> sessionVOs) {
		this.sessionVOs = sessionVOs;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public Long getConductedCnt() {
		return conductedCnt;
	}
	public void setConductedCnt(Long conductedCnt) {
		this.conductedCnt = conductedCnt;
	}
	public Long getNotConductedCnt() {
		return notConductedCnt;
	}
	public void setNotConductedCnt(Long notConductedCnt) {
		this.notConductedCnt = notConductedCnt;
	}
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public Long getInvitieCount() {
		return invitieCount;
	}
	public void setInvitieCount(Long invitieCount) {
		this.invitieCount = invitieCount;
	}
	public Long getInviteAbsentCount() {
		return inviteAbsentCount;
	}
	public void setInviteAbsentCount(Long inviteAbsentCount) {
		this.inviteAbsentCount = inviteAbsentCount;
	}
	public Long getAttendedCount() {
		return attendedCount;
	}
	public void setAttendedCount(Long attendedCount) {
		this.attendedCount = attendedCount;
	}
	public Long getLateAttendedCount() {
		return lateAttendedCount;
	}
	public void setLateAttendedCount(Long lateAttendedCount) {
		this.lateAttendedCount = lateAttendedCount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Long getAllSession() {
		return allSession;
	}
	public void setAllSession(Long allSession) {
		this.allSession = allSession;
	}
	public List<String> getSessionList() {
		return sessionList;
	}
	public void setSessionList(List<String> sessionList) {
		this.sessionList = sessionList;
	}
	public Long getMeetingId() {
		return meetingId;
	}
	public void setMeetingId(Long meetingId) {
		this.meetingId = meetingId;
	}
	public Long getSessionId() {
		return sessionId;
	}
	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}
	public List<Long> getLocLevelIdList() {
		return locLevelIdList;
	}
	public void setLocLevelIdList(List<Long> locLevelIdList) {
		this.locLevelIdList = locLevelIdList;
	}
	
}
