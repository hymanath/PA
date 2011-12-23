/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on December 1, 2009
 */
package com.itgrids.partyanalyst.model;

import java.io.Serializable;
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
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;


@Entity
@Table(name = "hamlet")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Hamlet extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5482664288490372919L;
	
	private Long hamletId;
	private String hamletCode;
	private String hamletName;
	private Township township;
	private String panchayatCode;
	private String panchayatName;
	private String mppCode;
	private Set<Voter> voters = new HashSet<Voter>(0);
	private Set<ProblemLocation> problemLocations = new HashSet<ProblemLocation>(0);
	private Set<HamletBoothElection> hamletBoothElections = new HashSet<HamletBoothElection>(0);
	private Set<LocalGroupRegion> localGroupRegion = new HashSet<LocalGroupRegion>(0);
	private Set<PanchayatHamlet> panchayathHamlets = new HashSet<PanchayatHamlet>(0);
	
	//default constructor
	public Hamlet(){
		
	}
	
	//parameterized constructor
	public Hamlet(Long hamletId){
		this.hamletId = hamletId;
	}
	
	//parameterized constructor
	public Hamlet(String hamletCode,String hamletName,Township township,
			String panchayatCode,String panchayatName,String mppCode,
			Set<Voter> voters, Set<ProblemLocation> problemLocations,Set<LocalGroupRegion> localGroupRegion){
		this.hamletCode = hamletCode;
		this.hamletName = hamletName;
		this.township = township;
		this.panchayatCode = panchayatCode;
		this.panchayatName = panchayatName;
		this.mppCode = mppCode;
		this.voters = voters;
		this.problemLocations = problemLocations;
		this.localGroupRegion = localGroupRegion;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "hamlet_id", unique = true, nullable = false)
	public Long getHamletId() {
		return hamletId;
	}

	public void setHamletId(Long hamletId) {
		this.hamletId = hamletId;
	}

	@Column(name = "hamlet_code")
	public String getHamletCode() {
		return hamletCode;
	}

	public void setHamletCode(String hamletCode) {
		this.hamletCode = hamletCode;
	}

	@Column(name = "hamlet_name")
	public String getHamletName() {
		return hamletName;
	}

	public void setHamletName(String hamletName) {
		this.hamletName = hamletName;
	}

	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "township_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Township getTownship() {
		return township;
	}

	public void setTownship(Township township) {
		this.township = township;
	}

	@Column(name = "panchayat_code")
	public String getPanchayatCode() {
		return panchayatCode;
	}

	public void setPanchayatCode(String panchayatCode) {
		this.panchayatCode = panchayatCode;
	}

	@Column(name = "panchayat_name")
	public String getPanchayatName() {
		return panchayatName;
	}

	public void setPanchayatName(String panchayatName) {
		this.panchayatName = panchayatName;
	}

	@Column(name = "mpp_code")
	public String getMppCode() {
		return mppCode;
	}

	public void setMppCode(String mppCode) {
		this.mppCode = mppCode;
	}

	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "hamlet")
	public Set<Voter> getVoters() {
		return voters;
	}

	public void setVoters(Set<Voter> voters) {
		this.voters = voters;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "hamlet")
	public Set<ProblemLocation> getProblemLocations() {
		return problemLocations;
	}

	public void setProblemLocations(Set<ProblemLocation> problemLocations) {
		this.problemLocations = problemLocations;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "hamlet")
	public Set<HamletBoothElection> getHamletBoothElections() {
		return hamletBoothElections;
	}

	public void setHamletBoothElections(
			Set<HamletBoothElection> hamletBoothElections) {
		this.hamletBoothElections = hamletBoothElections;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "hamlet")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<LocalGroupRegion> getLocalGroupRegion() {
		return localGroupRegion;
	}

	public void setLocalGroupRegion(Set<LocalGroupRegion> localGroupRegion) {
		this.localGroupRegion = localGroupRegion;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "hamlet")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<PanchayatHamlet> getPanchayathHamlets() {
		return panchayathHamlets;
	}

	public void setPanchayathHamlets(Set<PanchayatHamlet> panchayathHamlets) {
		this.panchayathHamlets = panchayathHamlets;
	}

	
}
