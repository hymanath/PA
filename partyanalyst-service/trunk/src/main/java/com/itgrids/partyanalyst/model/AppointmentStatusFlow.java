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
@Table(name = "appointment_status_flow")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppointmentStatusFlow extends BaseModel implements Serializable{
	
	private Long appointmentStatusFlowId;
	private Long fromStatusId;
	private Long toStatusId;
	
	private AppointmentStatus fromStatus;
	private AppointmentStatus toStatus;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "appointment_status_flow_id", unique = true, nullable = false)
	public Long getAppointmentStatusFlowId() {
		return appointmentStatusFlowId;
	}
	public void setAppointmentStatusFlowId(Long appointmentStatusFlowId) {
		this.appointmentStatusFlowId = appointmentStatusFlowId;
	}
	
	@Column(name="from_status_id")
	public Long getFromStatusId() {
		return fromStatusId;
	}
	public void setFromStatusId(Long fromStatusId) {
		this.fromStatusId = fromStatusId;
	}
	
	
	@Column(name="to_status_id")
	public Long getToStatusId() {
		return toStatusId;
	}
	public void setToStatusId(Long toStatusId) {
		this.toStatusId = toStatusId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="from_status_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AppointmentStatus getFromStatus() {
		return fromStatus;
	}
	public void setFromStatus(AppointmentStatus fromStatus) {
		this.fromStatus = fromStatus;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="to_status_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AppointmentStatus getToStatus() {
		return toStatus;
	}
	public void setToStatus(AppointmentStatus toStatus) {
		this.toStatus = toStatus;
	}
	
	
}
