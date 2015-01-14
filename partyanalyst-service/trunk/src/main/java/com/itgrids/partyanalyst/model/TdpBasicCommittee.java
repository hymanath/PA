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
@Table(name = "tdp_basic_committee")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdpBasicCommittee {
	private Long tdpBasicCommitteeId;
	private String name;
	private TdpCommitteeType tdpCommitteeType;
	private Long tdpCommitteeTypeId;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tdp_basic_committee_id", unique = true, nullable = false)
	public Long getTdpBasicCommitteeId() {
		return tdpBasicCommitteeId;
	}
	
	public void setTdpBasicCommitteeId(Long tdpBasicCommitteeId) {
		this.tdpBasicCommitteeId = tdpBasicCommitteeId;
	}
	
	@Column(name = "name")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "tdp_committee_type_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCommitteeType getTdpCommitteeType() {
		return tdpCommitteeType;
	}
	
	public void setTdpCommitteeType(TdpCommitteeType tdpCommitteeType) {
		this.tdpCommitteeType = tdpCommitteeType;
	}
	
	@Column(name = "tdp_committee_type_id")
	public Long getTdpCommitteeTypeId() {
		return tdpCommitteeTypeId;
	}
	
	public void setTdpCommitteeTypeId(Long tdpCommitteeTypeId) {
		this.tdpCommitteeTypeId = tdpCommitteeTypeId;
	}
	
	
	
}
