/* 
 * Copyright (c) 2011 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on October 31, 2011
 */
package com.itgrids.partyanalyst.model;

import java.util.Date;
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
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

/**
 * message_to_candidate entity.
 * 
 * @author sachin
 */
@Entity
@Table(name = "message_to_candidate")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MessageToCandidate extends BaseModel implements
		java.io.Serializable {
	
	private static final long serialVersionUID = 5056237509241032776L;
	private Long messageToCandidateId;
	private String name;
	private Constituency constituency;
	private String message;
	private Candidate candidate;
	private String isDelete;
	private String isApproved;
	private Date time;
	private String isPrivate;

	/** default constructor */
	public MessageToCandidate() {

	}

	/** full constructor */
	public MessageToCandidate(Long messageToCandidate_id, String name,
			Constituency constituency, String message, Candidate candidate,
			String isDelete,String isPrivate) {
		this.messageToCandidateId = messageToCandidateId;
		this.name = name;
		this.constituency = constituency;
		this.message = message;
		this.candidate = candidate;
		this.isDelete = isDelete;
		this.isPrivate = isPrivate;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "message_to_candidate_id", unique = true, nullable = false)
	public Long getMessageToCandidateId() {
		return messageToCandidateId;
	}

	public void setMessageToCandidateId(Long messageToCandidateId) {
		this.messageToCandidateId = messageToCandidateId;
	}
	@Column(name = "name", length = 100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "location")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Constituency getConstituency() {
		return constituency;
	}

	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}
	@Column(name = "message" , length = 100)
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "candidate_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	@Column(name = "is_delete")
	public String getIsDelete() {
		return isDelete;
	}
	
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	@Column(name = "is_approved")
	public String getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}

	@Column(name = "time", length = 10)
	public Date getTime() {
		return time;
	}
	
	public void setTime(Date time) {
		this.time = time;
	}
	
	@Column(name = "is_private", length=10)
	public String getIsPrivate() {
		return isPrivate;
	}

	public void setIsPrivate(String isPrivate) {
		this.isPrivate = isPrivate;
	}
	

}
