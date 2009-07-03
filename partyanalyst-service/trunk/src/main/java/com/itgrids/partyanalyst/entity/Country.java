/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 3, 2009
 */
package com.itgrids.partyanalyst.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Country entity. 
 * @author <a href="mailto:shan.amrita@gmail.com">Shan Nagarajan</a> 
 */
@Entity
@Table(name = "country", catalog = "party_analyst")
public class Country implements java.io.Serializable {

	/**
	 * The Serial Version UID.
	 */
	private static final long serialVersionUID = -1001384285493215728L;

	// Fields
	private Long countryId;
	private String countryName;
	private String capital;
	private String isoCode;
	private Set<State> states = new HashSet<State>(0);

	// Constructors

	/** 
	 * Default Constructor 
	 */
	public Country() {
	}

	/** 
	 * Minimal Constructor
	 * @param countryId - The Unique Contry Id
	 * @param countryName - The Country Name 
	 */
	public Country(Long countryId, String countryName) {
		this.countryId = countryId;
		this.countryName = countryName;
	}

	/** 
	 * Full Constructor with all the parameter
	 * @param countryId - The Unique Id
	 * @param capital - The Country Capital
	 * @param isoCode - The ISO Code
	 * @param countryName - The Country Full Name
	 * @param states - The States in the Country
	 */
	public Country(Long countryId, String countryName, String capital,
			String isoCode, Set<State> states) {
		this.countryId = countryId;
		this.countryName = countryName;
		this.capital = capital;
		this.isoCode = isoCode;
		this.states = states;
	}

	// Property Accessors
	
	/**
	 * @return - The Unique Country Id.
	 */
	@Id
	@Column(name = "country_id", unique = true, nullable = false)
	public Long getCountryId() {
		return this.countryId;
	}

	/**
	 * @param countryId - The unique country id.
	 */
	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	/**
	 * @return - The Country Full Name.
	 */
	@Column(name = "country_name", nullable = false, length = 100)
	public String getCountryName() {
		return this.countryName;
	}

	/**
	 * @param countryName - The Country Full Name.
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	/**
	 * @return - The Country's Capital
	 */
	@Column(name = "capital", length = 100)
	public String getCapital() {
		return this.capital;
	}

	/**
	 * @param capital - The Country's Capital
	 */
	public void setCapital(String capital) {
		this.capital = capital;
	}

	/**
	 * @return - The Country ISO code
	 */
	@Column(name = "iso_code", length = 25)
	public String getIsoCode() {
		return this.isoCode;
	}

	/**
	 * @param isoCode - The Country ISO Code
	 */
	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}

	/**
	 * @return - The Set of States for this country
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "country")
	public Set<State> getStates() {
		return this.states;
	}

	/**
	 * @param states - The Set of States for this country
	 */
	public void setStates(Set<State> states) {
		this.states = states;
	}

}