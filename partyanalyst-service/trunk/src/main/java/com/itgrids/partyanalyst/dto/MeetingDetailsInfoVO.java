package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MeetingDetailsInfoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Map<Long, Set<Long>> totalInviteesMap = new HashMap<Long, Set<Long>>();
	Map<Long, Set<Long>> totalAttendenceMap = new HashMap<Long, Set<Long>>();
	Map<Long, Set<Long>> totalInviteeAttendenceMap = new HashMap<Long, Set<Long>>();
	Map<Long, Set<Long>> totalNonInviteesMap = new HashMap<Long, Set<Long>>();
	Map<Long, Set<Long>> totalLateAttendedMap = new HashMap<Long, Set<Long>>();
	Map<Long,Map<Long,Set<Long>>> totalMeetingWiseThenSessionWiseAttendenceMap = new HashMap<Long,Map<Long,Set<Long>>>();
	Map<Long,Map<Long,Set<Long>>> totalMeetingWiseThenSessionWiseInviteeAttendenceMap = new HashMap<Long,Map<Long,Set<Long>>>();
	Map<Long,Map<Long,Set<Long>>> totalMeetingWiseThenSessionWiseNonInviteeAttendenceMap = new HashMap<Long,Map<Long,Set<Long>>>();
	Map<Long,Map<Long,Set<Long>>> totalMeetingWiseThenSessionWiseLateInviteeAttendenceMap = new HashMap<Long,Map<Long,Set<Long>>>();
	Map<Long,Map<Long,Long>> totalMeetingWiseThenSessionWiseImageMap = new HashMap<Long,Map<Long,Long>>();
	
	public Map<Long, Set<Long>> getTotalInviteesMap() { 
		return totalInviteesMap;    
	}
	public void setTotalInviteesMap(Map<Long, Set<Long>> totalInviteesMap) {
		this.totalInviteesMap = totalInviteesMap;
	}
	public Map<Long, Set<Long>> getTotalAttendenceMap() {
		return totalAttendenceMap;
	}
	public void setTotalAttendenceMap(Map<Long, Set<Long>> totalAttendenceMap) {
		this.totalAttendenceMap = totalAttendenceMap;
	}
	public Map<Long, Set<Long>> getTotalInviteeAttendenceMap() {
		return totalInviteeAttendenceMap;
	}
	public void setTotalInviteeAttendenceMap(
			Map<Long, Set<Long>> totalInviteeAttendenceMap) {
		this.totalInviteeAttendenceMap = totalInviteeAttendenceMap;
	}
	public Map<Long, Set<Long>> getTotalNonInviteesMap() {
		return totalNonInviteesMap;
	}
	public void setTotalNonInviteesMap(Map<Long, Set<Long>> totalNonInviteesMap) {
		this.totalNonInviteesMap = totalNonInviteesMap;
	}
	public Map<Long, Set<Long>> getTotalLateAttendedMap() {
		return totalLateAttendedMap;
	}
	public void setTotalLateAttendedMap(Map<Long, Set<Long>> totalLateAttendedMap) {
		this.totalLateAttendedMap = totalLateAttendedMap;
	}
	
	public Map<Long, Map<Long, Set<Long>>> getTotalMeetingWiseThenSessionWiseAttendenceMap() {
		return totalMeetingWiseThenSessionWiseAttendenceMap;
	}
	public void setTotalMeetingWiseThenSessionWiseAttendenceMap(
			Map<Long, Map<Long, Set<Long>>> totalMeetingWiseThenSessionWiseAttendenceMap) {
		this.totalMeetingWiseThenSessionWiseAttendenceMap = totalMeetingWiseThenSessionWiseAttendenceMap;
	}
	
	public Map<Long, Map<Long, Set<Long>>> getTotalMeetingWiseThenSessionWiseInviteeAttendenceMap() {
		return totalMeetingWiseThenSessionWiseInviteeAttendenceMap;
	}
	public void setTotalMeetingWiseThenSessionWiseInviteeAttendenceMap(
			Map<Long, Map<Long, Set<Long>>> totalMeetingWiseThenSessionWiseInviteeAttendenceMap) {
		this.totalMeetingWiseThenSessionWiseInviteeAttendenceMap = totalMeetingWiseThenSessionWiseInviteeAttendenceMap;
	}
	public Map<Long, Map<Long, Set<Long>>> getTotalMeetingWiseThenSessionWiseNonInviteeAttendenceMap() {
		return totalMeetingWiseThenSessionWiseNonInviteeAttendenceMap;
	}
	public void setTotalMeetingWiseThenSessionWiseNonInviteeAttendenceMap(
			Map<Long, Map<Long, Set<Long>>> totalMeetingWiseThenSessionWiseNonInviteeAttendenceMap) {
		this.totalMeetingWiseThenSessionWiseNonInviteeAttendenceMap = totalMeetingWiseThenSessionWiseNonInviteeAttendenceMap;
	}
	public Map<Long, Map<Long, Set<Long>>> getTotalMeetingWiseThenSessionWiseLateInviteeAttendenceMap() {
		return totalMeetingWiseThenSessionWiseLateInviteeAttendenceMap;
	}
	public void setTotalMeetingWiseThenSessionWiseLateInviteeAttendenceMap(
			Map<Long, Map<Long, Set<Long>>> totalMeetingWiseThenSessionWiseLateInviteeAttendenceMap) {
		this.totalMeetingWiseThenSessionWiseLateInviteeAttendenceMap = totalMeetingWiseThenSessionWiseLateInviteeAttendenceMap;
	}
	public Map<Long, Map<Long, Long>> getTotalMeetingWiseThenSessionWiseImageMap() {
		return totalMeetingWiseThenSessionWiseImageMap;
	}
	public void setTotalMeetingWiseThenSessionWiseImageMap(
			Map<Long, Map<Long, Long>> totalMeetingWiseThenSessionWiseImageMap) {
		this.totalMeetingWiseThenSessionWiseImageMap = totalMeetingWiseThenSessionWiseImageMap;
	}
	
	
}
