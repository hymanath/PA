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
@Table(name = "alert_feedback_status")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AlertFeedbackStatus extends BaseModel implements Serializable{

	private Long alertFeedbackStatusId;
	private String status;
	private String isDeleted;
	private Long orderNo;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "alert_feedback_status_id", unique = true, nullable = false)
	public Long getAlertFeedbackStatusId() {
		return alertFeedbackStatusId;
	}
	public void setAlertFeedbackStatusId(Long alertFeedbackStatusId) {
		this.alertFeedbackStatusId = alertFeedbackStatusId;
	}
	
	@Column(name = "status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@Column(name = "order_no")
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
}
