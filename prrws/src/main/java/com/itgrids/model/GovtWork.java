package com.itgrids.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "govt_work")
public class GovtWork {
	private Long govtWorkId;
	private Long govtWorkTypeId;
	private String workName;
	private String workZoneName;
	private Double fundAllocated;
	private Double workLength;
	private Long locationScopeId;
	private Long locationValue;
	private Long locationAddressId;
	private Long govtWorkStatusId;
	private Double compledPercentage;
	private String lattitude;
	private String longitude;
	private Long createdBy;
	private Date createdIime;
	private Long updatedBy;
	private Date updatedTime;
	private String isDeleted;
	
	
	private GovtWorkType govtWorkType;
	private RegionScopes locationScope;
	private LocationAddress locationAddress;
	private GovtWorkStatus govtWorkStatus;
	private MobileAppUser createdByUser;
	private MobileAppUser updatedByUser;
	
	@Column(name="govt_work_id")
	public Long getGovtWorkId() {
		return govtWorkId;
	}
	public void setGovtWorkId(Long govtWorkId) {
		this.govtWorkId = govtWorkId;
	}
	
	@Column(name="govt_work_type_id")
	public Long getGovtWorkTypeId() {
		return govtWorkTypeId;
	}
	public void setGovtWorkTypeId(Long govtWorkTypeId) {
		this.govtWorkTypeId = govtWorkTypeId;
	}
	
	@Column(name="work_name")
	public String getWorkName() {
		return workName;
	}
	public void setWorkName(String workName) {
		this.workName = workName;
	}
	
	@Column(name="work_zone_name")
	public String getWorkZoneName() {
		return workZoneName;
	}
	public void setWorkZoneName(String workZoneName) {
		this.workZoneName = workZoneName;
	}
	
	@Column(name="fund_allocated")
	public Double getFundAllocated() {
		return fundAllocated;
	}
	public void setFundAllocated(Double fundAllocated) {
		this.fundAllocated = fundAllocated;
	}
	
	@Column(name="work_length")
	public Double getWorkLength() {
		return workLength;
	}
	public void setWorkLength(Double workLength) {
		this.workLength = workLength;
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
	
	@Column(name="location_address_id")
	public Long getLocationAddressId() {
		return locationAddressId;
	}
	public void setLocationAddressId(Long locationAddressId) {
		this.locationAddressId = locationAddressId;
	}
	
	@Column(name="govt_work_status_id")
	public Long getGovtWorkStatusId() {
		return govtWorkStatusId;
	}
	public void setGovtWorkStatusId(Long govtWorkStatusId) {
		this.govtWorkStatusId = govtWorkStatusId;
	}
	
	@Column(name="compled_percentage")
	public Double getCompledPercentage() {
		return compledPercentage;
	}
	public void setCompledPercentage(Double compledPercentage) {
		this.compledPercentage = compledPercentage;
	}
	
	@Column(name="created_by")
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	
	@Column(name="created_time")
	public Date getCreatedIime() {
		return createdIime;
	}
	public void setCreatedIime(Date createdIime) {
		this.createdIime = createdIime;
	}
	
	@Column(name="updated_by")
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	@Column(name="updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "govt_work_type_id", insertable = false, updatable = false)
	public GovtWorkType getGovtWorkType() {
		return govtWorkType;
	}
	public void setGovtWorkType(GovtWorkType govtWorkType) {
		this.govtWorkType = govtWorkType;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "location_scope_id", insertable = false, updatable = false)
	public RegionScopes getLocationScope() {
		return locationScope;
	}
	public void setLocationScope(RegionScopes locationScope) {
		this.locationScope = locationScope;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "location_address_id", insertable = false, updatable = false)
	public LocationAddress getLocationAddress() {
		return locationAddress;
	}
	public void setLocationAddress(LocationAddress locationAddress) {
		this.locationAddress = locationAddress;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "govt_work_status_id", insertable = false, updatable = false)
	public GovtWorkStatus getGovtWorkStatus() {
		return govtWorkStatus;
	}
	public void setGovtWorkStatus(GovtWorkStatus govtWorkStatus) {
		this.govtWorkStatus = govtWorkStatus;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by", insertable = false, updatable = false)
	public MobileAppUser getCreatedByUser() {
		return createdByUser;
	}
	public void setCreatedByUser(MobileAppUser createdByUser) {
		this.createdByUser = createdByUser;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "updated_by", insertable = false, updatable = false)
	public MobileAppUser getUpdatedByUser() {
		return updatedByUser;
	}
	public void setUpdatedByUser(MobileAppUser updatedByUser) {
		this.updatedByUser = updatedByUser;
	}
	
	@Column(name="lattitude")
	public String getLattitude() {
		return lattitude;
	}
	public void setLattitude(String lattitude) {
		this.lattitude = lattitude;
	}
	
	@Column(name="longitude")
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	
	
	
}
