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
@Table(name = "pm_dept_subject_details")
public class PmDeptSubjectDetails {
	
	private Long pmDeptSubjectDetailsId;
	private Long pmDepartmentId;
	private Long pmSubjectId;
	private String isDeleted;
	private PmDepartment pmDepartment;
	private PmSubject pmSubject;
	
	@Id
	@Column(name="pm_dept_subject_details_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getPmDeptSubjectDetailsId() {
		return pmDeptSubjectDetailsId;
	}
	public void setPmDeptSubjectDetailsId(Long pmDeptSubjectDetailsId) {
		this.pmDeptSubjectDetailsId = pmDeptSubjectDetailsId;
	}
	@Column(name="pm_department_id")
	public Long getPmDepartmentId() {
		return pmDepartmentId;
	}
	public void setPmDepartmentId(Long pmDepartmentId) {
		this.pmDepartmentId = pmDepartmentId;
	}
	@Column(name="pm_subject_id")
	public Long getPmSubjectId() {
		return pmSubjectId;
	}
	public void setPmSubjectId(Long pmSubjectId) {
		this.pmSubjectId = pmSubjectId;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
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
	@JoinColumn(name = "pm_subject_id", insertable = false, updatable = false)
	public PmSubject getPmSubject() {
		return pmSubject;
	}
	public void setPmSubject(PmSubject pmSubject) {
		this.pmSubject = pmSubject;
	}
}
