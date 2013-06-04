/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 09, 2009
 */

package com.itgrids.partyanalyst.model;
import com.itgrids.partyanalyst.model.BaseModel;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * PartyWorkingCommittee entity. 
 * @author <a href="mailto:raghavenderprasad@gmail.com">Raghav</a>
 */
@Entity
@Table(name = "user_address")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserAddress implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long userAddressId;
	private Country country;	
	private State state;
	private District district;
	private Constituency constituency;
	private Constituency parliamentConstituency;
	private Tehsil tehsil;
	private Township township;
	private Hamlet hamlet;
	private String houseNo;
	private String street;
	private String pinCode;
	private LocalElectionBody localElectionBody;
	private Constituency ward;
	private Booth booth;
	/*private Cadre cadreCurrentAddress; 
	private Cadre cadrePermanentAddress;
	private InfluencingPeople influencingPeople;
	*/
	public UserAddress() {
		super();		
	}

	/*public UserAddress(Long userAddressId, Country country, State state,
			District district, Constituency constituency, Tehsil tehsil,
			Township township, Hamlet hamlet, String houseNo, String street,
			String pinCode, Cadre cadreCurrentAddress, Cadre cadrePermanentAddress,
			InfluencingPeople influencingPeople) {
		super();
		this.userAddressId = userAddressId;
		this.country = country;
		this.state = state;
		this.district = district;
		this.constituency = constituency;
		this.tehsil = tehsil;
		this.township = township;
		this.hamlet = hamlet;
		this.houseNo = houseNo;
		this.street = street;
		this.pinCode = pinCode;
		this.cadreCurrentAddress = cadreCurrentAddress;
		this.cadrePermanentAddress = cadrePermanentAddress;
		this.influencingPeople = influencingPeople;
	}*/

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name = "user_address_id", nullable = false, unique = true)
	public Long getUserAddressId() {
		return userAddressId;
	}

	public void setUserAddressId(Long userAddressId) {
		this.userAddressId = userAddressId;
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
	
	@Column(name = "house_no", length = 25)
	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	@Column(name = "street", length = 100)
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}
	
	@Column(name = "pincode", length = 10)
	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	
	/*@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "currentAddress")
	public Cadre getCadreCurrentAddress() {
		return cadreCurrentAddress;
	}

	public void setCadreCurrentAddress(Cadre cadreCurrentAddress) {
		this.cadreCurrentAddress = cadreCurrentAddress;
	}
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "permanentAddress")
	public Cadre getCadrePermanentAddress() {
		return cadrePermanentAddress;
	}

	public void setCadrePermanentAddress(Cadre cadrePermanentAddress) {
		this.cadrePermanentAddress = cadrePermanentAddress;
	}*/

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "local_election_body")
	public LocalElectionBody getLocalElectionBody() {
		return localElectionBody;
	}

	public void setLocalElectionBody(LocalElectionBody localElectionBody) {
		this.localElectionBody = localElectionBody;
	}

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name ="ward")
	public Constituency getWard() {
		return ward;
	}

	public void setWard(Constituency ward) {
		this.ward = ward;
	}

	/*@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userAddress")
	public InfluencingPeople getInfluencingPeople() {
		return influencingPeople;
	}

	public void setInfluencingPeople(InfluencingPeople influencingPeople) {
		this.influencingPeople = influencingPeople;
	}*/
	
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
