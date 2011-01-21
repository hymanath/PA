package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.Date;

public class ApprovalInfoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long userId;
	private String reason;
	private String isApproved;
	private String module;
	// if used for problems , this variable refers which problem this approval is posted
	private Long problemHistoryId;
	private Long totalNoAcceptance;
	private Long totalNoRejectance;
	private String lastUpdate;
	private String userName;
	private String postedDate;
	private String approvalDetailsId;
	private int count;
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getIsApproved() {
		return isApproved;
	}
	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public Long getProblemHistoryId() {
		return problemHistoryId;
	}
	public void setProblemHistoryId(Long problemHistoryId) {
		this.problemHistoryId = problemHistoryId;
	}
	public Long getTotalNoAcceptance() {
		return totalNoAcceptance;
	}
	public void setTotalNoAcceptance(Long totalNoAcceptance) {
		this.totalNoAcceptance = totalNoAcceptance;
	}
	public Long getTotalNoRejectance() {
		return totalNoRejectance;
	}
	public void setTotalNoRejectance(Long totalNoRejectance) {
		this.totalNoRejectance = totalNoRejectance;
	}
	public String getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setPostedDate(String postedDate) {
		this.postedDate = postedDate;
	}
	public String getPostedDate() {
		return postedDate;
	}
	public void setApprovalDetailsId(String approvalDetailsId) {
		
		this.approvalDetailsId = approvalDetailsId;
		
		}
	public String getApprovalDetailsId()
	{
		return approvalDetailsId;
	}
	
	
	
	
	

}
