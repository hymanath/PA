package com.itgrids.model;

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

@Entity
@Table(name = "pm_dept_designation_brief_leads")
public class PmDepartmentDesignationBriefLeads  {
	private Long PmDepartmentDesignationBriefLeadsId;
	private Long pmDepartmentDesignationId;
	private Long pmBriefLeadId;
	private String isDeleted;
	
	private PmDepartmentDesignation pmDepartmentDesignation;
	private  PmBriefLead pmBriefLead;
	
	@Id
	@Column(name="pm_dept_designation_brief_leads_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getPmDepartmentDesignationBriefLeadsId() {
		return PmDepartmentDesignationBriefLeadsId;
	}
	public void setPmDepartmentDesignationBriefLeadsId(Long pmDepartmentDesignationBriefLeadsId) {
		PmDepartmentDesignationBriefLeadsId = pmDepartmentDesignationBriefLeadsId;
	}
	@Column(name="pm_dept_designation_id")
	public Long getPmDepartmentDesignationId() {
		return pmDepartmentDesignationId;
	}
	public void setPmDepartmentDesignationId(Long pmDepartmentDesignationId) {
		this.pmDepartmentDesignationId = pmDepartmentDesignationId;
	}
	@Column(name="pm_brief_lead_id")
	public Long getPmBriefLeadId() {
		return pmBriefLeadId;
	}
	public void setPmBriefLeadId(Long pmBriefLeadId) {
		this.pmBriefLeadId = pmBriefLeadId;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "pm_dept_designation_id", insertable = false, updatable = false)
	public PmDepartmentDesignation getPmDepartmentDesignation() {
		return pmDepartmentDesignation;
	}
	public void setPmDepartmentDesignation(PmDepartmentDesignation pmDepartmentDesignation) {
		this.pmDepartmentDesignation = pmDepartmentDesignation;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "pm_brief_lead_id", insertable = false, updatable = false)
	public PmBriefLead getPmBriefLead() {
		return pmBriefLead;
	}
	public void setPmBriefLead(PmBriefLead pmBriefLead) {
		this.pmBriefLead = pmBriefLead;
	}
	

}
