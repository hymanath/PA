package com.itgrids.partyanalyst.model;

import java.io.Serializable;

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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "govt_department_designation_officer_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GovtDepartmentDesignationOfficerDetails extends BaseModel implements Serializable{

	private Long govtDepartmentDesignationOfficerDetailsId;
	private Long govtDepartmentDesignationOfficerId;
	private Long govtOfficerId;
	private String isDeleted;
	private Long userId;
	
	private User user;
	private GovtDepartmentDesignationOfficer govtDepartmentDesignationOfficer;
	private GovtOfficer govtOfficer;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "govt_department_designation_officer_details_id", unique = true, nullable = false)
	public Long getGovtDepartmentDesignationOfficerDetailsId() {
		return govtDepartmentDesignationOfficerDetailsId;
	}
	public void setGovtDepartmentDesignationOfficerDetailsId(
			Long govtDepartmentDesignationOfficerDetailsId) {
		this.govtDepartmentDesignationOfficerDetailsId = govtDepartmentDesignationOfficerDetailsId;
	}
	
	@Column(name = "govt_department_designation_officer_id")
	public Long getGovtDepartmentDesignationOfficerId() {
		return govtDepartmentDesignationOfficerId;
	}
	public void setGovtDepartmentDesignationOfficerId(
			Long govtDepartmentDesignationOfficerId) {
		this.govtDepartmentDesignationOfficerId = govtDepartmentDesignationOfficerId;
	}
	
	@Column(name = "govt_officer_id")
	public Long getGovtOfficerId() {
		return govtOfficerId;
	}
	public void setGovtOfficerId(Long govtOfficerId) {
		this.govtOfficerId = govtOfficerId;
	}
	
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="govt_department_designation_officer_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public GovtDepartmentDesignationOfficer getGovtDepartmentDesignationOfficer() {
		return govtDepartmentDesignationOfficer;
	}
	public void setGovtDepartmentDesignationOfficer(
			GovtDepartmentDesignationOfficer govtDepartmentDesignationOfficer) {
		this.govtDepartmentDesignationOfficer = govtDepartmentDesignationOfficer;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="govt_officer_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public GovtOfficer getGovtOfficer() {
		return govtOfficer;
	}
	public void setGovtOfficer(GovtOfficer govtOfficer) {
		this.govtOfficer = govtOfficer;
	}
	
	@Column(name = "user_id")
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
