package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="alert_sub_task_status")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AlertSubTaskStatus extends BaseModel implements Serializable{

	private static final long serialVersionUID = -3806379738904956901L;
	
	private Long alertSubTaskStatusId;
	private String status;
	private String statusOrder;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="alert_sub_task_status_id", unique=true, nullable=false)
	public Long getAlertSubTaskStatusId() {
		return alertSubTaskStatusId;
	}
	public void setAlertSubTaskStatusId(Long alertSubTaskStatusId) {
		this.alertSubTaskStatusId = alertSubTaskStatusId;
	}
	
	@Column(name="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name="status_order")
	public String getStatusOrder() {
		return statusOrder;
	}
	public void setStatusOrder(String statusOrder) {
		this.statusOrder = statusOrder;
	}
	
}
