package com.itgrids.partyanalyst.dto;

public class MissedCallCampaignVO {
	
	public String toMobileNo;
	public String from;
	public String calluid;
	public Long startTime;
	public Long endTime;	
	public String endReason;
	public String callStatus;
	public String direction;
	public String event;
	public Long Ring_time;
	public String name;
	public Long id;

	public String getCalluid() {
		return calluid;
	}
	public void setCalluid(String calluid) {
		this.calluid = calluid;
	}
	public Long getStartTime() {
		return startTime;
	}
	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}
	
	public String getEndReason() {
		return endReason;
	}
	public void setEndReason(String endReason) {
		this.endReason = endReason;
	}
	public String getCallStatus() {
		return callStatus;
	}
	public void setCallStatus(String callStatus) {
		this.callStatus = callStatus;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getToMobileNo() {
		return toMobileNo;
	}
	public void setToMobileNo(String toMobileNo) {
		this.toMobileNo = toMobileNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Long getEndTime() {
		return endTime;
	}
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public Long getRing_time() {
		return Ring_time;
	}
	public void setRing_time(Long ring_time) {
		Ring_time = ring_time;
	}

}
