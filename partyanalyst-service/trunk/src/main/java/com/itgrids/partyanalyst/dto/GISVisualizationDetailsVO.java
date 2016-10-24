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
	private Long regCount2016;
	private String regCount2016Perc;
	private Long regCount2014;
	private String regCount2014Perc;
	private Long newRegCount;
	private String newRegCountPerc;
	private Long renewalCount;
	private String renewalCountPerc;
	
	
	//VERY-GOOD, GOOD, OK, POOR, VERY_POOR / ISSSUES Status Details
	private List<GISVisualizationBasicVO> statusList = new ArrayList<GISVisualizationBasicVO>(0);
	private List<GISVisualizationDetailsVO> locationsList = new ArrayList<GISVisualizationDetailsVO>(0);
	private GISUserTrackingVO userTrackingVO;
	
	public GISVisualizationDetailsVO(){}
	public GISVisualizationDetailsVO(Long locationId,String locationName){
		this.locationId = locationId;
		this.locationName = locationName;
	}
	
	
	public Long getRegCount2016() {
		return regCount2016;
	}
	public void setRegCount2016(Long regCount2016) {
		this.regCount2016 = regCount2016;
	}
	public String getRegCount2016Perc() {
		return regCount2016Perc;
	}
	public void setRegCount2016Perc(String regCount2016Perc) {
		this.regCount2016Perc = regCount2016Perc;
	}
	public Long getRegCount2014() {
		return regCount2014;
	}
	public void setRegCount2014(Long regCount2014) {
		this.regCount2014 = regCount2014;
	}
	public String getRegCount2014Perc() {
		return regCount2014Perc;
	}
	public void setRegCount2014Perc(String regCount2014Perc) {
		this.regCount2014Perc = regCount2014Perc;
	}
	public Long getNewRegCount() {
		return newRegCount;
	}
	public void setNewRegCount(Long newRegCount) {
		this.newRegCount = newRegCount;
	}
	public String getNewRegCountPerc() {
		return newRegCountPerc;
	}
	public void setNewRegCountPerc(String newRegCountPerc) {
		this.newRegCountPerc = newRegCountPerc;
	}
	public Long getRenewalCount() {
		return renewalCount;
	}
	public void setRenewalCount(Long renewalCount) {
		this.renewalCount = renewalCount;
	}
	public String getRenewalCountPerc() {
		return renewalCountPerc;
	}
	public void setRenewalCountPerc(String renewalCountPerc) {
		this.renewalCountPerc = renewalCountPerc;
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
