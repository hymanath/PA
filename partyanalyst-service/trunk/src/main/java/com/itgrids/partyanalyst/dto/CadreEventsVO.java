package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class CadreEventsVO implements java.io.Serializable{
	public Long id;
	public String name="";
	public Long invitedCount;
	public Long attendedCount;
	public Long lateCount;
	public String attendedTime;
	private List<CadreEventsVO> subList = new ArrayList<CadreEventsVO>(0);
	
	
	public List<CadreEventsVO> getSubList() {
		return subList;
	}
	public void setSubList(List<CadreEventsVO> subList) {
		this.subList = subList;
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
	public String getAttendedTime() {
		return attendedTime;
	}
	public void setAttendedTime(String attendedTime) {
		this.attendedTime = attendedTime;
	}
	
}
