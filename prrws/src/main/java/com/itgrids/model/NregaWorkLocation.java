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
@Table(name = "nrega_work_location")
public class NregaWorkLocation {

	private Long nregaWorkLocationId;
	private Long nregaWorkId;
	private Long locationScopeId;
	private Long locationValue;
	private Long addressId;
	private String isDeleted;
	
	private NregaWork nregaWork;
	private LocationScope locationScope;
	private LocationAddress locationAddress;
	
	@Id
	@Column(name="nrega_work_location_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getNregaWorkLocationId() {
		return nregaWorkLocationId;
	}
	public void setNregaWorkLocationId(Long nregaWorkLocationId) {
		this.nregaWorkLocationId = nregaWorkLocationId;
	}
	
	@Column(name="nrega_work_id")
	public Long getNregaWorkId() {
		return nregaWorkId;
	}
	public void setNregaWorkId(Long nregaWorkId) {
		this.nregaWorkId = nregaWorkId;
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
	
	@Column(name="address_id")
	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "nrega_work_id", insertable = false, updatable = false)
	public NregaWork getNregaWork() {
		return nregaWork;
	}
	public void setNregaWork(NregaWork nregaWork) {
		this.nregaWork = nregaWork;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "location_scope_id", insertable = false, updatable = false)
	public LocationScope getLocationScope() {
		return locationScope;
	}
	public void setLocationScope(LocationScope locationScope) {
		this.locationScope = locationScope;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id", insertable = false, updatable = false)
	public LocationAddress getLocationAddress() {
		return locationAddress;
	}
	public void setLocationAddress(LocationAddress locationAddress) {
		this.locationAddress = locationAddress;
	}
}
