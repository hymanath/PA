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
@Table(name = "action_type_status")
public class ActionTypeStatus {

	private Long actionTypeStatusId;
	private Long actionTypeId;
	private String status;
	
	private ActionType actionType;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "action_type_status_id",unique = true,nullable = false)
	public Long getActionTypeStatusId() {
		return actionTypeStatusId;
	}
	public void setActionTypeStatusId(Long actionTypeStatusId) {
		this.actionTypeStatusId = actionTypeStatusId;
	}
    @Column(name = "action_type_id")
	public Long getActionTypeId() {
		return actionTypeId;
	}

	public void setActionTypeId(Long actionTypeId) {
		this.actionTypeId = actionTypeId;
	}
    @Column(name = "status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "action_type_id",insertable = false,updatable = false)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ActionType getActionType() {
		return actionType;
	}

	public void setActionType(ActionType actionType) {
		this.actionType = actionType;
	}
	
	
}
