package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;


public class MeetingVO implements java.io.Serializable{
	private Long id;
	private String meetingName;
	private String name;
	private Long levelId;
	private List<MeetingVO> userAccessLevelList = new ArrayList<MeetingVO>();
	private List<MeetingVO> userAccessLevelValuesList = new ArrayList<MeetingVO>() ;
	private List<Long> levelValues;
	private Long count;
	
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
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public List<Long> getLevelValues() {
		return levelValues;
	}
	public void setLevelValues(List<Long> levelValues) {
		this.levelValues = levelValues;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getLevelId() {
		return levelId;
	}
	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}
	public List<MeetingVO> getUserAccessLevelList() {
		return userAccessLevelList;
	}
	public void setUserAccessLevelList(List<MeetingVO> userAccessLevelList) {
		this.userAccessLevelList = userAccessLevelList;
	}
	public List<MeetingVO> getUserAccessLevelValuesList() {
		return userAccessLevelValuesList;
	}
	public void setUserAccessLevelValuesList(
			List<MeetingVO> userAccessLevelValuesList) {
		this.userAccessLevelValuesList = userAccessLevelValuesList;
	}
	
	
	
}
