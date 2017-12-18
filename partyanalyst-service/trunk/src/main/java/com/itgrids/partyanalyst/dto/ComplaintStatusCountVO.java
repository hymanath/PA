package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class ComplaintStatusCountVO {
	private Long count = 0l;
	private String status;
	private String issueType;
	private String typeOfIssue;
	private List<ComplaintStatusCountVO> subList = new ArrayList<ComplaintStatusCountVO>();
	private Long statusOrder;
	private String color ;
	private Long total = 0l;
	private Long approvedCount = 0l;
	private Long pendingCount = 0l;
	private String donationAmount;
	private Long apprPartyFund = 0l;
	private Long apprCMRefiedFund = 0l;
	private Long apprPartyMembsCount= 0l;
	private Long apprCMReliefMembsCount= 0l;
	private Long donationPartyFund= 0l;
	private Long donationCMRefiedFund= 0l;
	private Long donationPartyMembsCount= 0l;
	private Long donationCMReliefMembsCount= 0l;
	private List<ComplaintStatusCountVO> categoryList = new ArrayList<ComplaintStatusCountVO>();
	private List<ComplaintStatusCountVO> statusList = new ArrayList<ComplaintStatusCountVO>();
	private String statusVal;
	private Long maxIndex=0l;
	private Long startIndex=0l;
	private Long id;
	
	
	
	public String getStatusVal() {
		return statusVal;
	}
	public void setStatusVal(String statusVal) {
		this.statusVal = statusVal;
	}
	public Long getMaxIndex() {
		return maxIndex;
	}
	public void setMaxIndex(Long maxIndex) {
		this.maxIndex = maxIndex;
	}
	public Long getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(Long startIndex) {
		this.startIndex = startIndex;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<ComplaintStatusCountVO> getStatusList() {
		return statusList;
	}
	public void setStatusList(List<ComplaintStatusCountVO> statusList) {
		this.statusList = statusList;
	}
	
	public Long getStatusOrder() {
		return statusOrder;
	}
	public void setStatusOrder(Long statusOrder) {
		this.statusOrder = statusOrder;
	}
	public Long getDonationPartyFund() {
		return donationPartyFund;
	}
	public void setDonationPartyFund(Long donationPartyFund) {
		this.donationPartyFund = donationPartyFund;
	}
	public Long getDonationCMRefiedFund() {
		return donationCMRefiedFund;
	}
	public void setDonationCMRefiedFund(Long donationCMRefiedFund) {
		this.donationCMRefiedFund = donationCMRefiedFund;
	}
	public Long getDonationPartyMembsCount() {
		return donationPartyMembsCount;
	}
	public void setDonationPartyMembsCount(Long donationPartyMembsCount) {
		this.donationPartyMembsCount = donationPartyMembsCount;
	}
	public Long getDonationCMReliefMembsCount() {
		return donationCMReliefMembsCount;
	}
	public void setDonationCMReliefMembsCount(Long donationCMReliefMembsCount) {
		this.donationCMReliefMembsCount = donationCMReliefMembsCount;
	}
	public String getDonationAmount() {
		return donationAmount;
	}
	public void setDonationAmount(String donationAmount) {
		this.donationAmount = donationAmount;
	}
	public List<ComplaintStatusCountVO> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(List<ComplaintStatusCountVO> categoryList) {
		this.categoryList = categoryList;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIssueType() {
		return issueType;
	}
	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}
	public String getTypeOfIssue() {
		return typeOfIssue;
	}
	public void setTypeOfIssue(String typeOfIssue) {
		this.typeOfIssue = typeOfIssue;
	}
	public List<ComplaintStatusCountVO> getSubList() {
		return subList;
	}
	public void setSubList(List<ComplaintStatusCountVO> subList) {
		this.subList = subList;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public Long getApprovedCount() {
		return approvedCount;
	}
	public void setApprovedCount(Long approvedCount) {
		this.approvedCount = approvedCount;
	}
	public Long getPendingCount() {
		return pendingCount;
	}
	public void setPendingCount(Long pendingCount) {
		this.pendingCount = pendingCount;
	}
	public Long getApprPartyFund() {
		return apprPartyFund;
	}
	public void setApprPartyFund(Long apprPartyFund) {
		this.apprPartyFund = apprPartyFund;
	}
	public Long getApprCMRefiedFund() {
		return apprCMRefiedFund;
	}
	public void setApprCMRefiedFund(Long apprCMRefiedFund) {
		this.apprCMRefiedFund = apprCMRefiedFund;
	}
	public Long getApprPartyMembsCount() {
		return apprPartyMembsCount;
	}
	public void setApprPartyMembsCount(Long apprPartyMembsCount) {
		this.apprPartyMembsCount = apprPartyMembsCount;
	}
	public Long getApprCMReliefMembsCount() {
		return apprCMReliefMembsCount;
	}
	public void setApprCMReliefMembsCount(Long apprCMReliefMembsCount) {
		this.apprCMReliefMembsCount = apprCMReliefMembsCount;
	}
}
