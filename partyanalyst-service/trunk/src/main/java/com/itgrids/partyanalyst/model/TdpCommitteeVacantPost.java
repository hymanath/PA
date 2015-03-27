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
@Table(name = "tdp_committee_vacant_post")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdpCommitteeVacantPost {
	private Long  tdpCommitteeVacantPostId;
	private TdpCommitteeRole vacantCommitteeRole;
	private TdpCadre cadre;
	private TdpCommitteeRole cadreNewCommitteeRole;
	private TdpCommitteeEnrollment tdpCommitteeEnrollment;
	private Date insertedTime;
	private User insertedBy;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tdp_commt_vac_post_id", unique = true, nullable = false)
	public Long getTdpCommitteeVacantPostId() {
		return tdpCommitteeVacantPostId;
	}
	
	public void setTdpCommitteeVacantPostId(Long tdpCommitteeVacantPostId) {
		this.tdpCommitteeVacantPostId = tdpCommitteeVacantPostId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vacant_committee_role_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCommitteeRole getVacantCommitteeRole() {
		return vacantCommitteeRole;
	}
	
	public void setVacantCommitteeRole(TdpCommitteeRole vacantCommitteeRole) {
		this.vacantCommitteeRole = vacantCommitteeRole;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cadre_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCadre getCadre() {
		return cadre;
	}
	
	public void setCadre(TdpCadre cadre) {
		this.cadre = cadre;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cadre_new_committee_role_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCommitteeRole getCadreNewCommitteeRole() {
		return cadreNewCommitteeRole;
	}
	
	public void setCadreNewCommitteeRole(TdpCommitteeRole cadreNewCommitteeRole) {
		this.cadreNewCommitteeRole = cadreNewCommitteeRole;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tdp_committee_enrollment_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCommitteeEnrollment getTdpCommitteeEnrollment() {
		return tdpCommitteeEnrollment;
	}
	
	public void setTdpCommitteeEnrollment(
			TdpCommitteeEnrollment tdpCommitteeEnrollment) {
		this.tdpCommitteeEnrollment = tdpCommitteeEnrollment;
	}
	
	@Column(name = "inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "inserted_by")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getInsertedBy() {
		return insertedBy;
	}
	
	public void setInsertedBy(User insertedBy) {
		this.insertedBy = insertedBy;
	}
	
	
}
