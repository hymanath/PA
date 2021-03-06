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
	private TdpCadre tdpCadre;
	private Long tdpCadreId;
	private TrainingCampBatch trainingCampBatch;
	private Long trainingCampBatchId;
	
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
	
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "tdp_cadre_id", insertable = false, updatable = false)
    @LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCadre getTdpCadre() {
		return tdpCadre;
	}
	public void setTdpCadre(TdpCadre tdpCadre) {
		this.tdpCadre = tdpCadre;
	}
	@Column(name = "tdp_cadre_id")
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
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
	@Column(name = "training_camp_batch_id")
	public Long getTrainingCampBatchId() {
		return trainingCampBatchId;
	}
	public void setTrainingCampBatchId(Long trainingCampBatchId) {
		this.trainingCampBatchId = trainingCampBatchId;
	}

}
