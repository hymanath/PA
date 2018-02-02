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
@Table(name = "govt_work_progress_update")
public class GovtWorkProgressUpdate {
	private Long govtWorkProgressUpdateId;
	private Long govtWorkId;
	private Long updatedBy;
	private Date updatedTime;
	
	private GovtWork govtWork;
	private MobileAppUser updatedByUSer;
	
	@Id
	@Column(name="govt_work_progress_update_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
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
	@JoinColumn(name = "updated_by", insertable = false, updatable = false)
	public MobileAppUser getUpdatedByUSer() {
		return updatedByUSer;
	}
	public void setUpdatedByUSer(MobileAppUser updatedByUSer) {
		this.updatedByUSer = updatedByUSer;
	}
	
	
}
