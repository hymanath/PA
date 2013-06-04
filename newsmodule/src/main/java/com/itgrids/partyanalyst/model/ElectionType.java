/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 09, 2009
 */
package com.itgrids.partyanalyst.model;

import java.util.HashSet;
import com.itgrids.partyanalyst.model.BaseModel;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * ElectionType entity. 
 * @author <a href="mailto:shan.javaee@gmail.com">Shan Nagarajan</a>
 */
@Entity
@Table(name = "election_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ElectionType extends BaseModel implements java.io.Serializable {

	/**
	 * The Serial Version UID.
	 */
	private static final long serialVersionUID = -3703578759043525036L;
	
	// Fields
	
	private Long electionTypeId;
	private String electionType;
	private String scope;
	private Set<ElectionScope> electionScopes = new HashSet<ElectionScope>(0);

	// Constructors

	/** default constructor */
	public ElectionType() {
	}

	/** minimal constructor */
	public ElectionType(Long electionTypeId) {
		this.electionTypeId = electionTypeId;
	}

	/** full constructor */
	public ElectionType(Long electionTypeId, String electionType, String scope,
			Set<ElectionScope> electionScopes) {
		this.electionTypeId = electionTypeId;
		this.electionType = electionType;
		this.scope = scope;
		this.electionScopes = electionScopes;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "election_type_id", unique = true, nullable = false)
	public Long getElectionTypeId() {
		return this.electionTypeId;
	}

	public void setElectionTypeId(Long electionTypeId) {
		this.electionTypeId = electionTypeId;
	}

	@Column(name = "election_type", length = 25)
	public String getElectionType() {
		return this.electionType;
	}

	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}

	@Column(name = "scope", length = 25)
	public String getScope() {
		return this.scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "electionType")
	public Set<ElectionScope> getElectionScopes() {
		return this.electionScopes;
	}

	public void setElectionScopes(Set<ElectionScope> electionScopes) {
		this.electionScopes = electionScopes;
	}

}