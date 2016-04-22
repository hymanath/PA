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
@Table(name = "appointment_user_type_access_status")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppointmentUserTypeAccessStatus extends BaseModel implements Serializable{
	
	private Long  appointmentUserTypeAccessStatusId;
	private Long  appointmentUserTypeId;
	private Long  appointmentStatusId;
	
	private AppointmentUserType appointmentUserType;
	private AppointmentStatus appointmentStatus;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "appointment_user_type_access_status_id", unique = true, nullable = false)
	public Long getAppointmentUserTypeAccessStatusId() {
		return appointmentUserTypeAccessStatusId;
	}
	public void setAppointmentUserTypeAccessStatusId(
			Long appointmentUserTypeAccessStatusId) {
		this.appointmentUserTypeAccessStatusId = appointmentUserTypeAccessStatusId;
	}
	
	@Column(name="appointment_user_type_id")
	public Long getAppointmentUserTypeId() {
		return appointmentUserTypeId;
	}
	public void setAppointmentUserTypeId(Long appointmentUserTypeId) {
		this.appointmentUserTypeId = appointmentUserTypeId;
	}
	
	@Column(name="appointment_status_id")
	public Long getAppointmentStatusId() {
		return appointmentStatusId;
	}
	public void setAppointmentStatusId(Long appointmentStatusId) {
		this.appointmentStatusId = appointmentStatusId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="appointment_user_type_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AppointmentUserType getAppointmentUserType() {
		return appointmentUserType;
	}
	public void setAppointmentUserType(AppointmentUserType appointmentUserType) {
		this.appointmentUserType = appointmentUserType;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="appointment_status_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AppointmentStatus getAppointmentStatus() {
		return appointmentStatus;
	}
	public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}
	
	
	

	
}
