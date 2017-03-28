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
@Table(name = "govt_department_designation_mapping")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GovtDepartmentDesignationMapping extends BaseModel implements Serializable{

	//govt_department_designation_officer_mapping_id,parent_officer_id,govt_department_designation_officer_id,is_deleted
	private Long govtDepartmentDesignationMappingId;
	private Long parentDesignationId;
	private Long govtDepartmentDesignationId;
	private Long isDeleted;
	
	private GovtDepartmentDesignation parentDesignation;
	private GovtDepartmentDesignation govtDepartmentDesignation;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "govt_department_designation_mapping_id", unique = true, nullable = false)
	public Long getGovtDepartmentDesignationMappingId() {
		return govtDepartmentDesignationMappingId;
	}
	public void setGovtDepartmentDesignationMappingId(
			Long govtDepartmentDesignationMappingId) {
		this.govtDepartmentDesignationMappingId = govtDepartmentDesignationMappingId;
	}
	
	@Column(name = "parent_designation_id")
	public Long getParentDesignationId() {
		return parentDesignationId;
	}
	public void setParentDesignationId(Long parentDesignationId) {
		this.parentDesignationId = parentDesignationId;
	}
	
	@Column(name = "govt_department_designation_id")
	public Long getGovtDepartmentDesignationId() {
		return govtDepartmentDesignationId;
	}
	public void setGovtDepartmentDesignationId(
			Long govtDepartmentDesignationId) {
		this.govtDepartmentDesignationId = govtDepartmentDesignationId;
	}
	
	@Column(name = "is_deleted")
	public Long getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Long isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="parent_designation_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	
	public GovtDepartmentDesignation parentDesignation() {
		return parentDesignation;
	}
	public void setParentDesignation(GovtDepartmentDesignation parentDesignation) {
		this.parentDesignation = parentDesignation;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="govt_department_designation_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public GovtDepartmentDesignation getGovtDepartmentDesignation() {
		return govtDepartmentDesignation;
	}
	public void setGovtDepartmentDesignation(
			GovtDepartmentDesignation govtDepartmentDesignation) {
		this.govtDepartmentDesignation = govtDepartmentDesignation;
	}
	
	
	
	
}
