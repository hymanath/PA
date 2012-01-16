package com.itgrids.partyanalyst.model;

import java.io.Serializable;

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
 * party_election_result_with_gender_analysis entity. 
 * @author <a href="mailto:kamalakardandu@gmail.com">Kamalakar Dandu</a>
 */


@Entity
@Table(name="party_election_result_with_gender_analysis")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PartyElectionResultsWithGenderAnalysis implements Serializable{

	private static final long serialVersionUID = -2286161777505948883L;
	
	private Long partyElectionResultsWithGenderAnalysisId;
	private Party party;
	private Election election;
	private Long maleParticipated;
	private Long maleWon;
	private String maleCandidateVotesGainedPercetage; 
	private Long femaleParticipated;
	private Long femaleWon;
	private String femaleCandidateVotesGainedPercetage; 
	
	public PartyElectionResultsWithGenderAnalysis()
	{}
	
	public PartyElectionResultsWithGenderAnalysis(Party party,Election election,Long maleParticipated,
			Long maleWon,String maleCandidateVotesGainedPercetage,Long femaleParticipated,Long femaleWon,
			String femaleCandidateVotesGainedPercetage)
	{
		this.party = party;
		this.election = election;
		this.maleParticipated = maleParticipated;
		this.maleWon = maleWon;
		this.maleCandidateVotesGainedPercetage = maleCandidateVotesGainedPercetage;
		this.femaleParticipated = femaleParticipated;
		this.femaleWon = femaleWon;
		this.femaleCandidateVotesGainedPercetage = femaleCandidateVotesGainedPercetage;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="party_election_result_with_gender_analysis_id", unique=true, nullable=false)
	public Long getPartyElectionResultsWithGenderAnalysisId() {
		return partyElectionResultsWithGenderAnalysisId;
	}

	public void setPartyElectionResultsWithGenderAnalysisId(
			Long partyElectionResultsWithGenderAnalysisId) {
		this.partyElectionResultsWithGenderAnalysisId = partyElectionResultsWithGenderAnalysisId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="party_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		this.party = party;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="election_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Election getElection() {
		return election;
	}

	public void setElection(Election election) {
		this.election = election;
	}

	@Column(name = "male_participated")
	public Long getMaleParticipated() {
		return maleParticipated;
	}

	public void setMaleParticipated(Long maleParticipated) {
		this.maleParticipated = maleParticipated;
	}

	@Column(name = "male_won")
	public Long getMaleWon() {
		return maleWon;
	}

	public void setMaleWon(Long maleWon) {
		this.maleWon = maleWon;
	}

	@Column(name = "male_candidates_votes_gained_percentage")
	public String getMaleCandidateVotesGainedPercetage() {
		return maleCandidateVotesGainedPercetage;
	}

	public void setMaleCandidateVotesGainedPercetage(
			String maleCandidateVotesGainedPercetage) {
		this.maleCandidateVotesGainedPercetage = maleCandidateVotesGainedPercetage;
	}

	@Column(name = "female_participated")
	public Long getFemaleParticipated() {
		return femaleParticipated;
	}

	public void setFemaleParticipated(Long femaleParticipated) {
		this.femaleParticipated = femaleParticipated;
	}

	@Column(name = "female_won")
	public Long getFemaleWon() {
		return femaleWon;
	}

	public void setFemaleWon(Long femaleWon) {
		this.femaleWon = femaleWon;
	}

	@Column(name = "female_candidates_votes_gained_percentage")
	public String getFemaleCandidateVotesGainedPercetage() {
		return femaleCandidateVotesGainedPercetage;
	}

	public void setFemaleCandidateVotesGainedPercetage(
			String femaleCandidateVotesGainedPercetage) {
		this.femaleCandidateVotesGainedPercetage = femaleCandidateVotesGainedPercetage;
	}
	
	

}
