package com.itgrids.partyanalyst.model;

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
@Table(name="self_appraisal_candidate")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SelfAppraisalCandidate {
 
	private Long selfAppraisalCandidateId;
	private Long tdpCadreId;
	private Long selfAppraisalDesignationId;
	private String isActive; 
	
	private TdpCadre tdpCadre;
	private SelfAppraisalDesignation selfAppraisalDesignation;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="self_appraisal_candidate_id", unique=true, nullable=false)
	public Long getSelfAppraisalCandidateId() {
		return selfAppraisalCandidateId;
	}
	public void setSelfAppraisalCandidateId(Long selfAppraisalCandidateId) {
		this.selfAppraisalCandidateId = selfAppraisalCandidateId;
	}
	@Column(name="tdp_cadre_id")
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	@Column(name="self_appraisal_designation_id")
	public Long getSelfAppraisalDesignationId() {
		return selfAppraisalDesignationId;
	}
	public void setSelfAppraisalDesignationId(Long selfAppraisalDesignationId) {
		this.selfAppraisalDesignationId = selfAppraisalDesignationId;
	}
	@Column(name="is_active")
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="tdp_cadre_id",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCadre getTdpCadre() {
		return tdpCadre;
	}
	public void setTdpCadre(TdpCadre tdpCadre) {
		this.tdpCadre = tdpCadre;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="self_appraisal_designation_id",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public SelfAppraisalDesignation getSelfAppraisalDesignation() {
		return selfAppraisalDesignation;
	}
	public void setSelfAppraisalDesignation(
			SelfAppraisalDesignation selfAppraisalDesignation) {
		this.selfAppraisalDesignation = selfAppraisalDesignation;
	}
	
	
}
