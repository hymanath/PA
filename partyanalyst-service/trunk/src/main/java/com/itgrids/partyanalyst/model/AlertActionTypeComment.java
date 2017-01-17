package com.itgrids.partyanalyst.model;

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

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "alert_action_type_comment")
public class AlertActionTypeComment {
	 /*
     * Author:santosh
     */
	private Long alertActionTypeCommentId;
	private Long alertActionTypeId;
	private String comment;
	private String isDeleted;
	private Long insertedBy;
	private Date insertedTime;
	
	
	private AlertActionType alertActionType;
	private User insertedUser;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "alert_action_type_comment_id",unique = true,nullable = false)
	public Long getAlertActionTypeCommentId() {
		return alertActionTypeCommentId;
	}
	public void setAlertActionTypeCommentId(Long alertActionTypeCommentId) {
		this.alertActionTypeCommentId = alertActionTypeCommentId;
	}
	@Column(name = "alert_action_type_id")
	public Long getAlertActionTypeId() {
		return alertActionTypeId;
	}
	public void setAlertActionTypeId(Long alertActionTypeId) {
		this.alertActionTypeId = alertActionTypeId;
	}
	@Column(name = "comment")
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
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
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "alert_action_type_id",insertable = false,updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AlertActionType getAlertActionType() {
		return alertActionType;
	}
	public void setAlertActionType(AlertActionType alertActionType) {
		this.alertActionType = alertActionType;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "inserted_by",insertable = false,updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getInsertedUser() {
		return insertedUser;
	}
	public void setInsertedUser(User insertedUser) {
		this.insertedUser = insertedUser;
	}
	
	
}
