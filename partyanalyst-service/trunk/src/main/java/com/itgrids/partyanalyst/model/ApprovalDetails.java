/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on December 22,2010
 */

package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name = "approval_details")
public class ApprovalDetails extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long approvalDetailsId;
	private String reason;
	private String isApproved;
	private Set<UserApprovalDetails> userApprovalDetails = new HashSet<UserApprovalDetails>(0);
	private Date postedDate;
	private String isAdminApproved;
	
	public ApprovalDetails() {		
	}

	public ApprovalDetails(Long approvalDetailsId, String reason,
			String isApproved, Date postedDate,String isAdminApproved) {
		
		this.approvalDetailsId = approvalDetailsId;
		this.reason = reason;
		this.isApproved = isApproved;
		this.postedDate = postedDate;
		this.isAdminApproved=isAdminApproved;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "approval_details_id", unique = true, nullable = false)	
	public Long getApprovalDetailsId() {
		return approvalDetailsId;
	}

	public void setApprovalDetailsId(Long approvalDetailsId) {
		this.approvalDetailsId = approvalDetailsId;
	}

	@Column(name = "reason", length = 1000)
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Column(name = "is_Approved", length = 10)
	public String getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "approvalDetails")
	public Set<UserApprovalDetails> getUserApprovalDetails() {
		return userApprovalDetails;
	}

	public void setUserApprovalDetails(Set<UserApprovalDetails> userApprovalDetails) {
		this.userApprovalDetails = userApprovalDetails;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "posted_Date", length = 10)		
	public Date getPostedDate() {
		return postedDate;
	}
	
	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}

	@Column(name ="is_admin_approved", length = 10)
	public String getIsAdminApproved() {
		return isAdminApproved;
	}

	public void setIsAdminApproved(String isAdminApproved) {
		this.isAdminApproved = isAdminApproved;
	}
	
	
}
