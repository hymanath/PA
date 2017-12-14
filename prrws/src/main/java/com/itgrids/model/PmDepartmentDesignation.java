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
@Table(name = "pm_dept_designation")
public class PmDepartmentDesignation {

	private Long pmDepartmentDesignationId;
	private Long pmDepartmentId;
	private Long pmOfficerDesignationId;//pm_officer_designation_id  pmDesignationId
	private String isDeleted;
	
	private PmDepartment pmDepartment;
	//private PmSubWorkDetails pmSubWorkDetails;
//	private PmDesignation pmDesignation;
	
	private PmOfficerDesignation pmOfficerDesignation;
	
	@Id
	@Column(name="pm_dept_designation_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getPmDepartmentDesignationId() {
		return pmDepartmentDesignationId;
	}
	public void setPmDepartmentDesignationId(Long pmDepartmentDesignationId) {
		this.pmDepartmentDesignationId = pmDepartmentDesignationId;
	}
	@Column(name="pm_dept_id")
	public Long getPmDepartmentId() {
		return pmDepartmentId;
	}
	public void setPmDepartmentId(Long pmDepartmentId) {
		this.pmDepartmentId = pmDepartmentId;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "pm_dept_id", insertable = false, updatable = false)
	public PmDepartment getPmDepartment() {
		return pmDepartment;
	}
	public void setPmDepartment(PmDepartment pmDepartment) {
		this.pmDepartment = pmDepartment;
	}
	/*@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "pm_sub_work_details_id", insertable = false, updatable = false)
	public PmSubWorkDetails getPmSubWorkDetails() {
		return pmSubWorkDetails;
	}
	public void setPmSubWorkDetails(PmSubWorkDetails pmSubWorkDetails) {
		this.pmSubWorkDetails = pmSubWorkDetails;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "pm_designation_id", insertable = false, updatable = false)
	public PmDesignation getPmDesignation() {
		return pmDesignation;
	}
	public void setPmDesignation(PmDesignation pmDesignation) {
		this.pmDesignation = pmDesignation;
	}
	*/
	@Column(name="pm_officer_designation_id")
	public Long getPmOfficerDesignationId() {
		return pmOfficerDesignationId;
	}
	public void setPmOfficerDesignationId(Long pmOfficerDesignationId) {
		this.pmOfficerDesignationId = pmOfficerDesignationId;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "pm_officer_designation_id", insertable = false, updatable = false)
	public PmOfficerDesignation getPmOfficerDesignation() {
		return pmOfficerDesignation;
	}
	public void setPmOfficerDesignation(PmOfficerDesignation pmOfficerDesignation) {
		this.pmOfficerDesignation = pmOfficerDesignation;
	}
	
}
