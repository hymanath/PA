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
@Table(name = "nrega_work_expenditure_location")
public class NregaWorkExpenditureLocation {

	private Long nregaWorkExpenditureLocationId;
	private Long nregaWorkExpenditureId;
	private Long locationScopeId;
	private Long locationId;
	private Long addressId;
	private String isDeleted;
	
	private NregaWorkExpenditure nregaWorkExpenditure;
	private LocationScope locationScope;
	private LocationAddress locationAddress;
	
	@Id
	@Column(name="nrega_work_expenditure_location_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getNregaWorkExpenditureLocationId() {
		return nregaWorkExpenditureLocationId;
	}
	public void setNregaWorkExpenditureLocationId(Long nregaWorkExpenditureLocationId) {
		this.nregaWorkExpenditureLocationId = nregaWorkExpenditureLocationId;
	}
	
	@Column(name="nrega_work_expenditure_id")
	public Long getNregaWorkExpenditureId() {
		return nregaWorkExpenditureId;
	}
	public void setNregaWorkExpenditureId(Long nregaWorkExpenditureId) {
		this.nregaWorkExpenditureId = nregaWorkExpenditureId;
	}
	
	@Column(name="location_scope_id")
	public Long getLocationScopeId() {
		return locationScopeId;
	}
	public void setLocationScopeId(Long locationScopeId) {
		this.locationScopeId = locationScopeId;
	}
	
	@Column(name="location_id")
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
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
	@JoinColumn(name = "nrega_work_expenditure_id", insertable = false, updatable = false)
	public NregaWorkExpenditure getNregaWorkExpenditure() {
		return nregaWorkExpenditure;
	}
	public void setNregaWorkExpenditure(NregaWorkExpenditure nregaWorkExpenditure) {
		this.nregaWorkExpenditure = nregaWorkExpenditure;
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
