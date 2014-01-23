/* 
 * Copyright (c) 2013 TDP PARTY .
 * All Rights Reserved.
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
	//private Set<PartyImportantDates> partyImportantDates = new HashSet<PartyImportantDates>(0);
	//private Set<PartyElectionResult> partyElectionResult = new HashSet<PartyElectionResult>(0);
	private Set<PartyElectionDistrictResult> partyElectionDistrictResult = new HashSet<PartyElectionDistrictResult>(0);
	//private Set<CommentCategoryParty> commentCategoryParty = new HashSet<CommentCategoryParty>(0);
	//private Set<PartyWorkingCommittee> partyWorkingCommittees = new HashSet<PartyWorkingCommittee>(0);
	//private Set<PartyCadreSkills> partyCadreSkills = new HashSet<PartyCadreSkills>(0);
	//private Set<PartyTrainingCamps> partyTrainingCamps = new HashSet<PartyTrainingCamps>(0);
	private Set<PartyGallery> partyGallery = new HashSet<PartyGallery>(0);
	//private Set<PartyUpdatesEmail> partyUpdatesEmails = new HashSet<PartyUpdatesEmail>(0);
	//private Set<MessageToParty> messageToParty = new HashSet<MessageToParty>(0);
	//private Set<PartyElectionResultsWithGenderAnalysis> partyElectionResultsWithGenderAnalysis = new HashSet<PartyElectionResultsWithGenderAnalysis>(0);
	//private Set<PartyPageCustomPages> partyPageCustomPages = new HashSet<PartyPageCustomPages>(0);
	private Set<User> users = new HashSet<User>(0);
	private Set<Candidate> candidates = new HashSet<Candidate>(0);
	//private Set<UserPartyRelation> userPartyRelations = new HashSet<UserPartyRelation>(0);
	//private Set<UserVoterDetails> uservoterdetails = new HashSet<UserVoterDetails>(0);
	private Set<CandidateParty> candidateParty = new HashSet<CandidateParty>(0);
	private Set<PartyFileKeyword> partyFileKeywords = new HashSet<PartyFileKeyword>(0);
	private String isNewsPortal;
	//private Set<CandidatePartyFile> candidatePartyFiles = new HashSet<CandidatePartyFile>(0);
	private Set<DebateParticipant> debateParticipant = new HashSet<DebateParticipant>(0);

	
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
			String partyRecognization, Set<Nomination> nominations) {
		this.partyId = partyId;
		this.longName = longName;
		this.shortName = shortName;
		this.symbol = symbol;
		this.address = address;
		this.comments = comments;
		this.partyRecognization = partyRecognization;
		this.nominations = nominations;
		//this.commentCategoryParty = commentCategoryParty;
	}
	
/*	public Party(Long partyId, String longName, String shortName,
			String symbol, String address, String comments,
			String partyRecognization,
			Set<Nomination> nominations,
			Set<PartyImportantDates> partyImportantDates,Set<PartyElectionDistrictResult> partyElectionDistrictResult,
			Set<CommentCategoryParty> commentCategoryParty,State state) {
		this.partyId = partyId;
		this.longName = longName;
		this.shortName = shortName;
		this.symbol = symbol;
		this.address = address;
		this.comments = comments;
		this.partyRecognization = partyRecognization;	
		this.nominations = nominations;
		//this.partyImportantDates = partyImportantDates;
		//this.partyElectionResult = partyElectionResult;
		this.partyElectionDistrictResult = partyElectionDistrictResult;
		//this.commentCategoryParty = commentCategoryParty;
		this.state = state;
	}
*/
	

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
	
	/*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "party")
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
	}*/
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "party")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<PartyElectionDistrictResult> getPartyElectionDistrictResult() {
		return partyElectionDistrictResult;
	}

	public void setPartyElectionDistrictResult(
			Set<PartyElectionDistrictResult> partyElectionDistrictResult) {
		this.partyElectionDistrictResult = partyElectionDistrictResult;
	}

	/*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "party")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<CommentCategoryParty> getCommentCategoryParty() {
		return commentCategoryParty;
	}

	public void setCommentCategoryParty(
			Set<CommentCategoryParty> commentCategoryParty) {
		this.commentCategoryParty = commentCategoryParty;
	}*/

	/*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "party")
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
*/
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
	
	/*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "party")
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Set<PartyUpdatesEmail> getPartyUpdatesEmails() {
		return partyUpdatesEmails;
	}

	public void setPartyUpdatesEmails(Set<PartyUpdatesEmail> partyUpdatesEmails) {
		this.partyUpdatesEmails = partyUpdatesEmails;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "party")
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Set<MessageToParty> getMessageToParty() {
		return messageToParty;
	}

	public void setMessageToParty(Set<MessageToParty> messageToParty) {
		this.messageToParty = messageToParty;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "party")
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Set<PartyElectionResultsWithGenderAnalysis> getPartyElectionResultsWithGenderAnalysis() {
		return partyElectionResultsWithGenderAnalysis;
	}

	public void setPartyElectionResultsWithGenderAnalysis(
			Set<PartyElectionResultsWithGenderAnalysis> partyElectionResultsWithGenderAnalysis) {
		this.partyElectionResultsWithGenderAnalysis = partyElectionResultsWithGenderAnalysis;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "party")
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Set<PartyPageCustomPages> getPartyPageCustomPages() {
		return partyPageCustomPages;
	}

	public void setPartyPageCustomPages(
			Set<PartyPageCustomPages> partyPageCustomPages) {
		this.partyPageCustomPages = partyPageCustomPages;
	}*/

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "party")
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "party")
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Set<Candidate> getCandidates() {
		return candidates;
	}

	public void setCandidates(Set<Candidate> candidates) {
		this.candidates = candidates;
	}
	
	/*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "party")
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Set<UserPartyRelation> getUserPartyRelations() {
		return userPartyRelations;
	}

	public void setUserPartyRelations(Set<UserPartyRelation> userPartyRelations) {
		this.userPartyRelations = userPartyRelations;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "party")
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Set<UserVoterDetails> getUservoterdetails() {
		return uservoterdetails;
	}

	public void setUservoterdetails(Set<UserVoterDetails> uservoterdetails) {
		this.uservoterdetails = uservoterdetails;
	}
	*/
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "party")
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Set<CandidateParty> getCandidateParty() {
		return candidateParty;
	}

	public void setCandidateParty(Set<CandidateParty> candidateParty) {
		this.candidateParty = candidateParty;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "party")
	public Set<PartyFileKeyword> getPartyFileKeywords() {
		return partyFileKeywords;
	}

	public void setPartyFileKeywords(Set<PartyFileKeyword> partyFileKeywords) {
		this.partyFileKeywords = partyFileKeywords;
	}

	@Column(name = "is_news_portal", length = 2)
	public String getIsNewsPortal() {
		return isNewsPortal;
	}

	public void setIsNewsPortal(String isNewsPortal) {
		this.isNewsPortal = isNewsPortal;
	}
    
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "party")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<DebateParticipant> getDebateParticipant() {
		return debateParticipant;
	}

	public void setDebateParticipant(Set<DebateParticipant> debateParticipant) {
		this.debateParticipant = debateParticipant;
	}

	/*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "party")
	public Set<CandidatePartyFile> getCandidatePartyFiles() {
		return candidatePartyFiles;
	}

	public void setCandidatePartyFiles(Set<CandidatePartyFile> candidatePartyFiles) {
		this.candidatePartyFiles = candidatePartyFiles;
	}*/
	
	


}