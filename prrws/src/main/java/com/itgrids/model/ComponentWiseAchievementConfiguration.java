package com.itgrids.model;

import java.io.Serializable;
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
@Table(name = "component_wise_achievement_configuration")
public class ComponentWiseAchievementConfiguration extends BaseModel implements Serializable{
	
	private Long componentWiseAchievementConfigurationId;
	private Long nregaComponentId;
	private Long regionScopesId;
	private String scopeValue;
	private String achievedPercentage;
	private String year;
	private Long addressId;
	private String isDeleted;
	private Date insertedTime;
	
	private NregaComponent nregaComponent;
	private RegionScopes regionScopes;
	private LocationAddress locationAddress;
	
	@Id
	@Column(name="component_wise_achievement_configuration_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getComponentWiseAchievementConfigurationId() {
		return componentWiseAchievementConfigurationId;
	}
	public void setComponentWiseAchievementConfigurationId(Long componentWiseAchievementConfigurationId) {
		this.componentWiseAchievementConfigurationId = componentWiseAchievementConfigurationId;
	}
	
	@Column(name="nrega_component_id")
	public Long getNregaComponentId() {
		return nregaComponentId;
	}
	public void setNregaComponentId(Long nregaComponentId) {
		this.nregaComponentId = nregaComponentId;
	}
	
	@Column(name="region_scopes_id")
	public Long getRegionScopesId() {
		return regionScopesId;
	}
	public void setRegionScopesId(Long regionScopesId) {
		this.regionScopesId = regionScopesId;
	}
	
	@Column(name="scope_value")
	public String getScopeValue() {
		return scopeValue;
	}
	public void setScopeValue(String scopeValue) {
		this.scopeValue = scopeValue;
	}
	
	@Column(name="achieved_percentage")
	public String getAchievedPercentage() {
		return achievedPercentage;
	}
	public void setAchievedPercentage(String achievedPercentage) {
		this.achievedPercentage = achievedPercentage;
	}
	
	@Column(name="year")
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
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
	
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "nrega_component_id", insertable = false, updatable = false)
	public NregaComponent getNregaComponent() {
		return nregaComponent;
	}
	public void setNregaComponent(NregaComponent nregaComponent) {
		this.nregaComponent = nregaComponent;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "region_scopes_id", insertable = false, updatable = false)
	public RegionScopes getRegionScopes() {
		return regionScopes;
	}
	public void setRegionScopes(RegionScopes regionScopes) {
		this.regionScopes = regionScopes;
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
