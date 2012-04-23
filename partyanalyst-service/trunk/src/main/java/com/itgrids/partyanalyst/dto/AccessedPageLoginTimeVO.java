package com.itgrids.partyanalyst.dto;

public class AccessedPageLoginTimeVO {
	private String pageUrl;
	private Long accessTime;
	private Integer accessCount;
	private String fromTime;
	private String toTime;
	private String timeSpent;
	private String loginDate;
	
	public Integer getAccessCount() {
		return accessCount;
	}
	public void setAccessCount(Integer accessCount) {
		this.accessCount = accessCount;
	}
	public String getFromTime() {
		return fromTime;
	}
	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}
	public String getToTime() {
		return toTime;
	}
	public void setToTime(String toTime) {
		this.toTime = toTime;
	}
	public String getPageUrl() {
		return pageUrl;
	}
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
	public Long getAccessTime() {
		return accessTime;
	}
	public void setAccessTime(Long accessTime) {
		this.accessTime = accessTime;
	}
	public String getTimeSpent() {
		return timeSpent;
	}
	public void setTimeSpent(String timeSpent) {
		this.timeSpent = timeSpent;
	}
	public String getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(String loginDate) {
		this.loginDate = loginDate;
	}
}
