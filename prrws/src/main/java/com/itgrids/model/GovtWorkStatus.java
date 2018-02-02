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
@Table(name = "govt_work_status")
public class GovtWorkStatus {
	private Long govtWorkStatusId;
	private String statusName;
	private Long statusTypeId;
	private Long govtWorkTypeId;
	
	private StatusType statusType;
	private GovtWorkType govtWorkType;
	
	@Id
	@Column(name="govt_work_status_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getGovtWorkStatusId() {
		return govtWorkStatusId;
	}
	public void setGovtWorkStatusId(Long govtWorkStatusId) {
		this.govtWorkStatusId = govtWorkStatusId;
	}
	
	@Column(name="status_name")
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
	@Column(name="status_type_id")
	public Long getStatusTypeId() {
		return statusTypeId;
	}
	public void setStatusTypeId(Long statusTypeId) {
		this.statusTypeId = statusTypeId;
	}
	
	@Column(name="govt_work_type_id")
	public Long getGovtWorkTypeId() {
		return govtWorkTypeId;
	}
	public void setGovtWorkTypeId(Long govtWorkTypeId) {
		this.govtWorkTypeId = govtWorkTypeId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "status_type_id", insertable = false, updatable = false)
	public StatusType getStatusType() {
		return statusType;
	}
	public void setStatusType(StatusType statusType) {
		this.statusType = statusType;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "govt_work_type_id", insertable = false, updatable = false)
	public GovtWorkType getGovtWorkType() {
		return govtWorkType;
	}
	public void setGovtWorkType(GovtWorkType govtWorkType) {
		this.govtWorkType = govtWorkType;
	}
}
