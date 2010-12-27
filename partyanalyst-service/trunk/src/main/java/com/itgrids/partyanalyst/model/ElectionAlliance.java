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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "election_alliances")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ElectionAlliance extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long electionAllianceId;
	private Election election;
	private Group group;
	private State state;
	
	public ElectionAlliance(){}
	
	public ElectionAlliance(Long allianceId, Election election, Group group,State state) {
		this.electionAllianceId = allianceId;
		this.election = election;
		this.group = group;
		this.state = state;
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
	@JoinColumn(name = "group_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Group getGroup() {
		return group;
	}
	
	public void setGroup(Group group) {
		this.group = group;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "state_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)

	public State getState() {
		return this.state;
	}

	public void setState(State state) {
		this.state = state;
	}


}
