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
@Table(name="appointment_candidate_designation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppointmentCandidateDesignation extends BaseModel {

	private Long appointmentCandidateDesignationId;
	private String designation;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "appointment_candidate_designation_id", unique = true, nullable = false)
	public Long getAppointmentCandidateDesignationId() {
		return appointmentCandidateDesignationId;
	}
	public void setAppointmentCandidateDesignationId(
			Long appointmentCandidateDesignationId) {
		this.appointmentCandidateDesignationId = appointmentCandidateDesignationId;
	}
	@Column(name = "designation")
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
}
