package com.itgrids.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pm_officer_user")
public class PmOfficerUser {

	private Long pmOfficerUserId;
	private Long pmDepartmentDesignationId;
	private Long userId;
	private String isActive;
	
	@Id
	@Column(name="pm_officer_user_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getPmOfficerUserId() {
		return pmOfficerUserId;
	}
	public void setPmOfficerUserId(Long pmOfficerUserId) {
		this.pmOfficerUserId = pmOfficerUserId;
	}
	@Column(name="pm_dept_designation_id")
	public Long getPmDepartmentDesignationId() {
		return pmDepartmentDesignationId;
	}
	public void setPmDepartmentDesignationId(Long pmDepartmentDesignationId) {
		this.pmDepartmentDesignationId = pmDepartmentDesignationId;
	}
	@Column(name="user_id")
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	@Column(name="is_active")
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	
	
}
