package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "alert_comment")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AlertComment extends BaseModel implements Serializable {
	private Long alertCommentId;
	private Long alertId;
	private String comments;
	private Long insertedBy;
	private Date insertedTime;
	private String isDeleted;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "alert_comment_id", unique = true, nullable = false)

	public Long getAlertCommentId() {
		return alertCommentId;
	}

	public void setAlertCommentId(Long alertCommentId) {
		this.alertCommentId = alertCommentId;
	}
	
	@Column(name = "alert_id")
	public Long getAlertId() {
		return alertId;
	}


	public void setAlertId(Long alertId) {
		this.alertId = alertId;
	}

	@Column(name = "comments")
	public String getComments() {
		return comments;
	}


	public void setComments(String comments) {
		this.comments = comments;
	}

	@Column(name = "inserted_by")
	public Long getInsertedBy() {
		return insertedBy;
	}

	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
	}

	@Column(name = "inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}

	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}

	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

}
