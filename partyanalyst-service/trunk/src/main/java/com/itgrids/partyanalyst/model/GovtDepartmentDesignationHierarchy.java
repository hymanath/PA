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
@Table(name="govt_department_designation_hierarchy")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GovtDepartmentDesignationHierarchy extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long govtDepartmentDesignationHierarchyId;
	private Long parentDesignationId;
	private Long subDesignationId;
	private String  isDeleted;
	
	private GovtDepartmentDesignationNew parentDesignation;
	private GovtDepartmentDesignationNew subDesignation;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "govt_department_designation_hierarchy_id", unique = true, nullable = false)
	public Long getGovtDepartmentDesignationHierarchyId() {
		return govtDepartmentDesignationHierarchyId;
	}
	public void setGovtDepartmentDesignationHierarchyId(
			Long govtDepartmentDesignationHierarchyId) {
		this.govtDepartmentDesignationHierarchyId = govtDepartmentDesignationHierarchyId;
	}
	
	@Column(name="parent_designation_id")
	public Long getParentDesignationId() {
		return parentDesignationId;
	}
	public void setParentDesignationId(Long parentDesignationId) {
		this.parentDesignationId = parentDesignationId;
	}
	
	@Column(name="sub_designation_id")
	public Long getSubDesignationId() {
		return subDesignationId;
	}
	public void setSubDesignationId(Long subDesignationId) {
		this.subDesignationId = subDesignationId;
	}
	
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="parent_designation_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public GovtDepartmentDesignationNew getParentDesignation() {
		return parentDesignation;
	}
	public void setParentDesignation(GovtDepartmentDesignationNew parentDesignation) {
		this.parentDesignation = parentDesignation;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="sub_designation_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public GovtDepartmentDesignationNew getSubDesignation() {
		return subDesignation;
	}
	public void setSubDesignation(GovtDepartmentDesignationNew subDesignation) {
		this.subDesignation = subDesignation;
	}
	
}
