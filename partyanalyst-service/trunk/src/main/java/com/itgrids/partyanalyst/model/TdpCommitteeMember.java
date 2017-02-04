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
@Table(name = "tdp_committee_member")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdpCommitteeMember {
	private Long tdpCommitteeMemberId;
	private Long tdpCommitteeRoleId;
	private TdpCommitteeRole tdpCommitteeRole;
	private Long tdpCadreId;
	private TdpCadre tdpCadre;
	private Date startDate;
	private Date endDate;
	private String isActive;
	private Long tdpCommitteeEnrollmentId;
	private TdpCommitteeEnrollment tdpCommitteeEnrollment;
	private User insertedUser;
	private User updatedUser;
	private Long insertedUserId;
	private Long updatedUserId;	
	private Date insertedTime;
	private Date updatedTime;
	
	private String status;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tdp_committee_member_id", unique = true, nullable = false)
	public Long getTdpCommitteeMemberId() {
		return tdpCommitteeMemberId;
	}
	
	public void setTdpCommitteeMemberId(Long tdpCommitteeMemberId) {
		this.tdpCommitteeMemberId = tdpCommitteeMemberId;
	}
	
	@Column(name = "tdp_committee_role_id")
	public Long getTdpCommitteeRoleId() {
		return tdpCommitteeRoleId;
	}
	
	public void setTdpCommitteeRoleId(Long tdpCommitteeRoleId) {
		this.tdpCommitteeRoleId = tdpCommitteeRoleId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "tdp_committee_role_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCommitteeRole getTdpCommitteeRole() {
		return tdpCommitteeRole;
	}
	
	public void setTdpCommitteeRole(TdpCommitteeRole tdpCommitteeRole) {
		this.tdpCommitteeRole = tdpCommitteeRole;
	}
	
	@Column(name = "tdp_cadre_id")
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
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

	@Column(name = "start_date")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	
	@Column(name = "end_date")
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "is_active")
	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	@Column(name = "tdp_committee_enrollment_id")
	public Long getTdpCommitteeEnrollmentId() {
		return tdpCommitteeEnrollmentId;
	}
	
	public void setTdpCommitteeEnrollmentId(Long tdpCommitteeEnrollmentId) {
		this.tdpCommitteeEnrollmentId = tdpCommitteeEnrollmentId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "tdp_committee_enrollment_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCommitteeEnrollment getTdpCommitteeEnrollment() {
		return tdpCommitteeEnrollment;
	}
	
	public void setTdpCommitteeEnrollment(
			TdpCommitteeEnrollment tdpCommitteeEnrollment) {
		this.tdpCommitteeEnrollment = tdpCommitteeEnrollment;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="inserted_user_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getInsertedUser() {
		return insertedUser;
	}

	public void setInsertedUser(User insertedUser) {
		this.insertedUser = insertedUser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="updated_user_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUpdatedUser() {
		return updatedUser;
	}

	public void setUpdatedUser(User updatedUser) {
		this.updatedUser = updatedUser;
	}

	@Column(name="inserted_user_id")
	public Long getInsertedUserId() {
		return insertedUserId;
	}

	public void setInsertedUserId(Long insertedUserId) {
		this.insertedUserId = insertedUserId;
	}
	
	@Column(name="updated_user_id")
	public Long getUpdatedUserId() {
		return updatedUserId;
	}

	public void setUpdatedUserId(Long updatedUserId) {
		this.updatedUserId = updatedUserId;
	}

	@Column(name = "inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}

	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}

	@Column(name = "updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	@Column(name = "status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
