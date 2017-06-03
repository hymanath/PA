package com.itgrids.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "election_type")
public class ElectionType{
	
	
	private static final long serialVersionUID = -2853930539938433902L;

	@Id
	@Column(name="election_type_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long electionTypeId;
	
	@Column(name="election_type")
	private String electionType;
	
	@Column(name="scope")
	private String scope;
	
	@Column(name="designation")
	private String designation;

	public Long getElectionTypeId() {
		return electionTypeId;
	}

	public void setElectionTypeId(Long electionTypeId) {
		this.electionTypeId = electionTypeId;
	}

	public String getElectionType() {
		return electionType;
	}

	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	
}
