package com.itgrids.partyanalyst.model;

import java.io.Serializable;
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
@Table(name = "training_camp_cadre_achievement_history")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TrainingCampCadreAchievementHistory extends BaseModel implements Serializable{
   
private static final long serialVersionUID = 1L;
	
    private Long trainingCampCadreAchievementHistoryId;
    private TrainingCampCadreAchievement trainingCampCadreAchievement;
	private Long trainingCampCadreAchievementId;
	private TdpCadre tdpCadre;
	private Long tdpCadreId;
	private String achievement;
	private Long insertedBy;
	private Long updatedBy;
	private Date insertedTime;
	private Date updatedTime;
	private TrainingCampBatch trainingCampBatch;
	private Long trainingCampBatchId;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "training_camp_cadre_achievement_history_id", unique = true, nullable = false)
	public Long getTrainingCampCadreAchievementHistoryId() {
		return trainingCampCadreAchievementHistoryId;
	}
	public void setTrainingCampCadreAchievementHistoryId(
			Long trainingCampCadreAchievementHistoryId) {
		this.trainingCampCadreAchievementHistoryId = trainingCampCadreAchievementHistoryId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "training_camp_cadre_achievement_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TrainingCampCadreAchievement getTrainingCampCadreAchievement() {
		return trainingCampCadreAchievement;
	}
	public void setTrainingCampCadreAchievement(
			TrainingCampCadreAchievement trainingCampCadreAchievement) {
		this.trainingCampCadreAchievement = trainingCampCadreAchievement;
	}
	
	@Column(name = "training_camp_cadre_achievement_id")
	public Long getTrainingCampCadreAchievementId() {
		return trainingCampCadreAchievementId;
	}
	public void setTrainingCampCadreAchievementId(
			Long trainingCampCadreAchievementId) {
		this.trainingCampCadreAchievementId = trainingCampCadreAchievementId;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "tdp_cadre_id" , insertable = false, updatable = false)
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
	
	@Column(name = "achievement")
	public String getAchievement() {
		return achievement;
	}
	public void setAchievement(String achievement) {
		this.achievement = achievement;
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
