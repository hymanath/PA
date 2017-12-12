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
@Table(name = "tdp_committee_level_hierarchy")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdpCommitteeLevelHierarchy {

	private Long tdpCommitteeLevelHierarchyId;
	private Long tdpCommitteeLevelId;
	private Long committeeUpperLevelId;
	
	private TdpCommitteeLevel tdpCommitteeLevel;
	private TdpCommitteeLevel committeeUpperLevel;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tdp_committee_level_hierarchy_id", unique = true, nullable = false)
	public Long getTdpCommitteeLevelHierarchyId() {
		return tdpCommitteeLevelHierarchyId;
	}
	public void setTdpCommitteeLevelHierarchyId(Long tdpCommitteeLevelHierarchyId) {
		this.tdpCommitteeLevelHierarchyId = tdpCommitteeLevelHierarchyId;
	}
	@Column(name = "tdp_committee_level_id")
	public Long getTdpCommitteeLevelId() {
		return tdpCommitteeLevelId;
	}
	public void setTdpCommitteeLevelId(Long tdpCommitteeLevelId) {
		this.tdpCommitteeLevelId = tdpCommitteeLevelId;
	}
	@Column(name = "committee_upper_level_id")
	public Long getCommitteeUpperLevelId() {
		return committeeUpperLevelId;
	}
	public void setCommitteeUpperLevelId(Long committeeUpperLevelId) {
		this.committeeUpperLevelId = committeeUpperLevelId;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="tdp_committee_level_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCommitteeLevel getTdpCommitteeLevel() {
		return tdpCommitteeLevel;
	}
	public void setTdpCommitteeLevel(TdpCommitteeLevel tdpCommitteeLevel) {
		this.tdpCommitteeLevel = tdpCommitteeLevel;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="committee_upper_level_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCommitteeLevel getCommitteeUpperLevel() {
		return committeeUpperLevel;
	}
	public void setCommitteeUpperLevel(TdpCommitteeLevel committeeUpperLevel) {
		this.committeeUpperLevel = committeeUpperLevel;
	}
	
	
}
