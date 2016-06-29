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
@Table(name = "alert_user_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AlertUserType extends BaseModel implements Serializable {
	private Long alertUserTypeId;
	private String userType;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "alert_user_type_id", unique = true, nullable = false)
	public Long getAlertUserTypeId() {
		return alertUserTypeId;
	}

	public void setAlertUserTypeId(Long alertUserTypeId) {
		this.alertUserTypeId = alertUserTypeId;
	}

	@Column(name = "user_type")
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

}
