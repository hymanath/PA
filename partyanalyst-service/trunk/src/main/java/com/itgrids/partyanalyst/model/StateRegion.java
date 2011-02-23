
/* 
 * Copyright (c) 2011 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on February 17, 2011
 */
package com.itgrids.partyanalyst.model;

import java.io.Serializable;
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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.itgrids.partyanalyst.model.State;


@Entity
@Table(name = "state_region")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class StateRegion extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6899452742390074545L;

	private Long stateRegionId;
	private State state;
	private String regionName;
	private String description;
	private Set<StateRegionDistrict> stateRegionDistrict = new HashSet<StateRegionDistrict>(0);
	
	
	// Constructors
	/** 
	 * Default Constructor 
	 */
	
	
	public StateRegion(){
	
	}
	/** 
	 * Minimal Constructor
	 * @param StateRegionId - The Unique StateRegion Id
	 * @param regionName - The regionName Name 
	 */
	public StateRegion(State state, Long stateRegionId, String regionName) {
		this.state=state;
		this.stateRegionId = stateRegionId;
		this.regionName = regionName;
	}
	
	/** 
	 * Full Constructor with all the parameter
	 * @param stateRegionId - The Unique Id
	 * @param state - The State
	 * @param regionName - The Regions in The State
	 * @param description - The Description about Region
	 
	 */
	public StateRegion(Long stateRegionId, State state, String regionName,
                       String description,Set<StateRegionDistrict> stateRegionDistrict) {
		this.stateRegionId=stateRegionId;
		this.regionName=regionName;
		this.state=state;
		this.description=description;
		this.stateRegionDistrict=stateRegionDistrict;
		
	}
	
	
	
	/**
	 * @return - The Unique State Id.
	 */
	@Id
	@Column(name = "state_region_id", unique = true, nullable = false )
	public Long getStateRegionId() {
		return stateRegionId;
	}
	
	
	/**
	 * @param stateRegionId - The Unique state Region Id.
	 */
	public void setStateRegionId(Long stateRegionId) {
		this.stateRegionId = stateRegionId;
	}
	
	
	/**
	 * @return - The Unique State Id.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "state_id")	
	public State getState() {
		return state;
	}
	
	/**
	 * @param stateId - The Unique State Id.
	 */
	
	public void setState(State state) {
		this.state = state;
	}
	
	
	/**
	 * @return - The name of the Region
	 */
	
	@Column(name = "region_name", length = 100)
	public String getRegionName() {
		return regionName;
	}
	
	/**
	 * @param regionName - The region Full Name.
	 */
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	/**
	 * @return - The Description about the Region
	 */
	
	@Column(name = "description", length = 200)
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "stateRegion")
	public Set<StateRegionDistrict> getStateRegionDistrict() {
		return stateRegionDistrict;
	}
	public void setStateRegionDistrict(Set<StateRegionDistrict> stateRegionDistrict) {
		this.stateRegionDistrict = stateRegionDistrict;
	}
	
	
}