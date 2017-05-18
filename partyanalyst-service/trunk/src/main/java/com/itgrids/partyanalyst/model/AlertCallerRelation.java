package com.itgrids.partyanalyst.model;

import java.io.Serializable;

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
@Table(name="alert_caller_relation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AlertCallerRelation extends BaseModel implements Serializable{

	private Long alertCallerRelationId;
	private Long alertCallerId;
	private Long alertId;
	private String isDeleted;
	private Long callerOrder;
	
	private AlertCaller alertCaller;
	private Alert alert;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="alert_caller_relation_id", unique=true, nullable=false)
	public Long getAlertCallerRelationId() {
		return alertCallerRelationId;
	}
	public void setAlertCallerRelationId(Long alertCallerRelationId) {
		this.alertCallerRelationId = alertCallerRelationId;
	}
	
	@Column(name="alert_caller_id")
	public Long getAlertCallerId() {
		return alertCallerId;
	}
	public void setAlertCallerId(Long alertCallerId) {
		this.alertCallerId = alertCallerId;
	}
	
	@Column(name="alert_id")
	public Long getAlertId() {
		return alertId;
	}
	public void setAlertId(Long alertId) {
		this.alertId = alertId;
	}
	
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="alert_caller_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AlertCaller getAlertCaller() {
		return alertCaller;
	}
	public void setAlertCaller(AlertCaller alertCaller) {
		this.alertCaller = alertCaller;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="alert_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Alert getAlert() {
		return alert;
	}
	public void setAlert(Alert alert) {
		this.alert = alert;
	}
	
	@Column(name="caller_order")
	public Long getCallerOrder() {
		return callerOrder;
	}
	public void setCallerOrder(Long callerOrder) {
		this.callerOrder = callerOrder;
	}
}
