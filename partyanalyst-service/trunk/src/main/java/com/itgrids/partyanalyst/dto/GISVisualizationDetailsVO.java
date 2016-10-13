package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class GISVisualizationDetailsVO implements java.io.Serializable{
	
	private Long locationId =0L;
	private String locationName;
	private String areaType;
	
	private Long totalCount =0L;
	private Long startedCount =0L;
	private Long notStartedCount =0L;
	private Long registeredCount =0L;
	private String perc="0.0";
	
	//VERY-GOOD, GOOD, OK, POOR, VERY_POOR / ISSSUES Status Details
	private List<GISVisualizationBasicVO> statusList = new ArrayList<GISVisualizationBasicVO>(0);
	private List<GISVisualizationDetailsVO> locationsList = new ArrayList<GISVisualizationDetailsVO>(0);
	private GISUserTrackingVO userTrackingVO;
	
	public GISVisualizationDetailsVO(){}
	public GISVisualizationDetailsVO(Long locationId,String locationName){
		this.locationId = locationId;
		this.locationName = locationName;
	}
	
	
	public GISUserTrackingVO getUserTrackingVO() {
		return userTrackingVO;
	}
	public void setUserTrackingVO(GISUserTrackingVO userTrackingVO) {
		this.userTrackingVO = userTrackingVO;
	}
	public String getPerc() {
		return perc;
	}
	public void setPerc(String perc) {
		this.perc = perc;
	}
	public Long getRegisteredCount() {
		return registeredCount;
	}
	public void setRegisteredCount(Long registeredCount) {
		this.registeredCount = registeredCount;
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
	public String getAreaType() {
		return areaType;
	}
	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getStartedCount() {
		return startedCount;
	}
	public void setStartedCount(Long startedCount) {
		this.startedCount = startedCount;
	}
	public Long getNotStartedCount() {
		return notStartedCount;
	}
	public void setNotStartedCount(Long notStartedCount) {
		this.notStartedCount = notStartedCount;
	}
	public List<GISVisualizationBasicVO> getStatusList() {
		return statusList;
	}
	public void setStatusList(List<GISVisualizationBasicVO> statusList) {
		this.statusList = statusList;
	}
	public List<GISVisualizationDetailsVO> getLocationsList() {
		return locationsList;
	}
	public void setLocationsList(List<GISVisualizationDetailsVO> locationsList) {
		this.locationsList = locationsList;
	}
	
}
