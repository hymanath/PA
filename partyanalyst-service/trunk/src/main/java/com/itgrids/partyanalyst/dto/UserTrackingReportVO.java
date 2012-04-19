package com.itgrids.partyanalyst.dto;

public class UserTrackingReportVO {
	private Long uniqueVisitors;
	private String totalTimeSpent;
	private String avgTimeSpent;
	private Long totalNoOfPagesAccessed;
	private float avgNoOfPagesAccessed;
	
	public Long getUniqueVisitors() {
		return uniqueVisitors;
	}
	public void setUniqueVisitors(Long uniqueVisitors) {
		this.uniqueVisitors = uniqueVisitors;
	}
	public String getTotalTimeSpent() {
		return totalTimeSpent;
	}
	public void setTotalTimeSpent(String totalTimeSpent) {
		this.totalTimeSpent = totalTimeSpent;
	}
	public String getAvgTimeSpent() {
		return avgTimeSpent;
	}
	public void setAvgTimeSpent(String avgTimeSpent) {
		this.avgTimeSpent = avgTimeSpent;
	}
	public Long getTotalNoOfPagesAccessed() {
		return totalNoOfPagesAccessed;
	}
	public void setTotalNoOfPagesAccessed(Long totalNoOfPagesAccessed) {
		this.totalNoOfPagesAccessed = totalNoOfPagesAccessed;
	}
	public float getAvgNoOfPagesAccessed() {
		return avgNoOfPagesAccessed;
	}
	public void setAvgNoOfPagesAccessed(float avgNoOfPagesAccessed) {
		this.avgNoOfPagesAccessed = avgNoOfPagesAccessed;
	}
	
	
}
