package com.itgrids.partyanalyst.model;

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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "alert")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Alert extends BaseModel implements Serializable {
	private Long alertId;
	private AlertType alertType;
	private Long alertTypeId;
	private String description;
	private AlertStatus alertStatus;
	private Long alertStatusId;
	private AlertSeverity alertSeverity;
	private Long alertSeverityId;
	private Long impactLevelId;
	private Long impactLevelValue;
	private UserAddress userAddress;
	private Long addressId;
	private Long userTypeId;
	private Long createdBy;
	private Date createdTime;
	private String isDeleted;
	private Long updatedBy;
	private Date updatedTime;
	private RegionScopes regionScopes;
	private AlertUserType alertUserType;
	private AlertSource alertSource;
	private Long alertSourceId;

	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "alert_id", unique = true, nullable = false)
	public Long getAlertId() {
		return alertId;
	}

	public void setAlertId(Long alertId) {
		this.alertId = alertId;
	}

	@Column(name = "alert_type_id")
	public Long getAlertTypeId() {
		return alertTypeId;
	}

	public void setAlertTypeId(Long alertTypeId) {
		this.alertTypeId = alertTypeId;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "alert_status_id")
	public Long getAlertStatusId() {
		return alertStatusId;
	}

	public void setAlertStatusId(Long alertStatusId) {
		this.alertStatusId = alertStatusId;
	}

	@Column(name = "alert_severity_id")
	public Long getAlertSeverityId() {
		return alertSeverityId;
	}

	public void setAlertSeverityId(Long alertSeverityId) {
		this.alertSeverityId = alertSeverityId;
	}

	@Column(name = "impact_level_id")
	public Long getImpactLevelId() {
		return impactLevelId;
	}

	public void setImpactLevelId(Long impactLevelId) {
		this.impactLevelId = impactLevelId;
	}

	@Column(name = "impact_level_value")
	public Long getImpactLevelValue() {
		return impactLevelValue;
	}

	public void setImpactLevelValue(Long impactLevelValue) {
		this.impactLevelValue = impactLevelValue;
	}

	@Column(name = "address_id")
	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	@Column(name = "user_type_id")
	public Long getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(Long userTypeId) {
		this.userTypeId = userTypeId;
	}

	@Column(name = "created_by")
	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "created_time")
	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Column(name = "updated_by")
	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Column(name = "updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "alert_type_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public AlertType getAlertType() {
		return alertType;
	}

	public void setAlertType(AlertType alertType) {
		this.alertType = alertType;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "alert_status_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public AlertStatus getAlertStatus() {
		return alertStatus;
	}

	public void setAlertStatus(AlertStatus alertStatus) {
		this.alertStatus = alertStatus;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "alert_severity_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public AlertSeverity getAlertSeverity() {
		return alertSeverity;
	}

	public void setAlertSeverity(AlertSeverity alertSeverity) {
		this.alertSeverity = alertSeverity;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public UserAddress getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(UserAddress userAddress) {
		this.userAddress = userAddress;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "impact_level_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public RegionScopes getRegionScopes() {
		return regionScopes;
	}

	public void setRegionScopes(RegionScopes regionScopes) {
		this.regionScopes = regionScopes;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_type_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public AlertUserType getAlertUserType() {
		return alertUserType;
	}

	public void setAlertUserType(AlertUserType alertUserType) {
		this.alertUserType = alertUserType;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "alert_source_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public AlertSource getAlertSource() {
		return alertSource;
	}

	public void setAlertSource(AlertSource alertSource) {
		this.alertSource = alertSource;
	}
	@Column(name = "alert_source_id")
	public Long getAlertSourceId() {
		return alertSourceId;
	}

	public void setAlertSourceId(Long alertSourceId) {
		this.alertSourceId = alertSourceId;
	}
	
	

}
