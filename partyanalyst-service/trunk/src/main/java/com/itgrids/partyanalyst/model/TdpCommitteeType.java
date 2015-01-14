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
@Table(name = "tdp_committee_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdpCommitteeType {
	
	private Long tdpCommitteeTypeId;
	private String committeeType;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tdp_committee_type_id", unique = true, nullable = false)
	public Long getTdpCommitteeTypeId() {
		return tdpCommitteeTypeId;
	}

	public void setTdpCommitteeTypeId(Long tdpCommitteeTypeId) {
		this.tdpCommitteeTypeId = tdpCommitteeTypeId;
	}

	@Column(name = "committee_type")
	public String getCommitteeType() {
		return committeeType;
	}

	public void setCommitteeType(String committeeType) {
		this.committeeType = committeeType;
	}

}
