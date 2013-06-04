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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "state_region_district")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class StateRegionDistrict extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long stateRegionDistrictId;
	//private StateRegion stateRegion;
	private District district;
	
	
	// Constructors

	/** 
	 * Default Constructor 
	 */
	
	public StateRegionDistrict() {
		super();
		
	}



	/**
	 * @param stateRegion
	 * @param districtId
	 */
	/*public StateRegionDistrict(StateRegion stateRegion, District district) {
		super();
		this.stateRegion = stateRegion;
		this.district = district;
	}
*/
	/**
	 * @return - The Unique State Id.
	 */
	@Id
	@Column(name = "state_region_district_id", unique = true, nullable = false )
	public Long getStateRegionDistrictId() {
		return stateRegionDistrictId;
	}



	public void setStateRegionDistrictId(Long stateRegionDistrictId) {
		this.stateRegionDistrictId = stateRegionDistrictId;
	}

	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "state_region_id")	
	public StateRegion getStateRegion() {
		return stateRegion;
	}
	*//**
	 * @param StateregionName - The State region Full Name.
	 *//*
	public void setStateRegion(StateRegion stateRegion) {
		this.stateRegion = stateRegion;
	}*/

	@OneToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "district_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}
	
	
	

}
