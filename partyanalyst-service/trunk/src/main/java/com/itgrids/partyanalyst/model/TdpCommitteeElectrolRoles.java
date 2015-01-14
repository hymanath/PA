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

@Entity
@Table(name = "tdp_committee_electrol_roles")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdpCommitteeElectrolRoles {
	private Long tdpCommitteeElectrolRolesId;
	private Long tdpCommitteeElectrolsId;
	private TdpCommitteeElectrols tdpCommitteeElectrols; 
	private Long tdpCommitteeDesignationId;
	private TdpCommitteeDesignation tdpCommitteeDesignation;
	private String isDeleted;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tdp_committee_electrol_roles_id", unique = true, nullable = false)
	public Long getTdpCommitteeElectrolRolesId() {
		return tdpCommitteeElectrolRolesId;
	}
	
	public void setTdpCommitteeElectrolRolesId(Long tdpCommitteeElectrolRolesId) {
		this.tdpCommitteeElectrolRolesId = tdpCommitteeElectrolRolesId;
	}
	
	@Column(name = "tdp_committee_electrols_id")
	public Long getTdpCommitteeElectrolsId() {
		return tdpCommitteeElectrolsId;
	}
	
	public void setTdpCommitteeElectrolsId(Long tdpCommitteeElectrolsId) {
		this.tdpCommitteeElectrolsId = tdpCommitteeElectrolsId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "tdp_committee_electrols_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCommitteeElectrols getTdpCommitteeElectrols() {
		return tdpCommitteeElectrols;
	}
	
	public void setTdpCommitteeElectrols(TdpCommitteeElectrols tdpCommitteeElectrols) {
		this.tdpCommitteeElectrols = tdpCommitteeElectrols;
	}
	
	@Column(name = "tdp_committee_designation_id")
	public Long getTdpCommitteeDesignationId() {
		return tdpCommitteeDesignationId;
	}
	
	public void setTdpCommitteeDesignationId(Long tdpCommitteeDesignationId) {
		this.tdpCommitteeDesignationId = tdpCommitteeDesignationId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "tdp_committee_designation_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCommitteeDesignation getTdpCommitteeDesignation() {
		return tdpCommitteeDesignation;
	}
	
	public void setTdpCommitteeDesignation(
			TdpCommitteeDesignation tdpCommitteeDesignation) {
		this.tdpCommitteeDesignation = tdpCommitteeDesignation;
	}
	
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
	
}
