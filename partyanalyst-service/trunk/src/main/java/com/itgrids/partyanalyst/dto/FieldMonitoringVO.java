/**
 * @author Sravanth
 * Oct 17, 2016
 * FieldMonitoringVO.java
 * PA - SERV - com.itgrids.partyanalyst.dto
 */
package com.itgrids.partyanalyst.dto;

import java.util.List;

/**
 * @author Sravanth
 * @date Oct 17, 2016
 *
 */
public class FieldMonitoringVO {

	private Long id;
	private String name;
	
	private Long cadreSurveyUserId;
	private String userName;
	private Long tabUserId;
	private String tabUserName;
	private String mobileNo;
	private String todayTarget;
	private String firstRecord;
	private String recentRecord;
	private Long lastHourCount;
	private Long totalCount = 0l;
	private Long openIssues = 0l;
	private Long fixedIssues = 0l;
	private Long closedIssues = 0l;
	private Long TotalDataCollectors = 0l;
	private Long activeUsers;
	private Long passiveUsers;
	private List<FieldMonitoringVO> subList;
	private Long stateId;
	private Long districtId;
	private Long constituencyId;
	private String stateName;
	private String districtName;
	private String constituencyName;
	private Long vendorId;
	private String vendorName;
	private List<IdAndNameVO> idnameList;
	private List<IdAndNameVO> idnameList1;
	private String imagePath;
	private Long notYetStartedUsers = 0l;
	private Long startedUsers = 0l;
	private Long todayRegCount = 0l;
	private Long todayActiveUsers;
	private Long lastOneHrActUsers = 0l;
	private Long lastHourInActive = 0l;
	private String countPerc;
	private String fieldMonitrngName;
	private String issueStatus;
	private String issueType;
	private String description;
	private Long veryGoodCnt;
	private Long goodCnt;
	private Long poorCnt;
	private Long veryPoorCnt;
	private Long districtCount;
	private Long constituencyCount;
	private Long notYetStartedIssues = 0l;
	private Long startedIssues = 0l;
	private String issueTime;
	private String leaderMandal;
	private String leaderName;
	private String leadreMobile;
	private String performanceType;
	private String slowPerformer;
	private String betterPerformer;
	private Long pendingCount = 0l;
	private Long passedcount = 0l;
	private Long rejectedCount = 0l;
	
	
	
	public String getSlowPerformer() {
		return slowPerformer;
	}
	public void setSlowPerformer(String slowPerformer) {
		this.slowPerformer = slowPerformer;
	}
	public String getBetterPerformer() {
		return betterPerformer;
	}
	public void setBetterPerformer(String betterPerformer) {
		this.betterPerformer = betterPerformer;
	}
	public String getPerformanceType() {
		return performanceType;
	}
	public void setPerformanceType(String performanceType) {
		this.performanceType = performanceType;
	}
	public Long getLastHourInActive() {
		return lastHourInActive;
	}
	public void setLastHourInActive(Long lastHourInActive) {
		this.lastHourInActive = lastHourInActive;
	}
	public Long getStartedUsers() {
		return startedUsers;
	}
	public void setStartedUsers(Long startedUsers) {
		this.startedUsers = startedUsers;
	}
	public String getLeaderMandal() {
		return leaderMandal;
	}
	public void setLeaderMandal(String leaderMandal) {
		this.leaderMandal = leaderMandal;
	}
	public String getLeaderName() {
		return leaderName;
	}
	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}
	public String getLeadreMobile() {
		return leadreMobile;
	}
	public void setLeadreMobile(String leadreMobile) {
		this.leadreMobile = leadreMobile;
	}
	public String getIssueTime() {
		return issueTime;
	}
	public void setIssueTime(String issueTime) {
		this.issueTime = issueTime;
	}
	public Long getStartedIssues() {
		return startedIssues;
	}
	public void setStartedIssues(Long startedIssues) {
		this.startedIssues = startedIssues;
	}
	public Long getNotYetStartedIssues() {
		return notYetStartedIssues;
	}
	public void setNotYetStartedIssues(Long notYetStartedIssues) {
		this.notYetStartedIssues = notYetStartedIssues;
	}
	public Long getDistrictCount() {
		return districtCount;
	}
	public void setDistrictCount(Long districtCount) {
		this.districtCount = districtCount;
	}
	public Long getConstituencyCount() {
		return constituencyCount;
	}
	public void setConstituencyCount(Long constituencyCount) {
		this.constituencyCount = constituencyCount;
	}
	public String getIssueStatus() {
		return issueStatus;
	}
	public void setIssueStatus(String issueStatus) {
		this.issueStatus = issueStatus;
	}
	public String getIssueType() {
		return issueType;
	}
	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getVeryGoodCnt() {
		return veryGoodCnt;
	}
	public void setVeryGoodCnt(Long veryGoodCnt) {
		this.veryGoodCnt = veryGoodCnt;
	}
	public Long getGoodCnt() {
		return goodCnt;
	}
	public void setGoodCnt(Long goodCnt) {
		this.goodCnt = goodCnt;
	}
	public Long getPoorCnt() {
		return poorCnt;
	}
	public void setPoorCnt(Long poorCnt) {
		this.poorCnt = poorCnt;
	}
	public Long getVeryPoorCnt() {
		return veryPoorCnt;
	}
	public void setVeryPoorCnt(Long veryPoorCnt) {
		this.veryPoorCnt = veryPoorCnt;
	}
	public String getFieldMonitrngName() {
		return fieldMonitrngName;
	}
	public void setFieldMonitrngName(String fieldMonitrngName) {
		this.fieldMonitrngName = fieldMonitrngName;
	}
	public String getCountPerc() {
		return countPerc;
	}
	public void setCountPerc(String countPerc) {
		this.countPerc = countPerc;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
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
	public Long getCadreSurveyUserId() {
		return cadreSurveyUserId;
	}
	public void setCadreSurveyUserId(Long cadreSurveyUserId) {
		this.cadreSurveyUserId = cadreSurveyUserId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getTabUserId() {
		return tabUserId;
	}
	public void setTabUserId(Long tabUserId) {
		this.tabUserId = tabUserId;
	}
	public String getTabUserName() {
		return tabUserName;
	}
	public void setTabUserName(String tabUserName) {
		this.tabUserName = tabUserName;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getTodayTarget() {
		return todayTarget;
	}
	public void setTodayTarget(String todayTarget) {
		this.todayTarget = todayTarget;
	}
	public String getFirstRecord() {
		return firstRecord;
	}
	public void setFirstRecord(String firstRecord) {
		this.firstRecord = firstRecord;
	}
	public String getRecentRecord() {
		return recentRecord;
	}
	public void setRecentRecord(String recentRecord) {
		this.recentRecord = recentRecord;
	}
	public Long getLastHourCount() {
		return lastHourCount;
	}
	public void setLastHourCount(Long lastHourCount) {
		this.lastHourCount = lastHourCount;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getOpenIssues() {
		return openIssues;
	}
	public void setOpenIssues(Long openIssues) {
		this.openIssues = openIssues;
	}
	public Long getFixedIssues() {
		return fixedIssues;
	}
	public void setFixedIssues(Long fixedIssues) {
		this.fixedIssues = fixedIssues;
	}
	public Long getTotalDataCollectors() {
		return TotalDataCollectors;
	}
	public void setTotalDataCollectors(Long totalDataCollectors) {
		TotalDataCollectors = totalDataCollectors;
	}
	public Long getActiveUsers() {
		return activeUsers;
	}
	public void setActiveUsers(Long activeUsers) {
		this.activeUsers = activeUsers;
	}
	public Long getPassiveUsers() {
		return passiveUsers;
	}
	public void setPassiveUsers(Long passiveUsers) {
		this.passiveUsers = passiveUsers;
	}
	public List<FieldMonitoringVO> getSubList() {
		return subList;
	}
	public void setSubList(List<FieldMonitoringVO> subList) {
		this.subList = subList;
	}
	public Long getClosedIssues() {
		return closedIssues;
	}
	public void setClosedIssues(Long closedIssues) {
		this.closedIssues = closedIssues;
	}
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
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
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	public Long getVendorId() {
		return vendorId;
	}
	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public List<IdAndNameVO> getIdnameList() {
		return idnameList;
	}
	public void setIdnameList(List<IdAndNameVO> idnameList) {
		this.idnameList = idnameList;
	}
	public List<IdAndNameVO> getIdnameList1() {
		return idnameList1;
	}
	public void setIdnameList1(List<IdAndNameVO> idnameList1) {
		this.idnameList1 = idnameList1;
	}
	public Long getNotYetStartedUsers() {
		return notYetStartedUsers;
	}
	public void setNotYetStartedUsers(Long notYetStartedUsers) {
		this.notYetStartedUsers = notYetStartedUsers;
	}
	public Long getTodayRegCount() {
		return todayRegCount;
	}
	public void setTodayRegCount(Long todayRegCount) {
		this.todayRegCount = todayRegCount;
	}
	public Long getTodayActiveUsers() {
		return todayActiveUsers;
	}
	public void setTodayActiveUsers(Long todayActiveUsers) {
		this.todayActiveUsers = todayActiveUsers;
	}
	public Long getLastOneHrActUsers() {
		return lastOneHrActUsers;
	}
	public void setLastOneHrActUsers(Long lastOneHrActUsers) {
		this.lastOneHrActUsers = lastOneHrActUsers;
	}
	public Long getPendingCount() {
		return pendingCount;
	}
	public void setPendingCount(Long pendingCount) {
		this.pendingCount = pendingCount;
	}
	public Long getPassedcount() {
		return passedcount;
	}
	public void setPassedcount(Long passedcount) {
		this.passedcount = passedcount;
	}
	public Long getRejectedCount() {
		return rejectedCount;
	}
	public void setRejectedCount(Long rejectedCount) {
		this.rejectedCount = rejectedCount;
	}
	
}
