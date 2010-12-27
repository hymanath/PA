/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on August 25, 2010
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
 * cadre_participated_training_camps entity. 
 * @author <a href="mailto:raghavenderprasad@gmail.com">Raghav</a>
 */
@Entity
@Table(name = "cadre_participated_training_camps")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CadreParticipatedTrainingCamps implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long cadreParticipatedTrainingCampId;
	private Cadre cadre;
	private PartyTrainingCamps partyTrainingCamps;
	
	public CadreParticipatedTrainingCamps() {
		super();
		
	}

	public CadreParticipatedTrainingCamps(Long cadreParticipatedTrainingCampId,
			Cadre cadre, PartyTrainingCamps partyTrainingCamps) {
		super();
		this.cadreParticipatedTrainingCampId = cadreParticipatedTrainingCampId;
		this.cadre = cadre;
		this.partyTrainingCamps = partyTrainingCamps;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cadre_participated_training_camp_id", nullable = false, unique = true)
	public Long getCadreParticipatedTrainingCampId() {
		return cadreParticipatedTrainingCampId;
	}

	public void setCadreParticipatedTrainingCampId(
			Long cadreParticipatedTrainingCampId) {
		this.cadreParticipatedTrainingCampId = cadreParticipatedTrainingCampId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="cadre_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Cadre getCadre() {
		return cadre;
	}

	public void setCadre(Cadre cadre) {
		this.cadre = cadre;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="party_training_camps_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public PartyTrainingCamps getPartyTrainingCamps() {
		return partyTrainingCamps;
	}

	public void setPartyTrainingCamps(PartyTrainingCamps partyTrainingCamps) {
		this.partyTrainingCamps = partyTrainingCamps;
	}
	
	
	
	
	
	
}
