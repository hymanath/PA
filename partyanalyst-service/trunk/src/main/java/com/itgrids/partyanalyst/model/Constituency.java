/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 09, 2009
 */
package com.itgrids.partyanalyst.model;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

/**
 * Constituency entity. 
 * @author <a href="mailto:shan.javaee@gmail.com">Shan Nagarajan</a>
 */
@Entity
@Table(name = "constituency")
public class Constituency extends BaseModel implements java.io.Serializable {

	/**
	 * The Serial Version UID.
	 */
	private static final long serialVersionUID = 2770484105252236590L;
	
	// Fields
	
	private Long constituencyId;
	private District district;
	private ElectionScope electionScope;
	private State state;
	private String name;
	private Date startDate;
	private Date deformDate;
	private Long countryId;
	private Set<ConstituencyElection> constituencyElections = new HashSet<ConstituencyElection>(
			0);

	// Constructors

	/** default constructor */
	public Constituency() {
	}

	/** minimal constructor */
	public Constituency(Long constituencyId) {
		this.constituencyId = constituencyId;
	}

	/** full constructor */
	public Constituency(Long constituencyId, District district,
			ElectionScope electionScope, State state, String name,
			Date startDate, Date deformDate, Long countryId,
			Set<ConstituencyElection> constituencyElections) {
		this.constituencyId = constituencyId;
		this.district = district;
		this.electionScope = electionScope;
		this.state = state;
		this.name = name;
		this.startDate = startDate;
		this.deformDate = deformDate;
		this.countryId = countryId;
		this.constituencyElections = constituencyElections;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "constituency_id", unique = true, nullable = false)
	public Long getConstituencyId() {
		return this.constituencyId;
	}

	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "district_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public District getDistrict() {
		return this.district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "election_scope_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ElectionScope getElectionScope() {
		return this.electionScope;
	}

	public void setElectionScope(ElectionScope electionScope) {
		this.electionScope = electionScope;
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

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "start_date", length = 10)
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "deform_date", length = 10)
	public Date getDeformDate() {
		return this.deformDate;
	}

	public void setDeformDate(Date deformDate) {
		this.deformDate = deformDate;
	}

	@Column(name = "country_id")
	public Long getCountryId() {
		return this.countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "constituency")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<ConstituencyElection> getConstituencyElections() {
	return this.constituencyElections;
	}


	public void setConstituencyElections(
			Set<ConstituencyElection> constituencyElections) {
		this.constituencyElections = constituencyElections;
	}



}