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
@Table(name ="appointment_lable_status")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppointmentLableStatus extends BaseModel {
	
	private Long appointmentLableStatusId;
	private String status;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "appointment_lable_status_id", unique = true, nullable = false)
	public Long getAppointmentLableStatusId() {
		return appointmentLableStatusId;
	}
	public void setAppointmentLableStatusId(Long appointmentLableStatusId) {
		this.appointmentLableStatusId = appointmentLableStatusId;
	}
	@Column(name = "status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
