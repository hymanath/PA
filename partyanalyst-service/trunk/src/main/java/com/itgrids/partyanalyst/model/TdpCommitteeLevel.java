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
@Table(name = "tdp_committee_level")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdpCommitteeLevel {
	private Long tdpCommitteeLevelId;
	private String tdpCommitteeLevel;
	private Long orderNo;
	private Long appointmentOrder;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tdp_committee_level_id", unique = true, nullable = false)
	public Long getTdpCommitteeLevelId() {
		return tdpCommitteeLevelId;
	}

	public void setTdpCommitteeLevelId(Long tdpCommitteeLevelId) {
		this.tdpCommitteeLevelId = tdpCommitteeLevelId;
	}

	@Column(name = "tdp_committee_level")
	public String getTdpCommitteeLevel() {
		return tdpCommitteeLevel;
	}

	public void setTdpCommitteeLevel(String tdpCommitteeLevel) {
		this.tdpCommitteeLevel = tdpCommitteeLevel;
	}

	@Column(name = "order_no")
	public Long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
    @Column(name = "appointment_order")
	public Long getAppointmentOrder() {
		return appointmentOrder;
	}

	public void setAppointmentOrder(Long appointmentOrder) {
		this.appointmentOrder = appointmentOrder;
	}
	
}
