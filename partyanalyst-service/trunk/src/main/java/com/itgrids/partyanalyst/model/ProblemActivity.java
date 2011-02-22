/* 
 * Copyright (c) 2011 IT Grids India LTD.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on February 19, 2011
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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFoundAction;

/**
 * @author Sai Krishna
 *
 */
@Entity
@Table(name = "problem_activity")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProblemActivity extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long problemActivityId;
	private String activityDescription;
	private Date updatedDate;
	private String comments;
	
	private Set<AssignedProblemProgress> assignedProblemProgress = new HashSet<AssignedProblemProgress>();
	
	//Default Constructor
	public ProblemActivity() {
		super();
	}


    //Parameterized Constructor
	public ProblemActivity(Long problemActivityId, String activityDescription,
			Date updatedDate, String comments,
			Set<AssignedProblemProgress> assignedProblemProgress) {
		super();
		this.problemActivityId = problemActivityId;
		this.activityDescription = activityDescription;
		this.updatedDate = updatedDate;
		this.comments = comments;
		this.assignedProblemProgress = assignedProblemProgress;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "problem_activity_id", unique = true, nullable = false)
	public Long getProblemActivityId() {
		return problemActivityId;
	}


	public void setProblemActivityId(Long problemActivityId) {
		this.problemActivityId = problemActivityId;
	}


	@Column(name = "activity_desc", length = 100)
	public String getActivityDescription() {
		return activityDescription;
	}


	public void setActivityDescription(String activityDescription) {
		this.activityDescription = activityDescription;
	}


	@Column(name = "updated_date", length = 10)
	public Date getUpdatedDate() {
		return updatedDate;
	}


	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	
	@Column(name = "comments", length = 100)
	public String getComments() {
		return comments;
	}


	public void setComments(String comments) {
		this.comments = comments;
	}
 
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "problemActivity")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<AssignedProblemProgress> getAssignedProblemProgress() {
		return assignedProblemProgress;
	}


	public void setAssignedProblemProgress(
			Set<AssignedProblemProgress> assignedProblemProgress) {
		this.assignedProblemProgress = assignedProblemProgress;
	}
	
	
}
