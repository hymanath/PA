package com.itgrids.partyanalyst.model;

import java.io.Serializable;

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
@Table(name = "cadre_committee_role")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CadreCommitteeRole implements Serializable{

	
	private static final long serialVersionUID = 7309800970734005327L;
	private Long 				cadreCommitteeRoleId;
	private Long 				cadreCommitteeLevelId;
	private Long 				cadreCommiteeId;
	private Long 				cadreRolesId;
	
	private CadreCommitteeLevel cadreCommitteeLevel;
	private CadreCommittee      cadreCommittee;
	private CadreRoles 			cadreRoles;
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name = "cadre_committee_role_id", unique = true, nullable = false)
	public Long getCadreCommitteeRoleId() {
		return cadreCommitteeRoleId;
	}
	public void setCadreCommitteeRoleId(Long cadreCommitteeRoleId) {
		this.cadreCommitteeRoleId = cadreCommitteeRoleId;
	}
	
	@Column(name="cadre_committee_level_id")
	public Long getCadreCommitteeLevelId() {
		return cadreCommitteeLevelId;
	}
	public void setCadreCommitteeLevelId(Long cadreCommitteeLevelId) {
		this.cadreCommitteeLevelId = cadreCommitteeLevelId;
	}
	

	@Column(name="cadre_roles_id")
	public Long getCadreRolesId() {
		return cadreRolesId;
	}
	public Long getCadreCommiteeId() {
		return cadreCommiteeId;
	}
	
	@Column(name="cadre_committee_id")
	public void setCadreCommiteeId(Long cadreCommiteeId) {
		this.cadreCommiteeId = cadreCommiteeId;
	}
	public void setCadreRolesId(Long cadreRolesId) {
		this.cadreRolesId = cadreRolesId;
	}
	
	
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "cadre_committee_level_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CadreCommitteeLevel getCadreCommitteeLevel() {
		return cadreCommitteeLevel;
	}
	public void setCadreCommitteeLevel(CadreCommitteeLevel cadreCommitteeLevel) {
		this.cadreCommitteeLevel = cadreCommitteeLevel;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "cadre_committee_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CadreCommittee getCadreCommittee() {
		return cadreCommittee;
	}
	public void setCadreCommittee(CadreCommittee cadreCommittee) {
		this.cadreCommittee = cadreCommittee;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "cadre_roles_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CadreRoles getCadreRoles() {
		return cadreRoles;
	}
	public void setCadreRoles(CadreRoles cadreRoles) {
		this.cadreRoles = cadreRoles;
	}
	
	
	
}
