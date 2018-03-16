package com.itgrids.partyanalyst.model;

import java.util.Date;

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
@Table(name = "itdp_app_user_access_location")
public class ItdpAppUserAccessLocation {

	 private Long itdpAppUserAccessLocationId;
	 private Long itdpAppUserId;
	 private Long locationScopeId;
	 private Long locationValue;
	 private String isDeleted;
	 private Date insertedTime;
	 private Date updatedTime;
	 private Long insertedBy;
	 private Long updatedBy;
	 
	 //private ItdpAppUser itdpAppUser;
	 private RegionScopes regionScopes;
	
	@Id
	@Column(name = "itdp_app_user_access_location_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getItdpAppUserAccessLocationId() {
		return itdpAppUserAccessLocationId;
	}
	public void setItdpAppUserAccessLocationId(Long itdpAppUserAccessLocationId) {
		this.itdpAppUserAccessLocationId = itdpAppUserAccessLocationId;
	}
	@Column(name = "itdp_app_user_id")
	public Long getItdpAppUserId() {
		return itdpAppUserId;
	}
	public void setItdpAppUserId(Long itdpAppUserId) {
		this.itdpAppUserId = itdpAppUserId;
	}
	@Column(name = "location_scope_id")
	public Long getLocationScopeId() {
		return locationScopeId;
	}
	public void setLocationScopeId(Long locationScopeId) {
		this.locationScopeId = locationScopeId;
	}
	@Column(name = "location_value")
	public Long getLocationValue() {
		return locationValue;
	}
	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Column(name = "inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	@Column(name = "updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	@Column(name = "inserted_by")
	public Long getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
	}
	@Column(name = "updated_by")
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
	/*@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "itdp_app_user_id", insertable = false, updatable = false)
	public ItdpAppUser getItdpAppUser() {
		return itdpAppUser;
	}
	public void setItdpAppUser(ItdpAppUser itdpAppUser) {
		this.itdpAppUser = itdpAppUser;
	}*/
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "location_scope_id", insertable = false, updatable = false)
	public RegionScopes getRegionScopes() {
		return regionScopes;
	}
	public void setRegionScopes(RegionScopes regionScopes) {
		this.regionScopes = regionScopes;
	}
		
}
