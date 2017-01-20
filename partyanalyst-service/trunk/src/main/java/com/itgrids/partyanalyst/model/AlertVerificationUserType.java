package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
@Entity
@Table(name = "alert_verification_user_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AlertVerificationUserType extends BaseModel implements java.io.Serializable {
	
	
	private Long alertVerificationUserTypeId;
	private String userType;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "alert_verification_user_type_id", unique = true, nullable = false)
	public Long getAlertVerificationUserTypeId() {
		return alertVerificationUserTypeId;
	}
	public void setAlertVerificationUserTypeId(Long alertVerificationUserTypeId) {
		this.alertVerificationUserTypeId = alertVerificationUserTypeId;
	}
	@Column(name="user_type")
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
}
