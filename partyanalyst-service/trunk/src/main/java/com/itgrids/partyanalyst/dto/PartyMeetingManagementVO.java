package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class PartyMeetingManagementVO implements Serializable{

	private static final long serialVersionUID = -7761642942902577772L; 
	 
	  private Long id;
	  private String name;
	  private String meetingName;
	  private String startDate;
	  private String endDate;
	  private Long meetingTypeId;
	  private Long meetingLevelId;
	  
	  public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	  public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMeetingName() {
		return meetingName;
	}
	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Long getMeetingTypeId() {
		return meetingTypeId;
	}
	public void setMeetingTypeId(Long meetingTypeId) {
		this.meetingTypeId = meetingTypeId;
	}
	public Long getMeetingLevelId() {
		return meetingLevelId;
	}
	public void setMeetingLevelId(Long meetingLevelId) {
		this.meetingLevelId = meetingLevelId;
	}
	

}
