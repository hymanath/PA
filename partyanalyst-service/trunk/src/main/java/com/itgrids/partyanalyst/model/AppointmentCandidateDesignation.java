package com.itgrids.partyanalyst.model;

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
@Table(name="appointment_candidate_designation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppointmentCandidateDesignation extends BaseModel {

	private Long appointmentCandidateDesignationId;
	private String designation;
	private AppointmentCandidateType appointmentCandidateType;
	private Long appointmentCandidateTypeId;
	private Long orderNo;
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
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "appointment_candidate_type_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AppointmentCandidateType getAppointmentCandidateType() {
		return appointmentCandidateType;
	}
	public void setAppointmentCandidateType(AppointmentCandidateType appointmentCandidateType) {
		this.appointmentCandidateType = appointmentCandidateType;
	}
	@Column(name = "appointment_candidate_type_id")
	public Long getAppointmentCandidateTypeId() {
		return appointmentCandidateTypeId;
	}
	public void setAppointmentCandidateTypeId(Long appointmentCandidateTypeId) {
		this.appointmentCandidateTypeId = appointmentCandidateTypeId;
	}
	@Column(name = "order_no")
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	
	
}
