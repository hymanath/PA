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
@Table(name = "pm_dept_designation_pre_post_status_details")
public class PmDeptDesignationPrePostStatusDetails {
	
	private Long pmDeptDesignationPrePostStatusDetailsId;
	private Long pmOfficerDesignationId;
	private Long pmPreStatusId;
	private Long pmPostStatusId;
	private String isDeleted;
	
	private PmOfficerDesignation pmOfficerDesignation;
	private PmStatus pmPreStatus;
	private PmStatus pmPostStatus;
    
	@Id
	@Column(name="pm_dept_designation_pre_post_status_details_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getPmDeptDesignationPrePostStatusDetailsId() {
		return pmDeptDesignationPrePostStatusDetailsId;
	}
	public void setPmDeptDesignationPrePostStatusDetailsId(Long pmDeptDesignationPrePostStatusDetailsId) {
		this.pmDeptDesignationPrePostStatusDetailsId = pmDeptDesignationPrePostStatusDetailsId;
	}
	@Column(name="pm_officer_designation_id")
	public Long getPmOfficerDesignationId() {
		return pmOfficerDesignationId;
	}
	public void setPmOfficerDesignationId(Long pmOfficerDesignationId) {
		this.pmOfficerDesignationId = pmOfficerDesignationId;
	}
	@Column(name="pm_pre_status_id")
	public Long getPmPreStatusId() {
		return pmPreStatusId;
	}
	public void setPmPreStatusId(Long pmPreStatusId) {
		this.pmPreStatusId = pmPreStatusId;
	}
	@Column(name="pm_post_status_id")
	public Long getPmPostStatusId() {
		return pmPostStatusId;
	}
	public void setPmPostStatusId(Long pmPostStatusId) {
		this.pmPostStatusId = pmPostStatusId;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "pm_officer_designation_id", insertable = false, updatable = false)
	public PmOfficerDesignation getPmOfficerDesignation() {
		return pmOfficerDesignation;
	}
	public void setPmOfficerDesignation(PmOfficerDesignation pmOfficerDesignation) {
		this.pmOfficerDesignation = pmOfficerDesignation;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "pm_pre_status_id", insertable = false, updatable = false)
	public PmStatus getPmPreStatus() {
		return pmPreStatus;
	}
	public void setPmPreStatus(PmStatus pmPreStatus) {
		this.pmPreStatus = pmPreStatus;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "pm_post_status_id", insertable = false, updatable = false)
	public PmStatus getPmPostStatus() {
		return pmPostStatus;
	}
	public void setPmPostStatus(PmStatus pmPostStatus) {
		this.pmPostStatus = pmPostStatus;
	}	
}
