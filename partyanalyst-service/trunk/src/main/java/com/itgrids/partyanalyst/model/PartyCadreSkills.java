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
 * PartyCadreSkills entity. 
 * @author <a href="mailto:raghavenderprasad@gmail.com">Raghav</a>
 */
@Entity
@Table(name = "party_cadre_skills")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PartyCadreSkills implements java.io.Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long partyCadreSkillId;
	private String skill;
	private Party party;
	private Set<CadreSkills> cadreSkills =  new HashSet<CadreSkills>();
	
	public PartyCadreSkills() {
		super();	
	}

	public PartyCadreSkills(Long partyCadreSkillId, String skill, Party party) {
		
		this.partyCadreSkillId = partyCadreSkillId;
		this.skill = skill;
		this.party = party;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "party_cadre_skill_id", unique = true, nullable = false)
	public Long getPartyCadreSkillId() {
		return partyCadreSkillId;
	}

	public void setPartyCadreSkillId(Long partyCadreSkillId) {
		this.partyCadreSkillId = partyCadreSkillId;
	}

	@Column(name = "skill", length = 100)
	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "partyCadreSkills")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<CadreSkills> getCadreSkills() {
		return cadreSkills;
	}

	public void setCadreSkills(Set<CadreSkills> cadreSkills) {
		this.cadreSkills = cadreSkills;
	}
	
	
	
	
	
	
	
	
	
}
