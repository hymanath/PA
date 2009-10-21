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
@Table(name = "election_alliance")
public class ElectionAlliance extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long allianceId;
	private Election election;
	private Party party;
	
	public ElectionAlliance(){}
	
	public ElectionAlliance(Long allianceId, Election election, Party party) {
		this.allianceId = allianceId;
		this.election = election;
		this.party = party;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "alliance_id", unique = true, nullable = false)
	public Long getAllianceId() {
		return allianceId;
	}
	
	public void setAllianceId(Long allianceId) {
		this.allianceId = allianceId;
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
	@JoinColumn(name = "party_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Party getParty() {
		return party;
	}
	
	public void setParty(Party party) {
		this.party = party;
	}
}
