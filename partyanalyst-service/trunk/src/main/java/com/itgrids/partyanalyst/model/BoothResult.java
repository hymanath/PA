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

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "booth_result")
public class BoothResult extends BaseModel implements java.io.Serializable{

	private static final long serialVersionUID = 1L;

	private Long boothResultId;
	private Double votesEarned;
	private Nomination nomination;
	private BoothConstituencyElection boothConstituencyElection;
	
	public BoothResult(){
	}
		
	public BoothResult(Long boothResultId){
		this.boothResultId = boothResultId;
	}

	public BoothResult(Double votesEarned,
			Nomination nomination,
			BoothConstituencyElection boothConstituencyElection) {
		this.votesEarned = votesEarned;
		this.nomination = nomination;
		this.boothConstituencyElection = boothConstituencyElection;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "booth_result_id", unique = true, nullable = false)
	public Long getBoothResultId() {
		return boothResultId;
	}

	public void setBoothResultId(Long boothResultId) {
		this.boothResultId = boothResultId;
	}

	@Column(name = "votes_earned", precision = 10, scale = 0)
	public Double getVotesEarned() {
		return votesEarned;
	}

	public void setVotesEarned(Double votesEarned) {
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
	
	
}
