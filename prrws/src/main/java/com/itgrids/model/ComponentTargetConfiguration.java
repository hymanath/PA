package com.itgrids.model;

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
@Table(name = "component_target_configuration")
public class ComponentTargetConfiguration {
	
	private Long componentTargetConfigurationId;	
	private Long nregaComponentId;
	private Long regionScopesId;
	private String  scopeValue;
	private Long   componentTargetId;
	private Double totalExpenditure;
	private Double wage;
	private Double material;
	private String year;
	private String isDeleted;
	private Date insertedTime;
	private NregaComponent nregaComponent;
	private RegionScopes  regionScopes; 
	private ComponentTarget  componentTarget;
	@Id 
	@Column(name="component_target_configuration_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getComponentTargetConfigurationId() {
		return componentTargetConfigurationId;
	}
	public void setComponentTargetConfigurationId(Long componentTargetConfigurationId) {
		this.componentTargetConfigurationId = componentTargetConfigurationId;
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
	@Column(name="component_target_id")
	public Long getComponentTargetId() {
		return componentTargetId;
	}
	public void setComponentTargetId(Long componentTargetId) {
		this.componentTargetId = componentTargetId;
	}
	@Column(name="total_expenditure")
	public Double getTotalExpenditure() {
		return totalExpenditure;
	}
	public void setTotalExpenditure(Double totalExpenditure) {
		this.totalExpenditure = totalExpenditure;
	}
	@Column(name="wage")
	public Double getWage() {
		return wage;
	}
	public void setWage(Double wage) {
		this.wage = wage;
	}
	@Column(name="material")
	public Double getMaterial() {
		return material;
	}
	public void setMaterial(Double material) {
		this.material = material;
	}
	@Column(name="year")
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
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
	@JoinColumn(name = "component_target_id", insertable = false, updatable = false)
	public ComponentTarget getComponentTarget() {
		return componentTarget;
	}
	public void setComponentTarget(ComponentTarget componentTarget) {
		this.componentTarget = componentTarget;
	}
}

