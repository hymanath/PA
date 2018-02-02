package com.itgrids.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "govt_work_progress_track")
public class GovtWorkProgressTrack {
	private Long govtWorkProgressTrackId;
	private Long govtWorkProgressUpdateId;
	private Long govtWorkId;
	private Long govtWorkStatusId;
	private Double workLength;
	private Double completedPercentage;
	private Long updatedBy;
	private Date updatedTime;
	
	private GovtWorkProgressUpdate govtWorkProgressUpdate;
	private GovtWork govtWork;
	private GovtWorkStatus govtWorkStatus;
	private MobileAppUser updatedByUser;
	
	@Column(name="govt_work_progress_track_id")
	public Long getGovtWorkProgressTrackId() {
		return govtWorkProgressTrackId;
	}
	public void setGovtWorkProgressTrackId(Long govtWorkProgressTrackId) {
		this.govtWorkProgressTrackId = govtWorkProgressTrackId;
	}
	
	@Column(name="govt_work_progress_update_id")
	public Long getGovtWorkProgressUpdateId() {
		return govtWorkProgressUpdateId;
	}
	public void setGovtWorkProgressUpdateId(Long govtWorkProgressUpdateId) {
		this.govtWorkProgressUpdateId = govtWorkProgressUpdateId;
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
	@JoinColumn(name = "govt_work_progress_update_id", insertable = false, updatable = false)
	public GovtWorkProgressUpdate getGovtWorkProgressUpdate() {
		return govtWorkProgressUpdate;
	}
	public void setGovtWorkProgressUpdate(GovtWorkProgressUpdate govtWorkProgressUpdate) {
		this.govtWorkProgressUpdate = govtWorkProgressUpdate;
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
