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
@Table(name = "appointment_candidate_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppointmentCandidateType extends BaseModel{

	private Long appointmentCandidateTypeId;
	private String candidateType;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "appointment_candidate_type_id", unique = true, nullable = false)
	public Long getAppointmentCandidateTypeId() {
		return appointmentCandidateTypeId;
	}
	public void setAppointmentCandidateTypeId(Long appointmentCandidateTypeId) {
		this.appointmentCandidateTypeId = appointmentCandidateTypeId;
	}
	@Column(name = "candidate_type")
	public String getCandidateType() {
		return candidateType;
	}
	public void setCandidateType(String candidateType) {
		this.candidateType = candidateType;
	}
	
	
}
