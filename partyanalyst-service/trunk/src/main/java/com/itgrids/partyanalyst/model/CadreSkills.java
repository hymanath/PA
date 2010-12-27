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
 * CadreSkills entity. 
 * @author <a href="mailto:raghavenderprasad@gmail.com">Raghav</a>
 */
@Entity
@Table(name = "cadre_skills")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CadreSkills implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long cadreSkillId;
	private PartyCadreSkills partyCadreSkills;
	private Cadre cadre;
	
	public CadreSkills() {
		super();
		
	}

	public CadreSkills(Long cadreSkillId, PartyCadreSkills partyCadreSkills,
			Cadre cadre) {
		super();
		this.cadreSkillId = cadreSkillId;
		this.partyCadreSkills = partyCadreSkills;
		this.cadre = cadre;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cadre_skill_id", nullable = false, unique = true)
	public Long getCadreSkillId() {
		return cadreSkillId;
	}

	public void setCadreSkillId(Long cadreSkillId) {
		this.cadreSkillId = cadreSkillId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="party_cadre_skill_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public PartyCadreSkills getPartyCadreSkills() {
		return partyCadreSkills;
	}

	public void setPartyCadreSkills(PartyCadreSkills partyCadreSkills) {
		this.partyCadreSkills = partyCadreSkills;
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
	
	
	
	
	
	
}
