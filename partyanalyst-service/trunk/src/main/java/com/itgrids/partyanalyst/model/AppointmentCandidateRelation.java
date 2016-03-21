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
@Table(name="appointment_candidate_relation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppointmentCandidateRelation extends BaseModel {
	private Long appointmentCandidateRelationId;
	private Long appointmentCandidateId;
	private Long appointmentId;
	
	private AppointmentCandidate appointmentCandidate;
	private Appointment appointment;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "appointment_candidate_relation_id", unique = true, nullable = false)
	public Long getAppointmentCandidateRelationId() {
		return appointmentCandidateRelationId;
	}
	public void setAppointmentCandidateRelationId(
			Long appointmentCandidateRelationId) {
		this.appointmentCandidateRelationId = appointmentCandidateRelationId;
	}
	@Column(name = "appointment_candidate_id")
	public Long getAppointmentCandidateId() {
		return appointmentCandidateId;
	}
	public void setAppointmentCandidateId(Long appointmentCandidateId) {
		this.appointmentCandidateId = appointmentCandidateId;
	}
	@Column(name = "appointment_id")
	public Long getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="appointment_candidate_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AppointmentCandidate getAppointmentCandidate() {
		return appointmentCandidate;
	}
	public void setAppointmentCandidate(AppointmentCandidate appointmentCandidate) {
		this.appointmentCandidate = appointmentCandidate;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="appointment_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Appointment getAppointment() {
		return appointment;
	}
	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}
	
	
}
