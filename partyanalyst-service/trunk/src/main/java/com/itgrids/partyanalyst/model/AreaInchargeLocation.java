package com.itgrids.partyanalyst.model;

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
@Table(name = "area_incharge_location")
public class AreaInchargeLocation {

	private Long areaInchargeLocationId;
	private Long areaInchargeEnrollmetId;
	private AreaInchargeEnrollment areaInchargeEnrollmet;
	private Long regionScopeId;
	private RegionScopes regionScopes;
	private Long regionScopeValue;
	private Long addressId;
	private UserAddress address;
    private String isAssinged;
    private String createdBy;
    private String updatedBy;
    private String insertedTime;
    private String updatedTime;
    private String isDeleted;
    
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "area_incharge_location_id",unique = true,nullable = false)
	public Long getAreaInchargeLocationId() {
		return areaInchargeLocationId;
	}
	public void setAreaInchargeLocationId(Long areaInchargeLocationId) {
		this.areaInchargeLocationId = areaInchargeLocationId;
	}
	@Column(name = "area_incharge_enrollmet_id")
	public Long getAreaInchargeEnrollmetId() {
		return areaInchargeEnrollmetId;
	}
	public void setAreaInchargeEnrollmetId(Long areaInchargeEnrollmetId) {
		this.areaInchargeEnrollmetId = areaInchargeEnrollmetId;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "area_incharge_enrollmet_id",insertable = false,updatable = false)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AreaInchargeEnrollment getAreaInchargeEnrollmet() {
		return areaInchargeEnrollmet;
	}
	public void setAreaInchargeEnrollmet(
			AreaInchargeEnrollment areaInchargeEnrollmet) {
		this.areaInchargeEnrollmet = areaInchargeEnrollmet;
	}
	@Column(name = "region_scope_id")
	public Long getRegionScopeId() {
		return regionScopeId;
	}
	public void setRegionScopeId(Long regionScopeId) {
		this.regionScopeId = regionScopeId;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "region_scope_id",insertable = false,updatable = false)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public RegionScopes getRegionScopes() {
		return regionScopes;
	}
	public void setRegionScopes(RegionScopes regionScopes) {
		this.regionScopes = regionScopes;
	}
	@Column(name = "region_scope_value")
	public Long getRegionScopeValue() {
		return regionScopeValue;
	}
	public void setRegionScopeValue(Long regionScopeValue) {
		this.regionScopeValue = regionScopeValue;
	}
	@Column(name = "address_id")
	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id",insertable = false,updatable = false)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UserAddress getAddress() {
		return address;
	}
	public void setAddress(UserAddress address) {
		this.address = address;
	}
	@Column(name = "is_assinged")
	public String getIsAssinged() {
		return isAssinged;
	}
	public void setIsAssinged(String isAssinged) {
		this.isAssinged = isAssinged;
	}
	@Column(name = "created_by")
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	@Column(name = "updated_by")
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	@Column(name = "inserted_time")
	public String getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(String insertedTime) {
		this.insertedTime = insertedTime;
	}
	@Column(name = "updated_time")
	public String getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	
}
