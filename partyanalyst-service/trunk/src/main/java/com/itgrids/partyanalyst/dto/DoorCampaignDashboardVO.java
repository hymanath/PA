package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DoorCampaignDashboardVO implements Serializable{

	private Long totalUsers = 0l;
	private Long activeUsers = 0l;
	private Long inActiveUsers = 0l;
	private Long totalHouseHolds = 0l;
	private Long visitedHouseHolds = 0l;
	private Long pendingHouseHolds = 0l;
	private Long totalImagesReceived = 0l;
	private Long totalGrievances = 0l;
	private Long individual = 0l;
	private Long community = 0l;
	private String vistedPerc;
	private String pendingPerc;
	private List<Long> notVistedLst = new ArrayList<Long>();
	private List<DoorCampaignDashboardVO> subList = new ArrayList<DoorCampaignDashboardVO>();
	private Long id;
	private String name;
	private Long districtId;
	private String districtName;
	private Long totalCount = 0l;
	private Long startedCount = 0l;
	private Long notStartedCount=0l;
	
	private Long constituencyId;
	private String constituencyName;
	private Long tehsilId;
	private String tehsilName;
	private Long lebId;
	private String lebName;
	private Long panchayatId;
	private String pancahyatName;
	private Long boothId;
	private String boothNo;
	private String boothLocation;
	private Long houseHoldId;
	private String date;
	
	private Long oneHourActive = 0L;
	private Long threeHoursActive = 0L;
	private Long fiveHoursActive = 0L;
	
	private Long issueTypeId;
	private String issueType;
	private Long subIssueTypeId;
	private String subIssueType;
	private String localElectionBodyName;
	
	private List<Long> accessLevelIds= new ArrayList<Long>();
	private List<Long>   accessLevelValuesList = new ArrayList<Long>();
	
	
	
	public List<Long> getAccessLevelIds() {
		return accessLevelIds;
	}
	public void setAccessLevelIds(List<Long> accessLevelIds) {
		this.accessLevelIds = accessLevelIds;
	}
	public List<Long> getAccessLevelValuesList() {
		return accessLevelValuesList;
	}
	public void setAccessLevelValuesList(List<Long> accessLevelValuesList) {
		this.accessLevelValuesList = accessLevelValuesList;
	}
	public String getLocalElectionBodyName() {
		return localElectionBodyName;
	}
	public void setLocalElectionBodyName(String localElectionBodyName) {
		this.localElectionBodyName = localElectionBodyName;
	}
	public List<Long> getNotVistedLst() {
		return notVistedLst;
	}
	public void setNotVistedLst(List<Long> notVistedLst) {
		this.notVistedLst = notVistedLst;
	}
	public Long getNotStartedCount() {
		return notStartedCount;
	}
	public void setNotStartedCount(Long notStartedCount) {
		this.notStartedCount = notStartedCount;
	}
	public Long getIssueTypeId() {
		return issueTypeId;
	}
	public void setIssueTypeId(Long issueTypeId) {
		this.issueTypeId = issueTypeId;
	}
	public String getIssueType() {
		return issueType;
	}
	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}
	public Long getSubIssueTypeId() {
		return subIssueTypeId;
	}
	public void setSubIssueTypeId(Long subIssueTypeId) {
		this.subIssueTypeId = subIssueTypeId;
	}
	public String getSubIssueType() {
		return subIssueType;
	}
	public void setSubIssueType(String subIssueType) {
		this.subIssueType = subIssueType;
	}
	public Long getOneHourActive() {
		return oneHourActive;
	}
	public void setOneHourActive(Long oneHourActive) {
		this.oneHourActive = oneHourActive;
	}
	public Long getThreeHoursActive() {
		return threeHoursActive;
	}
	public void setThreeHoursActive(Long threeHoursActive) {
		this.threeHoursActive = threeHoursActive;
	}
	public Long getFiveHoursActive() {
		return fiveHoursActive;
	}
	public void setFiveHoursActive(Long fiveHoursActive) {
		this.fiveHoursActive = fiveHoursActive;
	}
	public Long getHouseHoldId() {
		return houseHoldId;
	}
	public void setHouseHoldId(Long houseHoldId) {
		this.houseHoldId = houseHoldId;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	public Long getTehsilId() {
		return tehsilId;
	}
	public void setTehsilId(Long tehsilId) {
		this.tehsilId = tehsilId;
	}
	public String getTehsilName() {
		return tehsilName;
	}
	public void setTehsilName(String tehsilName) {
		this.tehsilName = tehsilName;
	}
	public Long getLebId() {
		return lebId;
	}
	public void setLebId(Long lebId) {
		this.lebId = lebId;
	}
	public String getLebName() {
		return lebName;
	}
	public void setLebName(String lebName) {
		this.lebName = lebName;
	}
	public Long getPanchayatId() {
		return panchayatId;
	}
	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}
	public String getPancahyatName() {
		return pancahyatName;
	}
	public void setPancahyatName(String pancahyatName) {
		this.pancahyatName = pancahyatName;
	}
	public Long getBoothId() {
		return boothId;
	}
	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}
	public String getBoothNo() {
		return boothNo;
	}
	public void setBoothNo(String boothNo) {
		this.boothNo = boothNo;
	}
	public String getBoothLocation() {
		return boothLocation;
	}
	public void setBoothLocation(String boothLocation) {
		this.boothLocation = boothLocation;
	}
	public List<DoorCampaignDashboardVO> getSubList() {
		return subList;
	}
	public void setSubList(List<DoorCampaignDashboardVO> subList) {
		this.subList = subList;
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
	public Long getTotalUsers() {
		return totalUsers;
	}
	public void setTotalUsers(Long totalUsers) {
		this.totalUsers = totalUsers;
	}
	public Long getActiveUsers() {
		return activeUsers;
	}
	public void setActiveUsers(Long activeUsers) {
		this.activeUsers = activeUsers;
	}
	public Long getInActiveUsers() {
		return inActiveUsers;
	}
	public void setInActiveUsers(Long inActiveUsers) {
		this.inActiveUsers = inActiveUsers;
	}
	public Long getTotalHouseHolds() {
		return totalHouseHolds;
	}
	public void setTotalHouseHolds(Long totalHouseHolds) {
		this.totalHouseHolds = totalHouseHolds;
	}
	public Long getVisitedHouseHolds() {
		return visitedHouseHolds;
	}
	public void setVisitedHouseHolds(Long visitedHouseHolds) {
		this.visitedHouseHolds = visitedHouseHolds;
	}
	public Long getPendingHouseHolds() {
		return pendingHouseHolds;
	}
	public void setPendingHouseHolds(Long pendingHouseHolds) {
		this.pendingHouseHolds = pendingHouseHolds;
	}
	public Long getTotalGrievances() {
		return totalGrievances;
	}
	public void setTotalGrievances(Long totalGrievances) {
		this.totalGrievances = totalGrievances;
	}
	public Long getIndividual() {
		return individual;
	}
	public void setIndividual(Long individual) {
		this.individual = individual;
	}
	public Long getCommunity() {
		return community;
	}
	public void setCommunity(Long community) {
		this.community = community;
	}
	public Long getTotalImagesReceived() {
		return totalImagesReceived;
	}
	public void setTotalImagesReceived(Long totalImagesReceived) {
		this.totalImagesReceived = totalImagesReceived;
	}
	public String getVistedPerc() {
		return vistedPerc;
	}
	public void setVistedPerc(String vistedPerc) {
		this.vistedPerc = vistedPerc;
	}
	public String getPendingPerc() {
		return pendingPerc;
	}
	public void setPendingPerc(String pendingPerc) {
		this.pendingPerc = pendingPerc;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
