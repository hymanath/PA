package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class MeetingTrackingVO implements java.io.Serializable{

	private Long id;
	private String name;
	private Long totalCount;
	private Long actualCount;
	private String monthName;
	List<MeetingTrackingVO> meetingTrackingVOList = new ArrayList<MeetingTrackingVO>(0);
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
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getActualCount() {
		return actualCount;
	}
	public void setActualCount(Long actualCount) {
		this.actualCount = actualCount;
	}
	public String getMonthName() {
		return monthName;
	}
	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}
	public List<MeetingTrackingVO> getMeetingTrackingVOList() {
		return meetingTrackingVOList;
	}
	public void setMeetingTrackingVOList(
			List<MeetingTrackingVO> meetingTrackingVOList) {
		this.meetingTrackingVOList = meetingTrackingVOList;
	}
	
}
