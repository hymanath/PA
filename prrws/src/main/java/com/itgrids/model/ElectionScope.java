package com.itgrids.model;

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

@Entity
@Table(name = "election_scope")
public class ElectionScope{
	
	
	private static final long serialVersionUID = -2853930539938433902L;

	@Id
	@Column(name="election_scope_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long electionScopeId;
	
	@Column(name="election_type_id")
	private Long electionTypeId;
	
	@Column(name="state_id")
	private Long stateId;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "election_type_id", insertable = false, updatable = false)
	private ElectionType electionType;

	public Long getElectionScopeId() {
		return electionScopeId;
	}

	public void setElectionScopeId(Long electionScopeId) {
		this.electionScopeId = electionScopeId;
	}

	public Long getElectionTypeId() {
		return electionTypeId;
	}

	public void setElectionTypeId(Long electionTypeId) {
		this.electionTypeId = electionTypeId;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public ElectionType getElectionType() {
		return electionType;
	}

	public void setElectionType(ElectionType electionType) {
		this.electionType = electionType;
	}
	
	
}
