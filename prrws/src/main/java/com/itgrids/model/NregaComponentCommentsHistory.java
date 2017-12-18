package com.itgrids.model;

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

@Entity
@Table(name = "nrega_component_comments_history")
public class NregaComponentCommentsHistory extends BaseModel implements Serializable{

	private static final long serialVersionUID = -2853930539938433902L;

	private Long nregaComponentCommentsHistoryId;
	private Long nregaComponentCommentsId;
	private Long nregaComponentId;
	private Long nregaComponentStatusId;
	private String comment;
	private String  actionPlan;
	private Long  insertedBy;
	private Long  updatedBy;
	private Date insertedTime;
	private Date updatedTime;
	private String isDeleted;
	private NregaComponent nregaComponent;
	private NregaComponentStatus nregaComponentStatus;
	private String uniqueCode;
	private NregaComponentComments nregaComponentComments;
	
	@Id
	@Column(name="nrega_component_comments_history_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getNregaComponentCommentsHistoryId() {
		return nregaComponentCommentsHistoryId;
	}
	public void setNregaComponentCommentsHistoryId(Long nregaComponentCommentsHistoryId) {
		this.nregaComponentCommentsHistoryId = nregaComponentCommentsHistoryId;
	}
	
	@Column(name="nrega_component_comments_id")
	public Long getNregaComponentCommentsId() {
		return nregaComponentCommentsId;
	}
	public void setNregaComponentCommentsId(Long nregaComponentCommentsId) {
		this.nregaComponentCommentsId = nregaComponentCommentsId;
	}

	@Column(name="inserted_by")
	public Long getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
	}
	@Column(name="nrega_component_id")
	public Long getNregaComponentId() {
		return nregaComponentId;
	}
	public void setNregaComponentId(Long nregaComponentId) {
		this.nregaComponentId = nregaComponentId;
	}
	@Column(name="nrega_component_status_id")
	public Long getNregaComponentStatusId() {
		return nregaComponentStatusId;
	}
	public void setNregaComponentStatusId(Long nregaComponentStatusId) {
		this.nregaComponentStatusId = nregaComponentStatusId;
	}
	@Column(name="comment")
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Column(name="action_plan")
	public String getActionPlan() {
		return actionPlan;
	}
	public void setActionPlan(String actionPlan) {
		this.actionPlan = actionPlan;
	}
	@Column(name="updated_by")
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}

	@Column(name="updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "nrega_component_id", insertable = false, updatable = false)
	public NregaComponent getNregaComponent() {
		return nregaComponent;
	}
	public void setNregaComponent(NregaComponent nregaComponent) {
		this.nregaComponent = nregaComponent;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "nrega_component_status_id", insertable = false, updatable = false)
	public NregaComponentStatus getNregaComponentStatus() {
		return nregaComponentStatus;
	}
	public void setNregaComponentStatus(NregaComponentStatus nregaComponentStatus) {
		this.nregaComponentStatus = nregaComponentStatus;
	}
	@Column(name="unique_code")
	public String getUniqueCode() {
		return uniqueCode;
	}
	public void setUniqueCode(String uniqueCode) {
		this.uniqueCode = uniqueCode;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "nrega_component_comments_id", insertable = false, updatable = false)
	public NregaComponentComments getNregaComponentComments() {
		return nregaComponentComments;
	}
	public void setNregaComponentComments(NregaComponentComments nregaComponentComments) {
		this.nregaComponentComments = nregaComponentComments;
	}
	
}
