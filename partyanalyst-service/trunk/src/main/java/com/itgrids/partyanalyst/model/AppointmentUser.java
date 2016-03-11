package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name ="appointment_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppointmentUser extends BaseModel {
	
	private Long 	appointmenUserId;
	private String name;
	private String mobile;
  
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "appointment_user_id", unique = true, nullable = false)
	public Long getAppointmenUserId() {
		return appointmenUserId;
	}
	public void setAppointmenUserId(Long appointmenUserId) {
		this.appointmenUserId = appointmenUserId;
	}
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "mobile")
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	}
