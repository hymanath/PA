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
@Table(name = "rurban_work_location")
public class RurbanWorkLocation {
	private Long rurbanWorkLocationId;
	private Long rurbanWorkId;
	private Long locationScopeId;
	private Long locationValue;
	private Long locationAddressId;
	
	private RurbanWork rurbanWork;
	private RegionScopes regionScopes;
	private LocationAddress locationAddress;
	
	@Id
	@Column(name="rurban_work_location_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getRurbanWorkLocationId() {
		return rurbanWorkLocationId;
	}
	public void setRurbanWorkLocationId(Long rurbanWorkLocationId) {
		this.rurbanWorkLocationId = rurbanWorkLocationId;
	}
	@Column(name="rurban_work_id")
	public Long getRurbanWorkId() {
		return rurbanWorkId;
	}
	public void setRurbanWorkId(Long rurbanWorkId) {
		this.rurbanWorkId = rurbanWorkId;
	}
	@Column(name="location_scope_id")
	public Long getLocationScopeId() {
		return locationScopeId;
	}
	public void setLocationScopeId(Long locationScopeId) {
		this.locationScopeId = locationScopeId;
	}
	@Column(name="location_value")
	public Long getLocationValue() {
		return locationValue;
	}
	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
	@Column(name="location_address_id")
	public Long getLocationAddressId() {
		return locationAddressId;
	}
	public void setLocationAddressId(Long locationAddressId) {
		this.locationAddressId = locationAddressId;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "rurban_work_id", insertable = false, updatable = false)
	public RurbanWork getRurbanWork() {
		return rurbanWork;
	}
	public void setRurbanWork(RurbanWork rurbanWork) {
		this.rurbanWork = rurbanWork;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "location_scope_id", insertable = false, updatable = false)
	public RegionScopes getRegionScopes() {
		return regionScopes;
	}
	public void setRegionScopes(RegionScopes regionScopes) {
		this.regionScopes = regionScopes;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "location_address_id", insertable = false, updatable = false)
	public LocationAddress getLocationAddress() {
		return locationAddress;
	}
	public void setLocationAddress(LocationAddress locationAddress) {
		this.locationAddress = locationAddress;
	}
}
