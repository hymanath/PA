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
@Table(name = "govt_department_level_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GovtDepartmentLevelDetails extends BaseModel implements Serializable{
	
	private Long govtDepartmentLevelDetailsId;
	private Long govtDepartmentId;
	private Long govtDepartmentLevelid;
	private String isDeleted;
	
	private GovtDepartment govtDepartment;
	private GovtDepartmentLevel govtDepartmentLevel;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "govt_department_level_details_id", unique = true, nullable = false)
	public Long getGovtDepartmentLevelDetailsId() {
		return govtDepartmentLevelDetailsId;
	}
	public void setGovtDepartmentLevelDetailsId(Long govtDepartmentLevelDetailsId) {
		this.govtDepartmentLevelDetailsId = govtDepartmentLevelDetailsId;
	}
	
	@Column(name = "govt_department_id")
	public Long getGovtDepartmentId() {
		return govtDepartmentId;
	}
	public void setGovtDepartmentId(Long govtDepartmentId) {
		this.govtDepartmentId = govtDepartmentId;
	}
	
	@Column(name = "govt_department_level_id")
	public Long getGovtDepartmentLevelid() {
		return govtDepartmentLevelid;
	}
	public void setGovtDepartmentLevelid(Long govtDepartmentLevelid) {
		this.govtDepartmentLevelid = govtDepartmentLevelid;
	}
	
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
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
	@JoinColumn(name="govt_department_level_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public GovtDepartmentLevel getGovtDepartmentLevel() {
		return govtDepartmentLevel;
	}
	public void setGovtDepartmentLevel(GovtDepartmentLevel govtDepartmentLevel) {
		this.govtDepartmentLevel = govtDepartmentLevel;
	}
	
}
