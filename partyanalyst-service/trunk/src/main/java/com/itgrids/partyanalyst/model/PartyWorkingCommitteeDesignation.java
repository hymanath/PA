/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on December 12, 2009
 */
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

/**
 * PartyWorkingCommittee entity. 
 * @author <a href="mailto:raghavenderprasad@gmail.com">Raghav</a>
 */
@Entity
@Table(name = "party_wkg_committee_designation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PartyWorkingCommitteeDesignation implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long partyWkgCommitteeDesignationId;
	
	private PartyWorkingCommittee partyWorkingCommittee;
	private String  designation;
	
	
	
	public PartyWorkingCommitteeDesignation() {
		super();
		
	}

	public PartyWorkingCommitteeDesignation(
			Long partyWkgCommitteeDesignationId,
			PartyWorkingCommittee partyWorkingCommittee, String designation) {
		super();
		this.partyWkgCommitteeDesignationId = partyWkgCommitteeDesignationId;
		this.partyWorkingCommittee = partyWorkingCommittee;
		this.designation = designation;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "party_wkg_committee_designation_id", unique = true, nullable = false)
	public Long getPartyWkgCommitteeDesignationId() {
		return partyWkgCommitteeDesignationId;
	}

	public void setPartyWkgCommitteeDesignationId(
			Long partyWkgCommitteeDesignationId) {
		this.partyWkgCommitteeDesignationId = partyWkgCommitteeDesignationId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="party_working_committee_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public PartyWorkingCommittee getPartyWorkingCommittee() {
		return partyWorkingCommittee;
	}
	public void setPartyWorkingCommittee(PartyWorkingCommittee partyWorkingCommittee) {
		this.partyWorkingCommittee = partyWorkingCommittee;
	}
	
	@Column(name = "designation", length = 100)
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	
}
