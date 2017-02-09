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
@Table(name = "govt_alert_department_location")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GovtAlertDepartmentLocation extends BaseModel implements Serializable{

	private Long govtAlertDepartmentLocationId;
	private Long userId;
	private Long departmentId;
	private Long govtDepartmentLevelId;
	private Long levelValue;
	private Long addressId;
	private String isDeleted;
	
	private User user;
	private Department department;
	private GovtDepartmentLevel govtDepartmentLevel;
	private UserAddress address;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "govt_alert_department_location_id", unique = true, nullable = false)
	public Long getGovtAlertDepartmentLocationId() {
		return govtAlertDepartmentLocationId;
	}
	public void setGovtAlertDepartmentLocationId(Long govtAlertDepartmentLocationId) {
		this.govtAlertDepartmentLocationId = govtAlertDepartmentLocationId;
	}
	
	@Column(name = "user_id")
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@Column(name = "department_id")
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
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
	
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="department_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
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
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="address_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UserAddress getAddress() {
		return address;
	}
	public void setAddress(UserAddress address) {
		this.address = address;
	}
}
