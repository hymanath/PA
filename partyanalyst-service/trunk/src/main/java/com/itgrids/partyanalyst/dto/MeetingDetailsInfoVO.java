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
	Map<Long,Map<Long,Set<Long>>> totalSessionWiseAttendenceMap = new HashMap<Long,Map<Long,Set<Long>>>();
	Map<Long, Set<Long>> totalSessionWiseInviteeAttendenceMap = new HashMap<Long, Set<Long>>();
	Map<Long, Set<Long>> totalSessionWiseNonInviteeAttendenceMap = new HashMap<Long, Set<Long>>();
	Map<Long, Set<Long>> totalSessionWiseLateInviteeAttendenceMap = new HashMap<Long, Set<Long>>();
	
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
	public Map<Long, Map<Long, Set<Long>>> getTotalSessionWiseAttendenceMap() {
		return totalSessionWiseAttendenceMap;
	}
	public void setTotalSessionWiseAttendenceMap(
			Map<Long, Map<Long, Set<Long>>> totalSessionWiseAttendenceMap) {
		this.totalSessionWiseAttendenceMap = totalSessionWiseAttendenceMap;
	}
	public Map<Long, Set<Long>> getTotalSessionWiseInviteeAttendenceMap() {
		return totalSessionWiseInviteeAttendenceMap;
	}
	public void setTotalSessionWiseInviteeAttendenceMap(
			Map<Long, Set<Long>> totalSessionWiseInviteeAttendenceMap) {
		this.totalSessionWiseInviteeAttendenceMap = totalSessionWiseInviteeAttendenceMap;
	}
	public Map<Long, Set<Long>> getTotalSessionWiseNonInviteeAttendenceMap() {
		return totalSessionWiseNonInviteeAttendenceMap;
	}
	public void setTotalSessionWiseNonInviteeAttendenceMap(
			Map<Long, Set<Long>> totalSessionWiseNonInviteeAttendenceMap) {
		this.totalSessionWiseNonInviteeAttendenceMap = totalSessionWiseNonInviteeAttendenceMap;
	}
	public Map<Long, Set<Long>> getTotalSessionWiseLateInviteeAttendenceMap() {
		return totalSessionWiseLateInviteeAttendenceMap;
	}
	public void setTotalSessionWiseLateInviteeAttendenceMap(
			Map<Long, Set<Long>> totalSessionWiseLateInviteeAttendenceMap) {
		this.totalSessionWiseLateInviteeAttendenceMap = totalSessionWiseLateInviteeAttendenceMap;
	}
	
	
}
