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
@Table(name = "govt_department_designation_location")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GovtDepartmentDesignationLocation extends BaseModel implements Serializable{

	private Long govtDepartmentDesignationLocationId;
	private Long govtDepartmentDesignationId;
	private Long govtDepartmentLevelId;
	private Long levelValue;
	private Long addressId;
	private Long userId;
	private Long offId;
	
	private GovtDepartmentDesignation govtDepartmentDesignation;
	private GovtDepartmentLevel govtDepartmentLevel;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "govt_department_designation_location_id", unique = true, nullable = false)
	public Long getGovtDepartmentDesignationLocationId() {
		return govtDepartmentDesignationLocationId;
	}
	public void setGovtDepartmentDesignationLocationId(
			Long govtDepartmentDesignationLocationId) {
		this.govtDepartmentDesignationLocationId = govtDepartmentDesignationLocationId;
	}
	
	@Column(name = "govt_department_designation_id")
	public Long getGovtDepartmentDesignationId() {
		return govtDepartmentDesignationId;
	}
	public void setGovtDepartmentDesignationId(Long govtDepartmentDesignationId) {
		this.govtDepartmentDesignationId = govtDepartmentDesignationId;
	}
	
	@Column(name = "govt_department_level_id")
	public Long getGovtDepartmentLevelId() {
		return govtDepartmentLevelId;
	}
	public void setGovtDepartmentLevelId(Long govtDepartmentLevelId) {
		this.govtDepartmentLevelId = govtDepartmentLevelId;
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
	public GovtDepartmentDesignation getGovtDepartmentDesignation() {
		return govtDepartmentDesignation;
	}
	public void setGovtDepartmentDesignation(
			GovtDepartmentDesignation govtDepartmentDesignation) {
		this.govtDepartmentDesignation = govtDepartmentDesignation;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="govt_department_level_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public GovtDepartmentLevel getGovtDepartmentLevel() {
		return govtDepartmentLevel;
	}
	public void setGovtDepartmentLevel(GovtDepartmentLevel govtDepartmentLevel) {
		this.govtDepartmentLevel = govtDepartmentLevel;
	}
}
