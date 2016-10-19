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
	private Long totalCount;
	private Long openIssues;
	private Long fixedIssues;
	private Long closedIssues;
	private Long TotalDataCollectors;
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
}
