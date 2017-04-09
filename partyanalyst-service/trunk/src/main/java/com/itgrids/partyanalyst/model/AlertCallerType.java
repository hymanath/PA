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
@Table(name = "alert_caller_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AlertCallerType extends BaseModel implements Serializable{

	private Long alertCallerTypeId;
	private String callerType;
	private Long orderNo;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "alert_caller_type", unique = true, nullable = false)
	public Long getAlertCallerTypeId() {
		return alertCallerTypeId;
	}
	public void setAlertCallerTypeId(Long alertCallerTypeId) {
		this.alertCallerTypeId = alertCallerTypeId;
	}

	@Column(name = "caller_type")
	public String getCallerType() {
		return callerType;
	}
	public void setCallerType(String callerType) {
		this.callerType = callerType;
	}
	
	@Column(name = "order_no")
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
}
