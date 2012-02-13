/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 09, 2009
 */
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
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFoundAction;

/**
 * ElectionScope entity. 
 * @author <a href="mailto:shan.javaee@gmail.com">Shan Nagarajan</a>
 */
@Entity
@Table(name = "election_scope")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ElectionScope extends BaseModel implements java.io.Serializable {

	/**
	 * The Serial Version UID.
	 */
	private static final long serialVersionUID = 4621970188219643414L;
	
	// Fields
	
	private Long electionScopeId;
	private ElectionType electionType;
	private State state;
	private Country country;
	private Set<Election> elections = new HashSet<Election>(0);
	private Set<Constituency> constituencies = new HashSet<Constituency>(0);
	private Set<MinistryScope> ministryScopes = new HashSet<MinistryScope>(0);

	// Constructors

	/** default constructor */
	public ElectionScope() {
	}

	/** minimal constructor */
	public ElectionScope(Long electionScopeId) {
		this.electionScopeId = electionScopeId;
	}

	/** full constructor */
	public ElectionScope(Long electionScopeId, ElectionType electionType,
			State state, Country country, Set<Election> elections,
			Set<Constituency> constituencies) {
		super();
		this.electionScopeId = electionScopeId;
		this.electionType = electionType;
		this.state = state;
		this.country = country;
		this.elections = elections;
		this.constituencies = constituencies;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "election_scope_id", unique = true, nullable = false)
	public Long getElectionScopeId() {
		return this.electionScopeId;
	}

	public void setElectionScopeId(Long electionScopeId) {
		this.electionScopeId = electionScopeId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "election_type_id")	
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ElectionType getElectionType() {
		return this.electionType;
	}

	public void setElectionType(ElectionType electionType) {
		this.electionType = electionType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "state_id")
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country_id")
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "electionScope")
	public Set<Election> getElections() {
		return this.elections;
	}

	public void setElections(Set<Election> elections) {
		this.elections = elections;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "electionScope")
	public Set<Constituency> getConstituencies() {
		return this.constituencies;
	}

	public void setConstituencies(Set<Constituency> constituencies) {
		this.constituencies = constituencies;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "electionScope")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<MinistryScope> getMinistryScopes() {
		return ministryScopes;
	}

	public void setMinistryScopes(Set<MinistryScope> ministryScopes) {
		this.ministryScopes = ministryScopes;
	}

}