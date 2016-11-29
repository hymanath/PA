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
	private Long regCount2016=0L;
	private String regCount2016Perc;
	private Long regCount2014=0L;
	private String regCount2014Perc;
	private Long newRegCount=0L;
	private String newRegCountPerc;
	private Long renewalCount=0L;
	private String renewalCountPerc;
	private Long todayRegCount=0L;
	private String todayRegPerc;
	private Long todayNewRegCount=0L;
	private String todayNewRegPerc;
	private Long todayRenewalCount=0L;
	private String todayRenewalPerc;
	private Long targetCount=0L;
	private Long todayTarget=0L;
	private String achievedPerc;
	
	private String earnedVotesIn2014;
	private String earnedVotesPercIn2014;
	private String validVotes;
	private String marginVotes;
	private String marginVotesPerc;
	private String rank;
	private String isYCPArea;
	
	private Long firstPositionPartyId;
	private Long secondPositionPartyId;
	private Long thirdPositionPartyId;
	private String firstPositionPartyName;
	private String secondPositionPartyName;
	private String thirdPositionPartyName;
	private String firstPartyImageLogo;
	private String secondPartyImageLogo;
	private String thirdPartyImageLogo;
	
	//VERY-GOOD, GOOD, OK, POOR, VERY_POOR / ISSSUES Status Details
	private List<GISVisualizationBasicVO> statusList = new ArrayList<GISVisualizationBasicVO>(0);
	private List<GISVisualizationDetailsVO> locationsList = new ArrayList<GISVisualizationDetailsVO>(0);
	private GISUserTrackingVO userTrackingVO;
	
	public GISVisualizationDetailsVO(){}
	public GISVisualizationDetailsVO(Long locationId,String locationName){
		this.locationId = locationId;
		this.locationName = locationName;
	}
	
	public Long getTodayTarget() {
		return todayTarget;
	}
	public void setTodayTarget(Long todayTarget) {
		this.todayTarget = todayTarget;
	}
	public String getMarginVotes() {
		return marginVotes;
	}
	public void setMarginVotes(String marginVotes) {
		this.marginVotes = marginVotes;
	}
	public String getMarginVotesPerc() {
		return marginVotesPerc;
	}
	public void setMarginVotesPerc(String marginVotesPerc) {
		this.marginVotesPerc = marginVotesPerc;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getIsYCPArea() {
		return isYCPArea;
	}
	public void setIsYCPArea(String isYCPArea) {
		this.isYCPArea = isYCPArea;
	}
	public String getEarnedVotesIn2014() {
		return earnedVotesIn2014;
	}
	public void setEarnedVotesIn2014(String earnedVotesIn2014) {
		this.earnedVotesIn2014 = earnedVotesIn2014;
	}
	public String getEarnedVotesPercIn2014() {
		return earnedVotesPercIn2014;
	}
	public void setEarnedVotesPercIn2014(String earnedVotesPercIn2014) {
		this.earnedVotesPercIn2014 = earnedVotesPercIn2014;
	}
	public String getValidVotes() {
		return validVotes;
	}
	public void setValidVotes(String validVotes) {
		this.validVotes = validVotes;
	}
	public Long getTargetCount() {
		return targetCount;
	}
	public void setTargetCount(Long targetCount) {
		this.targetCount = targetCount;
	}
	public String getAchievedPerc() {
		return achievedPerc;
	}
	public void setAchievedPerc(String achievedPerc) {
		this.achievedPerc = achievedPerc;
	}
	public Long getTodayRegCount() {
		return todayRegCount;
	}
	public void setTodayRegCount(Long todayRegCount) {
		this.todayRegCount = todayRegCount;
	}
	public String getTodayRegPerc() {
		return todayRegPerc;
	}
	public void setTodayRegPerc(String todayRegPerc) {
		this.todayRegPerc = todayRegPerc;
	}
	public Long getTodayNewRegCount() {
		return todayNewRegCount;
	}
	public void setTodayNewRegCount(Long todayNewRegCount) {
		this.todayNewRegCount = todayNewRegCount;
	}
	public String getTodayNewRegPerc() {
		return todayNewRegPerc;
	}
	public void setTodayNewRegPerc(String todayNewRegPerc) {
		this.todayNewRegPerc = todayNewRegPerc;
	}
	public Long getTodayRenewalCount() {
		return todayRenewalCount;
	}
	public void setTodayRenewalCount(Long todayRenewalCount) {
		this.todayRenewalCount = todayRenewalCount;
	}
	public String getTodayRenewalPerc() {
		return todayRenewalPerc;
	}
	public void setTodayRenewalPerc(String todayRenewalPerc) {
		this.todayRenewalPerc = todayRenewalPerc;
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
	public String getFirstPositionPartyName() {
		return firstPositionPartyName;
	}
	public void setFirstPositionPartyName(String firstPositionPartyName) {
		this.firstPositionPartyName = firstPositionPartyName;
	}
	public String getSecondPositionPartyName() {
		return secondPositionPartyName;
	}
	public void setSecondPositionPartyName(String secondPositionPartyName) {
		this.secondPositionPartyName = secondPositionPartyName;
	}
	public Long getFirstPositionPartyId() {
		return firstPositionPartyId;
	}
	public void setFirstPositionPartyId(Long firstPositionPartyId) {
		this.firstPositionPartyId = firstPositionPartyId;
	}
	public Long getSecondPositionPartyId() {
		return secondPositionPartyId;
	}
	public void setSecondPositionPartyId(Long secondPositionPartyId) {
		this.secondPositionPartyId = secondPositionPartyId;
	}
	public Long getThirdPositionPartyId() {
		return thirdPositionPartyId;
	}
	public void setThirdPositionPartyId(Long thirdPositionPartyId) {
		this.thirdPositionPartyId = thirdPositionPartyId;
	}
	public String getThirdPositionPartyName() {
		return thirdPositionPartyName;
	}
	public void setThirdPositionPartyName(String thirdPositionPartyName) {
		this.thirdPositionPartyName = thirdPositionPartyName;
	}
	public String getFirstPartyImageLogo() {
		return firstPartyImageLogo;
	}
	public void setFirstPartyImageLogo(String firstPartyImageLogo) {
		this.firstPartyImageLogo = firstPartyImageLogo;
	}
	public String getSecondPartyImageLogo() {
		return secondPartyImageLogo;
	}
	public void setSecondPartyImageLogo(String secondPartyImageLogo) {
		this.secondPartyImageLogo = secondPartyImageLogo;
	}
	public String getThirdPartyImageLogo() {
		return thirdPartyImageLogo;
	}
	public void setThirdPartyImageLogo(String thirdPartyImageLogo) {
		this.thirdPartyImageLogo = thirdPartyImageLogo;
	}

}
