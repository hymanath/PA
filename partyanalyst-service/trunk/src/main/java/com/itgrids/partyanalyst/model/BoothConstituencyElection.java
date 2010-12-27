package com.itgrids.partyanalyst.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "booth_constituency_election")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BoothConstituencyElection extends BaseModel implements java.io.Serializable{

	private static final long serialVersionUID = 1L;

	private Long boothConstituencyElectionId;
	private Booth booth;
	private ConstituencyElection constituencyElection;
	private BoothResult boothResult;
	private Set<CandidateBoothResult> candidateBoothResults = new HashSet<CandidateBoothResult>(0);
	private VillageBoothElection villageBoothElection;
	private Set<HamletBoothElection> hamletBoothElections = new HashSet<HamletBoothElection>(0);
	private Constituency mappedConstituency;
 	
	public BoothConstituencyElection(){
		
	}
	
	public BoothConstituencyElection(Long boothConstituencyElectionId){
		this.boothConstituencyElectionId = boothConstituencyElectionId;
	}

	public BoothConstituencyElection(Booth booth, ConstituencyElection constituencyElection,
			BoothResult boothResult,Set<CandidateBoothResult> candidateBoothResults, Constituency mappedConstituency) {
		this.booth = booth;
		this.constituencyElection = constituencyElection;
		this.boothResult = boothResult;
		this.candidateBoothResults = candidateBoothResults;
		this.mappedConstituency = mappedConstituency;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "booth_constituency_election_id", unique = true, nullable = false)
	public Long getBoothConstituencyElectionId() {
		return boothConstituencyElectionId;
	}

	public void setBoothConstituencyElectionId(Long boothConstituencyElectionId) {
		this.boothConstituencyElectionId = boothConstituencyElectionId;
	}

	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "booth_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Booth getBooth() {
		return booth;
	}

	public void setBooth(Booth booth) {
		this.booth = booth;
	}

	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "consti_elec_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ConstituencyElection getConstituencyElection() {
		return constituencyElection;
	}

	public void setConstituencyElection(ConstituencyElection constituencyElection) {
		this.constituencyElection = constituencyElection;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "boothConstituencyElection")
	public BoothResult getBoothResult() {
		return boothResult;
	}

	public void setBoothResult(BoothResult boothResult) {
		this.boothResult = boothResult;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "boothConstituencyElection")
	public Set<CandidateBoothResult> getCandidateBoothResults() {
		return candidateBoothResults;
	}

	public void setCandidateBoothResults(
			Set<CandidateBoothResult> candidateBoothResults) {
		this.candidateBoothResults = candidateBoothResults;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "boothConstituencyElection")
	public VillageBoothElection getVillageBoothElection() {
		return villageBoothElection;
	}

	public void setVillageBoothElection(VillageBoothElection villageBoothElection) {
		this.villageBoothElection = villageBoothElection;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "boothConstituencyElection")
	public Set<HamletBoothElection> getHamletBoothElections() {
		return hamletBoothElections;
	}

	public void setHamletBoothElections(
			Set<HamletBoothElection> hamletBoothElections) {
		this.hamletBoothElections = hamletBoothElections;
	}

	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "mapped_constituency_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Constituency getMappedConstituency() {
		return mappedConstituency;
	}

	public void setMappedConstituency(Constituency mappedConstituency) {
		this.mappedConstituency = mappedConstituency;
	}

}
