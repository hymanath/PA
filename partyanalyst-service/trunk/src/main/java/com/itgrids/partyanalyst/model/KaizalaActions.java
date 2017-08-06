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
@Table(name = "kaizala_actions")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class KaizalaActions extends BaseModel implements Serializable {

	private Long kaizalaActionsId;
	private String actionId;
	private String title;
	private String actionType;
	private String referenceId;
	private String sender;
	private Date sentAt;
	private String isSenderOnly;
	private String isAnonymous;
	private String acceptMultiresponse;
	private Date expiryDate;
	private String groupId;
	private String isDeleted;
	
	@Id
	@Column(name="kaizala_actions_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getKaizalaActionsId() {
		return kaizalaActionsId;
	}
	public void setKaizalaActionsId(Long kaizalaActionsId) {
		this.kaizalaActionsId = kaizalaActionsId;
	}
	@Column(name="actionId")
	public String getActionId() {
		return actionId;
	}
	public void setActionId(String actionId) {
		this.actionId = actionId;
	}
	@Column(name="title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name="action_type")
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	@Column(name="referenceId")
	public String getReferenceId() {
		return referenceId;
	}
	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}
	@Column(name="sender")
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	@Column(name="sent_at")
	public Date getSentAt() {
		return sentAt;
	}
	public void setSentAt(Date sentAt) {
		this.sentAt = sentAt;
	}
	@Column(name="is_sender_only")
	public String getIsSenderOnly() {
		return isSenderOnly;
	}
	public void setIsSenderOnly(String isSenderOnly) {
		this.isSenderOnly = isSenderOnly;
	}
	@Column(name="is_anonymous")
	public String getIsAnonymous() {
		return isAnonymous;
	}
	public void setIsAnonymous(String isAnonymous) {
		this.isAnonymous = isAnonymous;
	}
	@Column(name="accept_multiresponse")
	public String getAcceptMultiresponse() {
		return acceptMultiresponse;
	}
	public void setAcceptMultiresponse(String acceptMultiresponse) {
		this.acceptMultiresponse = acceptMultiresponse;
	}
	@Column(name="expiry_date")
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	@Column(name="group_id")
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
	
	
}
