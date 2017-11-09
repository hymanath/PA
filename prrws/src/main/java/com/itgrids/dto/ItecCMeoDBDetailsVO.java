package com.itgrids.dto;

import java.util.ArrayList;
import java.util.List;

public class ItecCMeoDBDetailsVO {

	private String dashboardName;
	private String clearenceName;
	private String totalApplications;
	private String totalApproved;
	private String totalRejected;
	private String totalPending;
	private String pendingWithInSLA;
	private String pendingBeyondSLA;
	private String totalReApproved;
	private String dashBoardNO;
	private String clearenceId;
	private String lowApproveClearenceName;
	private String lowRejectedClearenceName;
	private String lowPendingClearenceName;
	private String lowApprDashBoardName;
	private String lowRejctedDashBoardName;
	private String lowPendingDashBoardName;
	private String highRejectedClearenceName;
	private String highRejectedDashboardName;
	private String highPendingClearenceName;
	private String highPendingDashboardName;
	private String lowApproval;
	private String highRejected;
	private String lowRejected;
	private String highPending;
	private String lowPending;
	private String category;
	private String finaYear;
	private String sectorName;
	private String industryName;
	private String address;
	private String activity;
	private String totalCost;
	private String empolyeement;
	private String appFilledDate;
	private String recievedDate;
	private String slaDays;
	private String permApprovalDate;
	private String appRejDate;
	private String approvalFileId;
	private String applicationId;
	private String regId;
	private List<ItecCMeoDBDetailsVO>  subList = new ArrayList<ItecCMeoDBDetailsVO>(0);
	private Long deprtTotalApplications = 0L;
	private Long deprtTotalApproval = 0L;
	private String districtName;
	private String approvalDate;
	private String investmentAmount;
	private String status;
	
	
	public String getDashboardName() {
		return dashboardName;
	}
	public void setDashboardName(String dashboardName) {
		this.dashboardName = dashboardName;
	}
	public String getClearenceName() {
		return clearenceName;
	}
	public void setClearenceName(String clearenceName) {
		this.clearenceName = clearenceName;
	}
	public String getTotalApplications() {
		return totalApplications;
	}
	public void setTotalApplications(String totalApplications) {
		this.totalApplications = totalApplications;
	}
	public String getTotalApproved() {
		return totalApproved;
	}
	public void setTotalApproved(String totalApproved) {
		this.totalApproved = totalApproved;
	}
	public String getTotalRejected() {
		return totalRejected;
	}
	public void setTotalRejected(String totalRejected) {
		this.totalRejected = totalRejected;
	}
	public String getTotalPending() {
		return totalPending;
	}
	public void setTotalPending(String totalPending) {
		this.totalPending = totalPending;
	}
	public String getPendingWithInSLA() {
		return pendingWithInSLA;
	}
	public void setPendingWithInSLA(String pendingWithInSLA) {
		this.pendingWithInSLA = pendingWithInSLA;
	}
	public String getPendingBeyondSLA() {
		return pendingBeyondSLA;
	}
	public void setPendingBeyondSLA(String pendingBeyondSLA) {
		this.pendingBeyondSLA = pendingBeyondSLA;
	}
	public String getTotalReApproved() {
		return totalReApproved;
	}
	public void setTotalReApproved(String totalReApproved) {
		this.totalReApproved = totalReApproved;
	}
	public String getDashBoardNO() {
		return dashBoardNO;
	}
	public void setDashBoardNO(String dashBoardNO) {
		this.dashBoardNO = dashBoardNO;
	}
	public String getClearenceId() {
		return clearenceId;
	}
	public void setClearenceId(String clearenceId) {
		this.clearenceId = clearenceId;
	}
	public String getLowApproveClearenceName() {
		return lowApproveClearenceName;
	}
	public void setLowApproveClearenceName(String lowApproveClearenceName) {
		this.lowApproveClearenceName = lowApproveClearenceName;
	}
	public String getLowRejectedClearenceName() {
		return lowRejectedClearenceName;
	}
	public void setLowRejectedClearenceName(String lowRejectedClearenceName) {
		this.lowRejectedClearenceName = lowRejectedClearenceName;
	}
	public String getLowPendingClearenceName() {
		return lowPendingClearenceName;
	}
	public void setLowPendingClearenceName(String lowPendingClearenceName) {
		this.lowPendingClearenceName = lowPendingClearenceName;
	}
	public String getLowApprDashBoardName() {
		return lowApprDashBoardName;
	}
	public void setLowApprDashBoardName(String lowApprDashBoardName) {
		this.lowApprDashBoardName = lowApprDashBoardName;
	}
	public String getLowRejctedDashBoardName() {
		return lowRejctedDashBoardName;
	}
	public void setLowRejctedDashBoardName(String lowRejctedDashBoardName) {
		this.lowRejctedDashBoardName = lowRejctedDashBoardName;
	}
	public String getLowPendingDashBoardName() {
		return lowPendingDashBoardName;
	}
	public void setLowPendingDashBoardName(String lowPendingDashBoardName) {
		this.lowPendingDashBoardName = lowPendingDashBoardName;
	}
	public String getHighRejectedClearenceName() {
		return highRejectedClearenceName;
	}
	public void setHighRejectedClearenceName(String highRejectedClearenceName) {
		this.highRejectedClearenceName = highRejectedClearenceName;
	}
	public String getHighRejectedDashboardName() {
		return highRejectedDashboardName;
	}
	public void setHighRejectedDashboardName(String highRejectedDashboardName) {
		this.highRejectedDashboardName = highRejectedDashboardName;
	}
	public String getHighPendingClearenceName() {
		return highPendingClearenceName;
	}
	public void setHighPendingClearenceName(String highPendingClearenceName) {
		this.highPendingClearenceName = highPendingClearenceName;
	}
	public String getHighPendingDashboardName() {
		return highPendingDashboardName;
	}
	public void setHighPendingDashboardName(String highPendingDashboardName) {
		this.highPendingDashboardName = highPendingDashboardName;
	}
	public String getLowApproval() {
		return lowApproval;
	}
	public void setLowApproval(String lowApproval) {
		this.lowApproval = lowApproval;
	}
	public String getHighRejected() {
		return highRejected;
	}
	public void setHighRejected(String highRejected) {
		this.highRejected = highRejected;
	}
	public String getLowRejected() {
		return lowRejected;
	}
	public void setLowRejected(String lowRejected) {
		this.lowRejected = lowRejected;
	}
	public String getHighPending() {
		return highPending;
	}
	public void setHighPending(String highPending) {
		this.highPending = highPending;
	}
	public String getLowPending() {
		return lowPending;
	}
	public void setLowPending(String lowPending) {
		this.lowPending = lowPending;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getFinaYear() {
		return finaYear;
	}
	public void setFinaYear(String finaYear) {
		this.finaYear = finaYear;
	}
	public String getSectorName() {
		return sectorName;
	}
	public void setSectorName(String sectorName) {
		this.sectorName = sectorName;
	}
	public String getIndustryName() {
		return industryName;
	}
	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public String getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(String totalCost) {
		this.totalCost = totalCost;
	}
	public String getEmpolyeement() {
		return empolyeement;
	}
	public void setEmpolyeement(String empolyeement) {
		this.empolyeement = empolyeement;
	}
	public String getAppFilledDate() {
		return appFilledDate;
	}
	public void setAppFilledDate(String appFilledDate) {
		this.appFilledDate = appFilledDate;
	}
	
	public String getRecievedDate() {
		return recievedDate;
	}
	public void setRecievedDate(String recievedDate) {
		this.recievedDate = recievedDate;
	}
	public String getSlaDays() {
		return slaDays;
	}
	public void setSlaDays(String slaDays) {
		this.slaDays = slaDays;
	}
	public String getPermApprovalDate() {
		return permApprovalDate;
	}
	public void setPermApprovalDate(String permApprovalDate) {
		this.permApprovalDate = permApprovalDate;
	}
	public String getAppRejDate() {
		return appRejDate;
	}
	public void setAppRejDate(String appRejDate) {
		this.appRejDate = appRejDate;
	}
	public String getApprovalFileId() {
		return approvalFileId;
	}
	public void setApprovalFileId(String approvalFileId) {
		this.approvalFileId = approvalFileId;
	}
	public String getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public List<ItecCMeoDBDetailsVO> getSubList() {
		return subList;
	}
	public void setSubList(List<ItecCMeoDBDetailsVO> subList) {
		this.subList = subList;
	}
	public Long getDeprtTotalApplications() {
		return deprtTotalApplications;
	}
	public void setDeprtTotalApplications(Long deprtTotalApplications) {
		this.deprtTotalApplications = deprtTotalApplications;
	}
	public Long getDeprtTotalApproval() {
		return deprtTotalApproval;
	}
	public void setDeprtTotalApproval(Long deprtTotalApproval) {
		this.deprtTotalApproval = deprtTotalApproval;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getApprovalDate() {
		return approvalDate;
	}
	public void setApprovalDate(String approvalDate) {
		this.approvalDate = approvalDate;
	}
	public String getInvestmentAmount() {
		return investmentAmount;
	}
	public void setInvestmentAmount(String investmentAmount) {
		this.investmentAmount = investmentAmount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
