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
	@Id
	@Column(name="fund_sanction_location_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long fundSanctionLocationId;
	@Column(name="fund_sanction_id")
	private Long fundSanctionId;
	@Column(name="location_scope_id")
	private Long locationScopeId;
	@Column(name="location_value")
	private Long locationValue;
	@Column(name="address_id")
	private Long addressId;
	@Column(name="is_deleted")
	private String isDeleted;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "fund_sanction_id", insertable = false, updatable = false)
	private FundSanction fundSanction;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "location_scope_id", insertable = false, updatable = false)
	private LocationScope locationScope;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id", insertable = false, updatable = false)
	private LocationAddress locationAddress;

	public LocationAddress getLocationAddress() {
		return locationAddress;
	}

	public void setLocationAddress(LocationAddress locationAddress) {
		this.locationAddress = locationAddress;
	}

	public Long getFundSanctionLocationId() {
		return fundSanctionLocationId;
	}

	public void setFundSanctionLocationId(Long fundSanctionLocationId) {
		this.fundSanctionLocationId = fundSanctionLocationId;
	}

	public Long getFundSanctionId() {
		return fundSanctionId;
	}

	public void setFundSanctionId(Long fundSanctionId) {
		this.fundSanctionId = fundSanctionId;
	}

	public Long getLocationScopeId() {
		return locationScopeId;
	}

	public void setLocationScopeId(Long locationScopeId) {
		this.locationScopeId = locationScopeId;
	}

	public Long getLocationValue() {
		return locationValue;
	}

	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}



	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public FundSanction getFundSanction() {
		return fundSanction;
	}

	public void setFundSanction(FundSanction fundSanction) {
		this.fundSanction = fundSanction;
	}

	public LocationScope getLocationScope() {
		return locationScope;
	}

	public void setLocationScope(LocationScope locationScope) {
		this.locationScope = locationScope;
	}
	
}
