package com.itgrids.model;

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

@Entity
@Table(name = "location_address")
public class LocationAddress{
		
	private static final long serialVersionUID = -2853930539938433902L;
	
	private Long locationAddressId;
	private Long constituencyId;
	private Long districtId;
	private Long tehsilId;
	private Long panchayatId;
	private Long stateId;
	private Long parliamentId;
	
	private Constituency constituency;
	private District district;
	private Tehsil tehsil;
	private Panchayat panchayat;
	private State state;
	private Constituency parliament;
	private Hamlet hamlet;

	@Id
	@Column(name="location_address_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getLocationAddressId() {
		return locationAddressId;
	}
	public void setLocationAddressId(Long locationAddressId) {
		this.locationAddressId = locationAddressId;
	}

	@Column(name="constituency_id")
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}

	@Column(name="district_id")
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "constituency_id", insertable = false, updatable = false)
	public Constituency getConstituency() {
		return constituency;
	}
	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "district_id", insertable = false, updatable = false)
	public District getDistrict() {
		return district;
	}
	public void setDistrict(District district) {
		this.district = district;
	}
	
	@Column(name="tehsil_id")
	public Long getTehsilId() {
		return tehsilId;
	}
	public void setTehsilId(Long tehsilId) {
		this.tehsilId = tehsilId;
	}
	
	@Column(name="panchayat_id")
	public Long getPanchayatId() {
		return panchayatId;
	}
	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "tehsil_id", insertable = false, updatable = false)
	public Tehsil getTehsil() {
		return tehsil;
	}
	public void setTehsil(Tehsil tehsil) {
		this.tehsil = tehsil;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "panchayat_id", insertable = false, updatable = false)
	public Panchayat getPanchayat() {
		return panchayat;
	}
	public void setPanchayat(Panchayat panchayat) {
		this.panchayat = panchayat;
	}
	
	@Column(name="state_id")
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "state_id", insertable = false, updatable = false)
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	
	@Column(name="parliament_id")
	public Long getParliamentId() {
		return parliamentId;
	}
	public void setParliamentId(Long parliamentId) {
		this.parliamentId = parliamentId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "parliament_id", insertable = false, updatable = false)
	public Constituency getParliament() {
		return parliament;
	}
	public void setParliament(Constituency parliament) {
		this.parliament = parliament;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "hamlet_id", insertable = false, updatable = false)
	public Hamlet getHamlet() {
		return hamlet;
	}
	public void setHamlet(Hamlet hamlet) {
		this.hamlet = hamlet;
	}
	
	
	
}
