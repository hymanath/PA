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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;


@Entity
@Table(name = "user_entitlement_group_region")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserEntitlementGroupRegion implements Serializable{

	private static final long serialVersionUID = -8963831760162053319L;
	private Long userEntitlementGroupRegionId;
	private Registration registration;
	private GroupEntitlement groupEntitlement;
	private RegionScopes regionScopes;
	private Long regionScopeValue;
	private Integer accessCode;
	
	public UserEntitlementGroupRegion()
	{}
	
	public UserEntitlementGroupRegion(Registration registration,GroupEntitlement groupEntitlement,
			RegionScopes regionScopes,Long regionScopeValue,Integer accessCode)
	{
		this.registration = registration;
		this.groupEntitlement = groupEntitlement;
		this.regionScopes = regionScopes;
		this.regionScopeValue = regionScopeValue;
		this.accessCode = accessCode;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_entitlement_group_region_id", unique=true, nullable=false)
	public Long getUserEntitlementGroupRegionId() {
		return userEntitlementGroupRegionId;
	}

	public void setUserEntitlementGroupRegionId(Long userEntitlementGroupRegionId) {
		this.userEntitlementGroupRegionId = userEntitlementGroupRegionId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="registration_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Registration getRegistration() {
		return registration;
	}

	public void setRegistration(Registration registration) {
		this.registration = registration;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="entitlement_group_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public GroupEntitlement getGroupEntitlement() {
		return groupEntitlement;
	}

	public void setGroupEntitlement(GroupEntitlement groupEntitlement) {
		this.groupEntitlement = groupEntitlement;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="region_scopes_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public RegionScopes getRegionScopes() {
		return regionScopes;
	}

	public void setRegionScopes(RegionScopes regionScopes) {
		this.regionScopes = regionScopes;
	}

	@Column(name="region_scopes_value")
	public Long getRegionScopeValue() {
		return regionScopeValue;
	}

	public void setRegionScopeValue(Long regionScopeValue) {
		this.regionScopeValue = regionScopeValue;
	}

	@Column(name="access_code")
	public Integer getAccessCode() {
		return accessCode;
	}

	public void setAccessCode(Integer accessCode) {
		this.accessCode = accessCode;
	}
	
	

}
