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
@Table(name = "pm_officer_dept_officer")
public class PmOfficerDepartMentOfficer {

	
	private Long pmOfficerDepartMentOfficerId;
	private Long pmDepartmentId;
	private Long pmOfficerId;
	private PmDepartment pmDepartment;
	private PmOfficer pmOfficer;
	
	@Id
	@Column(name="pm_officer_dept_officer_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getPmOfficerDepartMentOfficerId() {
		return pmOfficerDepartMentOfficerId;
	}
	public void setPmOfficerDepartMentOfficerId(Long pmOfficerDepartMentOfficerId) {
		this.pmOfficerDepartMentOfficerId = pmOfficerDepartMentOfficerId;
	}
	@Column(name="pm_dept_id")
	public Long getPmDepartmentId() {
		return pmDepartmentId;
	}
	public void setPmDepartmentId(Long pmDepartmentId) {
		this.pmDepartmentId = pmDepartmentId;
	}
	@Column(name="pm_officer_id")
	public Long getPmOfficerId() {
		return pmOfficerId;
	}
	public void setPmOfficerId(Long pmOfficerId) {
		this.pmOfficerId = pmOfficerId;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "pm_department_id", insertable = false, updatable = false)
	public PmDepartment getPmDepartment() {
		return pmDepartment;
	}
	public void setPmDepartment(PmDepartment pmDepartment) {
		this.pmDepartment = pmDepartment;
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
