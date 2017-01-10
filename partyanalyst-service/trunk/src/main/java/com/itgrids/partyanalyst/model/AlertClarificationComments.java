package com.itgrids.partyanalyst.model;

import java.io.Serializable;
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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "alert_clarification_comments")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AlertClarificationComments extends BaseModel implements Serializable{
	
	private Long alertClarificationCommentsId;
	private Long alertClarificationId;
	private String comments;
	private String isDeleted;
	private Date insertedTime;
	private Long insertedBy;
	private Date updatedTime;
	private Long updatedBy;
	private String clarificationRequired;
	
	private AlertClarification alertClarification;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "alert_clarification_comments_id", unique = true, nullable = false)
	public Long getAlertClarificationCommentsId() {
		return alertClarificationCommentsId;
	}
	public void setAlertClarificationCommentsId(Long alertClarificationCommentsId) {
		this.alertClarificationCommentsId = alertClarificationCommentsId;
	}

	@Column(name="alert_clarification_id", insertable=false, updatable = false)
	public Long getAlertClarificationId() {
		return alertClarificationId;
	}
	public void setAlertClarificationId(Long alertClarificationId) {
		this.alertClarificationId = alertClarificationId;
	}

	@Column(name="comments")
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}

	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}

	@Column(name="inserted_by")
	public Long getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
	}

	@Column(name="updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	@Column(name="updated_by")
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="alert_clarification_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AlertClarification getAlertClarification() {
		return alertClarification;
	}
	public void setAlertClarification(AlertClarification alertClarification) {
		this.alertClarification = alertClarification;
	}
	
	@Column(name="clarification_required")
	public String getClarificationRequired() {
		return clarificationRequired;
	}
	public void setClarificationRequired(String clarificationRequired) {
		this.clarificationRequired = clarificationRequired;
	}

}
