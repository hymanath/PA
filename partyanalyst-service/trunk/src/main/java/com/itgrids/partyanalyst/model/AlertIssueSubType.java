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
@Table(name="alert_issue_sub_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AlertIssueSubType extends BaseModel implements Serializable{

	private static final long serialVersionUID = 9039469603692401537L;
	
	private Long alertIssueSubTypeId;
	private Long issueType;
	private AlertIssueType alertIssueType;
	private Long orderNo;
	
	public AlertIssueSubType(){}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="alert_issue_sub_type_id", unique=true, nullable=false)
	public Long getAlertIssueSubTypeId() {
		return alertIssueSubTypeId;
	}

	public void setAlertIssueSubTypeId(Long alertIssueSubTypeId) {
		this.alertIssueSubTypeId = alertIssueSubTypeId;
	}

	@Column(name="issue_type")
	public Long getIssueType() {
		return issueType;
	}

	public void setIssueType(Long issueType) {
		this.issueType = issueType;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="alert_issue_type_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AlertIssueType getAlertIssueType() {
		return alertIssueType;
	}
	
	public void setAlertIssueType(AlertIssueType alertIssueType) {
		this.alertIssueType = alertIssueType;
	}

	@Column(name="order_no")
	public Long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	
}
