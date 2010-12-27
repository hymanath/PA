/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 09, 2009
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
 * CandidateResult entity. 
 * @author <a href="mailto:shan.javaee@gmail.com">Shan Nagarajan</a>
 */
@Entity
@Table(name = "candidate_result")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CandidateResult extends BaseModel implements java.io.Serializable {

	/**
	 * The Serial Version UID. 
	 */
	private static final long serialVersionUID = 5363767538287226340L;
	
	// Fields
	
	private Long candidateResultId;
	private Nomination nomination;
	private Double votesEarned;
	private Long rank;
	private String votesPercengate;

	// Constructors

	/** default constructor */
	public CandidateResult() {
	}

	/** minimal constructor */
	public CandidateResult(Long candidateResultId) {
		this.candidateResultId = candidateResultId;
	}

	/** full constructor */
	public CandidateResult(Long candidateResultId, Nomination nomination,
			Double votesEarned, Long rank) {
		this.candidateResultId = candidateResultId;
		this.nomination = nomination;
		this.votesEarned = votesEarned;
		this.rank = rank;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "candidate_result_id", unique = true, nullable = false)
	public Long getCandidateResultId() {
		return this.candidateResultId;
	}

	public void setCandidateResultId(Long candidateResultId) {
		this.candidateResultId = candidateResultId;
	}

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "nomination_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Nomination getNomination() {
		return this.nomination;
	}

	public void setNomination(Nomination nomination) {
		this.nomination = nomination;
	}

	@Column(name = "votes_earned", precision = 10, scale = 0)
	public Double getVotesEarned() {
		return this.votesEarned;
	}

	public void setVotesEarned(Double votesEarned) {
		this.votesEarned = votesEarned;
	}

	@Column(name = "rank")
	public Long getRank() {
		return this.rank;
	}

	public void setRank(Long rank) {
		this.rank = rank;
	}

		
	@Column(name = "votes_percentage")
	public String getVotesPercengate() {
		return votesPercengate;
	}

	public void setVotesPercengate(String votesPercengate) {
		this.votesPercengate = votesPercengate;
	}
	
}