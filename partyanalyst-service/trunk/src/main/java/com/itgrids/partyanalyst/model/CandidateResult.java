/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 09, 2009
 */
package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * CandidateResult entity. 
 * @author <a href="mailto:shan.javaee@gmail.com">Shan Nagarajan</a>
 */
@Entity
@Table(name = "candidate_result")
public class CandidateResult implements java.io.Serializable {

	/**
	 * The Serial Version UID. 
	 */
	private static final long serialVersionUID = 5363767538287226340L;
	
	// Fields
	
	private Long candidateResultId;
	private Nomination nomination;
	private Double votesEarned;
	private Long rank;

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
	@Column(name = "candidate_result_id", unique = true, nullable = false)
	public Long getCandidateResultId() {
		return this.candidateResultId;
	}

	public void setCandidateResultId(Long candidateResultId) {
		this.candidateResultId = candidateResultId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nomination_id")
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

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof CandidateResult))
			return false;
		CandidateResult castOther = (CandidateResult) other;
		return new EqualsBuilder().append(candidateResultId,
				castOther.candidateResultId).append(nomination,
				castOther.nomination)
				.append(votesEarned, castOther.votesEarned).append(rank,
						castOther.rank).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(candidateResultId).append(
				nomination).append(votesEarned).append(rank).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("candidateResultId",
				candidateResultId).append("nomination", nomination).append(
				"votesEarned", votesEarned).append("rank", rank).toString();
	}
	
}