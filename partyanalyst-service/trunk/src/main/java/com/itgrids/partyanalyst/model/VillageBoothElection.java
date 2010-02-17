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
@Entity(name = "village_booth_election")
public class VillageBoothElection extends BaseModel{

	private Long villageBoothElectionId;
	private BoothConstituencyElection boothConstituencyElection;
	private Hamlet hamlet;
	private Township township;
	
	public VillageBoothElection(){
		
	}
	
	public VillageBoothElection(Long villageBoothElectionId){
		this.villageBoothElectionId = villageBoothElectionId;
	}
	
	public VillageBoothElection(BoothConstituencyElection boothConstituencyElection,
			Hamlet hamlet, Township township){
		this.boothConstituencyElection = boothConstituencyElection;
		this.hamlet = hamlet;
		this.township = township;
	}

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "village_booth_election_id",  unique = true, nullable = false)
	public Long getVillageBoothElectionId() {
		return villageBoothElectionId;
	}

	public void setVillageBoothElectionId(Long villageBoothElectionId) {
		this.villageBoothElectionId = villageBoothElectionId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hamlet_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Hamlet getHamlet() {
		return hamlet;
	}

	public void setHamlet(Hamlet hamlet) {
		this.hamlet = hamlet;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "township_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Township getTownship() {
		return township;
	}

	public void setTownship(Township township) {
		this.township = township;
	}
	
	
}
