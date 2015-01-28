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

@Entity
@Table(name = "tdp_committee_role_history")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdpCommitteeRoleHistory {
	private Long tdpCommitteeRoleHistoryId;
	private Long tdpCommitteeRoleId;
	private Long tdpCommitteeId;
	private Long tdpRolesId;
	private Long maxMembers;
	private Date updatedTime;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tdp_committee_role_history_id", unique = true, nullable = false)
	public Long getTdpCommitteeRoleHistoryId() {
		return tdpCommitteeRoleHistoryId;
	}

	public void setTdpCommitteeRoleHistoryId(Long tdpCommitteeRoleHistoryId) {
		this.tdpCommitteeRoleHistoryId = tdpCommitteeRoleHistoryId;
	}

	@Column(name = "tdp_committee_role_id")
	public Long getTdpCommitteeRoleId() {
		return tdpCommitteeRoleId;
	}
	
	public void setTdpCommitteeRoleId(Long tdpCommitteeRoleId) {
		this.tdpCommitteeRoleId = tdpCommitteeRoleId;
	}
	
	
	@Column(name = "tdp_committee_id")
	public Long getTdpCommitteeId() {
		return tdpCommitteeId;
	}
	
	public void setTdpCommitteeId(Long tdpCommitteeId) {
		this.tdpCommitteeId = tdpCommitteeId;
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

	@Column(name = "updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	
	
	
}
