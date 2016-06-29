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
@Table(name = "alert_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AlertUser extends BaseModel implements Serializable {
	private Long alertUserId;
	private Long alertUserTypeId;
	private Long userId;
	private Long isDeleted;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "alert_user_id", unique = true, nullable = false)
	public Long getAlertUserId() {
		return alertUserId;
	}

	public void setAlertUserId(Long alertUserId) {
		this.alertUserId = alertUserId;
	}

	@Column(name = "alert_user_type_id")
	public Long getAlertUserTypeId() {
		return alertUserTypeId;
	}

	public void setAlertUserTypeId(Long alertUserTypeId) {
		this.alertUserTypeId = alertUserTypeId;
	}

	@Column(name = "user_id")
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "is_deleted")
	public Long getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Long isDeleted) {
		this.isDeleted = isDeleted;
	}

}
