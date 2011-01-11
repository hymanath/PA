/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on December 22,2010
 */
package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "user_approval_details")
public class UserApprovalDetails extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long userApprovalDetailsId;
	private AnanymousUser user;
	private ApprovalDetails approvalDetails;
	private Set<UserProblemApproval> userApprovalDetails = new HashSet<UserProblemApproval>(0);
	
	public UserApprovalDetails() {
		
	}

	public UserApprovalDetails(Long userApprovalDetailsId, AnanymousUser user,
			ApprovalDetails approvalDetails) {
		this.userApprovalDetailsId = userApprovalDetailsId;
		this.user = user;
		this.approvalDetails = approvalDetails;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_approval_details_id", unique = true, nullable = false)	
	public Long getUserApprovalDetailsId() {
		return userApprovalDetailsId;
	}

	public void setUserApprovalDetailsId(Long userApprovalDetailsId) {
		this.userApprovalDetailsId = userApprovalDetailsId;
	}

	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)	
	public AnanymousUser getUser() {
		return user;
	}

	public void setUser(AnanymousUser user) {
		this.user = user;
	}

	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "approval_details_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)	
	public ApprovalDetails getApprovalDetails() {
		return approvalDetails;
	}

	public void setApprovalDetails(ApprovalDetails approvalDetails) {
		this.approvalDetails = approvalDetails;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userApprovalDetails")
	public Set<UserProblemApproval> getUserApprovalDetails() {
		return userApprovalDetails;
	}

	public void setUserApprovalDetails(Set<UserProblemApproval> userApprovalDetails) {
		this.userApprovalDetails = userApprovalDetails;
	}
	
	
	
	
	
	
	
	
	

}
