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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFoundAction;

/**
 * ConstituencyElectionResult entity. 
 * @author <a href="mailto:shan.javaee@gmail.com">Shan Nagarajan</a>
 */
@Entity
@Table(name = "constituency_election_result")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ConstituencyElectionResult extends BaseModel implements java.io.Serializable {

	/**
	 * The Serial Version UID. 
	 */
	private static final long serialVersionUID = -4522212878226452263L;
	
	// Fields
	
	private Long constiElecResultId;
	private ConstituencyElection constituencyElection;
	private Double totalVotes;
	private Double validVotes;
	private Double rejectedVotes;
	private Double missingVotes;
	private Double totalVotesPolled;
	private Double tenderedVotes;
	private String votingPercentage;

	// Constructors

	/** default constructor */
	public ConstituencyElectionResult() {
	}

	/** minimal constructor */
	public ConstituencyElectionResult(Long constiElecResultId) {
		this.constiElecResultId = constiElecResultId;
	}

	/** full constructor */
	public ConstituencyElectionResult(Long constiElecResultId,
			ConstituencyElection constituencyElection, Double totalVotes,
			Double validVotes, Double rejectedVotes, Double missingVotes,
			Double totalVotesPolled, Double tenderedVotes) {
		this.constiElecResultId = constiElecResultId;
		this.constituencyElection = constituencyElection;
		this.totalVotes = totalVotes;
		this.validVotes = validVotes;
		this.rejectedVotes = rejectedVotes;
		this.missingVotes = missingVotes;
		this.totalVotesPolled = totalVotesPolled;
		this.tenderedVotes = tenderedVotes;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "consti_elec_result_id", unique = true, nullable = false)
	public Long getConstiElecResultId() {
		return this.constiElecResultId;
	}

	public void setConstiElecResultId(Long constiElecResultId) {
		this.constiElecResultId = constiElecResultId;
	}
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "consti_elec_id")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ConstituencyElection getConstituencyElection() {
		return this.constituencyElection;
	}

	public void setConstituencyElection(
			ConstituencyElection constituencyElection) {
		this.constituencyElection = constituencyElection;
	}

	@Column(name = "total_votes", precision = 10, scale = 0)
	public Double getTotalVotes() {
		return this.totalVotes;
	}

	public void setTotalVotes(Double totalVotes) {
		this.totalVotes = totalVotes;
	}

	@Column(name = "valid_votes", precision = 10, scale = 0)
	public Double getValidVotes() {
		return this.validVotes;
	}

	public void setValidVotes(Double validVotes) {
		this.validVotes = validVotes;
	}

	@Column(name = "rejected_votes", precision = 10, scale = 0)
	public Double getRejectedVotes() {
		return this.rejectedVotes;
	}

	public void setRejectedVotes(Double rejectedVotes) {
		this.rejectedVotes = rejectedVotes;
	}

	@Column(name = "missing_votes", precision = 10, scale = 0)
	public Double getMissingVotes() {
		return this.missingVotes;
	}

	public void setMissingVotes(Double missingVotes) {
		this.missingVotes = missingVotes;
	}

	@Column(name = "total_votes_polled", precision = 10, scale = 0)
	public Double getTotalVotesPolled() {
		return this.totalVotesPolled;
	}

	public void setTotalVotesPolled(Double totalVotesPolled) {
		this.totalVotesPolled = totalVotesPolled;
	}

	@Column(name = "tendered_votes", precision = 10, scale = 0)
	public Double getTenderedVotes() {
		return this.tenderedVotes;
	}

	public void setTenderedVotes(Double tenderedVotes) {
		this.tenderedVotes = tenderedVotes;
	}

	@Column(name = "voting_percentage")
	public String getVotingPercentage() {
		return votingPercentage;
	}

	public void setVotingPercentage(String votingPercentage) {
		this.votingPercentage = votingPercentage;
	}

/*	 (non-Javadoc)
	 * @see java.lang.Object#toString()
	 
	@Override
	public String toString() {
		return new ToStringBuilder(this).appendSuper(super.toString()).append(
				"constiElecResultId", constiElecResultId).append(
				"constituencyElection", constituencyElection).append(
				"totalVotes", totalVotes).append("validVotes", validVotes)
				.append("rejectedVotes", rejectedVotes).append("missingVotes",
						missingVotes).append("totalVotesPolled",
						totalVotesPolled)
				.append("tenderedVotes", tenderedVotes).toString();
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof ConstituencyElectionResult))
			return false;
		ConstituencyElectionResult castOther = (ConstituencyElectionResult) other;
		return new EqualsBuilder().append(constiElecResultId,
				castOther.constiElecResultId).append(constituencyElection,
				castOther.constituencyElection).append(totalVotes,
				castOther.totalVotes).append(validVotes, castOther.validVotes)
				.append(rejectedVotes, castOther.rejectedVotes).append(
						missingVotes, castOther.missingVotes).append(
						totalVotesPolled, castOther.totalVotesPolled).append(
						tenderedVotes, castOther.tenderedVotes).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(constiElecResultId).append(
				constituencyElection).append(totalVotes).append(validVotes)
				.append(rejectedVotes).append(missingVotes).append(
						totalVotesPolled).append(tenderedVotes).toHashCode();
	}
	
*/
}