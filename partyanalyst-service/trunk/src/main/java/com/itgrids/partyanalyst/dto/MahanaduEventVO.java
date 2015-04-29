package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class MahanaduEventVO {

	private Long id;
	private String name;
	private Long invitees=0l;
	private Long nonInvitees=0l;
	private Long attendees=0l;
	private Long total=0l;
	private Long voterCount = 0l;
	private Long cadreCount = 0l;
	
	private String startTime;
	private String endTime;
	private String desc;
	private String inviteeExists;
	List<MahanaduEventVO> hoursList = new ArrayList<MahanaduEventVO>();
	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getInviteeExists() {
		return inviteeExists;
	}
	public void setInviteeExists(String inviteeExists) {
		this.inviteeExists = inviteeExists;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
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
	public Long getInvitees() {
		return invitees;
	}
	public void setInvitees(Long invitees) {
		this.invitees = invitees;
	}
	public Long getNonInvitees() {
		return nonInvitees;
	}
	public void setNonInvitees(Long nonInvitees) {
		this.nonInvitees = nonInvitees;
	}
	public Long getAttendees() {
		return attendees;
	}
	public void setAttendees(Long attendees) {
		this.attendees = attendees;
	}
	public Long getVoterCount() {
		return voterCount;
	}
	public void setVoterCount(Long voterCount) {
		this.voterCount = voterCount;
	}
	public Long getCadreCount() {
		return cadreCount;
	}
	public void setCadreCount(Long cadreCount) {
		this.cadreCount = cadreCount;
	}
	public List<MahanaduEventVO> getHoursList() {
		return hoursList;
	}
	public void setHoursList(List<MahanaduEventVO> hoursList) {
		this.hoursList = hoursList;
	}

}
