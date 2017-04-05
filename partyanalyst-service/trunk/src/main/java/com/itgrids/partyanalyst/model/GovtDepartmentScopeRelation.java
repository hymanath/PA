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
@Table(name="govt_department_scope_relation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GovtDepartmentScopeRelation extends BaseModel implements Serializable{

	private static final long serialVersionUID = -3806379738904956901L;
	
	private Long govtDepartmentScopeRelationId;
	private Long govtDepartmentId;
	private Long parentGovtDepartmentScopeId;
	
	private Long subGovtDepartmentScopeId;
	private Long order;
	private Long isDeleted;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="govt_department_scope_relation_id", unique=true, nullable=false)
	public Long getGovtDepartmentScopeRelationId() {
		return govtDepartmentScopeRelationId;
	}
	public void setGovtDepartmentScopeRelationId(Long govtDepartmentScopeRelationId) {
		this.govtDepartmentScopeRelationId = govtDepartmentScopeRelationId;
	}
	
	@Column(name="govt_department_id")
	public Long getGovtDepartmentId() {
		return govtDepartmentId;
	}
	public void setGovtDepartmentId(Long govtDepartmentId) {
		this.govtDepartmentId = govtDepartmentId;
	}
	
	@Column(name="parent_govt_department_scope_id")
	public Long getParentGovtDepartmentScopeId() {
		return parentGovtDepartmentScopeId;
	}
	public void setParentGovtDepartmentScopeId(Long parentGovtDepartmentScopeId) {
		this.parentGovtDepartmentScopeId = parentGovtDepartmentScopeId;
	}
	
	@Column(name="sub_govt_department_scope_id")
	public Long getSubGovtDepartmentScopeId() {
		return subGovtDepartmentScopeId;
	}
	public void setSubGovtDepartmentScopeId(Long subGovtDepartmentScopeId) {
		this.subGovtDepartmentScopeId = subGovtDepartmentScopeId;
	}
	
	@Column(name="order")
	public Long getOrder() {
		return order;
	}
	public void setOrder(Long order) {
		this.order = order;
	}
	
	@Column(name="is_deleted")
	public Long getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Long isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}
