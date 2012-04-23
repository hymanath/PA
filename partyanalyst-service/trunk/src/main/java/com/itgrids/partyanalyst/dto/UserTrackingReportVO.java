package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class UserTrackingReportVO extends UserTrackingVO implements Serializable{
	
	private static final long serialVersionUID = 2217831199065585193L;
	private String sessionId;
	private Long uniqueVisitors;
	private String totalTimeSpent;
	private String avgTimeSpent;
	private Long totalNoOfPagesAccessed;
	private Float avgNoOfPagesAccessed;
	private Date time;
	private String landingPage;
	private String exitPage;
	private String spentTime;
	private Date fromDate;
	private Date toDate;
	private String userName;
	private Map<String, String> landingPageMap;
	private Map<String, String> exitPageMap;
	private Map<String, String> bouncePageMap;
	private List<AccessedPageLoginTimeVO> urlTimeVOList;
	
	public Map<String, String> getLandingPageMap() {
		return landingPageMap;
	}
	public void setLandingPageMap(Map<String, String> landingPageMap) {
		this.landingPageMap = landingPageMap;
	}
	public Map<String, String> getExitPageMap() {
		return exitPageMap;
	}
	public void setExitPageMap(Map<String, String> exitPageMap) {
		this.exitPageMap = exitPageMap;
	}
	public Map<String, String> getBouncePageMap() {
		return bouncePageMap;
	}
	public void setBouncePageMap(Map<String, String> bouncePageMap) {
		this.bouncePageMap = bouncePageMap;
	}
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
	public Float getAvgNoOfPagesAccessed() {
		return avgNoOfPagesAccessed;
	}
	public void setAvgNoOfPagesAccessed(Float avgNoOfPagesAccessed) {
		this.avgNoOfPagesAccessed = avgNoOfPagesAccessed;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public List<AccessedPageLoginTimeVO> getUrlTimeVOList() {
		return urlTimeVOList;
	}
	public void setUrlTimeVOList(List<AccessedPageLoginTimeVO> urlTimeVOList) {
		this.urlTimeVOList = urlTimeVOList;
	}		
}
