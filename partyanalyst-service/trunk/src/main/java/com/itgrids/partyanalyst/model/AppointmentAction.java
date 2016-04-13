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
@Table(name ="appointment_action")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppointmentAction extends BaseModel {

	private Long appointmentActionId;
	private String action;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "appointment_action_id", unique = true, nullable = false)
	public Long getAppointmentActionId() {
		return appointmentActionId;
	}
	public void setAppointmentActionId(Long appointmentActionId) {
		this.appointmentActionId = appointmentActionId;
	}
	@Column(name = "action")
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
}
