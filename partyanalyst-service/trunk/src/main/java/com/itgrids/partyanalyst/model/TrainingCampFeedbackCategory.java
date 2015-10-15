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
@Table(name = "training_camp_feedback_category")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TrainingCampFeedbackCategory {
	
	private Long trainingCampFeedbackCategoryId;
	private Long trainingCampProgramId;
	private Long trainingCampBatchId;
	private Long trainingCampId;
	private Long feedbackCategoryId;
	private Long parentFeedbackCategoryId;
	private String isSubCategoryExist;
	private String isDeleted;
	private Date insertedTime;
	private TrainingCampBatch trainingCampBatch;
	private TrainingCamp trainingCamp;
	private TrainingCampProgram trainingCampProgram;
	private FeedbackCategory feedbackCategory;
	private FeedbackCategory parentFeedbackCategory;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "training_camp_feedback_category_id", unique = true, nullable = false)
	public Long getTrainingCampFeedbackCategoryId() {
		return trainingCampFeedbackCategoryId;
	}
	public void setTrainingCampFeedbackCategoryId(Long trainingCampFeedbackCategoryId) {
		this.trainingCampFeedbackCategoryId = trainingCampFeedbackCategoryId;
	}
	@Column(name = "training_camp_batch_id")
	public Long getTrainingCampBatchId() {
		return trainingCampBatchId;
	}
	public void setTrainingCampBatchId(Long trainingCampBatchId) {
		this.trainingCampBatchId = trainingCampBatchId;
	}
	@Column(name = "training_camp_program_id")
	public Long getTrainingCampProgramId() {
		return trainingCampProgramId;
	}
	public void setTrainingCampProgramId(Long trainingCampProgramId) {
		this.trainingCampProgramId = trainingCampProgramId;
	}
	@Column(name = "training_camp_id")
	public Long getTrainingCampId() {
		return trainingCampId;
	}
	public void setTrainingCampId(Long trainingCampId) {
		this.trainingCampId = trainingCampId;
	}
	@Column(name = "feedback_category_id")
	public Long getFeedbackCategoryId() {
		return feedbackCategoryId;
	}
	public void setFeedbackCategoryId(Long feedbackCategoryId) {
		this.feedbackCategoryId = feedbackCategoryId;
	}
	@Column(name = "parent_feedback_category_id")
	public Long getParentFeedbackCategoryId() {
		return parentFeedbackCategoryId;
	}
	public void setParentFeedbackCategoryId(Long parentFeedbackCategoryId) {
		this.parentFeedbackCategoryId = parentFeedbackCategoryId;
	}
	@Column(name = "is_sub_category_exist")
	public String getIsSubCategoryExist() {
		return isSubCategoryExist;
	}
	public void setIsSubCategoryExist(String isSubCategoryExist) {
		this.isSubCategoryExist = isSubCategoryExist;
	}
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Column(name = "inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "training_camp_batch_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TrainingCampBatch getTrainingCampBatch() {
		return trainingCampBatch;
	}
	public void setTrainingCampBatch(TrainingCampBatch trainingCampBatch) {
		this.trainingCampBatch = trainingCampBatch;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "training_camp_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TrainingCamp getTrainingCamp() {
		return trainingCamp;
	}
	public void setTrainingCamp(TrainingCamp trainingCamp) {
		this.trainingCamp = trainingCamp;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "training_camp_program_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TrainingCampProgram getTrainingCampProgram() {
		return trainingCampProgram;
	}
	public void setTrainingCampProgram(TrainingCampProgram trainingCampProgram) {
		this.trainingCampProgram = trainingCampProgram;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "feedback_category_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public FeedbackCategory getFeedbackCategory() {
		return feedbackCategory;
	}
	public void setFeedbackCategory(FeedbackCategory feedbackCategory) {
		this.feedbackCategory = feedbackCategory;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "parent_feedback_category_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public FeedbackCategory getParentFeedbackCategory() {
		return parentFeedbackCategory;
	}
	public void setParentFeedbackCategory(FeedbackCategory parentFeedbackCategory) {
		this.parentFeedbackCategory = parentFeedbackCategory;
	}
	
	

}
