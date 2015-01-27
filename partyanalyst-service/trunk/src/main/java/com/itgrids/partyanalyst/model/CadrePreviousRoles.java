package com.itgrids.partyanalyst.model;

import java.util.Date;

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
 * 
 * @author Prasad Thiragabathina
 *
 */

@Entity
@Table(name = "cadre_previous_roles")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CadrePreviousRoles {

	private Long 				cadrePreviousRolesId;
	private Long 				tdpCadreId;
/*	private Long 				cadreLevelId;
	private Long 				partyDesignationId;*/
	private Date 				fromDate;
	private Date 				toDate;
	private String				isDeleted;
	private Date				insertedDate;
	private Date 				updatedDate;
	private Long				cadreCommitteeRoleId;
	
	
	private TdpCadre 			tdpCadre;
	private CadreCommitteeRole  cadreCommitteeRole;
	private Long				committeeLocationId;
/*	private CadreLevel 			cadreLevel;
	private PartyDesignation    partyDesignation;*/
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cadre_previous_roles_id", unique = true, nullable = false)

	public Long getCadrePreviousRolesId() {
		return cadrePreviousRolesId;
	}
	public void setCadrePreviousRolesId(Long cadrePreviousRolesId) {
		this.cadrePreviousRolesId = cadrePreviousRolesId;
	}
	
	@Column(name="tdp_cadre_id")
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	
	/*@Column(name="cadre_level_id")
	public Long getCadreLevelId() {
		return cadreLevelId;
	}
	public void setCadreLevelId(Long cadreLevelId) {
		this.cadreLevelId = cadreLevelId;
	}
	
	@Column(name="party_designation_id")
	public Long getPartyDesignationId() {
		return partyDesignationId;
	}
	public void setPartyDesignationId(Long partyDesignationId) {
		this.partyDesignationId = partyDesignationId;
	}*/
	
	@Column(name="from_date")
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	
	@Column(name="to_date")
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "tdp_cadre_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCadre getTdpCadre() {
		return tdpCadre;
	}
	public void setTdpCadre(TdpCadre tdpCadre) {
		this.tdpCadre = tdpCadre;
	}
	
	/*@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "cadre_level_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CadreLevel getCadreLevel() {
		return cadreLevel;
	}
	public void setCadreLevel(CadreLevel cadreLevel) {
		this.cadreLevel = cadreLevel;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "party_designation_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public PartyDesignation getPartyDesignation() {
		return partyDesignation;
	}
	public void setPartyDesignation(PartyDesignation partyDesignation) {
		this.partyDesignation = partyDesignation;
	}*/
	
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
	@Column(name="inserted_date")
	public Date getInsertedDate() {
		return insertedDate;
	}
	public void setInsertedDate(Date insertedDate) {
		this.insertedDate = insertedDate;
	}
	
	@Column(name="updated_date")
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	@Column(name="cadre_committee_role_Id")
	public Long getCadreCommitteeRoleId() {
		return cadreCommitteeRoleId;
	}
	public void setCadreCommitteeRoleId(Long cadreCommitteeRoleId) {
		this.cadreCommitteeRoleId = cadreCommitteeRoleId;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "cadre_committee_role_Id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CadreCommitteeRole getCadreCommitteeRole() {
		return cadreCommitteeRole;
	}
	public void setCadreCommitteeRole(CadreCommitteeRole cadreCommitteeRole) {
		this.cadreCommitteeRole = cadreCommitteeRole;
	}
	
	@Column(name="committee_location_id")
	public Long getCommitteeLocationId() {
		return committeeLocationId;
	}
	public void setCommitteeLocationId(Long committeeLocationId) {
		this.committeeLocationId = committeeLocationId;
	}
	
	
	
	
	
	
}
