package com.itgrids.partyanalyst.dto;

import java.util.List;

public class DataMonitoringOverviewVO {

	private Long surveryUserId;
	private Long tabUserId;
	private String surveryUserName;
	private String tabUserName;
	private String mobileNo;
	private String isActive;
	
	private Long totalRegCnt=0l;
	private Long totalTabRegCnt=0l;
	private Long totalWebRegCnt=0l;
	private Long totalOnlineRegCnt=0l;
	
	private Long totalVerifyRegCnt=0l;
	private Long totalTabVerifyCnt=0l;
	private Long totalWebVerifyCnt=0l;
	private Long totalOnlineVerifyCnt=0l;
	
	private Long totalVerifyRejectCnt=0l;
	private Long totalWebVerifyRejectedCnt=0l;
	private Long totalTabVerifyRejectedCnt=0l;
	private Long totalOnlineVerifyRejectedCnt=0l;
	
	private Long totalPendingCnt=0l;
	private Long totalWebPendingCnt=0l;
	private Long totalTabPendingCnt=0l;
	private Long totalOnlinePendingCnt=0l;
	
	private Double totalVerifyTabPer=0.0d;
	private Double totalVerifyWebPer=0.0d;
	private Double totalVerifyOnlinePer=0.0d;
	
	private Double totalRejectedTabPer=0.0d;
	private Double totalRejectedWebPer=0.0d;
	private Double totalRejectedOnlinePer=0.0d;
	
	private Double totalPendingTabPer=0.0d;
	private Double totalPendingWebPer=0.0d;
	private Double totalPendingOnlinePer=0.0d;
	
	private Long activyTeamMemberCnt=0l;
	
	
	private List<DataMonitoringOverviewVO> subList;
	
	public Long getTotalRegCnt() {
		return totalRegCnt;
	}
	public void setTotalRegCnt(Long totalRegCnt) {
		this.totalRegCnt = totalRegCnt;
	}
	public Long getTotalTabRegCnt() {
		return totalTabRegCnt;
	}
	public void setTotalTabRegCnt(Long totalTabRegCnt) {
		this.totalTabRegCnt = totalTabRegCnt;
	}
	public Long getTotalWebRegCnt() {
		return totalWebRegCnt;
	}
	public void setTotalWebRegCnt(Long totalWebRegCnt) {
		this.totalWebRegCnt = totalWebRegCnt;
	}
	public Long getTotalOnlineRegCnt() {
		return totalOnlineRegCnt;
	}
	public void setTotalOnlineRegCnt(Long totalOnlineRegCnt) {
		this.totalOnlineRegCnt = totalOnlineRegCnt;
	}
	public Long getTotalVerifyRegCnt() {
		return totalVerifyRegCnt;
	}
	public void setTotalVerifyRegCnt(Long totalVerifyRegCnt) {
		this.totalVerifyRegCnt = totalVerifyRegCnt;
	}
	public Long getTotalTabVerifyCnt() {
		return totalTabVerifyCnt;
	}
	public void setTotalTabVerifyCnt(Long totalTabVerifyCnt) {
		this.totalTabVerifyCnt = totalTabVerifyCnt;
	}
	public Long getTotalWebVerifyCnt() {
		return totalWebVerifyCnt;
	}
	public void setTotalWebVerifyCnt(Long totalWebVerifyCnt) {
		this.totalWebVerifyCnt = totalWebVerifyCnt;
	}
    public Long getTotalOnlineVerifyCnt() {
		return totalOnlineVerifyCnt;
	}
	public void setTotalOnlineVerifyCnt(Long totalOnlineVerifyCnt) {
		this.totalOnlineVerifyCnt = totalOnlineVerifyCnt;
	}
    public Long getTotalVerifyRejectCnt() {
		return totalVerifyRejectCnt;
	}
	public void setTotalVerifyRejectCnt(Long totalVerifyRejectCnt) {
		this.totalVerifyRejectCnt = totalVerifyRejectCnt;
	}
	public Long getTotalWebVerifyRejectedCnt() {
		return totalWebVerifyRejectedCnt;
	}
	public void setTotalWebVerifyRejectedCnt(Long totalWebVerifyRejectedCnt) {
		this.totalWebVerifyRejectedCnt = totalWebVerifyRejectedCnt;
	}
	public Long getTotalTabVerifyRejectedCnt() {
		return totalTabVerifyRejectedCnt;
	}
	public void setTotalTabVerifyRejectedCnt(Long totalTabVerifyRejectedCnt) {
		this.totalTabVerifyRejectedCnt = totalTabVerifyRejectedCnt;
	}
	public Long getTotalOnlineVerifyRejectedCnt() {
		return totalOnlineVerifyRejectedCnt;
	}
	public void setTotalOnlineVerifyRejectedCnt(Long totalOnlineVerifyRejectedCnt) {
		this.totalOnlineVerifyRejectedCnt = totalOnlineVerifyRejectedCnt;
	}
	public Long getTotalPendingCnt() {
		return totalPendingCnt;
	}
	public void setTotalPendingCnt(Long totalPendingCnt) {
		this.totalPendingCnt = totalPendingCnt;
	}
   public Long getTotalWebPendingCnt() {
		return totalWebPendingCnt;
	}
	public void setTotalWebPendingCnt(Long totalWebPendingCnt) {
		this.totalWebPendingCnt = totalWebPendingCnt;
	}
	public Long getTotalTabPendingCnt() {
		return totalTabPendingCnt;
	}
	public void setTotalTabPendingCnt(Long totalTabPendingCnt) {
		this.totalTabPendingCnt = totalTabPendingCnt;
	}
	public Long getTotalOnlinePendingCnt() {
		return totalOnlinePendingCnt;
	}
	public void setTotalOnlinePendingCnt(Long totalOnlinePendingCnt) {
		this.totalOnlinePendingCnt = totalOnlinePendingCnt;
	}
	public Double getTotalVerifyTabPer() {
		return totalVerifyTabPer;
	}
	public void setTotalVerifyTabPer(Double totalVerifyTabPer) {
		this.totalVerifyTabPer = totalVerifyTabPer;
	}
	public Double getTotalVerifyWebPer() {
		return totalVerifyWebPer;
	}
	public void setTotalVerifyWebPer(Double totalVerifyWebPer) {
		this.totalVerifyWebPer = totalVerifyWebPer;
	}
	public Double getTotalVerifyOnlinePer() {
		return totalVerifyOnlinePer;
	}
	public void setTotalVerifyOnlinePer(Double totalVerifyOnlinePer) {
		this.totalVerifyOnlinePer = totalVerifyOnlinePer;
	}
	public Double getTotalRejectedTabPer() {
		return totalRejectedTabPer;
	}
	public void setTotalRejectedTabPer(Double totalRejectedTabPer) {
		this.totalRejectedTabPer = totalRejectedTabPer;
	}
	public Double getTotalRejectedWebPer() {
		return totalRejectedWebPer;
	}
	public void setTotalRejectedWebPer(Double totalRejectedWebPer) {
		this.totalRejectedWebPer = totalRejectedWebPer;
	}
	public Double getTotalRejectedOnlinePer() {
		return totalRejectedOnlinePer;
	}
	public void setTotalRejectedOnlinePer(Double totalRejectedOnlinePer) {
		this.totalRejectedOnlinePer = totalRejectedOnlinePer;
	}
	public Double getTotalPendingTabPer() {
		return totalPendingTabPer;
	}
	public void setTotalPendingTabPer(Double totalPendingTabPer) {
		this.totalPendingTabPer = totalPendingTabPer;
	}
	public Double getTotalPendingWebPer() {
		return totalPendingWebPer;
	}
	public void setTotalPendingWebPer(Double totalPendingWebPer) {
		this.totalPendingWebPer = totalPendingWebPer;
	}
	public Double getTotalPendingOnlinePer() {
		return totalPendingOnlinePer;
	}
	public void setTotalPendingOnlinePer(Double totalPendingOnlinePer) {
		this.totalPendingOnlinePer = totalPendingOnlinePer;
	}
	public Long getActivyTeamMemberCnt() {
		return activyTeamMemberCnt;
	}
	public void setActivyTeamMemberCnt(Long activyTeamMemberCnt) {
		this.activyTeamMemberCnt = activyTeamMemberCnt;
	}
	public Long getSurveryUserId() {
		return surveryUserId;
	}
	public void setSurveryUserId(Long surveryUserId) {
		this.surveryUserId = surveryUserId;
	}
	public Long getTabUserId() {
		return tabUserId;
	}
	public void setTabUserId(Long tabUserId) {
		this.tabUserId = tabUserId;
	}
	public String getSurveryUserName() {
		return surveryUserName;
	}
	public void setSurveryUserName(String surveryUserName) {
		this.surveryUserName = surveryUserName;
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
	public List<DataMonitoringOverviewVO> getSubList() {
		return subList;
	}
	public void setSubList(List<DataMonitoringOverviewVO> subList) {
		this.subList = subList;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	
	
}
