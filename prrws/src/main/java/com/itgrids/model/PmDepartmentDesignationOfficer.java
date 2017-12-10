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
@Table(name = "pm_dept_designation_officer")
public class PmDepartmentDesignationOfficer {
	
	private Long pmDepartmentDesignationOfficerId;
	private Long  pmDepartmentDesignationId;
	private Long pmOfficerId;
	private String isActive;
	
	private PmDepartmentDesignation pmDepartmentDesignation;
	private PmOfficer pmOfficer;
	
	@Id
	@Column(name="pm_dept_designation_officer_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getPmDepartmentDesignationOfficerId() {
		return pmDepartmentDesignationOfficerId;
	}
	public void setPmDepartmentDesignationOfficerId(Long pmDepartmentDesignationOfficerId) {
		this.pmDepartmentDesignationOfficerId = pmDepartmentDesignationOfficerId;
	}
	@Column(name="pm_dept_designation_id")
	public Long getPmDepartmentDesignationId() {
		return pmDepartmentDesignationId;
	}
	public void setPmDepartmentDesignationId(Long pmDepartmentDesignationId) {
		this.pmDepartmentDesignationId = pmDepartmentDesignationId;
	}
	@Column(name="pm_Officer_Id")
	public Long getPmOfficerId() {
		return pmOfficerId;
	}
	public void setPmOfficerId(Long pmOfficerId) {
		this.pmOfficerId = pmOfficerId;
	}
	@Column(name="is_active")
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "pm_dept_designation_id", insertable = false, updatable = false)
	public PmDepartmentDesignation getPmDepartmentDesignation() {
		return pmDepartmentDesignation;
	}
	public void setPmDepartmentDesignation(PmDepartmentDesignation pmDepartmentDesignation) {
		this.pmDepartmentDesignation = pmDepartmentDesignation;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "pm_officer_id", insertable = false, updatable = false)
	public PmOfficer getPmOfficer() {
		return pmOfficer;
	}
	public void setPmOfficer(PmOfficer pmOfficer) {
		this.pmOfficer = pmOfficer;
	}
}
