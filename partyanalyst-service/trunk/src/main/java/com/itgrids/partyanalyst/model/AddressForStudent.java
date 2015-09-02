package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "address_for_student")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AddressForStudent extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long addressId;
	private String stateId;
	private String districtId;
	private String constituencyId;
	private String tehsilId;
	private String localElectionBodyId;
	private String panchayatId;
	private String wardId;
	private String location;
	private String houseNo;
	private String street;
	private Long pinCode;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "address_id", unique = true, nullable = false)
	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	
	@Column(name = "state_id")
	public String getStateId() {
		return stateId;
	}
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}
	
	@Column(name = "district_id")
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	
	@Column(name = "constituency_id")
	public String getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(String constituencyId) {
		this.constituencyId = constituencyId;
	}
	
	@Column(name = "tehsil_id")
	public String getTehsilId() {
		return tehsilId;
	}
	public void setTehsilId(String tehsilId) {
		this.tehsilId = tehsilId;
	}
	
	@Column(name = "local_election_body_id")
	public String getLocalElectionBodyId() {
		return localElectionBodyId;
	}
	public void setLocalElectionBodyId(String localElectionBodyId) {
		this.localElectionBodyId = localElectionBodyId;
	}
	
	@Column(name = "panchayat_id")
	public String getPanchayatId() {
		return panchayatId;
	}
	public void setPanchayatId(String panchayatId) {
		this.panchayatId = panchayatId;
	}
	
	@Column(name = "ward_id")
	public String getWardId() {
		return wardId;
	}
	public void setWardId(String wardId) {
		this.wardId = wardId;
	}
	
	@Column(name = "location")
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	@Column(name = "house_no")
	public String getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	
	@Column(name = "street")
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	
	@Column(name = "pin_code")
	public Long getPinCode() {
		return pinCode;
	}
	public void setPinCode(Long pinCode) {
		this.pinCode = pinCode;
	}
}
