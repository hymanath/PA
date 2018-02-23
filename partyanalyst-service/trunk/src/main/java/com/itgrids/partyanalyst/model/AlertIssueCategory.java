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
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="alert_issue_category")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class AlertIssueCategory implements Serializable{

	private Long alertIssueCategoryId;
	private String issueCategory;
	private Long alertTypeId;
	private String isDeleted;
	private String categoryColor;
	
	private AlertType alertType;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="alert_issue_category_id", unique = true , nullable =false)
	public Long getAlertIssueCategoryId() {
		return alertIssueCategoryId;
	}

	public void setAlertIssueCategoryId(Long alertIssueCategoryId) {
		this.alertIssueCategoryId = alertIssueCategoryId;
	}
	@Column(name="issue_category")
	public String getIssueCategory() {
		return issueCategory;
	}

	public void setIssueCategory(String issueCategory) {
		this.issueCategory = issueCategory;
	}
	@Column(name="alert_type_id")
	public Long getAlertTypeId() {
		return alertTypeId;
	}

	public void setAlertTypeId(Long alertTypeId) {
		this.alertTypeId = alertTypeId;
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
	public AlertType getAlertType() {
		return alertType;
	}

	public void setAlertType(AlertType alertType) {
		this.alertType = alertType;
	}
	@Column(name="category_color")
	public String getCategoryColor() {
		return categoryColor;
	}

	public void setCategoryColor(String categoryColor) {
		this.categoryColor = categoryColor;
	}
	
	
	
}
