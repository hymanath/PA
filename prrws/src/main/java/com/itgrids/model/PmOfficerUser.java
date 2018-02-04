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
@Table(name = "pm_officer_user")
public class PmOfficerUser {

	private Long pmOfficerUserId;
	private Long pmDepartmentDesignationId;
	private Long userId;
	private Long pmOfficerId;
	private String isActive;
	
	private PmDepartmentDesignation pmDepartmentDesignation;
	private User insertedUser;
	private PmOfficer pmOfficer;
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
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "pm_dept_designation_id", insertable = false, updatable = false)
	public PmDepartmentDesignation getPmDepartmentDesignation() {
		return pmDepartmentDesignation;
	}
	public void setPmDepartmentDesignation(PmDepartmentDesignation pmDepartmentDesignation) {
		this.pmDepartmentDesignation = pmDepartmentDesignation;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	public User getInsertedUser() {
		return insertedUser;
	}
	public void setInsertedUser(User insertedUser) {
		this.insertedUser = insertedUser;
	}

	@Column(name = "pm_officer_id")
	public Long getPmOfficerId() {
		return pmOfficerId;
	}
	public void setPmOfficerId(Long pmOfficerId) {
		this.pmOfficerId = pmOfficerId;
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
