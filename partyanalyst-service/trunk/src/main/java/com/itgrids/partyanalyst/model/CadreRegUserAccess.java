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
@Table(name = "cadre_reg_user_access")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CadreRegUserAccess extends BaseModel implements Serializable {
	/*
	 * cadre_reg_user_access_id,
	 * cadre_reg_user_id,
	 * location_scope_id,
	 * location_value
	 */

	private Long cadreRegUserAccessId;
	private Long cadreRegUserId;
	private Long locationScopeId;
	private Long locationValue;
	
	private CadreRegUser cadreRegUser;
	private RegionScopes regionScopes;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cadre_reg_user_access_id", unique = true, nullable = false)
	public Long getCadreRegUserAccessId() {
		return cadreRegUserAccessId;
	}
	public void setCadreRegUserAccessId(Long cadreRegUserAccessId) {
		this.cadreRegUserAccessId = cadreRegUserAccessId;
	}
	@Column(name="cadre_reg_user_id")
	public Long getCadreRegUserId() {
		return cadreRegUserId;
	}
	public void setCadreRegUserId(Long cadreRegUserId) {
		this.cadreRegUserId = cadreRegUserId;
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
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="cadre_reg_user_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CadreRegUser getCadreRegUser() {
		return cadreRegUser;
	}
	public void setCadreRegUser(CadreRegUser cadreRegUser) {
		this.cadreRegUser = cadreRegUser;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="location_scope_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	public RegionScopes getRegionScopes() {
		return regionScopes;
	}
	public void setRegionScopes(RegionScopes regionScopes) {
		this.regionScopes = regionScopes;
	}
	
	
	
}
