/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 09, 2009
 */
package com.itgrids.partyanalyst.model;

import java.util.Date;
import com.itgrids.partyanalyst.model.BaseModel;

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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFoundAction;

/**
 * State entity. 
 * @author <a href="mailto:shan.javaee@gmail.com">Shan Nagarajan</a>
 */
@Entity
@Table(name = "state")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class State implements java.io.Serializable {

	/**
	 * The Serial Version UID. 
	 */
	private static final long serialVersionUID = -3464096627699145332L;
	
	// Fields
	
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
	private Long stateCode;
	private Date startDate;
	private Date deformDate;
	private String adminControl;
	
	
	private Set<Constituency> constituencies = new HashSet<Constituency>(0);
	private Set<District> districts = new HashSet<District>(0);
	private Set<PartyElectionDistrictResult> partyElectionDistrictResult = new HashSet<PartyElectionDistrictResult>(0);
	private Set<Party> party = new HashSet<Party>(0);
	//private Set<LocalGroupRegion> localGroupRegion = new HashSet<LocalGroupRegion>(0);
	//private Set<ModuleRegionScopes> moduleRegionScopes = new HashSet<ModuleRegionScopes>(0);
	//private Set<ProblemSourceScope> problemSourceScope = new HashSet<ProblemSourceScope>(0);
	//private Set<StateRegion> stateRegion = new HashSet<StateRegion>(0);
	private Set<User> users = new HashSet<User>(0);
	//private Set<CasteState> casteState = new HashSet<CasteState>(0);
	
	// Constructors

	/** default constructor */
	public State() {
	}

	/** minimal constructor */
	public State(Long stateId, String stateName) {
		this.stateId = stateId;
		this.stateName = stateName;
	}/*

	*//** full constructor *//*
	public State(Long stateId, Country country, String stateName,
			String adminCapital, String legisCapital, String judiciaryCapital,
			Date yearEstablished, String stateLanguage, String stateSymbol,
			String stateSong, String stateAnimal, String stateBird,
			String stateTree, String stateSport, String stateDance,
			String stateFlower, String isoCode, Long stateCode, Date startDate,
			Date deformDate, Set<Constituency> constituencies,
			Set<District> districts,Set<PartyElectionDistrictResult> partyElectionDistrictResult,
			Set<Party> party,Set<LocalGroupRegion> localGroupRegion) {
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
		this.stateCode = stateCode;
		this.startDate = startDate;
		this.deformDate = deformDate;
		this.constituencies = constituencies;
		this.districts = districts;
		this.partyElectionDistrictResult = partyElectionDistrictResult;
		this.party = party;
		this.localGroupRegion = localGroupRegion;
	}
*/

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "state_id", unique = true, nullable = false)
	public Long getStateId() {
		return this.stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country_id")
	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Column(name = "state_name", nullable = false, length = 100)
	public String getStateName() {
		return this.stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

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

	public void setStateTree(String stateTree) {
		this.stateTree = stateTree;
	}

	@Column(name = "state_sport", length = 50)
	public String getStateSport() {
		return this.stateSport;
	}

	public void setStateSport(String stateSport) {
		this.stateSport = stateSport;
	}

	@Column(name = "state_dance", length = 50)
	public String getStateDance() {
		return this.stateDance;
	}

	public void setStateDance(String stateDance) {
		this.stateDance = stateDance;
	}

	@Column(name = "state_flower", length = 50)
	public String getStateFlower() {
		return this.stateFlower;
	}

	public void setStateFlower(String stateFlower) {
		this.stateFlower = stateFlower;
	}

	@Column(name = "iso_code", length = 25)
	public String getIsoCode() {
		return this.isoCode;
	}

	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}

	@Column(name = "state_code")
	public Long getStateCode() {
		return this.stateCode;
	}

	public void setStateCode(Long stateCode) {
		this.stateCode = stateCode;
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

	@Column(name = "admin_control")
	public String getAdminControl() {
		return adminControl;
	}

	public void setAdminControl(String adminControl) {
		this.adminControl = adminControl;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "state")
	public Set<Constituency> getConstituencies() {
		return this.constituencies;
	}

	public void setConstituencies(Set<Constituency> constituencies) {
		this.constituencies = constituencies;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "state")
	public Set<District> getDistricts() {
		return this.districts;
	}

	public void setDistricts(Set<District> districts) {
		this.districts = districts;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "state")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<PartyElectionDistrictResult> getPartyElectionDistrictResult() {
		return partyElectionDistrictResult;
	}

	public void setPartyElectionDistrictResult(
			Set<PartyElectionDistrictResult> partyElectionDistrictResult) {
		this.partyElectionDistrictResult = partyElectionDistrictResult;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "state")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<Party> getParty() {
		return party;
	}

	public void setParty(Set<Party> party) {
		this.party = party;
	}
	
	
/*
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "state")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<CasteState> getCasteState() {
		return casteState;
	}

	public void setCasteState(Set<CasteState> casteState) {
		this.casteState = casteState;
	}
	
	
	
	
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "state")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<LocalGroupRegion> getLocalGroupRegion() {
		return localGroupRegion;
	}

	public void setLocalGroupRegion(Set<LocalGroupRegion> localGroupRegion) {
		this.localGroupRegion = localGroupRegion;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "state")
	public Set<ModuleRegionScopes> getModuleRegionScopes() {
		return moduleRegionScopes;
	}

	public void setModuleRegionScopes(Set<ModuleRegionScopes> moduleRegionScopes) {
		this.moduleRegionScopes = moduleRegionScopes;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "state")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<ProblemSourceScope> getProblemSourceScope() {
		return problemSourceScope;
	}

	public void setProblemSourceScope(Set<ProblemSourceScope> problemSourceScope) {
		this.problemSourceScope = problemSourceScope;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "state")
	public Set<StateRegion> getStateRegion() {
		return stateRegion;
	}

	public void setStateRegion(Set<StateRegion> stateRegion) {
		this.stateRegion = stateRegion;
	}
*/
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "state")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}