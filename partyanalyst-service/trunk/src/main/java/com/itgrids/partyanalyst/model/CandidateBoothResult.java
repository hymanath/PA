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
@Table(name = "candidate_booth_result")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CandidateBoothResult extends BaseModel implements java.io.Serializable{

	private static final long serialVersionUID = 1L;

	private Long candidateBoothResultId;
	private Long votesEarned;
	private Nomination nomination;
	private BoothConstituencyElection boothConstituencyElection;
	private String percentage;
	
	public CandidateBoothResult(){
	}
		
	public CandidateBoothResult(Long candidateBoothResultId){
		this.candidateBoothResultId = candidateBoothResultId;
	}

	public CandidateBoothResult(Long votesEarned,
			Nomination nomination, String percentage,
			BoothConstituencyElection boothConstituencyElection) {
		this.votesEarned = votesEarned;
		this.nomination = nomination;
		this.boothConstituencyElection = boothConstituencyElection;
		this.percentage = percentage;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "candidate_booth_result_id", unique = true, nullable = false)
	public Long getcandidateBoothResultId() {
		return candidateBoothResultId;
	}

	public void setcandidateBoothResultId(Long candidateBoothResultId) {
		this.candidateBoothResultId = candidateBoothResultId;
	}

	@Column(name = "votes_earned")
	public Long getVotesEarned() {
		return votesEarned;
	}

	public void setVotesEarned(Long votesEarned) {
		this.votesEarned = votesEarned;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "nomination_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Nomination getNomination() {
		return nomination;
	}

	public void setNomination(Nomination nomination) {
		this.nomination = nomination;
	}

	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "booth_constituency_election_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public BoothConstituencyElection getBoothConstituencyElection() {
		return boothConstituencyElection;
	}

	public void setBoothConstituencyElection(
			BoothConstituencyElection boothConstituencyElection) {
		this.boothConstituencyElection = boothConstituencyElection;
	}

	@Column(name = "percentage", length = 10)
	public String getPercentage() {
		return percentage;
	}

	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}
	
}
