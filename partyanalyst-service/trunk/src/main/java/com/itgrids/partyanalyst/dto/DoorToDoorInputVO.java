package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class DoorToDoorInputVO implements Serializable{
	private Long id;
	private List<Long> levelIds;
	private List<Long> levelValues;
	private String startDateStr;
	private String endDateStr;
	private String locationType;
    private List<Long> notStartedList;
	private Long departmentId;
	private Long issueTypeId;
	
	private String imageType;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Long> getLevelIds() {
		return levelIds;
	}
	public void setLevelIds(List<Long> levelIds) {
		this.levelIds = levelIds;
	}
	public List<Long> getLevelValues() {
		return levelValues;
	}
	public void setLevelValues(List<Long> levelValues) {
		this.levelValues = levelValues;
	}
	public String getStartDateStr() {
		return startDateStr;
	}
	public void setStartDateStr(String startDateStr) {
		this.startDateStr = startDateStr;
	}
	public String getEndDateStr() {
		return endDateStr;
	}
	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}
	public String getLocationType() {
		return locationType;
	}
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
	public List<Long> getNotStartedList() {
		return notStartedList;
	}
	public void setNotStartedList(List<Long> notStartedList) {
		this.notStartedList = notStartedList;
	}
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public Long getIssueTypeId() {
		return issueTypeId;
	}
	public void setIssueTypeId(Long issueTypeId) {
		this.issueTypeId = issueTypeId;
	}
	public String getImageType() {
		return imageType;
	}
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
	
	
}
