package com.itgrids.partyanalyst.model;

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

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;
/*
 * Author: Santosh Kumar Verma
 */
@Entity
@Table(name = "alert_verification_user")
public class AlertVerificationUser {

	private Long alertVerificationUserId;
	private Long alertId;
	private Long verificationUserId;
	private Long assignedUserId;
	private Date assignedTime;
	private String isDeleted;
	
	private User verificationUser;
	private User assignedUser;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "alert_verification_user_id",unique = true,nullable = false)
	public Long getAlertVerificationUserId() {
		return alertVerificationUserId;
	}
	public void setAlertVerificationUserId(Long alertVerificationUserId) {
		this.alertVerificationUserId = alertVerificationUserId;
	}
	@Column(name = "alert_id")
	public Long getAlertId() {
		return alertId;
	}
	public void setAlertId(Long alertId) {
		this.alertId = alertId;
	}
	@Column(name = "verification_user_id")
	public Long getVerificationUserId() {
		return verificationUserId;
	}
	public void setVerificationUserId(Long verificationUserId) {
		this.verificationUserId = verificationUserId;
	}
	@Column(name = "assigned_user_id")
	public Long getAssignedUserId() {
		return assignedUserId;
	}
	public void setAssignedUserId(Long assignedUserId) {
		this.assignedUserId = assignedUserId;
	}
	@Column(name = "assigned_time")
	public Date getAssignedTime() {
		return assignedTime;
	}
	public void setAssignedTime(Date assignedTime) {
		this.assignedTime = assignedTime;
	}
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY )
	@JoinColumn(name = "verification_user_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getVerificationUser() {
		return verificationUser;
	}
	public void setVerificationUser(User verificationUser) {
		this.verificationUser = verificationUser;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY )
	@JoinColumn(name = "assigned_user_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getAssignedUser() {
		return assignedUser;
	}
	public void setAssignedUser(User assignedUser) {
		this.assignedUser = assignedUser;
	}
	
	
}
