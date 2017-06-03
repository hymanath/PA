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

	@Id
	@Column(name="location_address_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long locationAddressId;
	
	@Column(name="constituency_id")
	private Long constituencyId;
	
	@Column(name="district_id")
	private Long districtId;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "constituency_id", insertable = false, updatable = false)
	private Constituency constituency;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "district_id", insertable = false, updatable = false)
	private District district;

	public Long getLocationAddressId() {
		return locationAddressId;
	}

	public void setLocationAddressId(Long locationAddressId) {
		this.locationAddressId = locationAddressId;
	}

	public Long getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public Constituency getConstituency() {
		return constituency;
	}

	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}
	
	

}
