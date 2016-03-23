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
@Table(name ="appointment_priority")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppointmentPriority extends BaseModel {
	
	private Long appointmentPriorityId;
	private String priority;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "appointment_priority_id", unique = true, nullable = false)
	public Long getAppointmentPriorityId() {
		return appointmentPriorityId;
	}
	public void setAppointmentPriorityId(Long appointmentPriorityId) {
		this.appointmentPriorityId = appointmentPriorityId;
	}
	@Column(name = "priority")
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}

}
