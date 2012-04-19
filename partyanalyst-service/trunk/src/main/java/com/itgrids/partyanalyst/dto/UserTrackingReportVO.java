package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.Date;

public class UserTrackingReportVO extends UserTrackingVO implements Serializable{
	
	private static final long serialVersionUID = 2217831199065585193L;
	private Long uniqueVisitors;
	private String totalTimeSpent;
	private String avgTimeSpent;
	private Long totalNoOfPagesAccessed;
	private float avgNoOfPagesAccessed;
	private Date time;
	private String landingPage;
	private String exitPage;
	private String spentTime;
	private Date fromDate;
	private Date toDate;
	private String userName;
	private Long noOfPages;
	
	
	public Long getUniqueVisitors() {
		return uniqueVisitors;
	}
	public void setUniqueVisitors(Long uniqueVisitors) {
		this.uniqueVisitors = uniqueVisitors;
	}
	
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getLandingPage() {
		return landingPage;
	}
	public void setLandingPage(String landingPage) {
		this.landingPage = landingPage;
	}
	public String getExitPage() {
		return exitPage;
	}
	public void setExitPage(String exitPage) {
		this.exitPage = exitPage;
	}
	public String getSpentTime() {
		return spentTime;
	}
	public void setSpentTime(String spentTime) {
		this.spentTime = spentTime;
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
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
