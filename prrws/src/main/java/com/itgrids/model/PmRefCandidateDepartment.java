package com.itgrids.model;

import java.io.Serializable;
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
@Table(name = "pm_ref_candidate_department")
public class PmRefCandidateDepartment extends BaseModel implements Serializable{
	
	private Long PmRefCandidateDepartmentId;
	private Long pmRefCandidateId;
	//private Long pmDepartmentId;
	private String isDeleted;
	private String isActive;
	private Long insertedBy;
	private Long updatedBy;
	private Date insertedTime;
	private Date udpatedTime;
	private Long pmMinistryDepartmentId;
	
	private PmRefCandidate pmRefCandidate;
	//private PmDepartment pmDepartment;
	private PmMinistryDepartment pmMinistryDepartment;
	
	@Id
	@Column(name="pm_ref_candidate_department_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getPmRefCandidateDepartmentId() {
		return PmRefCandidateDepartmentId;
	}
	public void setPmRefCandidateDepartmentId(Long pmRefCandidateDepartmentId) {
		PmRefCandidateDepartmentId = pmRefCandidateDepartmentId;
	}
	
	@Column(name="pm_ref_candidate_id")
	public Long getPmRefCandidateId() {
		return pmRefCandidateId;
	}
	public void setPmRefCandidateId(Long pmRefCandidateId) {
		this.pmRefCandidateId = pmRefCandidateId;
	}
	
	/*@Column(name="pm_department_id")
	public Long getPmDepartmentId() {
		return pmDepartmentId;
	}
	public void setPmDepartmentId(Long pmDepartmentId) {
		this.pmDepartmentId = pmDepartmentId;
	}*/
	
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@Column(name="is_active")
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	
	@Column(name="inserted_by")
	public Long getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
	}
	
	@Column(name="updated_by")
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@Column(name="udpated_time")
	public Date getUdpatedTime() {
		return udpatedTime;
	}
	public void setUdpatedTime(Date udpatedTime) {
		this.udpatedTime = udpatedTime;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "pm_ref_candidate_id", insertable = false, updatable = false)
	public PmRefCandidate getPmRefCandidate() {
		return pmRefCandidate;
	}
	public void setPmRefCandidate(PmRefCandidate pmRefCandidate) {
		this.pmRefCandidate = pmRefCandidate;
	}
	
	@Column(name="pm_ministry_department_id")
	public Long getPmMinistryDepartmentId() {
		return pmMinistryDepartmentId;
	}
	public void setPmMinistryDepartmentId(Long pmMinistryDepartmentId) {
		this.pmMinistryDepartmentId = pmMinistryDepartmentId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "pm_ministry_department_id", insertable = false, updatable = false)
	public PmMinistryDepartment getPmMinistryDepartment() {
		return pmMinistryDepartment;
	}
	public void setPmMinistryDepartment(PmMinistryDepartment pmMinistryDepartment) {
		this.pmMinistryDepartment = pmMinistryDepartment;
	}
	
	/*@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "pm_department_id", insertable = false, updatable = false)
	public PmDepartment getPmDepartment() {
		return pmDepartment;
	}
	public void setPmDepartment(PmDepartment pmDepartment) {
		this.pmDepartment = pmDepartment;
	}
	*/
	
	
}
