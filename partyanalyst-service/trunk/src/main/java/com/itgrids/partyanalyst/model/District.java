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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFoundAction;

/**
 * District entity. 
 * @author <a href="mailto:shan.javaee@gmail.com">Shan Nagarajan</a>
 */
@Entity
@Table(name = "district")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class District extends BaseModel implements java.io.Serializable {

	/**
	 * The Serial Version UID.
	 */
	private static final long serialVersionUID = 6608505852511099145L;
	
	// Fields
	
	private Long districtId;
	private State state;
	private String districtName;
	private String districtCapital;
	private Double area;
	private Double population;
	private Long districtCode;
	private Date startDate;
	private Date deformDate;
	private Set<Constituency> constituencies = new HashSet<Constituency>(0);
	private Set<Tehsil> tehsils = new HashSet<Tehsil>(0);
	private Set<PartyElectionDistrictResult> partyElectionDistrictResult = new HashSet<PartyElectionDistrictResult>(0);
	private Set<LocalGroupRegion> localGroupRegion = new HashSet<LocalGroupRegion>(0);
	private StateRegionDistrict stateRegionDistrict;
	private Set<ElectionGoverningBody> electionGoverningBody = new HashSet<ElectionGoverningBody>(0);
	

	// Constructors

	/** default constructor */
	public District() {
	}

	/** minimal constructor */
	public District(Long districtId) {
		this.districtId = districtId;
	}

	/** full constructor */
	public District(Long districtId, String districtName,
			String districtCapital, Double area, Double population,  State state,
			Long districtCode, Date startDate, Date deformDate,
			Set<Constituency> constituencies, Set<Tehsil> tehsils,Set<PartyElectionDistrictResult> partyElectionDistrictResult,
			Set<LocalGroupRegion> localGroupRegion) {
		this.districtId = districtId;
		this.state = state;
		this.districtName = districtName;
		this.districtCapital = districtCapital;
		this.area = area;
		this.population = population;
		this.districtCode = districtCode;
		this.startDate = startDate;
		this.deformDate = deformDate;
		this.constituencies = constituencies;
		this.tehsils = tehsils;
		this.partyElectionDistrictResult = partyElectionDistrictResult;
		this.localGroupRegion = localGroupRegion;		
		
	}

	public District(Long districtId, String districtName,
			String districtCapital, Double area, Double population,  State state,
			Long districtCode, Date startDate, Date deformDate,
			Set<Constituency> constituencies, Set<Tehsil> tehsils,Set<PartyElectionDistrictResult> partyElectionDistrictResult,Set<LocalGroupRegion> localGroupRegion,
			StateRegionDistrict stateRegionDistrict) {
		this.districtId = districtId;
		this.state = state;
		this.districtName = districtName;
		this.districtCapital = districtCapital;
		this.area = area;
		this.population = population;
		this.districtCode = districtCode;
		this.startDate = startDate;
		this.deformDate = deformDate;
		this.constituencies = constituencies;
		this.tehsils = tehsils;
		this.partyElectionDistrictResult = partyElectionDistrictResult;
		this.localGroupRegion = localGroupRegion;
		this.stateRegionDistrict = stateRegionDistrict;
		
	}
	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "district_id", unique = true, nullable = false)
	public Long getDistrictId() {
		return this.districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "state_id")
	public State getState() {
		return this.state;
	}

	public void setState(State state) {
		this.state = state;
	}

	@Column(name = "district_name", length = 100)
	public String getDistrictName() {
		return this.districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	@Column(name = "district_capital", length = 100)
	public String getDistrictCapital() {
		return this.districtCapital;
	}

	public void setDistrictCapital(String districtCapital) {
		this.districtCapital = districtCapital;
	}

	@Column(name = "area", precision = 15, scale = 5)
	public Double getArea() {
		return this.area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	@Column(name = "population", precision = 10, scale = 0)
	public Double getPopulation() {
		return this.population;
	}

	public void setPopulation(Double population) {
		this.population = population;
	}

	@Column(name = "district_code")
	public Long getDistrictCode() {
		return this.districtCode;
	}

	public void setDistrictCode(Long districtCode) {
		this.districtCode = districtCode;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "district")
	public Set<Constituency> getConstituencies() {
		return this.constituencies;
	}

	public void setConstituencies(Set<Constituency> constituencies) {
		this.constituencies = constituencies;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "district")
	public Set<Tehsil> getTehsils() {
		return this.tehsils;
	}

	public void setTehsils(Set<Tehsil> tehsils) {
		this.tehsils = tehsils;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "district")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<PartyElectionDistrictResult> getPartyElectionDistrictResult() {
		return partyElectionDistrictResult;
	}

	public void setPartyElectionDistrictResult(
			Set<PartyElectionDistrictResult> partyElectionDistrictResult) {
		this.partyElectionDistrictResult = partyElectionDistrictResult;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "district")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<LocalGroupRegion> getLocalGroupRegion() {
		return localGroupRegion;
	}

	public void setLocalGroupRegion(Set<LocalGroupRegion> localGroupRegion) {
		this.localGroupRegion = localGroupRegion;
	}
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "district")
	public StateRegionDistrict getStateRegionDistrict() {
		return stateRegionDistrict;
	}

	public void setStateRegionDistrict(StateRegionDistrict stateRegionDistrict) {
		this.stateRegionDistrict = stateRegionDistrict;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "district")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<ElectionGoverningBody> getElectionGoverningBody() {
		return electionGoverningBody;
	}

	public void setElectionGoverningBody(
			Set<ElectionGoverningBody> electionGoverningBody) {
		this.electionGoverningBody = electionGoverningBody;
	}
	
	
}