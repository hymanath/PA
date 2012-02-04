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
 * party_election_result_with_area_type entity. 
 * @author <a href="mailto:kamalakardandu@gmail.com">Kamalakar Dandu</a>
 */

@Entity
@Table(name="party_election_result_with_area_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PartyElectionResultsWithAreaType implements Serializable{

	private static final long serialVersionUID = 2262946389410501500L;
	
	private Long partyElectionResultsWithAreaTypeId;
	private Party party; 
	private Election election; 
	private Long ruralParticipated;
	private Long ruralWon;
	private String ruralVotesGainedPercentage;
	private Long urbanParticipated;
	private Long urbanWon;
	private String urbanVotesGainedPercentage;
	private Long ruralUrbanParticipated;
	private Long ruralUrbanWon;
	private String ruralUrbanVotesGainedPercentage;

	public PartyElectionResultsWithAreaType()
	{}
	
	public PartyElectionResultsWithAreaType(Party party,Election election,Long ruralParticipated,
			Long ruralWon,String ruralVotesGainedPercentage,Long urbanParticipated,Long urbanWon,
			String urbanVotesGainedPercentage,Long ruralUrbanParticipated,Long ruralUrbanWon,String ruralUrbanVotesGainedPercentage)
	{
		this.party = party;
		this.election = election;
		this.ruralParticipated = ruralParticipated;
		this.ruralWon = ruralWon;
		this.ruralVotesGainedPercentage = ruralVotesGainedPercentage;
		this.urbanParticipated = urbanParticipated;
		this.urbanWon = urbanWon;
		this.urbanVotesGainedPercentage = urbanVotesGainedPercentage;
		this.ruralUrbanParticipated = ruralUrbanParticipated;
		this.ruralUrbanWon = ruralUrbanWon;
		this.ruralUrbanVotesGainedPercentage = ruralUrbanVotesGainedPercentage;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="party_election_result_with_area_type_id", unique=true, nullable=false)
	public Long getPartyElectionResultsWithAreaTypeId() {
		return partyElectionResultsWithAreaTypeId;
	}

	public void setPartyElectionResultsWithAreaTypeId(
			Long partyElectionResultsWithAreaTypeId) {
		this.partyElectionResultsWithAreaTypeId = partyElectionResultsWithAreaTypeId;
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

	@Column(name = "rural_participated")
	public Long getRuralParticipated() {
		return ruralParticipated;
	}

	public void setRuralParticipated(Long ruralParticipated) {
		this.ruralParticipated = ruralParticipated;
	}

	@Column(name = "rural_won")
	public Long getRuralWon() {
		return ruralWon;
	}

	public void setRuralWon(Long ruralWon) {
		this.ruralWon = ruralWon;
	}

	@Column(name = "rural_votes_gained_percentage")
	public String getRuralVotesGainedPercentage() {
		return ruralVotesGainedPercentage;
	}

	public void setRuralVotesGainedPercentage(String ruralVotesGainedPercentage) {
		this.ruralVotesGainedPercentage = ruralVotesGainedPercentage;
	}

	@Column(name = "urban_participated")
	public Long getUrbanParticipated() {
		return urbanParticipated;
	}

	public void setUrbanParticipated(Long urbanParticipated) {
		this.urbanParticipated = urbanParticipated;
	}

	@Column(name = "urban_won")
	public Long getUrbanWon() {
		return urbanWon;
	}

	public void setUrbanWon(Long urbanWon) {
		this.urbanWon = urbanWon;
	}

	@Column(name = "urban_votes_gained_percentage")
	public String getUrbanVotesGainedPercentage() {
		return urbanVotesGainedPercentage;
	}

	public void setUrbanVotesGainedPercentage(String urbanVotesGainedPercentage) {
		this.urbanVotesGainedPercentage = urbanVotesGainedPercentage;
	}

	@Column(name = "rural_urban_participated")
	public Long getRuralUrbanParticipated() {
		return ruralUrbanParticipated;
	}

	public void setRuralUrbanParticipated(Long ruralUrbanParticipated) {
		this.ruralUrbanParticipated = ruralUrbanParticipated;
	}

	@Column(name = "rural_urban_won")
	public Long getRuralUrbanWon() {
		return ruralUrbanWon;
	}

	public void setRuralUrbanWon(Long ruralUrbanWon) {
		this.ruralUrbanWon = ruralUrbanWon;
	}

	@Column(name = "rural_urban_votes_gained_percentage")
	public String getRuralUrbanVotesGainedPercentage() {
		return ruralUrbanVotesGainedPercentage;
	}

	public void setRuralUrbanVotesGainedPercentage(
			String ruralUrbanVotesGainedPercentage) {
		this.ruralUrbanVotesGainedPercentage = ruralUrbanVotesGainedPercentage;
	}
	
	
}
