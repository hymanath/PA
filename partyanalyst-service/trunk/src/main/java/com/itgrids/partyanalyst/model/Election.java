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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

/**
 * Election entity. 
 * @author <a href="mailto:shan.javaee@gmail.com">Shan Nagarajan</a>
 */
@Entity
@Table(name = "election")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Election extends BaseModel implements java.io.Serializable {

	/**
	 * The Serial Version UID.
	 */
	private static final long serialVersionUID = 5050050993475618601L;
	
	// Fields
	
	private Long electionId;
	private ElectionScope electionScope;
	private Date notificationDate;
	private Date startDate;
	private String endDate;
	private String electionYear;
	private String elecSubtype;
	private String hasAssets;
	private Set<ConstituencyElection> constituencyElections = new HashSet<ConstituencyElection>(0);
	private Set<PartyElectionResult> partyElectionResult = new HashSet<PartyElectionResult>(0);
	private Set<PartyElectionDistrictResult> partyElectionDistrictResult = new HashSet<PartyElectionDistrictResult>(0);
	private Set<CommentCategoryParty> commentCategoryParty = new HashSet<CommentCategoryParty>(0);
	private Set<CommentCategoryConstituency> commentCategoryConstituency = new HashSet<CommentCategoryConstituency>(0);
	private Set<PartyElectionResultsWithGenderAnalysis> partyElectionResultsWithGenderAnalysis = new HashSet<PartyElectionResultsWithGenderAnalysis>(0);
	private String isPartial;

	// Constructors

	/** default constructor */
	public Election() {
	}

	/** minimal constructor */
	public Election(Long electionId) {
		this.electionId = electionId;
	}

	/** full constructor */
	public Election(Long electionId, ElectionScope electionScope,
			Date notificationDate, Date startDate, String endDate,
			String electionYear, Set<ConstituencyElection> constituencyElections,Set<PartyElectionResult> partyElectionResult,Set<PartyElectionDistrictResult> partyElectionDistrictResult,
			Set<CommentCategoryParty> commentCategoryParty,
			Set<CommentCategoryConstituency> commentCategoryConstituency,String isPartial) {
		this.electionId = electionId;
		this.electionScope = electionScope;
		this.notificationDate = notificationDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.electionYear = electionYear;
		this.constituencyElections = constituencyElections;
		this.partyElectionResult = partyElectionResult;
		this.partyElectionDistrictResult = partyElectionDistrictResult;
		this.commentCategoryParty = commentCategoryParty;
		this.commentCategoryConstituency = commentCategoryConstituency;
		this.isPartial = isPartial;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "election_id", unique = true, nullable = false)
	public Long getElectionId() {
		return this.electionId;
	}

	public void setElectionId(Long electionId) {
		this.electionId = electionId;
	}

	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "election_scope_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ElectionScope getElectionScope() {
		return this.electionScope;
	}
	
	public void setElectionScope(ElectionScope electionScope) {
		this.electionScope = electionScope;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "notification_date", length = 10)
	public Date getNotificationDate() {
		return this.notificationDate;
	}

	public void setNotificationDate(Date notificationDate) {
		this.notificationDate = notificationDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "start_date", length = 10)
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Column(name = "end_date", length = 25)
	public String getEndDate() {
		return this.endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Column(name = "election_year", length = 25)
	public String getElectionYear() {
		return this.electionYear;
	}

	public void setElectionYear(String electionYear) {
		this.electionYear = electionYear;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "election")
	public Set<ConstituencyElection> getConstituencyElections() {
		return this.constituencyElections;
	}

	public void setConstituencyElections(
			Set<ConstituencyElection> constituencyElections) {
		this.constituencyElections = constituencyElections;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "election")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<PartyElectionResult> getPartyElectionResult() {
		return partyElectionResult;
	}

	public void setPartyElectionResult(Set<PartyElectionResult> partyElectionResult) {
		this.partyElectionResult = partyElectionResult;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "election")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<PartyElectionDistrictResult> getPartyElectionDistrictResult() {
		return partyElectionDistrictResult;
	}

	public void setPartyElectionDistrictResult(
			Set<PartyElectionDistrictResult> partyElectionDistrictResult) {
		this.partyElectionDistrictResult = partyElectionDistrictResult;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "election")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<CommentCategoryParty> getCommentCategoryParty() {
		return commentCategoryParty;
	}

	public void setCommentCategoryParty(
			Set<CommentCategoryParty> commentCategoryParty) {
		this.commentCategoryParty = commentCategoryParty;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "election")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<CommentCategoryConstituency> getCommentCategoryConstituency() {
		return commentCategoryConstituency;
	}

	public void setCommentCategoryConstituency(
			Set<CommentCategoryConstituency> commentCategoryConstituency) {
		this.commentCategoryConstituency = commentCategoryConstituency;
	}

	@Column(name = "sub_type", length = 25)
	public String getElecSubtype() {
		return elecSubtype;
	}

	public void setElecSubtype(String elecSubtype) {
		this.elecSubtype = elecSubtype;
	}

	@Column(name = "is_partial")
	public String getIsPartial() {
		return isPartial;
	}

	public void setIsPartial(String isPartial) {
		this.isPartial = isPartial;
	}

	@Column(name = "has_assets")
	public String getHasAssets() {
		return hasAssets;
	}

	public void setHasAssets(String hasAssets) {
		this.hasAssets = hasAssets;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "election")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<PartyElectionResultsWithGenderAnalysis> getPartyElectionResultsWithGenderAnalysis() {
		return partyElectionResultsWithGenderAnalysis;
	}

	public void setPartyElectionResultsWithGenderAnalysis(
			Set<PartyElectionResultsWithGenderAnalysis> partyElectionResultsWithGenderAnalysis) {
		this.partyElectionResultsWithGenderAnalysis = partyElectionResultsWithGenderAnalysis;
	}
	
}