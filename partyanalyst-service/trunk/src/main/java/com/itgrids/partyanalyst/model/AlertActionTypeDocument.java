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

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "alert_action_type_document")
public class AlertActionTypeDocument {
	 /*
     * Author:santosh
     */
	private Long alertActionTypeDocumentId;
	private Long alertActionTypeId;
	private String documentPath;
	private String isDeleted;
	private String insertedBy;
	private String insertedTime;
	
	private AlertActionType alertActionType;
	private User insertedUser;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "alert_action_type_document_id",unique = true,nullable = false)
	public Long getAlertActionTypeDocumentId() {
		return alertActionTypeDocumentId;
	}
	public void setAlertActionTypeDocumentId(Long alertActionTypeDocumentId) {
		this.alertActionTypeDocumentId = alertActionTypeDocumentId;
	}
	@Column(name = "alert_action_type_id")
	public Long getAlertActionTypeId() {
		return alertActionTypeId;
	}
	public void setAlertActionTypeId(Long alertActionTypeId) {
		this.alertActionTypeId = alertActionTypeId;
	}
	@Column(name = "document_path")
	public String getDocumentPath() {
		return documentPath;
	}
	public void setDocumentPath(String documentPath) {
		this.documentPath = documentPath;
	}
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Column(name = "inserted_by")
	public String getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(String insertedBy) {
		this.insertedBy = insertedBy;
	}
	@Column(name = "inserted_time")
	public String getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(String insertedTime) {
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
