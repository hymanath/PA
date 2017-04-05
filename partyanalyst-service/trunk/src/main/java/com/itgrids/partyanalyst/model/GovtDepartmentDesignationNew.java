package com.itgrids.partyanalyst.model;

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
@Table(name = "govt_department_designation_new")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GovtDepartmentDesignationNew {

	private Long govtDepartmentDesignationId;
	private String designationName;
	private Long govtDepartmentId;
	private Long govtDepartmentScopeId;
	
	private GovtDepartment govtDepartment;
	private GovtDepartmentScope GovtDepartmentScope;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "govt_department_designation_id", unique = true, nullable = false)
	public Long getGovtDepartmentDesignationId() {
		return govtDepartmentDesignationId;
	}
	public void setGovtDepartmentDesignationId(Long govtDepartmentDesignationId) {
		this.govtDepartmentDesignationId = govtDepartmentDesignationId;
	}
	
	@Column(name = "designation_name")
	public String getDesignationName() {
		return designationName;
	}
	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}
	
	@Column(name = "govt_department_id")
	public Long getGovtDepartmentId() {
		return govtDepartmentId;
	}
	public void setGovtDepartmentId(Long govtDepartmentId) {
		this.govtDepartmentId = govtDepartmentId;
	}
	@Column(name = "govt_department_scope_id")
	public Long getGovtDepartmentScopeId() {
		return govtDepartmentScopeId;
	}
	public void setGovtDepartmentScopeId(Long govtDepartmentScopeId) {
		this.govtDepartmentScopeId = govtDepartmentScopeId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="govt_department_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public GovtDepartment getGovtDepartment() {
		return govtDepartment;
	}
	public void setGovtDepartment(GovtDepartment govtDepartment) {
		this.govtDepartment = govtDepartment;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="govt_department_scope_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public GovtDepartmentScope getGovtDepartmentScope() {
		return GovtDepartmentScope;
	}
	public void setGovtDepartmentScope(GovtDepartmentScope govtDepartmentScope) {
		GovtDepartmentScope = govtDepartmentScope;
	}
	
	
	
}
