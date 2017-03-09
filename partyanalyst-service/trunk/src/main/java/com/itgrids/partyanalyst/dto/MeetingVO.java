package com.itgrids.partyanalyst.dto;

import java.util.List;


public class MeetingVO implements java.io.Serializable{
	private String name;
	private Long levelId;
	private List<MeetingVO> userAccessLevelList;
	private List<MeetingVO> userAccessLevelValuesList;
	private List<Long> levelValues;
	private Long count;
	
	
	
	
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
