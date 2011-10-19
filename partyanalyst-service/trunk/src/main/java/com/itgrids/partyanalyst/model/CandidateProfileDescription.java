/* 
 * Copyright (c) 2011 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on October 18, 2011
 */
package com.itgrids.partyanalyst.model;

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
 * file entity.
 * 
 * @author sachin
 */
@Entity
@Table(name = "candidate_profile_description")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CandidateProfileDescription extends BaseModel implements
		java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long candidateProfileDescriptionId;
	private Candidate candidate;
	private String description;
	private Long orderNo;
	

	/** default constructor */
	public CandidateProfileDescription() {

	}

	/** full constructor */
	public CandidateProfileDescription(Long candidateProfileDescriptionId,
			Candidate candidate, String description, Long orderNo) {

		this.candidateProfileDescriptionId = candidateProfileDescriptionId;
		this.candidate = candidate;
		this.description = description;
		this.orderNo = orderNo;

	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "candidate_profile_description_id", unique = true, nullable = false)
	public Long getCandidateProfileDescriptionId() {
		return candidateProfileDescriptionId;
	}

	public void setCandidateProfileDescriptionId(
			Long candidateProfileDescriptionId) {
		this.candidateProfileDescriptionId = candidateProfileDescriptionId;
	}

	@Column(name = "description", length = 300)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "order_no")
	public Long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
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

}
