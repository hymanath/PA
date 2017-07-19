package com.itgrids.partyanalyst.model;

import java.util.Date;

import javax.persistence.CascadeType;
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
@Table(name = "booth_incharge_role_condition_mapping")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BoothInchargeRoleConditionMapping {

	private Long boothInchargeRoleConditionMappingId;
	private Long boothInchargeRoleConditionId;
	private Long boothInchargeEnrollmentId;
	private String isConfirmed;
	private String isDeleted;
	private Long boothInchargeCommitteeId;
	
	private BoothInchargeCommittee boothInchargeCommittee;
	private BoothInchargeRoleCondition boothInchargeRoleCondition;
	private BoothInchargeEnrollment boothInchargeEnrollment;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "booth_incharge_role_condition_mapping_id", unique = true, nullable = false)
	public Long getBoothInchargeRoleConditionMappingId() {
		return boothInchargeRoleConditionMappingId;
	}
	public void setBoothInchargeRoleConditionMappingId(
			Long boothInchargeRoleConditionMappingId) {
		this.boothInchargeRoleConditionMappingId = boothInchargeRoleConditionMappingId;
	}
		
	@Column(name="booth_incharge_role_condition_id")
	public Long getBoothInchargeRoleConditionId() {
		return boothInchargeRoleConditionId;
	}
	public void setBoothInchargeRoleConditionId(Long boothInchargeRoleConditionId) {
		this.boothInchargeRoleConditionId = boothInchargeRoleConditionId;
	}
	
	@Column(name="booth_incharge_enrollment_id")
	public Long getBoothInchargeEnrollmentId() {
		return boothInchargeEnrollmentId;
	}
	public void setBoothInchargeEnrollmentId(Long boothInchargeEnrollmentId) {
		this.boothInchargeEnrollmentId = boothInchargeEnrollmentId;
	}
	
	@Column(name="is_confirmed")
	public String getIsConfirmed() {
		return isConfirmed;
	}
	public void setIsConfirmed(String isConfirmed) {
		this.isConfirmed = isConfirmed;
	}
	
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
		
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="booth_incharge_role_condition_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public BoothInchargeRoleCondition getBoothInchargeRoleCondition() {
		return boothInchargeRoleCondition;
	}
	public void setBoothInchargeRoleCondition(
			BoothInchargeRoleCondition boothInchargeRoleCondition) {
		this.boothInchargeRoleCondition = boothInchargeRoleCondition;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="booth_incharge_enrollment_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public BoothInchargeEnrollment getBoothInchargeEnrollment() {
		return boothInchargeEnrollment;
	}
	public void setBoothInchargeEnrollment(
			BoothInchargeEnrollment boothInchargeEnrollment) {
		this.boothInchargeEnrollment = boothInchargeEnrollment;
	}
		
	@Column(name="booth_incharge_committee_id")
	public Long getBoothInchargeCommitteeId() {
		return boothInchargeCommitteeId;
	}
	public void setBoothInchargeCommitteeId(Long boothInchargeCommitteeId) {
		this.boothInchargeCommitteeId = boothInchargeCommitteeId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="booth_incharge_committee_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public BoothInchargeCommittee getBoothInchargeCommittee() {
		return boothInchargeCommittee;
	}
	public void setBoothInchargeCommittee(
			BoothInchargeCommittee boothInchargeCommittee) {
		this.boothInchargeCommittee = boothInchargeCommittee;
	}
}
