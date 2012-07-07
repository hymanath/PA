
/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 03, 2012
 */
package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;

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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;
/**
 *  CandidateSubscriptions entity
 */
@Entity
@Table(name = "candidate_subscriptions")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

public class CandidateSubscriptions implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private Long candidateSubscriptionsId;
	private Candidate candidate;
	private User user;
	private Long candidateId;
	private Long userId;
	private Date insertedTime;
	private Date updatedTime;
	
	public CandidateSubscriptions()
	{}
	
	public CandidateSubscriptions(Long candidateId,Long userId,Date insertedTime,Date updatedTime)
	{
		this.candidateId=candidateId;
		this.userId=userId;
		this.insertedTime=insertedTime;
		this.updatedTime=updatedTime;
	}
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="candidate_subscriptions_id", unique=true, nullable=false)
	public Long getCandidateSubscriptionsId() {
		return candidateSubscriptionsId;
	}
	public void setCandidateSubscriptionsId(Long candidateSubscriptionsId) {
		this.candidateSubscriptionsId = candidateSubscriptionsId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="candidate_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "candidate_id", length = 10)
	public Long getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}
	
	@Column(name = "user_id", length = 10)
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@Column(name="inserted_time",length=10)
	public Date getInsertedTime() {
		return insertedTime;
	}

	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@Column(name="updated_time",length=10)
	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	
}
