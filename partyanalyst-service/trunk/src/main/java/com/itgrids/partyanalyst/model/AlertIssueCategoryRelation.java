package com.itgrids.partyanalyst.model;

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
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="alert_issue_category_relation")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class AlertIssueCategoryRelation {

	private Long alertIssueCategoryRelationId;
	private Long alertId;
	private Long alertIssueCategoryId;
	private String isDeleted;
	
	private Alert alert;
	private AlertIssueCategory alertIssueCategory;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="alert_issue_category_relation_id", unique = true , nullable =false)
	public Long getAlertIssueCategoryRelationId() {
		return alertIssueCategoryRelationId;
	}
	public void setAlertIssueCategoryRelationId(Long alertIssueCategoryRelationId) {
		this.alertIssueCategoryRelationId = alertIssueCategoryRelationId;
	}
	@Column(name="alert_id")
	public Long getAlertId() {
		return alertId;
	}
	public void setAlertId(Long alertId) {
		this.alertId = alertId;
	}
	@Column(name="alert_issue_category_id")
	public Long getAlertIssueCategoryId() {
		return alertIssueCategoryId;
	}
	public void setAlertIssueCategoryId(Long alertIssueCategoryId) {
		this.alertIssueCategoryId = alertIssueCategoryId;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "alert_id" ,insertable = false ,updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@NotFound(action=NotFoundAction.IGNORE)
	public Alert getAlert() {
		return alert;
	}
	public void setAlert(Alert alert) {
		this.alert = alert;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "alert_issue_category_id" ,insertable = false ,updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@NotFound(action=NotFoundAction.IGNORE)
	public AlertIssueCategory getAlertIssueCategory() {
		return alertIssueCategory;
	}
	public void setAlertIssueCategory(AlertIssueCategory alertIssueCategory) {
		this.alertIssueCategory = alertIssueCategory;
	}
	
	
	
}
