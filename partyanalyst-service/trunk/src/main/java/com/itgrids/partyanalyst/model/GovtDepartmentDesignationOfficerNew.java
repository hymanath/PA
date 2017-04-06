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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "govt_department_designation_officer_new")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GovtDepartmentDesignationOfficerNew {
	
	private Long govtDepartmentDesignationOfficerId;
	private Long govtDepartmentDesignationId;
	private Long govtDepartmentScopeId;
	private Long levelValue;
	private Long addressId;
	private Long userId;
	private Long offId;
	
	private GovtDepartmentDesignationNew govtDepartmentDesignation;
	private GovtDepartmentScope govtDepartmentScope;
	private UserAddress userAddress;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "govt_department_designation_officer_id", unique = true, nullable = false)
	public Long getGovtDepartmentDesignationOfficerId() {
		return govtDepartmentDesignationOfficerId;
	}
	public void setGovtDepartmentDesignationOfficerId(
			Long govtDepartmentDesignationOfficerId) {
		this.govtDepartmentDesignationOfficerId = govtDepartmentDesignationOfficerId;
	}
	
	@Column(name = "govt_department_designation_id")
	public Long getGovtDepartmentDesignationId() {
		return govtDepartmentDesignationId;
	}
	public void setGovtDepartmentDesignationId(Long govtDepartmentDesignationId) {
		this.govtDepartmentDesignationId = govtDepartmentDesignationId;
	}
	
	@Column(name = "govt_department_scope_id")
	public Long getGovtDepartmentScopeId() {
		return govtDepartmentScopeId;
	}
	public void setGovtDepartmentScopeId(Long govtDepartmentScopeId) {
		this.govtDepartmentScopeId = govtDepartmentScopeId;
	}
	
	@Column(name = "level_value")
	public Long getLevelValue() {
		return levelValue;
	}
	public void setLevelValue(Long levelValue) {
		this.levelValue = levelValue;
	}
	
	@Column(name = "address_id")
	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	
	@Column(name = "user_id")
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@Column(name = "off_id")
	public Long getOffId() {
		return offId;
	}
	public void setOffId(Long offId) {
		this.offId = offId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="govt_department_designation_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public GovtDepartmentDesignationNew getGovtDepartmentDesignation() {
		return govtDepartmentDesignation;
	}
	public void setGovtDepartmentDesignation(
			GovtDepartmentDesignationNew govtDepartmentDesignation) {
		this.govtDepartmentDesignation = govtDepartmentDesignation;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="govt_department_scope_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public GovtDepartmentScope getGovtDepartmentScope() {
		return govtDepartmentScope;
	}
	public void setGovtDepartmentScope(GovtDepartmentScope govtDepartmentScope) {
		this.govtDepartmentScope = govtDepartmentScope;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="address_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UserAddress getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(UserAddress userAddress) {
		this.userAddress = userAddress;
	}
}
