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
@Table(name = "alert_department_status")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AlertDepartmentStatus extends BaseModel implements Serializable{

	private Long alertDepartmentStatusId;
	private String isDefault;
	private Long alertTypeId;
	private Long alertStatusId;
	private Long govtDepartmentId;
	
	private GovtDepartment govtDepartment;
	private AlertType alertType;
	private AlertStatus alertStatus;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "alert_department_status_id", unique = true, nullable = false)
	public Long getAlertDepartmentStatusId() {
		return alertDepartmentStatusId;
	}
	public void setAlertDepartmentStatusId(Long alertDepartmentStatusId) {
		this.alertDepartmentStatusId = alertDepartmentStatusId;
	}
	
	@Column(name = "govt_department_id")
	public Long getGovtDepartmentId() {
		return govtDepartmentId;
	}
	public void setGovtDepartmentId(Long govtDepartmentId) {
		this.govtDepartmentId = govtDepartmentId;
	}
	
	@Column(name = "is_default")
	public String getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	
	@Column(name = "alert_type_id")
	public Long getAlertTypeId() {
		return alertTypeId;
	}
	public void setAlertTypeId(Long alertTypeId) {
		this.alertTypeId = alertTypeId;
	}
	
	@Column(name = "alert_status_id")
	public Long getAlertStatusId() {
		return alertStatusId;
	}
	public void setAlertStatusId(Long alertStatusId) {
		this.alertStatusId = alertStatusId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="alert_type_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AlertType getAlertType() {
		return alertType;
	}
	public void setAlertType(AlertType alertType) {
		this.alertType = alertType;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="alert_status_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AlertStatus getAlertStatus() {
		return alertStatus;
	}
	public void setAlertStatus(AlertStatus alertStatus) {
		this.alertStatus = alertStatus;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="govt_department_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public GovtDepartment getGovtDepartment() {
		return govtDepartment;
	}
	public void setGovtDepartment(GovtDepartment govtDepartment) {
		this.govtDepartment = govtDepartment;
	}
}
