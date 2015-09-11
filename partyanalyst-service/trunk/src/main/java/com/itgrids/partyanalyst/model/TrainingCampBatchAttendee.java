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
@Table(name = "training_camp_batch_attendee")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TrainingCampBatchAttendee extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long trainingCampBatchAttendeeId;
	private TrainingCampBatch trainingCampBatch;
	private Long trainingCampBatchId;
	private TdpCadre tdpCadre;
	private Long tdpCadreId;
	private TrainingCampScheduleInvitee trainningCampScheduleInvitee;
	private Long trainingCampScheduleInviteeId;
	private Date attendedTime;
	private User user;
	private Long insertedBy;
	private Long updatedBy;
	private Date insertedTime;
	private Date updatedTime;
	private String isDeleted;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="training_camp_batch_attendee_id", unique=true, nullable=false)
	public Long getTrainingCampBatchAttendeeId() {
		return trainingCampBatchAttendeeId;
	}
	public void setTrainingCampBatchAttendeeId(Long trainingCampBatchAttendeeId) {
		this.trainingCampBatchAttendeeId = trainingCampBatchAttendeeId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "training_camp_batch_id", insertable = false, updatable = false)
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
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "training_camp_schedule_invitee_id", insertable = false, updatable = false)
    @LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TrainingCampScheduleInvitee getTrainningCampScheduleInvitee() {
		return trainningCampScheduleInvitee;
	}
	public void setTrainningCampScheduleInvitee(
			TrainingCampScheduleInvitee trainningCampScheduleInvitee) {
		this.trainningCampScheduleInvitee = trainningCampScheduleInvitee;
	}
	
	@Column(name = "training_camp_schedule_invitee_id")
	public Long getTrainingCampScheduleInviteeId() {
		return trainingCampScheduleInviteeId;
	}
	public void setTrainingCampScheduleInviteeId(Long trainingCampScheduleInviteeId) {
		this.trainingCampScheduleInviteeId = trainingCampScheduleInviteeId;
	}
	
	@Column(name = "attended_time")
	public Date getAttendedTime() {
		return attendedTime;
	}
	public void setAttendedTime(Date attendedTime) {
		this.attendedTime = attendedTime;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "inserted_by", insertable = false, updatable = false)
    @LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
	
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
}
