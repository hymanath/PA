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
@Table(name = "verification_status")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VerificationStatus extends BaseModel implements Serializable {

	private Long verificationStatusId;
	private Long alertId;
	private Long actionTypeStatusId;
	private Long alertVerificationUserTypeId;
	private String isDeleted;
	private Long insertedBy;
	private Date insertedTime;
	
	private User insertedUser;
	private Alert alert;
	private  ActionTypeStatus  actionTypeStatus;
	private AlertVerificationUserType alertVerificationUserType;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "verification_status_id", unique = true, nullable = false)
	public Long getVerificationStatusId() {
		return verificationStatusId;
	}
	public void setVerificationStatusId(Long verificationStatusId) {
		this.verificationStatusId = verificationStatusId;
	}
	@Column(name="alert_id")
	public Long getAlertId() {
		return alertId;
	}
	public void setAlertId(Long alertId) {
		this.alertId = alertId;
	}
	@Column(name="action_type_status_id")
	public Long getActionTypeStatusId() {
		return actionTypeStatusId;
	}
	public void setActionTypeStatusId(Long actionTypeStatusId) {
		this.actionTypeStatusId = actionTypeStatusId;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Column(name="inserted_by")
	public Long getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
	}
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	@Column(name="alert_verification_user_type_id")
	public Long getAlertVerificationUserTypeId() {
		return alertVerificationUserTypeId;
	}
	public void setAlertVerificationUserTypeId(Long alertVerificationUserTypeId) {
		this.alertVerificationUserTypeId = alertVerificationUserTypeId;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY )
	@JoinColumn(name = "alert_verification_user_type_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AlertVerificationUserType getAlertVerificationUserType() {
		return alertVerificationUserType;
	}
	public void setAlertVerificationUserType(
			AlertVerificationUserType alertVerificationUserType) {
		this.alertVerificationUserType = alertVerificationUserType;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY )
	@JoinColumn(name = "inserted_by" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getInsertedUser() {
		return insertedUser;
	}
	public void setInsertedUser(User insertedUser) {
		this.insertedUser = insertedUser;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY )
	@JoinColumn(name = "alert_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Alert getAlert() {
		return alert;
	}
	public void setAlert(Alert alert) {
		this.alert = alert;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY )
	@JoinColumn(name = "action_type_status_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ActionTypeStatus getActionTypeStatus() {
		return actionTypeStatus;
	}
	public void setActionTypeStatus(ActionTypeStatus actionTypeStatus) {
		this.actionTypeStatus = actionTypeStatus;
	}
	
}
