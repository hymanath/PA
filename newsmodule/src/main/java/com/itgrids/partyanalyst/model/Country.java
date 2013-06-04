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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFoundAction;


/**
 * Country entity. 
 * @author <a href="mailto:shan.javaee@gmail.com">Shan Nagarajan</a>
 */
@Entity
@Table(name = "country")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Country implements java.io.Serializable {

	/**
	 * The Serial Version UID.
	 */
	private static final long serialVersionUID = 2455114946357544846L;
	
	// Fields
	
	private Long countryId;
	private String countryName;
	private String capital;
	private String isoCode;
	private Set<State> states = new HashSet<State>(0);
	//private Set<CensusYear> censusYear = new HashSet<CensusYear>(0);
	//private Set<DelimitationYear> delimitationYear = new HashSet<DelimitationYear>(0);

	// Constructors

	/** default constructor */
	public Country() {
	}

	/** minimal constructor */
	public Country(Long countryId, String countryName) {
		this.countryId = countryId;
		this.countryName = countryName;
	}

	/** full constructor */
	public Country(Long countryId, String countryName, String capital,
			String isoCode, Set<State> states) {
		this.countryId = countryId;
		this.countryName = countryName;
		this.capital = capital;
		this.isoCode = isoCode;
		this.states = states;
	}

	// Property accessors
	@Id
	@Column(name = "country_id", unique = true, nullable = false)
	public Long getCountryId() {
		return this.countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	@Column(name = "country_name", nullable = false, length = 100)
	public String getCountryName() {
		return this.countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	@Column(name = "capital", length = 100)
	public String getCapital() {
		return this.capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	@Column(name = "iso_code", length = 25)
	public String getIsoCode() {
		return this.isoCode;
	}

	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "country")
	public Set<State> getStates() {
		return this.states;
	}

	public void setStates(Set<State> states) {
		this.states = states;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 
	@Override
	public String toString() {
		return new ToStringBuilder(this).appendSuper(super.toString()).append(
				"countryId", countryId).append("countryName", countryName)
				.append("capital", capital).append("isoCode", isoCode).toString();
	}*/
	
	/*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "country")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<CensusYear> getCensusYear() {
		return censusYear;
	}

	public void setCensusYear(Set<CensusYear> censusYear) {
		this.censusYear = censusYear;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "country")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<DelimitationYear> getDelimitationYear() {
		return delimitationYear;
	}

	public void setDelimitationYear(Set<DelimitationYear> delimitationYear) {
		this.delimitationYear = delimitationYear;
	}*/

	
}