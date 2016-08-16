package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HYMAVATHI
 * @date July 15, 2016
 */
public class NominatedPostDashboardVO {

	private Long statusId;
	private String statusName;
	private Long statusCount = 0l;
	private List<NominatedPostDashboardVO>  applicatnStatsList= new ArrayList<NominatedPostDashboardVO>();
	private List<NominatedPostDashboardVO>  positinsList= new ArrayList<NominatedPostDashboardVO>();
	private List<NominatedPostDashboardVO>  nominatedStatusList= new ArrayList<NominatedPostDashboardVO>();
	private String ageRange;
	private Long ageCount = 0l;
	private Long totalCnt = 0l;
	private List<NominatedPostDashboardVO>  ageList= new ArrayList<NominatedPostDashboardVO>();
	private Long ageRangeId;
	private Long id;
	private String name;
	private Long maleCount = 0l;
	private Long femaleCount = 0l;
	private String percentage="0";
	private Long twentyTo29AgeRangeCount=0l;
	private Long thirtyTo39AgeRangeCount=0l;
	private Long fourtyTo49AgeRangeCount=0l;
	private Long fiftyTo59AgeRangeCount=0l;
	private Long sixtyAvoveAgeRangeCount=0l;
	private Long overAllCount=0l;
	
	
	public String getPercentage() {
		return percentage;
	}
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}
	public Long getAgeRangeId() {
		return ageRangeId;
	}
	public void setAgeRangeId(Long ageRangeId) {
		this.ageRangeId = ageRangeId;
	}
	public List<NominatedPostDashboardVO> getAgeList() {
		return ageList;
	}
	public void setAgeList(List<NominatedPostDashboardVO> ageList) {
		this.ageList = ageList;
	}
	public String getAgeRange() {
		return ageRange;
	}
	public void setAgeRange(String ageRange) {
		this.ageRange = ageRange;
	}
	public Long getAgeCount() {
		return ageCount;
	}
	public void setAgeCount(Long ageCount) {
		this.ageCount = ageCount;
	}
	public Long getMaleCount() {
		return maleCount;
	}
	public void setMaleCount(Long maleCount) {
		this.maleCount = maleCount;
	}
	public Long getFemaleCount() {
		return femaleCount;
	}
	public void setFemaleCount(Long femaleCount) {
		this.femaleCount = femaleCount;
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
	public Long getTotalCnt() {
		return totalCnt;
	}
	public void setTotalCnt(Long totalCnt) {
		this.totalCnt = totalCnt;
	}
	public List<NominatedPostDashboardVO> getApplicatnStatsList() {
		return applicatnStatsList;
	}
	public void setApplicatnStatsList(
			List<NominatedPostDashboardVO> applicatnStatsList) {
		this.applicatnStatsList = applicatnStatsList;
	}
	public List<NominatedPostDashboardVO> getPositinsList() {
		return positinsList;
	}
	public void setPositinsList(List<NominatedPostDashboardVO> positinsList) {
		this.positinsList = positinsList;
	}
	public List<NominatedPostDashboardVO> getNominatedStatusList() {
		return nominatedStatusList;
	}
	public void setNominatedStatusList(
			List<NominatedPostDashboardVO> nominatedStatusList) {
		this.nominatedStatusList = nominatedStatusList;
	}
	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public Long getStatusCount() {
		return statusCount;
	}
	public void setStatusCount(Long statusCount) {
		this.statusCount = statusCount;
	}
	public Long getTwentyTo29AgeRangeCount() {
		return twentyTo29AgeRangeCount;
	}
	public void setTwentyTo29AgeRangeCount(Long twentyTo29AgeRangeCount) {
		this.twentyTo29AgeRangeCount = twentyTo29AgeRangeCount;
	}
	public Long getThirtyTo39AgeRangeCount() {
		return thirtyTo39AgeRangeCount;
	}
	public void setThirtyTo39AgeRangeCount(Long thirtyTo39AgeRangeCount) {
		this.thirtyTo39AgeRangeCount = thirtyTo39AgeRangeCount;
	}
	public Long getFourtyTo49AgeRangeCount() {
		return fourtyTo49AgeRangeCount;
	}
	public void setFourtyTo49AgeRangeCount(Long fourtyTo49AgeRangeCount) {
		this.fourtyTo49AgeRangeCount = fourtyTo49AgeRangeCount;
	}
	public Long getFiftyTo59AgeRangeCount() {
		return fiftyTo59AgeRangeCount;
	}
	public void setFiftyTo59AgeRangeCount(Long fiftyTo59AgeRangeCount) {
		this.fiftyTo59AgeRangeCount = fiftyTo59AgeRangeCount;
	}
	public Long getSixtyAvoveAgeRangeCount() {
		return sixtyAvoveAgeRangeCount;
	}
	public void setSixtyAvoveAgeRangeCount(Long sixtyAvoveAgeRangeCount) {
		this.sixtyAvoveAgeRangeCount = sixtyAvoveAgeRangeCount;
	}
	public Long getOverAllCount() {
		return overAllCount;
	}
	public void setOverAllCount(Long overAllCount) {
		this.overAllCount = overAllCount;
	}
}
