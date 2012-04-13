package com.itgrids.partyanalyst.dto;

public class UserTrackingReportVO {
	private Integer uniqueVisitors;
	private String totalTimeSpent;
	private String avgTimeSpent;
	private String totalNoOfPagesAccessed;
	private String avgNoOfPagesAccessed;
	
	public Integer getUniqueVisitors() {
		return uniqueVisitors;
	}
	public void setUniqueVisitors(Integer uniqueVisitors) {
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
	public String getTotalNoOfPagesAccessed() {
		return totalNoOfPagesAccessed;
	}
	public void setTotalNoOfPagesAccessed(String totalNoOfPagesAccessed) {
		this.totalNoOfPagesAccessed = totalNoOfPagesAccessed;
	}
	public String getAvgNoOfPagesAccessed() {
		return avgNoOfPagesAccessed;
	}
	public void setAvgNoOfPagesAccessed(String avgNoOfPagesAccessed) {
		this.avgNoOfPagesAccessed = avgNoOfPagesAccessed;
	}
	
	
}
