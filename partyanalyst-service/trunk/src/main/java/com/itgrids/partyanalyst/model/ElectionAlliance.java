package com.itgrids.partyanalyst.model;

import java.io.Serializable;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "election_alliances")
public class ElectionAlliance extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long electionAllianceId;
	private Election election;
	private AllianceGroup allianceGroup;
	
	public ElectionAlliance(){}
	
	public ElectionAlliance(Long allianceId, Election election, AllianceGroup allianceGroup) {
		this.electionAllianceId = allianceId;
		this.election = election;
		this.allianceGroup = allianceGroup;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "election_alliance_id", unique = true, nullable = false)
	public Long getElectionAllianceId() {
		return electionAllianceId;
	}
	
	public void setElectionAllianceId(Long allianceId) {
		this.electionAllianceId = allianceId;
	}
	
	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "election_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Election getElection() {
		return election;
	}
	
	public void setElection(Election election) {
		this.election = election;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "alliance_group_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AllianceGroup getAllianceGroup() {
		return allianceGroup;
	}
	
	public void setAllianceGroup(AllianceGroup allianceGroup) {
		this.allianceGroup = allianceGroup;
	}

}
