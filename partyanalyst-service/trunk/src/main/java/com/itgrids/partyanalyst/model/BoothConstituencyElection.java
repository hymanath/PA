package com.itgrids.partyanalyst.model;

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

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "booth_constituency_election")
public class BoothConstituencyElection extends BaseModel implements java.io.Serializable{

	private static final long serialVersionUID = 1L;

	private Long boothConstituencyElectionId;
	private Booth booth;
	private ConstituencyElection constituencyElection;
	private Set<BoothResult> boothResults;
	
	public BoothConstituencyElection(){
		
	}
	
	public BoothConstituencyElection(Long boothConstituencyElectionId){
		this.boothConstituencyElectionId = boothConstituencyElectionId;
	}

	public BoothConstituencyElection(Booth booth, ConstituencyElection constituencyElection,
			Set<BoothResult> boothResults) {
		this.booth = booth;
		this.constituencyElection = constituencyElection;
		this.boothResults = boothResults;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "boothConstituencyElection")
	public Set<BoothResult> getBoothResults() {
		return boothResults;
	}

	public void setBoothResults(Set<BoothResult> boothResults) {
		this.boothResults = boothResults;
	}
		
}
