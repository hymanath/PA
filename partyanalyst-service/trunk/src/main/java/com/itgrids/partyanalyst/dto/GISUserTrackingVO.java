package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class GISUserTrackingVO implements java.io.Serializable{

	private Long id ;
	private String name;
	private Long allocatedCount=0L;
	private Long activeCount=0L;
	private Long lastOneHrActiveCount=0L;
	private Long inActiveCount=0L;
	private Long todayActiveCount=0L;
	private Long todayInActiveCount=0L;
	private Long lastOneHrInActiveCount=0L;
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
	private Long districtId;
	private String districtName; 
	private Long totalCount = 0l;
	private Long todayCount = 0l;
	private String imagePath;
	
	private List<GISUserTrackingVO> usersList = new ArrayList<GISUserTrackingVO>(0);
	private List<GISUserTrackingVO> todayActiveUsersList = new ArrayList<GISUserTrackingVO>(0);
	private List<GISUserTrackingVO> todayInActiveUsersList = new ArrayList<GISUserTrackingVO>(0);
	private List<GISUserTrackingVO> lastOneHrActiveusersList = new ArrayList<GISUserTrackingVO>(0);
	private List<GISUserTrackingVO> lastOneHrInActiveusersList = new ArrayList<GISUserTrackingVO>(0);
	
	private String activeLongitudeStr;
	private String activeLattitudeStr;
	
	private String last1hrActiveLongitudeStr;
	private String last1hrActiveLattitudeStr;
	
	private String inActiveLongitudeStr;
	private String inactiveLattitudeStr;
	
	private String last1hrInactiveLongitudeStr;
	private String last1hrInActiveLattitudeStr;
	
	
	public String getActiveLongitudeStr() {
		return activeLongitudeStr;
	}
	public void setActiveLongitudeStr(String activeLongitudeStr) {
		this.activeLongitudeStr = activeLongitudeStr;
	}
	public String getActiveLattitudeStr() {
		return activeLattitudeStr;
	}
	public void setActiveLattitudeStr(String activeLattitudeStr) {
		this.activeLattitudeStr = activeLattitudeStr;
	}
	public String getLast1hrActiveLongitudeStr() {
		return last1hrActiveLongitudeStr;
	}
	public void setLast1hrActiveLongitudeStr(String last1hrActiveLongitudeStr) {
		this.last1hrActiveLongitudeStr = last1hrActiveLongitudeStr;
	}
	public String getLast1hrActiveLattitudeStr() {
		return last1hrActiveLattitudeStr;
	}
	public void setLast1hrActiveLattitudeStr(String last1hrActiveLattitudeStr) {
		this.last1hrActiveLattitudeStr = last1hrActiveLattitudeStr;
	}
	public String getInActiveLongitudeStr() {
		return inActiveLongitudeStr;
	}
	public void setInActiveLongitudeStr(String inActiveLongitudeStr) {
		this.inActiveLongitudeStr = inActiveLongitudeStr;
	}
	public String getInactiveLattitudeStr() {
		return inactiveLattitudeStr;
	}
	public void setInactiveLattitudeStr(String inactiveLattitudeStr) {
		this.inactiveLattitudeStr = inactiveLattitudeStr;
	}
	public String getLast1hrInactiveLongitudeStr() {
		return last1hrInactiveLongitudeStr;
	}
	public void setLast1hrInactiveLongitudeStr(String last1hrInactiveLongitudeStr) {
		this.last1hrInactiveLongitudeStr = last1hrInactiveLongitudeStr;
	}
	public String getLast1hrInActiveLattitudeStr() {
		return last1hrInActiveLattitudeStr;
	}
	public void setLast1hrInActiveLattitudeStr(String last1hrInActiveLattitudeStr) {
		this.last1hrInActiveLattitudeStr = last1hrInActiveLattitudeStr;
	}
	public Long getTodayActiveCount() {
		return todayActiveCount;
	}
	public void setTodayActiveCount(Long todayActiveCount) {
		this.todayActiveCount = todayActiveCount;
	}
	public Long getTodayInActiveCount() {
		return todayInActiveCount;
	}
	public void setTodayInActiveCount(Long todayInActiveCount) {
		this.todayInActiveCount = todayInActiveCount;
	}
	public Long getInActiveCount() {
		return inActiveCount;
	}
	public void setInActiveCount(Long inActiveCount) {
		this.inActiveCount = inActiveCount;
	}
	public Long getLastOneHrInActiveCount() {
		return lastOneHrInActiveCount;
	}
	public void setLastOneHrInActiveCount(Long lastOneHrInActiveCount) {
		this.lastOneHrInActiveCount = lastOneHrInActiveCount;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public List<GISUserTrackingVO> getTodayActiveUsersList() {
		return todayActiveUsersList;
	}
	public void setTodayActiveUsersList(List<GISUserTrackingVO> todayActiveUsersList) {
		this.todayActiveUsersList = todayActiveUsersList;
	}
	public List<GISUserTrackingVO> getTodayInActiveUsersList() {
		return todayInActiveUsersList;
	}
	public void setTodayInActiveUsersList(
			List<GISUserTrackingVO> todayInActiveUsersList) {
		this.todayInActiveUsersList = todayInActiveUsersList;
	}
	public List<GISUserTrackingVO> getLastOneHrActiveusersList() {
		return lastOneHrActiveusersList;
	}
	public void setLastOneHrActiveusersList(
			List<GISUserTrackingVO> lastOneHrActiveusersList) {
		this.lastOneHrActiveusersList = lastOneHrActiveusersList;
	}
	public List<GISUserTrackingVO> getLastOneHrInActiveusersList() {
		return lastOneHrInActiveusersList;
	}
	public void setLastOneHrInActiveusersList(
			List<GISUserTrackingVO> lastOneHrInActiveusersList) {
		this.lastOneHrInActiveusersList = lastOneHrInActiveusersList;
	}
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
