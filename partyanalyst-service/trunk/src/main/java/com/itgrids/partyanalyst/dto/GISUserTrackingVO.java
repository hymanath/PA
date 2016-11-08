package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class GISUserTrackingVO implements java.io.Serializable{

	private Long id ;
	private String name;
	private Long allocatedCount=0L;
	private Long activeCount=0L;
	private Long lastOneHrActiveCount=0L;
	private Long lastOneHrCount=0L;
	private Long lastOneHrAvgCount=0L;
	private Long avgOutput=0L;
	private Long overAllOutput=0L;
	private Long registeredCount=0L;
	private String perc;
	
	private String mobileNo;
	private String lattitude;
	private String longitude;
	private String surveyTime;
	private String startDate;
	private String endDate;
	private Long locationId;
	private String locationName;
	private Long totalCount = 0l;
	private Long todayCount = 0l;
	private String imagePath;
	
	private List<GISUserTrackingVO> usersList = new ArrayList<GISUserTrackingVO>(0);
	private List<GISUserTrackingVO> lastOneHrusersList = new ArrayList<GISUserTrackingVO>(0);
	
	
	public Long getLastOneHrActiveCount() {
		return lastOneHrActiveCount;
	}
	public void setLastOneHrActiveCount(Long lastOneHrActiveCount) {
		this.lastOneHrActiveCount = lastOneHrActiveCount;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getTodayCount() {
		return todayCount;
	}
	public void setTodayCount(Long todayCount) {
		this.todayCount = todayCount;
	}
	public List<GISUserTrackingVO> getLastOneHrusersList() {
		return lastOneHrusersList;
	}
	public void setLastOneHrusersList(List<GISUserTrackingVO> lastOneHrusersList) {
		this.lastOneHrusersList = lastOneHrusersList;
	}
	public Long getLastOneHrAvgCount() {
		return lastOneHrAvgCount;
	}
	public void setLastOneHrAvgCount(Long lastOneHrAvgCount) {
		this.lastOneHrAvgCount = lastOneHrAvgCount;
	}
	public Long getRegisteredCount() {
		return registeredCount;
	}
	public void setRegisteredCount(Long registeredCount) {
		this.registeredCount = registeredCount;
	}
	public List<GISUserTrackingVO> getUsersList() {
		return usersList;
	}
	public void setUsersList(List<GISUserTrackingVO> usersList) {
		this.usersList = usersList;
	}
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
	public Long getAllocatedCount() {
		return allocatedCount;
	}
	public void setAllocatedCount(Long allocatedCount) {
		this.allocatedCount = allocatedCount;
	}
	public Long getActiveCount() {
		return activeCount;
	}
	public void setActiveCount(Long activeCount) {
		this.activeCount = activeCount;
	}
	public Long getLastOneHrCount() {
		return lastOneHrCount;
	}
	public void setLastOneHrCount(Long lastOneHrCount) {
		this.lastOneHrCount = lastOneHrCount;
	}
	public Long getAvgOutput() {
		return avgOutput;
	}
	public void setAvgOutput(Long avgOutput) {
		this.avgOutput = avgOutput;
	}
	public Long getOverAllOutput() {
		return overAllOutput;
	}
	public void setOverAllOutput(Long overAllOutput) {
		this.overAllOutput = overAllOutput;
	}
	public String getPerc() {
		return perc;
	}
	public void setPerc(String perc) {
		this.perc = perc;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getLattitude() {
		return lattitude;
	}
	public void setLattitude(String lattitude) {
		this.lattitude = lattitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getSurveyTime() {
		return surveyTime;
	}
	public void setSurveyTime(String surveyTime) {
		this.surveyTime = surveyTime;
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
	
}
