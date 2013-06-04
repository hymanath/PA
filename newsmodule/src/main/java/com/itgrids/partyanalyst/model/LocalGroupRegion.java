/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on October 25, 2010
 */
package com.itgrids.partyanalyst.model;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

/**
 * @author Sai Krishna
 *
 */
@Entity
@Table(name = "local_group_region")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class LocalGroupRegion extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long localGroupRegionId;
	private String description;
	private Date updatedDate;
	private String groupRegionScope;
	private String groupRegionScopeValue;
	private State state;
	private District district;
	private Constituency constituency;
	private Tehsil tehsil;
	private Township township;
	private LocalElectionBody localBody;
	private Constituency ward;
	private Hamlet hamlet;
	private Booth booth;
	
	private String houseNo;
	private String streetName;
	private String pincode;
	private Constituency parliamentConstituency;
	// private Set<PersonalUserGroup> personalUserGroup = new HashSet<PersonalUserGroup>(0);
	
	//Default Constructor
	public LocalGroupRegion(){
		
	}
	
	/*//Parameterized Constructor
	public LocalGroupRegion(String description, Date updatedDate,
			String groupRegionScope, State state, District district,
			Constituency constituency, Tehsil tehsil, Township township,
			LocalElectionBody localBody, Constituency ward, Hamlet hamlet,
			Set<PersonalUserGroup> personalUserGroup) {
		super();
		this.description = description;
		this.updatedDate = updatedDate;
		this.groupRegionScope = groupRegionScope;
		this.state = state;
		this.district = district;
		this.constituency = constituency;
		this.tehsil = tehsil;
		this.township = township;
		this.localBody = localBody;
		this.ward = ward;
		this.hamlet = hamlet;
		//this.personalUserGroup = personalUserGroup;
	}
*/
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "local_group_region_id", unique = true, nullable = false)
	public Long getLocalGroupRegionId() {
		return localGroupRegionId;
	}

	public void setLocalGroupRegionId(Long localGroupRegionId) {
		this.localGroupRegionId = localGroupRegionId;
	}

	@Column(name = "description", length = 300)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "updated_date")
	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Column(name = "group_region_scope", length = 50)
	public String getGroupRegionScope() {
		return groupRegionScope;
	}

	public void setGroupRegionScope(String groupRegionScope) {
		this.groupRegionScope = groupRegionScope;
	}

	@Column(name = "group_region_scope_value", length = 25)
	public String getGroupRegionScopeValue() {
		return groupRegionScopeValue;
	}

	public void setGroupRegionScopeValue(String groupRegionScopeValue) {
		this.groupRegionScopeValue = groupRegionScopeValue;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="state_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="district_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="constituency_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Constituency getConstituency() {
		return constituency;
	}

	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="tehsil_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Tehsil getTehsil() {
		return tehsil;
	}

	public void setTehsil(Tehsil tehsil) {
		this.tehsil = tehsil;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="township_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Township getTownship() {
		return township;
	}

	public void setTownship(Township township) {
		this.township = township;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="local_body_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public LocalElectionBody getLocalBody() {
		return localBody;
	}

	public void setLocalBody(LocalElectionBody localBody) {
		this.localBody = localBody;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="ward_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Constituency getWard() {
		return ward;
	}

	public void setWard(Constituency ward) {
		this.ward = ward;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="hamlet_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Hamlet getHamlet() {
		return hamlet;
	}

	public void setHamlet(Hamlet hamlet) {
		this.hamlet = hamlet;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="booth_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Booth getBooth() {
		return booth;
	}

	public void setBooth(Booth booth) {
		this.booth = booth;
	}

	/*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "localGroupRegion")
	public Set<PersonalUserGroup> getPersonalUserGroup() {
		return personalUserGroup;
	}

	public void setPersonalUserGroup(Set<PersonalUserGroup> personalUserGroup) {
		this.personalUserGroup = personalUserGroup;
	}
*/
	@Column(name = "house_no", length = 25)
	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	@Column(name = "street_name", length = 50)
	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	@Column(name = "pincode", length = 25)
	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="parliament_constituency_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)	
	public Constituency getParliamentConstituency() {
		return parliamentConstituency;
	}

	public void setParliamentConstituency(Constituency parliamentConstituency) {
		this.parliamentConstituency = parliamentConstituency;
	}
	
	

	

}
