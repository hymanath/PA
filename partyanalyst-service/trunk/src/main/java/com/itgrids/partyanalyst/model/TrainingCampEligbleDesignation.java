package com.itgrids.partyanalyst.model;

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
@Table(name = "training_camp_eligble_designation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TrainingCampEligbleDesignation {

	private Long trainingCampEligbleDesignationId;
	private Long trainingCampProgramId;
	private Long tdpBasicCommitteeId;
	private Long tdpCommitteeLevelId;
	private Long tdpRolesId;
	private String gender;
	private Long tdpCommitteeEnrollmentId;
	private TdpCommitteeEnrollment tdpCommitteeEnrollment;
	private TrainingCampProgram trainingCampProgram;
	private TdpBasicCommittee tdpBasicCommittee;
	private TdpCommitteeLevel tdpCommitteeLevel;
	private TdpRoles tdpRoles;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="training_camp_eligble_designation_id", unique=true, nullable=false)
	public Long getTrainingCampEligbleDesignationId() {
		return trainingCampEligbleDesignationId;
	}
	public void setTrainingCampEligbleDesignationId(
			Long trainingCampEligbleDesignationId) {
		this.trainingCampEligbleDesignationId = trainingCampEligbleDesignationId;
	}
	@Column(name="training_camp_program_id")
	public Long getTrainingCampProgramId() {
		return trainingCampProgramId;
	}
	public void setTrainingCampProgramId(Long trainingCampProgramId) {
		this.trainingCampProgramId = trainingCampProgramId;
	}
	@Column(name="tdp_basic_committee_id")
	public Long getTdpBasicCommitteeId() {
		return tdpBasicCommitteeId;
	}
	public void setTdpBasicCommitteeId(Long tdpBasicCommitteeId) {
		this.tdpBasicCommitteeId = tdpBasicCommitteeId;
	}
	@Column(name="tdp_committee_level_id")
	public Long getTdpCommitteeLevelId() {
		return tdpCommitteeLevelId;
	}
	public void setTdpCommitteeLevelId(Long tdpCommitteeLevelId) {
		this.tdpCommitteeLevelId = tdpCommitteeLevelId;
	}
	@Column(name="tdp_roles_id")
	public Long getTdpRolesId() {
		return tdpRolesId;
	}
	public void setTdpRolesId(Long tdpRolesId) {
		this.tdpRolesId = tdpRolesId;
	}
	@Column(name="gender")
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="training_camp_program_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TrainingCampProgram getTrainingCampProgram() {
		return trainingCampProgram;
	}
	public void setTrainingCampProgram(TrainingCampProgram trainingCampProgram) {
		this.trainingCampProgram = trainingCampProgram;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="tdp_basic_committee_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpBasicCommittee getTdpBasicCommittee() {
		return tdpBasicCommittee;
	}
	public void setTdpBasicCommittee(TdpBasicCommittee tdpBasicCommittee) {
		this.tdpBasicCommittee = tdpBasicCommittee;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="tdp_committee_level_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCommitteeLevel getTdpCommitteeLevel() {
		return tdpCommitteeLevel;
	}
	public void setTdpCommitteeLevel(TdpCommitteeLevel tdpCommitteeLevel) {
		this.tdpCommitteeLevel = tdpCommitteeLevel;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="tdp_roles_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpRoles getTdpRoles() {
		return tdpRoles;
	}
	public void setTdpRoles(TdpRoles tdpRoles) {
		this.tdpRoles = tdpRoles;
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
	
}
