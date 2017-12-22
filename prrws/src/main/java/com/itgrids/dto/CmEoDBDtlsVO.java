package com.itgrids.dto;

import java.util.List;

public class CmEoDBDtlsVO {

	private String idStr;
	private String name;
	private Long count;
	private Long total = 0l;
	private Long aprooved = 0l;
	private Long rejected = 0l;
	private Long reAprooved = 0l;
	private Long totalPending = 0l;
	private Long pendingWithinSLA = 0l;
	private Long pendingBeyondSLA = 0l;
	private Long highApprovalDepartmentCount;
	private String highApprovalDepartmentName;
	private Long lowApprovalDepartmentCount;
	private String lowApprovalDepartmentName;
	private Long highRejectedDepartmentCount;
	private String highRejectedDepartmentName;
	private Long lowRejectedDepartmentCount;
	private String lowRejectedDepartmentName;
	private Long highPendingDepartmentCount;
	private String highPendingDepartmentName;
	private Long lowPendingDepartmentCount;
	private String lowPendingDepartmentName;
	
	private CmEoDBDtlsVO overviewDtls;
	private CmEoDBDtlsVO itDtlsVO;
	private CmEoDBDtlsVO electronicsDtlsVO;
	
	private String approvedPerc;
	private String rejectedPerc;
	private String reApprovedPerc;
	private String pendingPerc;
	private String withinSLAPerc;
	private String beyongSLAPerc;
	
	
	public String getApprovedPerc() {
		return approvedPerc;
	}
	public void setApprovedPerc(String approvedPerc) {
		this.approvedPerc = approvedPerc;
	}
	public String getRejectedPerc() {
		return rejectedPerc;
	}
	public void setRejectedPerc(String rejectedPerc) {
		this.rejectedPerc = rejectedPerc;
	}
	public String getReApprovedPerc() {
		return reApprovedPerc;
	}
	public void setReApprovedPerc(String reApprovedPerc) {
		this.reApprovedPerc = reApprovedPerc;
	}
	public String getPendingPerc() {
		return pendingPerc;
	}
	public void setPendingPerc(String pendingPerc) {
		this.pendingPerc = pendingPerc;
	}
	public String getWithinSLAPerc() {
		return withinSLAPerc;
	}
	public void setWithinSLAPerc(String withinSLAPerc) {
		this.withinSLAPerc = withinSLAPerc;
	}
	public String getBeyongSLAPerc() {
		return beyongSLAPerc;
	}
	public void setBeyongSLAPerc(String beyongSLAPerc) {
		this.beyongSLAPerc = beyongSLAPerc;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public Long getAprooved() {
		return aprooved;
	}
	public void setAprooved(Long aprooved) {
		this.aprooved = aprooved;
	}
	public Long getRejected() {
		return rejected;
	}
	public void setRejected(Long rejected) {
		this.rejected = rejected;
	}
	public Long getReAprooved() {
		return reAprooved;
	}
	public void setReAprooved(Long reAprooved) {
		this.reAprooved = reAprooved;
	}
	public Long getPendingWithinSLA() {
		return pendingWithinSLA;
	}
	public void setPendingWithinSLA(Long pendingWithinSLA) {
		this.pendingWithinSLA = pendingWithinSLA;
	}
	public Long getPendingBeyondSLA() {
		return pendingBeyondSLA;
	}
	public void setPendingBeyondSLA(Long pendingBeyondSLA) {
		this.pendingBeyondSLA = pendingBeyondSLA;
	}
	public String getHighApprovalDepartmentName() {
		return highApprovalDepartmentName;
	}
	public void setHighApprovalDepartmentName(String highApprovalDepartmentName) {
		this.highApprovalDepartmentName = highApprovalDepartmentName;
	}
	public String getLowApprovalDepartmentName() {
		return lowApprovalDepartmentName;
	}
	public void setLowApprovalDepartmentName(String lowApprovalDepartmentName) {
		this.lowApprovalDepartmentName = lowApprovalDepartmentName;
	}
	public String getHighRejectedDepartmentName() {
		return highRejectedDepartmentName;
	}
	public void setHighRejectedDepartmentName(String highRejectedDepartmentName) {
		this.highRejectedDepartmentName = highRejectedDepartmentName;
	}
	public String getLowRejectedDepartmentName() {
		return lowRejectedDepartmentName;
	}
	public void setLowRejectedDepartmentName(String lowRejectedDepartmentName) {
		this.lowRejectedDepartmentName = lowRejectedDepartmentName;
	}
	public String getHighPendingDepartmentName() {
		return highPendingDepartmentName;
	}
	public void setHighPendingDepartmentName(String highPendingDepartmentName) {
		this.highPendingDepartmentName = highPendingDepartmentName;
	}
	public String getLowPendingDepartmentName() {
		return lowPendingDepartmentName;
	}
	public void setLowPendingDepartmentName(String lowPendingDepartmentName) {
		this.lowPendingDepartmentName = lowPendingDepartmentName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public CmEoDBDtlsVO getOverviewDtls() {
		return overviewDtls;
	}
	public void setOverviewDtls(CmEoDBDtlsVO overviewDtls) {
		this.overviewDtls = overviewDtls;
	}
	public Long getHighApprovalDepartmentCount() {
		return highApprovalDepartmentCount;
	}
	public void setHighApprovalDepartmentCount(Long highApprovalDepartmentCount) {
		this.highApprovalDepartmentCount = highApprovalDepartmentCount;
	}
	public Long getLowApprovalDepartmentCount() {
		return lowApprovalDepartmentCount;
	}
	public void setLowApprovalDepartmentCount(Long lowApprovalDepartmentCount) {
		this.lowApprovalDepartmentCount = lowApprovalDepartmentCount;
	}
	public Long getHighRejectedDepartmentCount() {
		return highRejectedDepartmentCount;
	}
	public void setHighRejectedDepartmentCount(Long highRejectedDepartmentCount) {
		this.highRejectedDepartmentCount = highRejectedDepartmentCount;
	}
	public Long getLowRejectedDepartmentCount() {
		return lowRejectedDepartmentCount;
	}
	public void setLowRejectedDepartmentCount(Long lowRejectedDepartmentCount) {
		this.lowRejectedDepartmentCount = lowRejectedDepartmentCount;
	}
	public Long getHighPendingDepartmentCount() {
		return highPendingDepartmentCount;
	}
	public void setHighPendingDepartmentCount(Long highPendingDepartmentCount) {
		this.highPendingDepartmentCount = highPendingDepartmentCount;
	}
	public Long getLowPendingDepartmentCount() {
		return lowPendingDepartmentCount;
	}
	public void setLowPendingDepartmentCount(Long lowPendingDepartmentCount) {
		this.lowPendingDepartmentCount = lowPendingDepartmentCount;
	}
	public String getIdStr() {
		return idStr;
	}
	public void setIdStr(String idStr) {
		this.idStr = idStr;
	}
	public Long getTotalPending() {
		return totalPending;
	}
	public void setTotalPending(Long totalPending) {
		this.totalPending = totalPending;
	}
	public CmEoDBDtlsVO getItDtlsVO() {
		return itDtlsVO;
	}
	public void setItDtlsVO(CmEoDBDtlsVO itDtlsVO) {
		this.itDtlsVO = itDtlsVO;
	}
	public CmEoDBDtlsVO getElectronicsDtlsVO() {
		return electronicsDtlsVO;
	}
	public void setElectronicsDtlsVO(CmEoDBDtlsVO electronicsDtlsVO) {
		this.electronicsDtlsVO = electronicsDtlsVO;
	}
	

	
    
   

}
