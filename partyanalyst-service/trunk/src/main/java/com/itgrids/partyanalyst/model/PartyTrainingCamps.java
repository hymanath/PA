/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on August 25, 2010
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
 * PartyTrainingCamps entity. 
 * @author <a href="mailto:raghavenderprasad@gmail.com">Raghav</a>
 */
@Entity
@Table(name = "party_training_camps")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PartyTrainingCamps implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long partyTrainingCampsId;
	private String regionLevel; 
	private Party party;
	private Set<CadreParticipatedTrainingCamps> cadresParticipatedInTrainingCamps = new HashSet<CadreParticipatedTrainingCamps>();  
	
	public PartyTrainingCamps() {
		super();		
	}

	public PartyTrainingCamps(Long partyTrainingCampsId, String regionLevel,
			Party party) {
		super();
		this.partyTrainingCampsId = partyTrainingCampsId;
		this.regionLevel = regionLevel;
		this.party = party;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "party_training_camps_id", nullable = false, unique = true)
	public Long getPartyTrainingCampsId() {
		return partyTrainingCampsId;
	}

	public void setPartyTrainingCampsId(Long partyTrainingCampsId) {
		this.partyTrainingCampsId = partyTrainingCampsId;
	}

	@Column(name = "region_level", length = 100)
	public String getRegionLevel() {
		return regionLevel;
	}

	public void setRegionLevel(String regionLevel) {
		this.regionLevel = regionLevel;
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
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "partyTrainingCamps")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<CadreParticipatedTrainingCamps> getCadresParticipatedInTrainingCamps() {
		return cadresParticipatedInTrainingCamps;
	}

	public void setCadresParticipatedInTrainingCamps(
			Set<CadreParticipatedTrainingCamps> cadresParticipatedInTrainingCamps) {
		this.cadresParticipatedInTrainingCamps = cadresParticipatedInTrainingCamps;
	}
	
	
	
	
	
	
	

}
