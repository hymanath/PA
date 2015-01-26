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
@Table(name = "cadre_committee_change_designations")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CadreCommitteeChangeDesignations extends BaseModel implements Serializable
{
	private Long cadreCommitteeChangeDesignationsId;
	private TdpCommitteeMember tdpCommitteeMember;
	private TdpCommitteeRole currentRole;
	private TdpCommitteeRole newRole;
	private CadreCommitteeIncreasedPositions cadreCommitteeIncreasedPositions;
	
	
	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	 @Column(name="cadre_committee_change_designations_id", unique=true, nullable=false) 
	public Long getCadreCommitteeChangeDesignationsId() {
		return cadreCommitteeChangeDesignationsId;
	}
	public void setCadreCommitteeChangeDesignationsId(
			Long cadreCommitteeChangeDesignationsId) {
		this.cadreCommitteeChangeDesignationsId = cadreCommitteeChangeDesignationsId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="tdp_committee_member_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCommitteeMember getTdpCommitteeMember() {
		return tdpCommitteeMember;
	}
	public void setTdpCommitteeMember(TdpCommitteeMember tdpCommitteeMember) {
		this.tdpCommitteeMember = tdpCommitteeMember;
	}
	
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="current_role")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCommitteeRole getCurrentRole() {
		return currentRole;
	}
	public void setCurrentRole(TdpCommitteeRole currentRole) {
		this.currentRole = currentRole;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="new_role")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCommitteeRole getNewRole() {
		return newRole;
	}
	public void setNewRole(TdpCommitteeRole newRole) {
		this.newRole = newRole;
	}
	
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="cadre_committee_increased_positions_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CadreCommitteeIncreasedPositions getCadreCommitteeIncreasedPositions() {
		return cadreCommitteeIncreasedPositions;
	}
	public void setCadreCommitteeIncreasedPositions(
			CadreCommitteeIncreasedPositions cadreCommitteeIncreasedPositions) {
		this.cadreCommitteeIncreasedPositions = cadreCommitteeIncreasedPositions;
	}

}
