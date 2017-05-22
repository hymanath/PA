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
@Table(name = "govt_department_relation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GovtDepartmentRelation implements Serializable{

	private Long govtDepartmentRelationId;
	private Long parentGovtDepartmentId;
	private Long subGovtDepartmentId;
	private String isDeleted;
	
	private GovtDepartment parentGovtDepartment;
	private GovtDepartment subGovtDepartment;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "govt_department_relation_id", unique = true, nullable = false)
	public Long getGovtDepartmentRelationId() {
		return govtDepartmentRelationId;
	}
	public void setGovtDepartmentRelationId(Long govtDepartmentRelationId) {
		this.govtDepartmentRelationId = govtDepartmentRelationId;
	}
	
	@Column(name = "parent_govt_department_id")
	public Long getParentGovtDepartmentId() {
		return parentGovtDepartmentId;
	}
	public void setParentGovtDepartmentId(Long parentGovtDepartmentId) {
		this.parentGovtDepartmentId = parentGovtDepartmentId;
	}
	@Column(name = "sub_govt_department_id")
	public Long getSubGovtDepartmentId() {
		return subGovtDepartmentId;
	}
	public void setSubGovtDepartmentId(Long subGovtDepartmentId) {
		this.subGovtDepartmentId = subGovtDepartmentId;
	}
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="parent_govt_department_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public GovtDepartment getParentGovtDepartment() {
		return parentGovtDepartment;
	}
	public void setParentGovtDepartment(GovtDepartment parentGovtDepartment) {
		this.parentGovtDepartment = parentGovtDepartment;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="sub_govt_department_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public GovtDepartment getSubGovtDepartment() {
		return subGovtDepartment;
	}
	public void setSubGovtDepartment(GovtDepartment subGovtDepartment) {
		this.subGovtDepartment = subGovtDepartment;
	}
	
	
	
	
	
}
