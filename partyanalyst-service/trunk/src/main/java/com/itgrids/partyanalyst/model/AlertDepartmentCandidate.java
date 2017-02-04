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
@Table(name = "alert_department_candidate")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AlertDepartmentCandidate extends BaseModel implements Serializable{

	private Long alertDepartmentCandidateId;
	private Long alertDepartmentId;
	private Long candidateId;
	private Long alertImpactId;
	private Long tdpCadreId;
	private Long newsCandidateId;
	private String newsCandidate;
	private String designation;
	private String organization;
	private Long newsOrganizationId;
	private String isDepartment;
	
	private AlertDepartment alertDepartment;
	private Candidate candidate;
	private AlertImpact alertImpact;
	private TdpCadre tdpCadre;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "alert_department_candidate_id", unique = true, nullable = false)
	public Long getAlertDepartmentCandidateId() {
		return alertDepartmentCandidateId;
	}
	public void setAlertDepartmentCandidateId(Long alertDepartmentCandidateId) {
		this.alertDepartmentCandidateId = alertDepartmentCandidateId;
	}
	
	@Column(name = "alert_department_id")
	public Long getAlertDepartmentId() {
		return alertDepartmentId;
	}
	public void setAlertDepartmentId(Long alertDepartmentId) {
		this.alertDepartmentId = alertDepartmentId;
	}
	
	@Column(name = "candidate_id")
	public Long getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}
	
	@Column(name = "alert_impact_id")
	public Long getAlertImpactId() {
		return alertImpactId;
	}
	public void setAlertImpactId(Long alertImpactId) {
		this.alertImpactId = alertImpactId;
	}
	
	@Column(name = "tdp_cadre_id")
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	
	@Column(name = "news_candidate_id")
	public Long getNewsCandidateId() {
		return newsCandidateId;
	}
	public void setNewsCandidateId(Long newsCandidateId) {
		this.newsCandidateId = newsCandidateId;
	}
	
	@Column(name = "news_candidate")
	public String getNewsCandidate() {
		return newsCandidate;
	}
	public void setNewsCandidate(String newsCandidate) {
		this.newsCandidate = newsCandidate;
	}
	
	@Column(name = "designation")
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	@Column(name = "organization")
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	
	@Column(name = "news_organization_id")
	public Long getNewsOrganizationId() {
		return newsOrganizationId;
	}
	public void setNewsOrganizationId(Long newsOrganizationId) {
		this.newsOrganizationId = newsOrganizationId;
	}
	
	@Column(name = "is_department")
	public String getIsDepartment() {
		return isDepartment;
	}
	public void setIsDepartment(String isDepartment) {
		this.isDepartment = isDepartment;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="alert_department_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AlertDepartment getAlertDepartment() {
		return alertDepartment;
	}
	public void setAlertDepartment(AlertDepartment alertDepartment) {
		this.alertDepartment = alertDepartment;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="candidate_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Candidate getCandidate() {
		return candidate;
	}
	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="alert_impact_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AlertImpact getAlertImpact() {
		return alertImpact;
	}
	public void setAlertImpact(AlertImpact alertImpact) {
		this.alertImpact = alertImpact;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="tdp_cadre_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCadre getTdpCadre() {
		return tdpCadre;
	}
	public void setTdpCadre(TdpCadre tdpCadre) {
		this.tdpCadre = tdpCadre;
	}
}
