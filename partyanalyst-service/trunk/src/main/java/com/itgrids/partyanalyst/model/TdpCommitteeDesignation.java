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
@Table(name = "tdp_committee_designation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdpCommitteeDesignation {
	private Long tdpCommitteeDesignationId;
	private String designation;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tdp_committee_designation_id", unique = true, nullable = false)
	public Long getTdpCommitteeDesignationId() {
		return tdpCommitteeDesignationId;
	}
	
	public void setTdpCommitteeDesignationId(Long tdpCommitteeDesignationId) {
		this.tdpCommitteeDesignationId = tdpCommitteeDesignationId;
	}
	
	@Column(name = "designation")
	public String getDesignation() {
		return designation;
	}
	
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	
}
