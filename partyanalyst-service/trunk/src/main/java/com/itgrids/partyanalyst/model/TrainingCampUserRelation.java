package com.itgrids.partyanalyst.model;

import java.util.Date;

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
@Table(name = "training_camp_user_relation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TrainingCampUserRelation {
	
	private Long trainingCampUserRelationId;
	private Long trainingCampCallAdminId;
	private Long trainingCampCallerId;
	private String isDeleted;
	private Long insertedBy;
	private Long updatedBy;
	private Date insertedTime;
	private Date updatedTime;
	private User trainingCampCallAdmin;
	private User trainingCampCaller;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "training_camp_user_relation_id", unique = true, nullable = false)
	public Long getTrainingCampUserRelationId() {
		return trainingCampUserRelationId;
	}
	public void setTrainingCampUserRelationId(Long trainingCampUserRelationId) {
		this.trainingCampUserRelationId = trainingCampUserRelationId;
	}
	
	@Column(name="training_camp_call_admin_id")
	public Long getTrainingCampCallAdminId() {
		return trainingCampCallAdminId;
	}
	public void setTrainingCampCallAdminId(Long trainingCampCallAdminId) {
		this.trainingCampCallAdminId = trainingCampCallAdminId;
	}
	@Column(name="training_camp_caller_id")
	public Long getTrainingCampCallerId() {
		return trainingCampCallerId;
	}
	public void setTrainingCampCallerId(Long trainingCampCallerId) {
		this.trainingCampCallerId = trainingCampCallerId;
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
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "training_camp_call_admin_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getTrainingCampCallAdmin() {
		return trainingCampCallAdmin;
	}
	public void setTrainingCampCallAdmin(User trainingCampCallAdmin) {
		this.trainingCampCallAdmin = trainingCampCallAdmin;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "training_camp_caller_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getTrainingCampCaller() {
		return trainingCampCaller;
	}
	public void setTrainingCampCaller(User trainingCampCaller) {
		this.trainingCampCaller = trainingCampCaller;
	}
	
	
	

}
