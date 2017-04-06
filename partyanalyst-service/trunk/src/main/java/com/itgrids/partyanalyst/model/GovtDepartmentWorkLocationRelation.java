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
@Table(name = "govt_department_work_location_relation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GovtDepartmentWorkLocationRelation extends BaseModel implements Serializable {

	private Long govtDepartmentWorkLocationRelationId;
	private Long parentGovtDepartmentWorkLocationId;
	private Long govtDepartmentWorkLocationId;
	private String isDeleted;
	
	private GovtDepartmentWorkLocation govtDepartmentWorkLocation;
	private GovtDepartmentWorkLocation parentGovtDepartmentWorkLocation;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "govt_department_work_location_relation_id", unique = true, nullable = false)
	public Long getGovtDepartmentWorkLocationRelationId() {
		return govtDepartmentWorkLocationRelationId;
	}

	public void setGovtDepartmentWorkLocationRelationId(
			Long govtDepartmentWorkLocationRelationId) {
		this.govtDepartmentWorkLocationRelationId = govtDepartmentWorkLocationRelationId;
	}

	@Column(name = "parent_govt_department_work_location_id")
	public Long getParentGovtDepartmentWorkLocationId() {
		return parentGovtDepartmentWorkLocationId;
	}

	public void setParentGovtDepartmentWorkLocationId(
			Long parentGovtDepartmentWorkLocationId) {
		this.parentGovtDepartmentWorkLocationId = parentGovtDepartmentWorkLocationId;
	}

	@Column(name = "govt_department_work_location_id")
	public Long getGovtDepartmentWorkLocationId() {
		return govtDepartmentWorkLocationId;
	}

	public void setGovtDepartmentWorkLocationId(Long govtDepartmentWorkLocationId) {
		this.govtDepartmentWorkLocationId = govtDepartmentWorkLocationId;
	}

	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "govt_department_work_location_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public GovtDepartmentWorkLocation getGovtDepartmentWorkLocation() {
		return govtDepartmentWorkLocation;
	}

	public void setGovtDepartmentWorkLocation(
			GovtDepartmentWorkLocation govtDepartmentWorkLocation) {
		this.govtDepartmentWorkLocation = govtDepartmentWorkLocation;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_govt_department_work_location_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public GovtDepartmentWorkLocation getParentGovtDepartmentWorkLocation() {
		return parentGovtDepartmentWorkLocation;
	}

	public void setParentGovtDepartmentWorkLocation(
			GovtDepartmentWorkLocation parentGovtDepartmentWorkLocation) {
		this.parentGovtDepartmentWorkLocation = parentGovtDepartmentWorkLocation;
	}
	 
	
	
}
