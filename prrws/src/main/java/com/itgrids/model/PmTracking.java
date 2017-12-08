package com.itgrids.model;

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

@Entity
@Table(name = "pm_tracking")
public class PmTracking {

	private Long pmTrackingId;
	private Long petitionId;
	private Long pmSubWorkDetailsId;
	private Long pmStatusId;
	private Long pmDepartmentDesignationId;
	
	 private Petition petition;
	 private PmSubWorkDetails pmSubWorkDetails;
	 private PmStatus pmStatus;
	 private PmDepartmentDesignation pmDepartmentDesignation;
	 
	@Id
	@Column(name="pm_tracking_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getPmTrackingId() {
		return pmTrackingId;
	}
	public void setPmTrackingId(Long pmTrackingId) {
		this.pmTrackingId = pmTrackingId;
	}
	@Column(name="petition_id")
	public Long getPetitionId() {
		return petitionId;
	}
	public void setPetitionId(Long petitionId) {
		this.petitionId = petitionId;
	}
	@Column(name="pm_sub_work_details_id")
	public Long getPmSubWorkDetailsId() {
		return pmSubWorkDetailsId;
	}
	public void setPmSubWorkDetailsId(Long pmSubWorkDetailsId) {
		this.pmSubWorkDetailsId = pmSubWorkDetailsId;
	}
	@Column(name="pm_status_id")
	public Long getPmStatusId() {
		return pmStatusId;
	}
	public void setPmStatusId(Long pmStatusId) {
		this.pmStatusId = pmStatusId;
	}
	@Column(name="pm_dept_designation_id")
	public Long getPmDepartmentDesignationId() {
		return pmDepartmentDesignationId;
	}
	public void setPmDepartmentDesignationId(Long pmDepartmentDesignationId) {
		this.pmDepartmentDesignationId = pmDepartmentDesignationId;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "petition_id", insertable = false, updatable = false)
	public Petition getPetition() {
		return petition;
	}
	public void setPetition(Petition petition) {
		this.petition = petition;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "pm_sub_work_details_id", insertable = false, updatable = false)
	public PmSubWorkDetails getPmSubWorkDetails() {
		return pmSubWorkDetails;
	}
	public void setPmSubWorkDetails(PmSubWorkDetails pmSubWorkDetails) {
		this.pmSubWorkDetails = pmSubWorkDetails;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "pm_status_id", insertable = false, updatable = false)
	public PmStatus getPmStatus() {
		return pmStatus;
	}
	public void setPmStatus(PmStatus pmStatus) {
		this.pmStatus = pmStatus;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "pm_department_designation_id", insertable = false, updatable = false)
	public PmDepartmentDesignation getPmDepartmentDesignation() {
		return pmDepartmentDesignation;
	}
	public void setPmDepartmentDesignation(PmDepartmentDesignation pmDepartmentDesignation) {
		this.pmDepartmentDesignation = pmDepartmentDesignation;
	}	
}
