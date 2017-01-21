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
@Table(name = "verification_comments")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VerificationComments extends BaseModel implements Serializable {

	
	private Long verificationCommentsId;
	private Long verificationConversationId;
	private Long alertVerificationUserTypeId;
	private String comments;
	private String isDeleted;
	private Long insertedBy;
	private Date insertedTime;
	private Long updatedBy;
	private Date  updatedTime;
	
	private VerificationConversation verificationConversation;
	private AlertVerificationUserType alertVerificationUserType;
	private User insertedUser;
	private User updatedUser;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "verification_comments_id", unique = true, nullable = false)
	public Long getVerificationCommentsId() {
		return verificationCommentsId;
	}
	public void setVerificationCommentsId(Long verificationCommentsId) {
		this.verificationCommentsId = verificationCommentsId;
	}
	@Column(name="verification_conversation_id")
	public Long getVerificationConversationId() {
		return verificationConversationId;
	}
	public void setVerificationConversationId(Long verificationConversationId) {
		this.verificationConversationId = verificationConversationId;
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
	@Column(name="inserted_by")
	public Long getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
	}
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	@Column(name="updated_by")
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
	@Column(name="updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	@Column(name="alert_verification_user_type_id")
	public Long getAlertVerificationUserTypeId() {
		return alertVerificationUserTypeId;
	}
	public void setAlertVerificationUserTypeId(Long alertVerificationUserTypeId) {
		this.alertVerificationUserTypeId = alertVerificationUserTypeId;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY )
	@JoinColumn(name = "alert_verification_user_type_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AlertVerificationUserType getAlertVerificationUserType() {
		return alertVerificationUserType;
	}
	public void setAlertVerificationUserType(
			AlertVerificationUserType alertVerificationUserType) {
		this.alertVerificationUserType = alertVerificationUserType;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY )
	@JoinColumn(name = "verification_conversation_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public VerificationConversation getVerificationConversation() {
		return verificationConversation;
	}
	public void setVerificationConversation(
			VerificationConversation verificationConversation) {
		this.verificationConversation = verificationConversation;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY )
	@JoinColumn(name = "inserted_by" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getInsertedUser() {
		return insertedUser;
	}
	public void setInsertedUser(User insertedUser) {
		this.insertedUser = insertedUser;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY )
	@JoinColumn(name = "updated_by" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUpdatedUser() {
		return updatedUser;
	}
	public void setUpdatedUser(User updatedUser) {
		this.updatedUser = updatedUser;
	}
	
	
}
