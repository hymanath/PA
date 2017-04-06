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
@Table(name="govt_department_scope_location;")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GovtDepartmentScopeLocation extends BaseModel implements Serializable{

	private static final long serialVersionUID = -3806379738904956901L;
	
	private Long govtDepartmentLevelLocationId;
	private String govtDepartmentScopeDetailsId;
	private String levelId;
	private String levelValue;
	private String isDeleted;
	
	private GovtDepartmentScopeDetails govtDepartmentScopeDetails;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="govt_department_level_location_id", unique=true, nullable=false)
	public Long getGovtDepartmentLevelLocationId() {
		return govtDepartmentLevelLocationId;
	}
	public void setGovtDepartmentLevelLocationId(Long govtDepartmentLevelLocationId) {
		this.govtDepartmentLevelLocationId = govtDepartmentLevelLocationId;
	}
	
	@Column(name="govt_department_scope_details_id")
	public String getGovtDepartmentScopeDetailsId() {
		return govtDepartmentScopeDetailsId;
	}
	public void setGovtDepartmentScopeDetailsId(String govtDepartmentScopeDetailsId) {
		this.govtDepartmentScopeDetailsId = govtDepartmentScopeDetailsId;
	}
	
	@Column(name="level_id")
	public String getLevelId() {
		return levelId;
	}
	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}
	
	@Column(name="level_value")
	public String getLevelValue() {
		return levelValue;
	}
	public void setLevelValue(String levelValue) {
		this.levelValue = levelValue;
	}
	
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "govt_department_scope_details_id" ,insertable = false ,updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public GovtDepartmentScopeDetails getGovtDepartmentScopeDetails() {
		return govtDepartmentScopeDetails;
	}
	public void setGovtDepartmentScopeDetails(
			GovtDepartmentScopeDetails govtDepartmentScopeDetails) {
		this.govtDepartmentScopeDetails = govtDepartmentScopeDetails;
	}
	
	
	
}
