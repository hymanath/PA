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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFoundAction;

/**
 * Township entity. 
 * @author <a href="mailto:shan.javaee@gmail.com">Shan Nagarajan</a>
 */
@Entity
@Table(name = "township")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Township implements java.io.Serializable {

	/**
	 * The Serial Version UID.
	 */
	private static final long serialVersionUID = 1525588565707839357L;

	// Fields

	private Long townshipId;
	private Tehsil tehsil;
	private String townshipName;
	private Long townshipCode;
	private String townshipType;
	private String greaterTown;
	private Set<Ward> wards = new HashSet<Ward>(0);
	//private Set<ProblemLocation> problemLocations = new HashSet<ProblemLocation>(0);
	private Set<Hamlet> hamlets = new HashSet<Hamlet>(0);
	//private Set<VillageBoothElection> villageBoothElections = new HashSet<VillageBoothElection>(0);
	private Set<LocalGroupRegion> localGroupRegion = new HashSet<LocalGroupRegion>(0);
	//private Set<DelimitationConstituencyTown> delimitationConstituencyTown = new HashSet<DelimitationConstituencyTown>(0);
	//private Set<DelimitationVillage> delimitationVillage = new HashSet<DelimitationVillage>(0);
	//private Set<Census> censuses = new HashSet<Census>();
	
	// Constructors

	/** default constructor */
	public Township() {
	}

	/** minimal constructor */
	public Township(Long townshipId) {
		this.townshipId = townshipId;
	}

	/** full constructor *//*
	public Township(Long townshipId, Tehsil tehsil, String townshipName,
			Long townshipCode, String townshipType, Set<Ward> wards,
			Set<ProblemLocation> problemLocations, Set<VillageBoothElection> villageBoothElections,Set<LocalGroupRegion> localGroupRegion) {
		this.townshipId = townshipId;
		this.tehsil = tehsil;
		this.townshipName = townshipName;
		this.townshipCode = townshipCode;
		this.townshipType = townshipType;
		this.wards = wards;
		this.problemLocations = problemLocations;
		this.villageBoothElections = villageBoothElections;
		this.localGroupRegion = localGroupRegion;
	}*/

	// Property accessors
	@Id
	@Column(name = "township_id", unique = true, nullable = false)
	public Long getTownshipId() {
		return this.townshipId;
	}

	public void setTownshipId(Long townshipId) {
		this.townshipId = townshipId;
	}

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "tehsil_id")
	public Tehsil getTehsil() {
		return this.tehsil;
	}

	public void setTehsil(Tehsil tehsil) {
		this.tehsil = tehsil;
	}

	@Column(name = "township_name", length = 100)
	public String getTownshipName() {
		return this.townshipName;
	}

	public void setTownshipName(String townshipName) {
		this.townshipName = townshipName;
	}

	@Column(name = "township_code")
	public Long getTownshipCode() {
		return this.townshipCode;
	}

	public void setTownshipCode(Long townshipCode) {
		this.townshipCode = townshipCode;
	}

	@Column(name = "township_type", length = 25)
	public String getTownshipType() {
		return this.townshipType;
	}

	public void setTownshipType(String townshipType) {
		this.townshipType = townshipType;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "township")
	public Set<Ward> getWards() {
		return this.wards;
	}

	public void setWards(Set<Ward> wards) {
		this.wards = wards;
	}

/*	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "township")
	public Set<ProblemLocation> getProblemLocations() {
		return problemLocations;
	}

	public void setProblemLocations(Set<ProblemLocation> problemLocations) {
		this.problemLocations = problemLocations;
	}*/

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "township")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<Hamlet> getHamlets() {
		return hamlets;
	}

	public void setHamlets(Set<Hamlet> hamlets) {
		this.hamlets = hamlets;
	}

	/*@OneToMany(fetch = FetchType.LAZY, mappedBy = "township")
	public Set<VillageBoothElection> getVillageBoothElections() {
		return villageBoothElections;
	}

	public void setVillageBoothElections(
			Set<VillageBoothElection> villageBoothElections) {
		this.villageBoothElections = villageBoothElections;
	}*/

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "township")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<LocalGroupRegion> getLocalGroupRegion() {
		return localGroupRegion;
	}

	public void setLocalGroupRegion(Set<LocalGroupRegion> localGroupRegion) {
		this.localGroupRegion = localGroupRegion;
	}

	/*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "township")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<DelimitationConstituencyTown> getDelimitationConstituencyTown() {
		return delimitationConstituencyTown;
	}

	public void setDelimitationConstituencyTown(
			Set<DelimitationConstituencyTown> delimitationConstituencyTown) {
		this.delimitationConstituencyTown = delimitationConstituencyTown;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "township")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<DelimitationVillage> getDelimitationVillage() {
		return delimitationVillage;
	}

	public void setDelimitationVillage(Set<DelimitationVillage> delimitationVillage) {
		this.delimitationVillage = delimitationVillage;
	}*/

	@Column(name = "greater_town", length = 25)
	public String getGreaterTown() {
		return greaterTown;
	}

	public void setGreaterTown(String greaterTown) {
		this.greaterTown = greaterTown;
	}

	/*@OneToMany(fetch = FetchType.LAZY, mappedBy = "township")
	public Set<Census> getCensuses() {
		return censuses;
	}

	public void setCensuses(Set<Census> censuses) {
		this.censuses = censuses;
	}*/
	
	
	
	
}