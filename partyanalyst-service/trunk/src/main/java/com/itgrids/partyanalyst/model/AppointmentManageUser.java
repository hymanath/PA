package com.itgrids.partyanalyst.model;

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
@Table(name = "appointment_manage_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppointmentManageUser extends BaseModel {
	private Long appointmentManageUserId;
	private Long appointmentUserId;
	private Long userId;
	
	private AppointmentUser appointmentUser;
	private User user;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "appointment_manage_user_id", unique = true, nullable = false)
	public Long getAppointmentManageUserId() {
		return appointmentManageUserId;
	}
	public void setAppointmentManageUserId(Long appointmentManageUserId) {
		this.appointmentManageUserId = appointmentManageUserId;
	}
	@Column(name = "appointment_user_id")
	public Long getAppointmentUserId() {
		return appointmentUserId;
	}
	public void setAppointmentUserId(Long appointmentUserId) {
		this.appointmentUserId = appointmentUserId;
	}
	@Column(name = "user_id")
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="appointment_user_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AppointmentUser getAppointmentUser() {
		return appointmentUser;
	}
	public void setAppointmentUser(AppointmentUser appointmentUser) {
		this.appointmentUser = appointmentUser;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
