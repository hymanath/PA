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
@Table(name = "tdp_committee_role")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdpCommitteeRole {
	private Long tdpCommitteeRoleId;
	private TdpCommittee tdpCommittee;
	private Long tdpCommitteeId;
	private TdpRoles tdpRoles;
	private Long tdpRolesId;
	private Long maxMembers;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tdp_committee_role_id", unique = true, nullable = false)
	public Long getTdpCommitteeRoleId() {
		return tdpCommitteeRoleId;
	}
	
	public void setTdpCommitteeRoleId(Long tdpCommitteeRoleId) {
		this.tdpCommitteeRoleId = tdpCommitteeRoleId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "tdp_committee_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCommittee getTdpCommittee() {
		return tdpCommittee;
	}
	
	public void setTdpCommittee(TdpCommittee tdpCommittee) {
		this.tdpCommittee = tdpCommittee;
	}
	
	@Column(name = "tdp_committee_id")
	public Long getTdpCommitteeId() {
		return tdpCommitteeId;
	}
	
	public void setTdpCommitteeId(Long tdpCommitteeId) {
		this.tdpCommitteeId = tdpCommitteeId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "tdp_roles_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpRoles getTdpRoles() {
		return tdpRoles;
	}
	
	public void setTdpRoles(TdpRoles tdpRoles) {
		this.tdpRoles = tdpRoles;
	}
	
	@Column(name = "tdp_roles_id")
	public Long getTdpRolesId() {
		return tdpRolesId;
	}
	
	public void setTdpRolesId(Long tdpRolesId) {
		this.tdpRolesId = tdpRolesId;
	}
	
	@Column(name = "max_members")
	public Long getMaxMembers() {
		return maxMembers;
	}
	
	public void setMaxMembers(Long maxMembers) {
		this.maxMembers = maxMembers;
	}
	
	
}
