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
@Table(name = "govt_department_meekosam_issue_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GovtDepartmentMeekosamIssueType extends BaseModel implements Serializable {
	private Long govtDepartmentMeekosamIssueTypeId;
	private Long govtDepartmentId;
	private Long meekosamIssueTypeId;
	private String isDeleted;
	
	private GovtDepartment govtDepartment;
	private MeekosamIssueType meekosamIssueType;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "govt_department_meekosam_issue_type_id", unique = true, nullable = false)
	public Long getGovtDepartmentMeekosamIssueTypeId() {
		return govtDepartmentMeekosamIssueTypeId;
	}
	public void setGovtDepartmentMeekosamIssueTypeId(
			Long govtDepartmentMeekosamIssueTypeId) {
		this.govtDepartmentMeekosamIssueTypeId = govtDepartmentMeekosamIssueTypeId;
	}
	@Column(name = "govt_department_id")
	public Long getGovtDepartmentId() {
		return govtDepartmentId;
	}
	public void setGovtDepartmentId(Long govtDepartmentId) {
		this.govtDepartmentId = govtDepartmentId;
	}
	@Column(name = "meekosam_issue_type_id")
	public Long getMeekosamIssueTypeId() {
		return meekosamIssueTypeId;
	}
	public void setMeekosamIssueTypeId(Long meekosamIssueTypeId) {
		this.meekosamIssueTypeId = meekosamIssueTypeId;
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
	@JoinColumn(name="meekosam_issue_type_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public MeekosamIssueType getMeekosamIssueType() {
		return meekosamIssueType;
	}
	public void setMeekosamIssueType(MeekosamIssueType meekosamIssueType) {
		this.meekosamIssueType = meekosamIssueType;
	}
}
