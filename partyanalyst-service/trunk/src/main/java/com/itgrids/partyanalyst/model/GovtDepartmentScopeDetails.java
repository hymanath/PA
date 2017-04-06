package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="govt_department_scope_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GovtDepartmentScopeDetails extends BaseModel implements Serializable{

	private static final long serialVersionUID = -3806379738904956901L;
	
	private Long govtDepartmentScopeDetailsId;
	private Long govtDepartmentScopeId;
	private Long govtDepartmentId;
	private String deptLevelName;
	private String isDeleted;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="govt_department_scope_details_id", unique=true, nullable=false)
	public Long getGovtDepartmentScopeDetailsId() {
		return govtDepartmentScopeDetailsId;
	}
	public void setGovtDepartmentScopeDetailsId(Long govtDepartmentScopeDetailsId) {
		this.govtDepartmentScopeDetailsId = govtDepartmentScopeDetailsId;
	}
	
	@Column(name="govt_department_scope_id")
	public Long getGovtDepartmentScopeId() {
		return govtDepartmentScopeId;
	}
	
	public void setGovtDepartmentScopeId(Long govtDepartmentScopeId) {
		this.govtDepartmentScopeId = govtDepartmentScopeId;
	}
	
	@Column(name="govt_department_id")
	public Long getGovtDepartmentId() {
		return govtDepartmentId;
	}
	public void setGovtDepartmentId(Long govtDepartmentId) {
		this.govtDepartmentId = govtDepartmentId;
	}
	
	@Column(name="dept_level_name")
	public String getDeptLevelName() {
		return deptLevelName;
	}
	public void setDeptLevelName(String deptLevelName) {
		this.deptLevelName = deptLevelName;
	}
	
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
}
