package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "village_booth_election")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VillageBoothElection extends BaseModel implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long villageBoothElectionId;
	private BoothConstituencyElection boothConstituencyElection;
	private Township township;
	
	public VillageBoothElection(){
		
	}
	
	public VillageBoothElection(Long villageBoothElectionId){
		this.villageBoothElectionId = villageBoothElectionId;
	}
	
	public VillageBoothElection(Township township, BoothConstituencyElection boothConstituencyElection){
		this.boothConstituencyElection = boothConstituencyElection;
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

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "booth_constituency_election_id")
	public BoothConstituencyElection getBoothConstituencyElection() {
		return boothConstituencyElection;
	}

	public void setBoothConstituencyElection(
			BoothConstituencyElection boothConstituencyElection) {
		this.boothConstituencyElection = boothConstituencyElection;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "township_id")
	public Township getTownship() {
		return township;
	}

	public void setTownship(Township township) {
		this.township = township;
	}
	
	
}
