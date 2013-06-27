/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 3, 2009
 */
package com.itgrids.partyanalyst.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * State entity. 
 * @author <a href="mailto:shan.amrita@gmail.com">Shan Nagarajan</a>
 */
@Entity
@Table(name = "state", catalog = "party_analyst")
public class State implements java.io.Serializable {

	// Fields

	/**
	 * The Serial Version UID.
	 */
	private static final long serialVersionUID = 3262036959797739995L;
	
	private Long stateId;
	private Country country;
	private String stateName;
	private String adminCapital;
	private String legisCapital;
	private String judiciaryCapital;
	private Date yearEstablished;
	private String stateLanguage;
	private String stateSymbol;
	private String stateSong;
	private String stateAnimal;
	private String stateBird;
	private String stateTree;
	private String stateSport;
	private String stateDance;
	private String stateFlower;
	private String isoCode;
	//private Set<EPaper> epaper = new HashSet<EPaper>(0);
	
	//private Set<StateRegion> stateRegion = new HashSet<StateRegion>(0);
	
	// Constructors

	/** 
	 * Default Constructor 
	 */
	public State() {
	}

	/** 
	 * Minimal Constructor
	 * @param stateId - The Unique State Id
	 * @param stateName - The State Name 
	 */
	public State(Long stateId, String stateName) {
		this.stateId = stateId;
		this.stateName = stateName;
	}

	/** Full Constructor with all parameter*/
	public State(Long stateId, Country country, String stateName,
			String adminCapital, String legisCapital, String judiciaryCapital,
			Date yearEstablished, String stateLanguage, String stateSymbol,
			String stateSong, String stateAnimal, String stateBird,
			String stateTree, String stateSport, String stateDance,
			String stateFlower, String isoCode){//,Set<EPaper> epaper) {
		this.stateId = stateId;
		this.country = country;
		this.stateName = stateName;
		this.adminCapital = adminCapital;
		this.legisCapital = legisCapital;
		this.judiciaryCapital = judiciaryCapital;
		this.yearEstablished = yearEstablished;
		this.stateLanguage = stateLanguage;
		this.stateSymbol = stateSymbol;
		this.stateSong = stateSong;
		this.stateAnimal = stateAnimal;
		this.stateBird = stateBird;
		this.stateTree = stateTree;
		this.stateSport = stateSport;
		this.stateDance = stateDance;
		this.stateFlower = stateFlower;
		this.isoCode = isoCode;
		//this.epaper = epaper;
	//	this.stateRegion =stateRegion;
		
	}

	// Property Accessors
	

	/**
	 * @return - The Unique State Id.
	 */
	@Id
	@Column(name = "state_id", unique = true, nullable = false)
	public Long getStateId() {
		return this.stateId;
	}

	/**
	 * @param stateId - The Unique State Id.
	 */
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	/**
	 * @return - The Country Object belongs to given State
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country_id")
	public Country getCountry() {
		return this.country;
	}

	/**
	 * @param country - The Country Object belongs to the State
	 */
	public void setCountry(Country country) {
		this.country = country;
	}

	/**
	 * @return - The State Full Name
	 */
	@Column(name = "state_name", nullable = false, length = 100)
	public String getStateName() {
		return this.stateName;
	}

	/**
	 * @param stateName - The State Full Name.
	 */
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	/**
	 * @return
	 */
	@Column(name = "admin_capital", length = 100)
	public String getAdminCapital() {
		return this.adminCapital;
	}

	public void setAdminCapital(String adminCapital) {
		this.adminCapital = adminCapital;
	}

	@Column(name = "legis_capital", length = 100)
	public String getLegisCapital() {
		return this.legisCapital;
	}

	public void setLegisCapital(String legisCapital) {
		this.legisCapital = legisCapital;
	}

	@Column(name = "judiciary_capital", length = 100)
	public String getJudiciaryCapital() {
		return this.judiciaryCapital;
	}

	public void setJudiciaryCapital(String judiciaryCapital) {
		this.judiciaryCapital = judiciaryCapital;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "year_established", length = 10)
	public Date getYearEstablished() {
		return this.yearEstablished;
	}

	public void setYearEstablished(Date yearEstablished) {
		this.yearEstablished = yearEstablished;
	}

	@Column(name = "state_language", length = 50)
	public String getStateLanguage() {
		return this.stateLanguage;
	}

	public void setStateLanguage(String stateLanguage) {
		this.stateLanguage = stateLanguage;
	}

	@Column(name = "state_symbol", length = 50)
	public String getStateSymbol() {
		return this.stateSymbol;
	}

	public void setStateSymbol(String stateSymbol) {
		this.stateSymbol = stateSymbol;
	}

	@Column(name = "state_song", length = 50)
	public String getStateSong() {
		return this.stateSong;
	}

	public void setStateSong(String stateSong) {
		this.stateSong = stateSong;
	}

	@Column(name = "state_animal", length = 50)
	public String getStateAnimal() {
		return this.stateAnimal;
	}

	public void setStateAnimal(String stateAnimal) {
		this.stateAnimal = stateAnimal;
	}

	@Column(name = "state_bird", length = 50)
	public String getStateBird() {
		return this.stateBird;
	}

	public void setStateBird(String stateBird) {
		this.stateBird = stateBird;
	}

	@Column(name = "state_tree", length = 50)
	public String getStateTree() {
		return this.stateTree;
	}

	/**
	 * @param stateTree - The State Tree
	 */
	public void setStateTree(String stateTree) {
		this.stateTree = stateTree;
	}

	/**
	 * @return - The State Sport
	 */
	@Column(name = "state_sport", length = 50)
	public String getStateSport() {
		return this.stateSport;
	}

	/**
	 * @param stateSport - The State Sport
	 */
	public void setStateSport(String stateSport) {
		this.stateSport = stateSport;
	}

	/**
	 * @return - The State Dance
	 */
	@Column(name = "state_dance", length = 50)
	public String getStateDance() {
		return this.stateDance;
	}

	/**
	 * @param stateDance - The State Dance
	 */
	public void setStateDance(String stateDance) {
		this.stateDance = stateDance;
	}

	/**
	 * @return - The State Flower
	 */
	@Column(name = "state_flower", length = 50)
	public String getStateFlower() {
		return this.stateFlower;
	}

	/**
	 * @param stateFlower - The State Flower
	 */
	public void setStateFlower(String stateFlower) {
		this.stateFlower = stateFlower;
	}

	/**
	 * @return - The State ISO Code.
	 */
	@Column(name = "iso_code", length = 25)
	public String getIsoCode() {
		return this.isoCode;
	}

	/**
	 * @param isoCode - The State ISO Code.
	 */
	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}
	/*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "state")
	public Set<EPaper> getEpaper() {
		return epaper;
	}

	public void setEpaper(Set<EPaper> epaper) {
		this.epaper = epaper;
	}
*/
	/*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "state")
	public Set<StateRegion> getStateRegion() {
		return stateRegion;
	}

	public void setStateRegion(Set<StateRegion> stateRegion) {
		this.stateRegion = stateRegion;
	}*/
}