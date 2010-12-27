package com.itgrids.partyanalyst.model;

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

@Entity
@Table(name = "hamlet_booth_election")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class HamletBoothElection extends BaseModel{

	private static final long serialVersionUID = 1L;
	private Long hamletBoothElectionId;
	private Hamlet hamlet;
	private BoothConstituencyElection boothConstituencyElection;
	
	public HamletBoothElection (){
		
	}
	
	public HamletBoothElection(Long hamletBoothElectionId){
		this.hamletBoothElectionId = hamletBoothElectionId;
	}

	public HamletBoothElection(Hamlet hamlet,
			BoothConstituencyElection boothConstituencyElection) {
		this.hamlet = hamlet;
		this.boothConstituencyElection = boothConstituencyElection;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "hamlet_booth_election_id", unique = true, nullable = false)
	public Long getHamletBoothElectionId() {
		return hamletBoothElectionId;
	}

	public void setHamletBoothElectionId(Long hamletBoothElectionId) {
		this.hamletBoothElectionId = hamletBoothElectionId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hamlet_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	public Hamlet getHamlet() {
		return hamlet;
	}

	public void setHamlet(Hamlet hamlet) {
		this.hamlet = hamlet;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "booth_constituency_election_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	public BoothConstituencyElection getBoothConstituencyElection() {
		return boothConstituencyElection;
	}

	public void setBoothConstituencyElection(
			BoothConstituencyElection boothConstituencyElection) {
		this.boothConstituencyElection = boothConstituencyElection;
	}
	
		
}
