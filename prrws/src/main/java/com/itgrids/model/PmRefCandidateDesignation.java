package com.itgrids.model;

import java.util.Date;

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
@Table(name = "pm_ref_candidate_designation")
public class PmRefCandidateDesignation {
	

	private Long pmRefCandidateDesignationId;
	private Long pmRefCandidateId;
	private Long pmDesginationId;
	private String is_active;
	private Date startDate;
	private Date endDate;
	private String isDeleted;
	private PmRefCandidate pmRefCandidate;
	private PmDesignation pmDesgination;
	
	
	
	@Id
	@Column(name="pm_ref_candidate_designation_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getPmRefCandidateDesignationId() {
		return pmRefCandidateDesignationId;
	}
	public void setPmRefCandidateDesignationId(Long pmRefCandidateDesignationId) {
		this.pmRefCandidateDesignationId = pmRefCandidateDesignationId;
	}
	@Column(name="pm_ref_candidate_id")
	public Long getPmRefCandidateId() {
		return pmRefCandidateId;
	}
	public void setPmRefCandidateId(Long pmRefCandidateId) {
		this.pmRefCandidateId = pmRefCandidateId;
	}
	@Column(name="pm_Desgination_id")
	public Long getPmDesginationId() {
		return pmDesginationId;
	}
	public void setPmDesginationId(Long pmDesginationId) {
		this.pmDesginationId = pmDesginationId;
	}
	@Column(name="is_active")
	public String getIs_active() {
		return is_active;
	}
	public void setIs_active(String is_active) {
		this.is_active = is_active;
	}
	@Column(name="start_date")
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	@Column(name="end_date")
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "pm_ref_candidate_id", insertable = false, updatable = false)
	public PmRefCandidate getPmRefCandidate() {
		return pmRefCandidate;
	}
	public void setPmRefCandidate(PmRefCandidate pmRefCandidate) {
		this.pmRefCandidate = pmRefCandidate;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "pm_Desgination_id", insertable = false, updatable = false)
	public PmDesignation getPmDesgination() {
		return pmDesgination;
	}
	public void setPmDesgination(PmDesignation pmDesgination) {
		this.pmDesgination = pmDesgination;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
}
