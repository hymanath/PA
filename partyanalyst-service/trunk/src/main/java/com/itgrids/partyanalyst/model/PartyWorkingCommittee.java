/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on August 09, 2010
 */

package com.itgrids.partyanalyst.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "party_working_committee")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PartyWorkingCommittee implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long  partyWorkingCommitteeId;
	private Party party; 
	private String committeeName;
	private Set<PartyWorkingCommitteeDesignation> partyWkgCommitteeDesignations = new HashSet<PartyWorkingCommitteeDesignation>();  
	private CadreLevel regionLevel;
	private Long regionLevelValue;
	
	
	public PartyWorkingCommittee() {
		super();		
	}
	public PartyWorkingCommittee(Long partyWorkingCommitteeId, Party party,
			String committeeName) {
		super();
		this.partyWorkingCommitteeId = partyWorkingCommitteeId;
		this.party = party;
		this.committeeName = committeeName;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "party_working_committee_id", unique = true, nullable = false)
	public Long getPartyWorkingCommitteeId() {
		return partyWorkingCommitteeId;
	}
	public void setPartyWorkingCommitteeId(Long partyWorkingCommitteeId) {
		this.partyWorkingCommitteeId = partyWorkingCommitteeId;
	}	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="party_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Party getParty() {
		return party;
	}
	public void setParty(Party party) {
		this.party = party;
	}
	
	@Column(name = "committee_name", length = 100)
	public String getCommitteeName() {
		return committeeName;
	}
	public void setCommitteeName(String committeeName) {
		this.committeeName = committeeName;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "partyWorkingCommittee")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<PartyWorkingCommitteeDesignation> getPartyWkgCommitteeDesignations() {
		return partyWkgCommitteeDesignations;
	}
	public void setPartyWkgCommitteeDesignations(
			Set<PartyWorkingCommitteeDesignation> partyWkgCommitteeDesignations) {
		this.partyWkgCommitteeDesignations = partyWkgCommitteeDesignations;
	}
		
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "region_level")
	public CadreLevel getRegionLevel() {
		return regionLevel;
	}
	public void setRegionLevel(CadreLevel regionLevel) {
		this.regionLevel = regionLevel;
	}
	
	@Column(name = "region_level_value")
	public Long getRegionLevelValue() {
		return regionLevelValue;
	}
	public void setRegionLevelValue(Long regionLevelValue) {
		this.regionLevelValue = regionLevelValue;
	}
	
	
	
	
	
	
	

	
	
}
