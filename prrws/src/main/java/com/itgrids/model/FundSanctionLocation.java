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
/**
 * Description
 * @author <a href="mailto:raghupathi.tirumala@itgrids.com">raghu</a> 
 * @version 1.0/
 */

@Entity
@Table(name = "fund_sanction_location")
public class FundSanctionLocation {
	
	private static final long serialVersionUID = -2853930539938433902L;
	
	private Long fundSanctionLocationId;
	private Long fundSanctionId;
	private Long locationScopeId;
	private Long locationValue;
	private Long addressId;
	private String isDeleted;
	
	private FundSanction fundSanction;
	private LocationScope locationScope;
	private LocationAddress locationAddress;
	
	@Id
	@Column(name="fund_sanction_location_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getFundSanctionLocationId() {
		return fundSanctionLocationId;
	}
	public void setFundSanctionLocationId(Long fundSanctionLocationId) {
		this.fundSanctionLocationId = fundSanctionLocationId;
	}

	@Column(name="fund_sanction_id")
	public Long getFundSanctionId() {
		return fundSanctionId;
	}
	public void setFundSanctionId(Long fundSanctionId) {
		this.fundSanctionId = fundSanctionId;
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
	@JoinColumn(name = "fund_sanction_id", insertable = false, updatable = false)
	public FundSanction getFundSanction() {
		return fundSanction;
	}
	public void setFundSanction(FundSanction fundSanction) {
		this.fundSanction = fundSanction;
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
