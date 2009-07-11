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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * ElectionScope entity. 
 * @author <a href="mailto:shan.javaee@gmail.com">Shan Nagarajan</a>
 */
@Entity
@Table(name = "election_scope")
public class ElectionScope implements java.io.Serializable {

	/**
	 * The Serial Version UID.
	 */
	private static final long serialVersionUID = 4621970188219643414L;
	
	// Fields
	
	private Long electionSocpeId;
	private ElectionType electionType;
	private Long countryStateId;
	private Set<Election> elections = new HashSet<Election>(0);
	private Set<Constituency> constituencies = new HashSet<Constituency>(0);

	// Constructors

	/** default constructor */
	public ElectionScope() {
	}

	/** minimal constructor */
	public ElectionScope(Long electionSocpeId) {
		this.electionSocpeId = electionSocpeId;
	}

	/** full constructor */
	public ElectionScope(Long electionSocpeId, ElectionType electionType,
			Long countryStateId, Set<Election> elections,
			Set<Constituency> constituencies) {
		this.electionSocpeId = electionSocpeId;
		this.electionType = electionType;
		this.countryStateId = countryStateId;
		this.elections = elections;
		this.constituencies = constituencies;
	}

	// Property accessors
	@Id
	@Column(name = "election_socpe_id", unique = true, nullable = false)
	public Long getElectionSocpeId() {
		return this.electionSocpeId;
	}

	public void setElectionSocpeId(Long electionSocpeId) {
		this.electionSocpeId = electionSocpeId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "election_type_id")
	public ElectionType getElectionType() {
		return this.electionType;
	}

	public void setElectionType(ElectionType electionType) {
		this.electionType = electionType;
	}

	@Column(name = "country_state_id")
	public Long getCountryStateId() {
		return this.countryStateId;
	}

	public void setCountryStateId(Long countryStateId) {
		this.countryStateId = countryStateId;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).appendSuper(super.toString()).append(
				"electionSocpeId", electionSocpeId).append("electionType",
				electionType).append("countryStateId", countryStateId).append(
				"elections", elections)
				.append("constituencies", constituencies).toString();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof ElectionScope))
			return false;
		ElectionScope castOther = (ElectionScope) other;
		return new EqualsBuilder().append(electionSocpeId,
				castOther.electionSocpeId).append(electionType,
				castOther.electionType).append(countryStateId,
				castOther.countryStateId)
				.append(elections, castOther.elections).append(constituencies,
						castOther.constituencies).isEquals();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(electionSocpeId).append(
				electionType).append(countryStateId).append(elections).append(
				constituencies).toHashCode();
	}

}