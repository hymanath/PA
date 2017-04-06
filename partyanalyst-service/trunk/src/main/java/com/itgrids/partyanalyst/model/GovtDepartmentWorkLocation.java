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
@Table(name = "govt_department_work_location")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GovtDepartmentWorkLocation extends BaseModel implements Serializable {
	
	private Long govtDepartmentWorkLocationId;
	private Long govtDepartmentScopeId;
	private Long govtDepartmentId;
	private String locationName;
	private Long govtUserAddressId;
	private Long alertRegionScopesId;
	private Long locationValue;
	private String isDeleted;
	
	private GovtDepartmentScope govtDepartmentScope;
	private GovtDepartment govtDepartment;
	private AlertRegionScopes alertRegionScopes;
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "govt_department_work_location_id", unique = true, nullable = false)
	public Long getGovtDepartmentWorkLocationId() {
		return govtDepartmentWorkLocationId;
	}
	public void setGovtDepartmentWorkLocationId(Long govtDepartmentWorkLocationId) {
		this.govtDepartmentWorkLocationId = govtDepartmentWorkLocationId;
	}
	
	@Column(name = "govt_department_scope_id")
	public Long getGovtDepartmentScopeId() {
		return govtDepartmentScopeId;
	}
	public void setGovtDepartmentScopeId(Long govtDepartmentScopeId) {
		this.govtDepartmentScopeId = govtDepartmentScopeId;
	}
	
	@Column(name = "govt_department_id")
	public Long getGovtDepartmentId() {
		return govtDepartmentId;
	}
	public void setGovtDepartmentId(Long govtDepartmentId) {
		this.govtDepartmentId = govtDepartmentId;
	}
	
	@Column(name = "location_name")
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	
	@Column(name = "govt_user_address_id")
	public Long getGovtUserAddressId() {
		return govtUserAddressId;
	}
	public void setGovtUserAddressId(Long govtUserAddressId) {
		this.govtUserAddressId = govtUserAddressId;
	}
	
	@Column(name = "alert_region_scopes_id")
	public Long getAlertRegionScopesId() {
		return alertRegionScopesId;
	}
	public void setAlertRegionScopesId(Long alertRegionScopesId) {
		this.alertRegionScopesId = alertRegionScopesId;
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
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "govt_department_scope_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public GovtDepartmentScope getGovtDepartmentScope() {
		return govtDepartmentScope;
	}
	public void setGovtDepartmentScope(GovtDepartmentScope govtDepartmentScope) {
		this.govtDepartmentScope = govtDepartmentScope;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "govt_department_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public GovtDepartment getGovtDepartment() {
		return govtDepartment;
	}
	public void setGovtDepartment(GovtDepartment govtDepartment) {
		this.govtDepartment = govtDepartment;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "alert_region_scopes_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public AlertRegionScopes getAlertRegionScopes() {
		return alertRegionScopes;
	}
	public void setAlertRegionScopes(AlertRegionScopes alertRegionScopes) {
		this.alertRegionScopes = alertRegionScopes;
	}
	
	

}
