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
@Table(name = "tdp_committee")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdpCommittee {
	private Long tdpCommitteeId;
	private String name;
	private TdpBasicCommittee tdpBasicCommittee;
	private Long tdpBasicCommitteeId;
	private TdpCommitteeLevel tdpCommitteeLevel;
	private Long tdpCommitteeLevelId;
	private Long tdpCommitteeLevelValue;
	private String isCommitteeConfirmed;
	private String state;
	private Constituency constituency;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tdp_committee_id", unique = true, nullable = false)
	public Long getTdpCommitteeId() {
		return tdpCommitteeId;
	}
	
	public void setTdpCommitteeId(Long tdpCommitteeId) {
		this.tdpCommitteeId = tdpCommitteeId;
	}
	
	@Column(name = "name")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "tdp_basic_committee_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpBasicCommittee getTdpBasicCommittee() {
		return tdpBasicCommittee;
	}
	
	public void setTdpBasicCommittee(TdpBasicCommittee tdpBasicCommittee) {
		this.tdpBasicCommittee = tdpBasicCommittee;
	}
	
	@Column(name = "tdp_basic_committee_id")
	public Long getTdpBasicCommitteeId() {
		return tdpBasicCommitteeId;
	}
	
	public void setTdpBasicCommitteeId(Long tdpBasicCommitteeId) {
		this.tdpBasicCommitteeId = tdpBasicCommitteeId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "tdp_committee_level_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCommitteeLevel getTdpCommitteeLevel() {
		return tdpCommitteeLevel;
	}
	
	public void setTdpCommitteeLevel(TdpCommitteeLevel tdpCommitteeLevel) {
		this.tdpCommitteeLevel = tdpCommitteeLevel;
	}
	
	@Column(name = "tdp_committee_level_id")
	public Long getTdpCommitteeLevelId() {
		return tdpCommitteeLevelId;
	}
	
	public void setTdpCommitteeLevelId(Long tdpCommitteeLevelId) {
		this.tdpCommitteeLevelId = tdpCommitteeLevelId;
	}
	
	@Column(name = "tdp_committee_level_value")
	public Long getTdpCommitteeLevelValue() {
		return tdpCommitteeLevelValue;
	}
	
	public void setTdpCommitteeLevelValue(Long tdpCommitteeLevelValue) {
		this.tdpCommitteeLevelValue = tdpCommitteeLevelValue;
	}
	
	@Column(name = "is_committee_confirmed")
	public String getIsCommitteeConfirmed() {
		return isCommitteeConfirmed;
	}

	public void setIsCommitteeConfirmed(String isCommitteeConfirmed) {
		this.isCommitteeConfirmed = isCommitteeConfirmed;
	}

	@Column(name = "state")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "constituency_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Constituency getConstituency() {
		return constituency;
	}

	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}
}

