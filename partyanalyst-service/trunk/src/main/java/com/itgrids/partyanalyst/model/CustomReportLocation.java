package com.itgrids.partyanalyst.model;

import java.io.Serializable;

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

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;
@Entity
@Table(name="custom_report_location")
public class CustomReportLocation implements Serializable {
	private static final long serialVersionUID = 1L;
	public Long customReportLocationId;
	public  Long customReportId;
	public Long locationScopeId;
	public Long locationValue;
	public Long addressId;
	public String isDeleted;
	
	public CustomReport customReport;
	public RegionScopes locationScope;
    public UserAddress Address;
   
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="custom_report_location_id",nullable=false,unique=true)
	public Long getCustomReportLocationId() {
		return customReportLocationId;
	}
	public void setCustomReportLocationId(Long customReportLocationId) {
		this.customReportLocationId = customReportLocationId;
	}
	@Column(name="custom_report_id")
	public Long getCustomReportId() {
		return customReportId;
	}
	public void setCustomReportId(Long customReportId) {
		this.customReportId = customReportId;
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
	@JoinColumn(name = "custom_report_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public CustomReport getCustomReport() {
		return customReport;
	}
	public void setCustomReport(CustomReport customReport) {
		this.customReport = customReport;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "location_scope_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public RegionScopes getLocationScope() {
		return locationScope;
	}
	public void setLocationScope(RegionScopes locationScope) {
		this.locationScope = locationScope;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public UserAddress getAddress() {
		return Address;
	}
	public void setAddress(UserAddress address) {
		Address = address;
	}
		
}
