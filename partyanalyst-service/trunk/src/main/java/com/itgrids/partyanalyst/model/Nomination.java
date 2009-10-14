/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 09, 2009
 */
package com.itgrids.partyanalyst.model;

import java.util.HashMap;
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

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

/**
 * Nomination entity. 
 * @author <a href="mailto:shan.javaee@gmail.com">Shan Nagarajan</a>
 */
@Entity
@Table(name = "nomination")
public class Nomination extends BaseModel implements java.io.Serializable {

	/**
	 * The Serial Version UID.
	 */
	private static final long serialVersionUID = 2093622838327054124L;
	
	// Fields
	
	private Long nominationId;
	private ConstituencyElection constituencyElection;
	private Party party;
	private Candidate candidate;
	private String nominationStatus;
	private Double assets;
	private Double liabilities;
	private String criminalCharges;
	private CandidateResult candidateResult;
	private Set<BoothResult> boothResults = new HashSet<BoothResult>(0);

	// Constructors

	/** default constructor */
	public Nomination() {
	}

	/** minimal constructor */
	public Nomination(Long nominationId) {
		this.nominationId = nominationId;
	}

	/** full constructor */
	public Nomination(Long nominationId,
			ConstituencyElection constituencyElection, Party party,
			Candidate candidate, String nominationStatus, Double assets,
			Double liabilities, String criminalCharges,
			CandidateResult candidateResult, Set<BoothResult> boothResults) {
		this.nominationId = nominationId;
		this.constituencyElection = constituencyElection;
		this.party = party;
		this.candidate = candidate;
		this.nominationStatus = nominationStatus;
		this.assets = assets;
		this.liabilities = liabilities;
		this.criminalCharges = criminalCharges;
		this.candidateResult = candidateResult;
		this.boothResults = boothResults;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "nomination_id", unique = true, nullable = false)
	public Long getNominationId() {
		return this.nominationId;
	}

	public void setNominationId(Long nominationId) {
		this.nominationId = nominationId;
	}

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "consti_elec_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ConstituencyElection getConstituencyElection() {
		return this.constituencyElection;
	}

	public void setConstituencyElection(
			ConstituencyElection constituencyElection) {
		this.constituencyElection = constituencyElection;
	}

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "party_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Party getParty() {
		return this.party;
	}

	public void setParty(Party party) {
		this.party = party;
	}

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "candidate_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Candidate getCandidate() {
		return this.candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	@Column(name = "nomination_status", length = 25)
	public String getNominationStatus() {
		return this.nominationStatus;
	}

	public void setNominationStatus(String nominationStatus) {
		this.nominationStatus = nominationStatus;
	}

	@Column(name = "assets", precision = 10, scale = 0)
	public Double getAssets() {
		return this.assets;
	}

	public void setAssets(Double assets) {
		this.assets = assets;
	}

	@Column(name = "liabilities", precision = 10, scale = 0)
	public Double getLiabilities() {
		return this.liabilities;
	}

	public void setLiabilities(Double liabilities) {
		this.liabilities = liabilities;
	}

	@Column(name = "criminal_charges", length = 300)
	public String getCriminalCharges() {
		return this.criminalCharges;
	}

	public void setCriminalCharges(String criminalCharges) {
		this.criminalCharges = criminalCharges;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "nomination")
	public CandidateResult getCandidateResult() {
		return this.candidateResult;
	}

	public void setCandidateResult(CandidateResult candidateResult) {
		this.candidateResult = candidateResult;
	}

	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "nomination")
	public Set<BoothResult> getBoothResults() {
		return boothResults;
	}

	public void setBoothResults(Set<BoothResult> boothResults) {
		this.boothResults = boothResults;
	}
	
	

}