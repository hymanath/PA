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

import com.google.gdata.data.introspection.IServiceDocument;

import sun.net.www.content.audio.basic;

@Entity
@Table(name = "govt_department_issue_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GovtDepartmentIssueType extends BaseModel implements Serializable{

	private Long govtDepartmentIssueTypeId;
	private Long alertIssueTypeId;
	private Long govtDepartmentId;
	private String areaType;
	
	private AlertIssueType alertIssueType;
	private GovtDepartment govtDepartment;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "govt_department_issue_type_id", unique = true, nullable = false)
	public Long getGovtDepartmentIssueTypeId() {
		return govtDepartmentIssueTypeId;
	}
	public void setGovtDepartmentIssueTypeId(Long govtDepartmentIssueTypeId) {
		this.govtDepartmentIssueTypeId = govtDepartmentIssueTypeId;
	}
	
	@Column(name = "alert_issue_type_id")
	public Long getAlertIssueTypeId() {
		return alertIssueTypeId;
	}
	public void setAlertIssueTypeId(Long alertIssueTypeId) {
		this.alertIssueTypeId = alertIssueTypeId;
	}
	
	@Column(name = "govt_department_id")
	public Long getGovtDepartmentId() {
		return govtDepartmentId;
	}
	public void setGovtDepartmentId(Long govtDepartmentId) {
		this.govtDepartmentId = govtDepartmentId;
	}
	
	@Column(name = "area_type")
	public String getAreaType() {
		return areaType;
	}
	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "alert_issue_type_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public AlertIssueType getAlertIssueType() {
		return alertIssueType;
	}
	public void setAlertIssueType(AlertIssueType alertIssueType) {
		this.alertIssueType = alertIssueType;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "govt_department_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public GovtDepartment getGovtDepartment() {
		return govtDepartment;
	}
	public void setGovtDepartment(GovtDepartment govtDepartment) {
		this.govtDepartment = govtDepartment;
	}
}
