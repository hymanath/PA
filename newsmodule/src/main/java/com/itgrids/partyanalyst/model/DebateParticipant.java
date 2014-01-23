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

@Entity
@Table(name = "debate_participant")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DebateParticipant {

	private Integer debateParticipantId;
	private Debate debate;
	private Candidate candidate;
	private Party party;
	private String summary;
	
	
	public DebateParticipant(){}

	@Id
   	@GeneratedValue(strategy = GenerationType.AUTO)
   	@Column(name = "debate_participant_id", unique = true, nullable = false)
	public Integer getDebateParticipantId() {
		return debateParticipantId;
	}


	public void setDebateParticipantId(Integer debateParticipantId) {
		this.debateParticipantId = debateParticipantId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "debate_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Debate getDebate() {
		return debate;
	}


	public void setDebate(Debate debate) {
		this.debate = debate;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "party_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Party getParty() {
		return party;
	}


	public void setParty(Party party) {
		this.party = party;
	}

	@Column(name = "summary")
	public String getSummary() {
		return summary;
	}


	public void setSummary(String summary) {
		this.summary = summary;
	}

	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "candidate_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
}
