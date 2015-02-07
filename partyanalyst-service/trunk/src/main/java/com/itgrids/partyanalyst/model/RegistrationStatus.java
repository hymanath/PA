package com.itgrids.partyanalyst.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "registration_status")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RegistrationStatus {

	private Long registrationStatusId;
	
	private String userId;
	
	private Date date;
	
	private String registrationCount;
	
	private String registrationFailedCount;
	
	private Date insertedTime;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "registration_status_id", unique = true, nullable = false)
	public Long getRegistrationStatusId() {
		return registrationStatusId;
	}

	public void setRegistrationStatusId(Long registrationStatusId) {
		this.registrationStatusId = registrationStatusId;
	}

	@Column(name = "date")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "registrationCount")
	public String getRegistrationCount() {
		return registrationCount;
	}

	public void setRegistrationCount(String registrationCount) {
		this.registrationCount = registrationCount;
	}

	@Column(name = "registrationFailedCount")
	public String getRegistrationFailedCount() {
		return registrationFailedCount;
	}

	public void setRegistrationFailedCount(String registrationFailedCount) {
		this.registrationFailedCount = registrationFailedCount;
	}

	@Column(name = "user_id")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}

	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	
}
