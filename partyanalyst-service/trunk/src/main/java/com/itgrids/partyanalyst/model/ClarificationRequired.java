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
@Table(name = "clarification_required")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ClarificationRequired extends BaseModel implements Serializable{
	
	private Long clarificationRequiredId;
	private Long alertId;
	private Long alertClarificationStatusId;
	private String isRequired;
	private Date insertedTime;
	private Date updatedTime;
	private Long insertedBy;
	private Long updatedBy;
	private String isDeleted;
	
	private Alert alert;
	private AlertClarificationStatus alertClarificationStatus;

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "clarification_required_id", unique = true, nullable = false)
	public Long getClarificationRequiredId() {
		return clarificationRequiredId;
	}
	public void setClarificationRequiredId(Long clarificationRequiredId) {
		this.clarificationRequiredId = clarificationRequiredId;
	}
	
	@Column(name="alert_id")
	public Long getAlertId() {
		return alertId;
	}
	public void setAlertId(Long alertId) {
		this.alertId = alertId;
	}
	
	@Column(name="is_required")
	public String getIsRequired() {
		return isRequired;
	}
	public void setIsRequired(String isRequired) {
		this.isRequired = isRequired;
	}
	
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@Column(name="updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	@Column(name="inserted_by")
	public Long getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
	}
	
	@Column(name="updated_by")
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="alert_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Alert getAlert() {
		return alert;
	}
	public void setAlert(Alert alert) {
		this.alert = alert;
	}
	
	@Column(name="alert_clarification_status_id")
	public Long getAlertClarificationStatusId() {
		return alertClarificationStatusId;
	}
	public void setAlertClarificationStatusId(Long alertClarificationStatusId) {
		this.alertClarificationStatusId = alertClarificationStatusId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="alert_clarification_status_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AlertClarificationStatus getAlertClarificationStatus() {
		return alertClarificationStatus;
	}
	public void setAlertClarificationStatus(
			AlertClarificationStatus alertClarificationStatus) {
		this.alertClarificationStatus = alertClarificationStatus;
	}
	
		
	
	
}
