/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 09, 2009
 */
package com.itgrids.partyanalyst.model;

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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFoundAction;

/**
 * Party entity. 
 * @author <a href="mailto:shan.javaee@gmail.com">Shan Nagarajan</a>
 */
@Entity
@Table(name = "party")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Party implements java.io.Serializable {
	

	/**
	 * The Serial Version UID.
	 */
	private static final long serialVersionUID = 6353823654394427829L;
	
	// Fields
	
	private Long partyId;
	private String longName;
	private String shortName;
	private String symbol;
	private String address;
	private String comments;
	private String partyRecognization;
	private String partyLogo;
	private String partyFlag;	
	private State state;
	private Set<Nomination> nominations = new HashSet<Nomination>(0);
	private Set<Registration> registrations = new HashSet<Registration>(0);
	private Set<PartyImportantDates> partyImportantDates = new HashSet<PartyImportantDates>(0);
	private Set<PartyElectionResult> partyElectionResult = new HashSet<PartyElectionResult>(0);
	private Set<PartyElectionDistrictResult> partyElectionDistrictResult = new HashSet<PartyElectionDistrictResult>(0);
	private Set<CommentCategoryParty> commentCategoryParty = new HashSet<CommentCategoryParty>(0);
	private Set<PartyWorkingCommittee> partyWorkingCommittees = new HashSet<PartyWorkingCommittee>(0);
	private Set<PartyCadreSkills> partyCadreSkills = new HashSet<PartyCadreSkills>(0);
	private Set<PartyTrainingCamps> partyTrainingCamps = new HashSet<PartyTrainingCamps>(0);
	private Set<PartyGallery> partyGallery = new HashSet<PartyGallery>(0);
	// Constructors
	
	/** default constructor */
	public Party() {
	}

	/** minimal constructor */
	public Party(Long partyId) {
		this.partyId = partyId;
	}

	/** full constructor */
	public Party(Long partyId, String longName, String shortName,
			String symbol, String address, String comments,
			String partyRecognization, Set<Nomination> nominations,
			Set<CommentCategoryParty> commentCategoryParty) {
		this.partyId = partyId;
		this.longName = longName;
		this.shortName = shortName;
		this.symbol = symbol;
		this.address = address;
		this.comments = comments;
		this.partyRecognization = partyRecognization;
		this.nominations = nominations;
		this.commentCategoryParty = commentCategoryParty;
	}
	
	public Party(Long partyId, String longName, String shortName,
			String symbol, String address, String comments,
			String partyRecognization,
			Set<Nomination> nominations,
			Set<Registration> registrations, Set<PartyImportantDates> partyImportantDates,Set<PartyElectionResult> partyElectionResult,Set<PartyElectionDistrictResult> partyElectionDistrictResult,
			Set<CommentCategoryParty> commentCategoryParty,State state) {
		this.partyId = partyId;
		this.longName = longName;
		this.shortName = shortName;
		this.symbol = symbol;
		this.address = address;
		this.comments = comments;
		this.partyRecognization = partyRecognization;	
		this.nominations = nominations;
		this.registrations = registrations;
		this.partyImportantDates = partyImportantDates;
		this.partyElectionResult = partyElectionResult;
		this.partyElectionDistrictResult = partyElectionDistrictResult;
		this.commentCategoryParty = commentCategoryParty;
		this.state = state;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "party_id", unique = true, nullable = false)
	public Long getPartyId() {
		return this.partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	@Column(name = "long_name", length = 100)
	public String getLongName() {
		return this.longName;
	}

	public void setLongName(String longName) {
		this.longName = longName;
	}

	@Column(name = "short_name", length = 25)
	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@Column(name = "symbol", length = 25)
	public String getSymbol() {
		return this.symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	@Column(name = "address", length = 300)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "comments", length = 300)
	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Column(name = "party_recognization", length = 25)
	public String getPartyRecognization() {
		return this.partyRecognization;
	}

	public void setPartyRecognization(String partyRecognization) {
		this.partyRecognization = partyRecognization;
	}
	
	@Column(name = "party_logo", length = 25)
	public String getPartyLogo() {
		return partyLogo;
	}

	public void setPartyLogo(String partyLogo) {
		this.partyLogo = partyLogo;
	}
	
	@Column(name = "party_flag", length = 25)
	public String getPartyFlag() {
		return partyFlag;
	}

	public void setPartyFlag(String partyFlag) {
		this.partyFlag = partyFlag;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "party")
	public Set<Nomination> getNominations() {
		return this.nominations;
	}

	public void setNominations(Set<Nomination> nominations) {
		this.nominations = nominations;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "party")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
		public Set<Registration> getRegistrations() {
		return registrations;
	}

	public void setRegistrations(Set<Registration> registrations) {
		this.registrations = registrations;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "party")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<PartyImportantDates> getpartyImportantDates() {
		return partyImportantDates;
	}

	public void setpartyImportantDates(Set<PartyImportantDates> partyImportantDates) {
		this.partyImportantDates = partyImportantDates;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "party")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<PartyElectionResult> getPartyElectionResult() {
		return partyElectionResult;
	}

	public void setPartyElectionResult(Set<PartyElectionResult> partyElectionResult) {
		this.partyElectionResult = partyElectionResult;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "party")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<PartyElectionDistrictResult> getPartyElectionDistrictResult() {
		return partyElectionDistrictResult;
	}

	public void setPartyElectionDistrictResult(
			Set<PartyElectionDistrictResult> partyElectionDistrictResult) {
		this.partyElectionDistrictResult = partyElectionDistrictResult;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "party")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<CommentCategoryParty> getCommentCategoryParty() {
		return commentCategoryParty;
	}

	public void setCommentCategoryParty(
			Set<CommentCategoryParty> commentCategoryParty) {
		this.commentCategoryParty = commentCategoryParty;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "party")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<PartyWorkingCommittee> getPartyWorkingCommittees() {
		return partyWorkingCommittees;
	}

	public void setPartyWorkingCommittees(
			Set<PartyWorkingCommittee> partyWorkingCommittees) {
		this.partyWorkingCommittees = partyWorkingCommittees;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "party")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<PartyCadreSkills> getPartyCadreSkills() {
		return partyCadreSkills;
	}

	public void setPartyCadreSkills(Set<PartyCadreSkills> partyCadreSkills) {
		this.partyCadreSkills = partyCadreSkills;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "party")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)	
	public Set<PartyTrainingCamps> getPartyTrainingCamps() {
		return partyTrainingCamps;
	}

	public void setPartyTrainingCamps(Set<PartyTrainingCamps> partyTrainingCamps) {
		this.partyTrainingCamps = partyTrainingCamps;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "state_id")
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "party")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)	
	public Set<PartyGallery> getPartyGallery() {
		return partyGallery;
	}

	public void setPartyGallery(Set<PartyGallery> partyGallery) {
		this.partyGallery = partyGallery;
	}
	
	
	
	
	



}