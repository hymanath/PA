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
@Table(name = "govt_work_progress")
public class GovtWorkProgress {
	private Long govtWorkProgressId;
	private Long govtWorkId;
	private Long govtWorkStatusId;
	private Double workLength;
	private Double completedPercentage;
	private Long updatedBy;
	private Date updatedTime;
	
	private GovtWork govtWork;
	private GovtWorkStatus govtWorkStatus;
	private MobileAppUser updatedByUser;
	
	@Id
	@Column(name="govt_work_progress_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getGovtWorkProgressId() {
		return govtWorkProgressId;
	}
	public void setGovtWorkProgressId(Long govtWorkProgressId) {
		this.govtWorkProgressId = govtWorkProgressId;
	}
	
	@Column(name="govt_work_id")
	public Long getGovtWorkId() {
		return govtWorkId;
	}
	public void setGovtWorkId(Long govtWorkId) {
		this.govtWorkId = govtWorkId;
	}
	
	@Column(name="govt_work_status_id")
	public Long getGovtWorkStatusId() {
		return govtWorkStatusId;
	}
	public void setGovtWorkStatusId(Long govtWorkStatusId) {
		this.govtWorkStatusId = govtWorkStatusId;
	}
	
	@Column(name="work_length")
	public Double getWorkLength() {
		return workLength;
	}
	public void setWorkLength(Double workLength) {
		this.workLength = workLength;
	}
	
	@Column(name="completed_percentage")
	public Double getCompletedPercentage() {
		return completedPercentage;
	}
	public void setCompletedPercentage(Double completedPercentage) {
		this.completedPercentage = completedPercentage;
	}
	
	@Column(name="updated_by")
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	@Column(name="updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "govt_work_id", insertable = false, updatable = false)
	public GovtWork getGovtWork() {
		return govtWork;
	}
	public void setGovtWork(GovtWork govtWork) {
		this.govtWork = govtWork;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "govt_work_status_id", insertable = false, updatable = false)
	public GovtWorkStatus getGovtWorkStatus() {
		return govtWorkStatus;
	}
	public void setGovtWorkStatus(GovtWorkStatus govtWorkStatus) {
		this.govtWorkStatus = govtWorkStatus;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "updated_by", insertable = false, updatable = false)
	public MobileAppUser getUpdatedByUser() {
		return updatedByUser;
	}
	public void setUpdatedByUser(MobileAppUser updatedByUser) {
		this.updatedByUser = updatedByUser;
	}
	
	
}
