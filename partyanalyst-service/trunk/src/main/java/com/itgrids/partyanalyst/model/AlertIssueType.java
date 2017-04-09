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
@Table(name = "alert_issue_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AlertIssueType extends BaseModel implements Serializable{

	private Long alertIssueTypeId;
	private String issueType;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "alert_issue_type_id", unique = true, nullable = false)
	public Long getAlertIssueTypeId() {
		return alertIssueTypeId;
	}
	public void setAlertIssueTypeId(Long alertIssueTypeId) {
		this.alertIssueTypeId = alertIssueTypeId;
	}
	
	@Column(name = "issue_type")
	public String getIssueType() {
		return issueType;
	}
	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}
}
