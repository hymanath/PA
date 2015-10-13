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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;
@Entity
@Table(name = "training_camp_cadre_feedback_health_card")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TrainingCampCadreFeedbackHealthCard {
	
	private Long trainingCampCadreFeedbackHealthCardId;
	private Long trainingCampCadreFeedbackDetailsId;
	private String healthCardAttachment;
	private TrainingCampCadreFeedbackDetails trainingCampCadreFeedbackDetails ;
	private Date insertedTime;
	private Date updatedTime;
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name = "training_camp_cadre_feedback_health_card_id", unique = true, nullable = false)
	public Long getTrainingCampCadreFeedbackHealthCardId() {
		return trainingCampCadreFeedbackHealthCardId;
	}
	public void setTrainingCampCadreFeedbackHealthCardId(
			Long trainingCampCadreFeedbackHealthCardId) {
		this.trainingCampCadreFeedbackHealthCardId = trainingCampCadreFeedbackHealthCardId;
	}
	 @Column(name = "training_camp_cadre_feedback_details_id")
	public Long getTrainingCampCadreFeedbackDetailsId() {
		return trainingCampCadreFeedbackDetailsId;
	}
	public void setTrainingCampCadreFeedbackDetailsId(
			Long trainingCampCadreFeedbackDetailsId) {
		this.trainingCampCadreFeedbackDetailsId = trainingCampCadreFeedbackDetailsId;
	}
	 @Column(name = "health_card_attachment")
	public String getHealthCardAttachment() {
		return healthCardAttachment;
	}
	public void setHealthCardAttachment(String healthCardAttachment) {
		this.healthCardAttachment = healthCardAttachment;
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
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "training_camp_cadre_feedback_details_id",insertable=false,updatable=false)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TrainingCampCadreFeedbackDetails getTrainingCampCadreFeedbackDetails() {
		return trainingCampCadreFeedbackDetails;
	}
	public void setTrainingCampCadreFeedbackDetails(
			TrainingCampCadreFeedbackDetails trainingCampCadreFeedbackDetails) {
		this.trainingCampCadreFeedbackDetails = trainingCampCadreFeedbackDetails;
	}
	
	
	

}
