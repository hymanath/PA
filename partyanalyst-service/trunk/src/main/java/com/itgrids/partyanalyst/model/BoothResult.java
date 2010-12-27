package com.itgrids.partyanalyst.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;
@Entity
@Table(name = "booth_result")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BoothResult extends BaseModel implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long boothResultId;
	private BoothConstituencyElection boothConstituencyElection;
	private Long validVotes;
	private Long rejectedVotes;
	private Long tenderedVotes;
	
	public BoothResult(){
		
	}
	
	public BoothResult(Long boothResultId){
		this.boothResultId = boothResultId;
	}

	public BoothResult(BoothConstituencyElection boothConstituencyElection,
			Long validVotes, Long rejectedVotes, Long tenderedVotes) {
		this.boothConstituencyElection = boothConstituencyElection;
		this.validVotes = validVotes;
		this.rejectedVotes = rejectedVotes;
		this.tenderedVotes = tenderedVotes;
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

	@OneToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
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

	@Column(name = "valid_votes")
	public Long getValidVotes() {
		return validVotes;
	}

	public void setValidVotes(Long validVotes) {
		this.validVotes = validVotes;
	}

	@Column(name = "rejected_votes")
	public Long getRejectedVotes() {
		return rejectedVotes;
	}

	public void setRejectedVotes(Long rejectedVotes) {
		this.rejectedVotes = rejectedVotes;
	}

	@Column(name = "tendered_votes")
	public Long getTenderedVotes() {
		return tenderedVotes;
	}

	public void setTenderedVotes(Long tenderedVotes) {
		this.tenderedVotes = tenderedVotes;
	}
	
}
