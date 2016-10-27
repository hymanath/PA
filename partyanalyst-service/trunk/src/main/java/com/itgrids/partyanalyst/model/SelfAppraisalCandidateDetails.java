package com.itgrids.partyanalyst.model;

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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="self_appraisal_candidate_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SelfAppraisalCandidateDetails {

	private Long selfAppraisalCandidateDetailsId;
	private Long selfAppraisalCandidateId;
	private String month;
	private String year;
	private Long ownLocationId;
	private Long ownLocationScopeId;
	private Long ownTours;
	private Long inchargeLocationId;
	private Long inchargeLocationScopeId;
	private Long inchargeTours;
	private String remarks;
	private String reportPath;
	private Date insertedTime;
	private Date updatedTime;
	private Long insertedBy;
	private Long updatedBy;
	
	private SelfAppraisalCandidate selfAppraisalCandidate;
	private User insertedUser;
	private User updatedUser;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="self_appraisal_candidate_details_id", unique=true, nullable=false)
	public Long getSelfAppraisalCandidateDetailsId() {
		return selfAppraisalCandidateDetailsId;
	}
	public void setSelfAppraisalCandidateDetailsId(
			Long selfAppraisalCandidateDetailsId) {
		this.selfAppraisalCandidateDetailsId = selfAppraisalCandidateDetailsId;
	}
	@Column(name="self_appraisal_candidate_id")
	public Long getSelfAppraisalCandidateId() {
		return selfAppraisalCandidateId;
	}
	public void setSelfAppraisalCandidateId(Long selfAppraisalCandidateId) {
		this.selfAppraisalCandidateId = selfAppraisalCandidateId;
	}
	@Column(name="month")
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	@Column(name="year")
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	@Column(name="own_location_id")
	public Long getOwnLocationId() {
		return ownLocationId;
	}
	public void setOwnLocationId(Long ownLocationId) {
		this.ownLocationId = ownLocationId;
	}
	@Column(name="own_location_scope_id")
	public Long getOwnLocationScopeId() {
		return ownLocationScopeId;
	}
	public void setOwnLocationScopeId(Long ownLocationScopeId) {
		this.ownLocationScopeId = ownLocationScopeId;
	}
	@Column(name="own_tours")
	public Long getOwnTours() {
		return ownTours;
	}
	public void setOwnTours(Long ownTours) {
		this.ownTours = ownTours;
	}
	@Column(name="incharge_location_id")
	public Long getInchargeLocationId() {
		return inchargeLocationId;
	}
	public void setInchargeLocationId(Long inchargeLocationId) {
		this.inchargeLocationId = inchargeLocationId;
	}
	@Column(name="incharge_location_scope_id")
	public Long getInchargeLocationScopeId() {
		return inchargeLocationScopeId;
	}
	public void setInchargeLocationScopeId(Long inchargeLocationScopeId) {
		this.inchargeLocationScopeId = inchargeLocationScopeId;
	}
	@Column(name="incharge_tours")
	public Long getInchargeTours() {
		return inchargeTours;
	}
	public void setInchargeTours(Long inchargeTours) {
		this.inchargeTours = inchargeTours;
	}
	@Column(name="remarks")
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Column(name="report_path")
	public String getReportPath() {
		return reportPath;
	}
	public void setReportPath(String reportPath) {
		this.reportPath = reportPath;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="self_appraisal_candidate_id",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public SelfAppraisalCandidate getSelfAppraisalCandidate() {
		return selfAppraisalCandidate;
	}
	public void setSelfAppraisalCandidate(
			SelfAppraisalCandidate selfAppraisalCandidate) {
		this.selfAppraisalCandidate = selfAppraisalCandidate;
	}
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	@Column(name="updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
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
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="inserted_by",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getInsertedUser() {
		return insertedUser;
	}
	public void setInsertedUser(User insertedUser) {
		this.insertedUser = insertedUser;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="updated_by",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUpdatedUser() {
		return updatedUser;  
	}
	public void setUpdatedUser(User updatedUser) {
		this.updatedUser = updatedUser;
	}  
}
