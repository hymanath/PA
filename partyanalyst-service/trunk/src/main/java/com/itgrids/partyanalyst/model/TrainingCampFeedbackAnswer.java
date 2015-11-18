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
@Table(name = "training_camp_feedback_answer")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TrainingCampFeedbackAnswer {
	
	private Long trainingCampFeedbackAnswerId;
	private Long trainingCampFeedbackCategoryId;
	private TrainingCampFeedbackCategory trainingCampFeedbackCategory;
	private String answer;
	private Long tdpCadreId;
	private TdpCadre tdpCadre;
	private Date insertedTime;
	private Date updatedTime;
	private String isDeleted;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "training_camp_feedback_answer_id", unique = true, nullable = false)
	public Long getTrainingCampFeedbackAnswerId() {
		return trainingCampFeedbackAnswerId;
	}
	public void setTrainingCampFeedbackAnswerId(Long trainingCampFeedbackAnswerId) {
		this.trainingCampFeedbackAnswerId = trainingCampFeedbackAnswerId;
	}
	
	@Column(name = "training_camp_feedback_category_id")
	public Long getTrainingCampFeedbackCategoryId() {
		return trainingCampFeedbackCategoryId;
	}
	public void setTrainingCampFeedbackCategoryId(
			Long trainingCampFeedbackCategoryId) {
		this.trainingCampFeedbackCategoryId = trainingCampFeedbackCategoryId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "training_camp_feedback_category_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TrainingCampFeedbackCategory getTrainingCampFeedbackCategory() {
		return trainingCampFeedbackCategory;
	}
	public void setTrainingCampFeedbackCategory(
			TrainingCampFeedbackCategory trainingCampFeedbackCategory) {
		this.trainingCampFeedbackCategory = trainingCampFeedbackCategory;
	}
	
	@Column(name = "answer")
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	@Column(name = "cadre_id")
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "cadre_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCadre getTdpCadre() {
		return tdpCadre;
	}
	public void setTdpCadre(TdpCadre tdpCadre) {
		this.tdpCadre = tdpCadre;
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
	
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	

}
