/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on December 22,2010
 */
package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;
@Entity
@Table(name = "user_problem_approval")
public class UserProblemApproval extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long userProblemApprovalId;
	private UserApprovalDetails userApprovalDetails;
	private ProblemHistory problemHistory;
	
	public UserProblemApproval() {
	
	}

	public UserProblemApproval(Long userProblemApprovalId,
			UserApprovalDetails userApprovalDetails,
			ProblemHistory problemHistory) {
		
		this.userProblemApprovalId = userProblemApprovalId;
		this.userApprovalDetails = userApprovalDetails;
		this.problemHistory = problemHistory;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_problem_approval_id", unique = true, nullable = false)	
	public Long getUserProblemApprovalId() {
		return userProblemApprovalId;
	}

	public void setUserProblemApprovalId(Long userProblemApprovalId) {
		this.userProblemApprovalId = userProblemApprovalId;
	}
	
	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_approval_details_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)	
	public UserApprovalDetails getUserApprovalDetails() {
		return userApprovalDetails;
	}

	public void setUserApprovalDetails(UserApprovalDetails userApprovalDetails) {
		this.userApprovalDetails = userApprovalDetails;
	}
	
	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "problem_history_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)	
	public ProblemHistory getProblemHistory() {
		return problemHistory;
	}

	public void setProblemHistory(ProblemHistory problemHistory) {
		this.problemHistory = problemHistory;
	}
	
	
	
	
	
	

}
