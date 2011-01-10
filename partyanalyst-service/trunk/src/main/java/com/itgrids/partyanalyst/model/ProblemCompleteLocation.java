/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 09, 2009
 */

package com.itgrids.partyanalyst.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "problem_complete_location")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProblemCompleteLocation implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long problemCompleteLocationId;
	private Country country;	
	private State state;
	private District district;
	private Constituency constituency;
	private Constituency parliamentConstituency;
	private Tehsil tehsil;
	private Township township;
	private Hamlet hamlet;	
	private LocalElectionBody localElectionBody;
	private Constituency ward;
	private Booth booth;
	
	
	public ProblemCompleteLocation() {
		super();		
	}

	public ProblemCompleteLocation(Long problemCompleteLocationId, Country country, State state,
			District district, Constituency constituency,Constituency parliamentConstituency, Tehsil tehsil,
			Township township, Hamlet hamlet,LocalElectionBody localElectionBody,Constituency ward,Booth booth) {
		super();
		this.problemCompleteLocationId = problemCompleteLocationId;
		this.country = country;
		this.state = state;
		this.district = district;
		this.constituency = constituency;
		this.parliamentConstituency = parliamentConstituency;
		this.tehsil = tehsil;
		this.township = township;
		this.hamlet = hamlet;	
		this.localElectionBody = localElectionBody;
		this.ward = ward;
		this.booth = booth;
	}

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name = "problem_complete_location_id", nullable = false, unique = true)
	public Long getProblemCompleteLocationId() {
		return problemCompleteLocationId;
	}

	public void setProblemCompleteLocationId(Long problemCompleteLocationId) {
		this.problemCompleteLocationId = problemCompleteLocationId;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "country_id")
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "state_id")
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "district_id")
	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "constituency_id")
	public Constituency getConstituency() {
		return constituency;
	}

	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "tehsil_id")
	public Tehsil getTehsil() {
		return tehsil;
	}

	public void setTehsil(Tehsil tehsil) {
		this.tehsil = tehsil;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "township_id")
	public Township getTownship() {
		return township;
	}

	public void setTownship(Township township) {
		this.township = township;
	}

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "hamlet_id")
	public Hamlet getHamlet() {
		return hamlet;
	}

	public void setHamlet(Hamlet hamlet) {
		this.hamlet = hamlet;
	}

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "local_election_body_id")
	public LocalElectionBody getLocalElectionBody() {
		return localElectionBody;
	}

	public void setLocalElectionBody(LocalElectionBody localElectionBody) {
		this.localElectionBody = localElectionBody;
	}

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name ="ward_id")
	public Constituency getWard() {
		return ward;
	}

	public void setWard(Constituency ward) {
		this.ward = ward;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "parliament_constituency_id")
	public Constituency getParliamentConstituency() {
		return parliamentConstituency;
	}

	public void setParliamentConstituency(Constituency parliamentConstituency) {
		this.parliamentConstituency = parliamentConstituency;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "booth_id")	
	public Booth getBooth() {
		return booth;
	}

	public void setBooth(Booth booth) {
		this.booth = booth;
	}
	
}
