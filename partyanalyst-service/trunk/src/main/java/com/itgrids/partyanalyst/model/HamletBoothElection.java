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

@Table
@Entity(name = "hamlet_booth_election")
public class HamletBoothElection extends BaseModel{

	private Long hamletBoothElectionId;
	private BoothConstituencyElection boothConstituencyElection;
	private Hamlet hamlet;
	
	public HamletBoothElection(){
		
	}
	
	public HamletBoothElection(Long hamletBoothElectionId){
		this.hamletBoothElectionId = hamletBoothElectionId;
	}
	
	public HamletBoothElection(BoothConstituencyElection boothConstituencyElection, Hamlet hamlet) {
		this.boothConstituencyElection = boothConstituencyElection;
		this.hamlet = hamlet;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "hamlet_booth_election_id",  unique = true, nullable = false)
	public Long getHamletBoothElectionId() {
		return hamletBoothElectionId;
	}

	public void setHamletBoothElectionId(Long hamletBoothElectionId) {
		this.hamletBoothElectionId = hamletBoothElectionId;
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

	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "hamlet_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Hamlet getHamlet() {
		return hamlet;
	}

	public void setHamlet(Hamlet hamlet) {
		this.hamlet = hamlet;
	}
	
	
	
	
}
