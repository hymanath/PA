/* 
 * Copyright (c) 2011 IT Grids India LTD.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on February 15, 2011
 */
package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

/**
 * @author Sai Krishna
 *
 */

@Entity
@Table(name = "department_organisation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DepartmentOrganisation extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long departmentOrganisationId;
	private String organisationName;
	private String description;
	private Date updatedDate;
	private String siteLink;
	private ProblemSourceScopeConcernedDepartment problemDepartmentCategory;
	private ProblemSourceScope problemSourceScope;
	
	private Set<AssignedProblemProgress> problemProgress = new HashSet<AssignedProblemProgress>(0);
	
	
	//default constructor
	public DepartmentOrganisation(){
		
	}
	
	//constructor with parameters
	public DepartmentOrganisation(String organisationName, String description,
			Date updatedDate, String siteLink,
			ProblemSourceScopeConcernedDepartment problemDepartmentCategory,
			ProblemSourceScope problemSourceScope,
			Set<AssignedProblemProgress> problemProgress) {
		super();
		this.organisationName = organisationName;
		this.description = description;
		this.updatedDate = updatedDate;
		this.siteLink = siteLink;
		this.problemDepartmentCategory = problemDepartmentCategory;
		this.problemSourceScope = problemSourceScope;
		this.problemProgress = problemProgress;
	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "department_organisation_id", unique = true, nullable = false)
	public Long getDepartmentOrganisationId() {
		return departmentOrganisationId;
	}
	public void setDepartmentOrganisationId(Long departmentOrganisationId) {
		this.departmentOrganisationId = departmentOrganisationId;
	}
	
	@Column(name = "organisation_name", length = 250)
	public String getOrganisationName() {
		return organisationName;
	}
	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}
	
	@Column(name = "description", length = 300)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "updated_date", length = 10)
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	@Column(name = "site_link", length = 300)
	public String getSiteLink() {
		return siteLink;
	}
	public void setSiteLink(String siteLink) {
		this.siteLink = siteLink;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "problem_department_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ProblemSourceScopeConcernedDepartment getProblemDepartmentCategory() {
		return problemDepartmentCategory;
	}
	public void setProblemDepartmentCategory(
			ProblemSourceScopeConcernedDepartment problemDepartmentCategory) {
		this.problemDepartmentCategory = problemDepartmentCategory;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "problem_source_scope_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ProblemSourceScope getProblemSourceScope() {
		return problemSourceScope;
	}
	public void setProblemSourceScope(ProblemSourceScope problemSourceScope) {
		this.problemSourceScope = problemSourceScope;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "departmentOrganisation")
	public Set<AssignedProblemProgress> getProblemProgress() {
		return problemProgress;
	}

	public void setProblemProgress(Set<AssignedProblemProgress> problemProgress) {
		this.problemProgress = problemProgress;
	}

}
