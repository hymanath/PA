package com.itgrids.model;

import java.io.Serializable;
import java.sql.Date;

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
@Table(name = "location_wise_meeseva_centers")
public class LocationWiseMeesevaCenters implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long locationWiseMeesevaCentersId;
	private Long locationScopeId;
	private Long locationValue;
	private Long addressId;
	private Long totalCenters;
	private Long establishedSince2014;
	private Long establishedLastYear;
	private Long establishedThisYear;
	private Long establishedLastMonth;
	private Date insertedDate;
	private String isDeleted;
	
	private LocationAddress address;
	private LocationScope locationScope;
	
	@Id
	@Column(name="location_wise_meeseva_centers_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getLocationWiseMeesevaCentersId() {
		return locationWiseMeesevaCentersId;
	}
	public void setLocationWiseMeesevaCentersId(Long locationWiseMeesevaCentersId) {
		this.locationWiseMeesevaCentersId = locationWiseMeesevaCentersId;
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
	
	@Column(name="total_centers")
	public Long getTotalCenters() {
		return totalCenters;
	}
	public void setTotalCenters(Long totalCenters) {
		this.totalCenters = totalCenters;
	}
	
	@Column(name="established_since_2014")
	public Long getEstablishedSince2014() {
		return establishedSince2014;
	}
	public void setEstablishedSince2014(Long establishedSince2014) {
		this.establishedSince2014 = establishedSince2014;
	}
	
	@Column(name="established_last_year")
	public Long getEstablishedLastYear() {
		return establishedLastYear;
	}
	public void setEstablishedLastYear(Long establishedLastYear) {
		this.establishedLastYear = establishedLastYear;
	}
	
	@Column(name="established_this_year")
	public Long getEstablishedThisYear() {
		return establishedThisYear;
	}
	public void setEstablishedThisYear(Long establishedThisYear) {
		this.establishedThisYear = establishedThisYear;
	}
	
	@Column(name="established_last_month")
	public Long getEstablishedLastMonth() {
		return establishedLastMonth;
	}
	public void setEstablishedLastMonth(Long establishedLastMonth) {
		this.establishedLastMonth = establishedLastMonth;
	}
	
	@Column(name="inserted_date")
	public Date getInsertedDate() {
		return insertedDate;
	}
	public void setInsertedDate(Date insertedDate) {
		this.insertedDate = insertedDate;
	}
	
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id", insertable = false, updatable = false)
	public LocationAddress getAddress() {
		return address;
	}
	public void setAddress(LocationAddress address) {
		this.address = address;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "location_scope_id", insertable = false, updatable = false)
	public LocationScope getLocationScope() {
		return locationScope;
	}
	public void setLocationScope(LocationScope locationScope) {
		this.locationScope = locationScope;
	}
}
