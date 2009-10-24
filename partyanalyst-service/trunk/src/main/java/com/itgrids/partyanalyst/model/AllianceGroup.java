package com.itgrids.partyanalyst.model;

import java.io.Serializable;

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
@Table(name= "alliance_groups")
public class AllianceGroup extends BaseModel implements Serializable {

	private Long allianceGroupId;
	private Party party;
	private ElectionAlliance electionAlliance;
	
	
	public AllianceGroup() {
		super();
	}

	public AllianceGroup(Long alliance_group_id, Party party,
			ElectionAlliance electionAlliance) {
		super();
		this.allianceGroupId = alliance_group_id;
		this.party = party;
		this.electionAlliance = electionAlliance;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="alliance_group_id", unique = true, nullable = false)
	public Long getAllianceGroupId() {
		return allianceGroupId;
	}

	public void setAllianceGroupId(Long alliance_group_id) {
		this.allianceGroupId = alliance_group_id;
	}

	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "party_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		this.party = party;
	}

	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "election_alliance_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ElectionAlliance getElectionAlliance() {
		return electionAlliance;
	}

	public void setElectionAlliance(ElectionAlliance electionAlliance) {
		this.electionAlliance = electionAlliance;
	}
	
	
	
}
