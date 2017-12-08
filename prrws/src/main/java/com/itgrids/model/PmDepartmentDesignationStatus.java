package com.itgrids.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pm_dept_designation_status")
public class PmDepartmentDesignationStatus {

	private Long pmDepartmentDesignationStatusId;
	private Long pmDepartmentDesignationId;
	private Long pmStatusId;
	
	
	@Id
	@Column(name="pm_dept_designation_status_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getPmDepartmentDesignationStatusId() {
		return pmDepartmentDesignationStatusId;
	}
	public void setPmDepartmentDesignationStatusId(Long pmDepartmentDesignationStatusId) {
		this.pmDepartmentDesignationStatusId = pmDepartmentDesignationStatusId;
	}
	@Column(name="pm_dept_designation_id")
	public Long getPmDepartmentDesignationId() {
		return pmDepartmentDesignationId;
	}
	public void setPmDepartmentDesignationId(Long pmDepartmentDesignationId) {
		this.pmDepartmentDesignationId = pmDepartmentDesignationId;
	}
	@Column(name="pm_status-id")
	public Long getPmStatusId() {
		return pmStatusId;
	}
	public void setPmStatusId(Long pmStatusId) {
		this.pmStatusId = pmStatusId;
	}
}
