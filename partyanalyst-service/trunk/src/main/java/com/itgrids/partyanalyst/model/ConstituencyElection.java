/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 09, 2009
 */
package com.itgrids.partyanalyst.model;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

/**
 * ConstituencyElection entity. 
 * @author <a href="mailto:shan.javaee@gmail.com">Shan Nagarajan</a>
 */
@Entity
@Table(name = "constituency_election")
public class ConstituencyElection extends BaseModel implements java.io.Serializable {

	/**
	 * The Serial Version UID.
	 */
	private static final long serialVersionUID = 3514866776285997902L;
	
	// Fields
	
	private Long constiElecId;
	private Election election;
	private Constituency constituency;
	private Date electionDate;
	private Set<Nomination> nominations = new HashSet<Nomination>(0);
	private ConstituencyElectionResult constituencyElectionResult;

	// Constructors

	/** default constructor */
	public ConstituencyElection() {
	}

	/** minimal constructor */
	public ConstituencyElection(Long constiElecId) {
		this.constiElecId = constiElecId;
	}

	/** full constructor */
	public ConstituencyElection(Long constiElecId, Election election,
			Constituency constituency, Date electionDate,
			Set<Nomination> nominations,
			ConstituencyElectionResult constituencyElectionResult) {
		this.constiElecId = constiElecId;
		this.election = election;
		this.constituency = constituency;
		this.electionDate = electionDate;
		this.nominations = nominations;
		this.constituencyElectionResult = constituencyElectionResult;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "consti_elec_id", unique = true, nullable = false)
	public Long getConstiElecId() {
		return this.constiElecId;
	}

	public void setConstiElecId(Long constiElecId) {
		this.constiElecId = constiElecId;
	}

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "election_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Election getElection() {
		return this.election;
	}

	public void setElection(Election election) {
		this.election = election;
	}

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "constituency_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Constituency getConstituency() {
		return this.constituency;
	}

	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "election_date", length = 10)
	public Date getElectionDate() {
		return this.electionDate;
	}

	public void setElectionDate(Date electionDate) {
		this.electionDate = electionDate;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "constituencyElection")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<Nomination> getNominations() {
		return this.nominations;
	}

	public void setNominations(Set<Nomination> nominations) {
		this.nominations = nominations;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "constituencyElection")
	@JoinColumn(name = "consti_elec_result_id")
	/*@OneToOne(mappedBy = "constituencyElection")*/
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ConstituencyElectionResult getConstituencyElectionResult() {
		return this.constituencyElectionResult;
	}

	public void setConstituencyElectionResult(
			ConstituencyElectionResult constituencyElectionResult) {
		this.constituencyElectionResult = constituencyElectionResult;
	}

}