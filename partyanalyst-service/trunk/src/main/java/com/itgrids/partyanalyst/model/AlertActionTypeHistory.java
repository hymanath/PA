package com.itgrids.partyanalyst.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "alert_action_type_history")
public class AlertActionTypeHistory {
	 /*
     * Author:santosh
     */
	private Long alertActionTypeHistoryId;
	private Long alertActionTypeId;
	private Long alertId;
	private Long actionTypeId;
	private Long actionTypeStatusId;
	private Date insertedTime;
	private Date updatedTime;
	private Long insertedBy;
	private Long updatedBy;
	private String isDeleted;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "alert_action_type_history_id",unique = true,nullable = false)
	public Long getAlertActionTypeHistoryId() {
		return alertActionTypeHistoryId;
	}
	public void setAlertActionTypeHistoryId(Long alertActionTypeHistoryId) {
		this.alertActionTypeHistoryId = alertActionTypeHistoryId;
	}
	@Column(name = "alert_action_type_id")
	public Long getAlertActionTypeId() {
		return alertActionTypeId;
	}
	public void setAlertActionTypeId(Long alertActionTypeId) {
		this.alertActionTypeId = alertActionTypeId;
	}
	@Column(name = "alert_id")
	public Long getAlertId() {
		return alertId;
	}
	public void setAlertId(Long alertId) {
		this.alertId = alertId;
	}
	@Column(name = "action_type_id")
	public Long getActionTypeId() {
		return actionTypeId;
	}
	public void setActionTypeId(Long actionTypeId) {
		this.actionTypeId = actionTypeId;
	}
	@Column(name = "action_type_status_id")
	public Long getActionTypeStatusId() {
		return actionTypeStatusId;
	}
	public void setActionTypeStatusId(Long actionTypeStatusId) {
		this.actionTypeStatusId = actionTypeStatusId;
	}
	@Column(name = "inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	@Column(name = "updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	@Column(name = "inserted_by")
	public Long getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
	}
	@Column(name = "updated_by")
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
}
