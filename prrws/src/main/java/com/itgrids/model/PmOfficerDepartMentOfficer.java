package com.itgrids.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pm_officer_dept_officer")
public class PmOfficerDepartMentOfficer {

	
	private Long pmOfficerDepartMentOfficerId;
	private Long pmDepartmentId;
	private Long pmOfficerId;
	
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
	
	
}
