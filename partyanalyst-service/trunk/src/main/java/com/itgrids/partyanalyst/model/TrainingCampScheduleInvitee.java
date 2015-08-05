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
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "training_camp_schedule_invitee")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TrainingCampScheduleInvitee {
	private Long trainingCampScheduleInviteeId;
	private TrainingCampSchedule trainingCampSchedule;
	private Long trainingcampScheduleId;
	private Long tdpCadreId;
	private Long scheduleInviteeStatusId;
	private Long attendingBatchId;
	private String remarks;
	private TdpCadre tdpCadre;
	private ScheduleInviteeStatus scheduleInviteeStatus;
	private TrainingCampBatch trainingCampBatch;
	
	private User user;
	private Long insertedBy;
	private Long updatedBy;
	private Date insertedTime;
	private Date updatedTime;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="training_camp_schedule_invitee_id", unique=true, nullable=false)
	public Long getTrainingCampScheduleInviteeId() {
		return trainingCampScheduleInviteeId;
	}
	public void setTrainingCampScheduleInviteeId(Long trainingCampScheduleInviteeId) {
		this.trainingCampScheduleInviteeId = trainingCampScheduleInviteeId;
	}
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "training_camp_schedule_id",insertable =false,updatable = false)
	public TrainingCampSchedule getTrainingCampSchedule() {
		return trainingCampSchedule;
	}
	public void setTrainingCampSchedule(TrainingCampSchedule trainingCampSchedule) {
		this.trainingCampSchedule = trainingCampSchedule;
	}
	@Column(name = "training_camp_schedule_id")
	public Long getTrainingcampScheduleId() {
		return trainingcampScheduleId;
	}
	public void setTrainingcampScheduleId(Long trainingcampScheduleId) {
		this.trainingcampScheduleId = trainingcampScheduleId;
	}
	@Column(name = "tdp_cadre_id")
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	@Column(name = "schedule_invitee_status_id")
	public Long getScheduleInviteeStatusId() {
		return scheduleInviteeStatusId;
	}
	public void setScheduleInviteeStatusId(Long scheduleInviteeStatusId) {
		this.scheduleInviteeStatusId = scheduleInviteeStatusId;
	}
	@Column(name = "attending_batch_id")
	public Long getAttendingBatchId() {
		return attendingBatchId;
	}
	public void setAttendingBatchId(Long attendingBatchId) {
		this.attendingBatchId = attendingBatchId;
	}
	@Column(name = "remarks")
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "tdp_cadre_id",insertable =false,updatable = false)
	public TdpCadre getTdpCadre() {
		return tdpCadre;
	}
	public void setTdpCadre(TdpCadre tdpCadre) {
		this.tdpCadre = tdpCadre;
	}
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "schedule_invitee_status_id",insertable =false,updatable = false)
	public ScheduleInviteeStatus getScheduleInviteeStatus() {
		return scheduleInviteeStatus;
	}
	public void setScheduleInviteeStatus(ScheduleInviteeStatus scheduleInviteeStatus) {
		this.scheduleInviteeStatus = scheduleInviteeStatus;
	}
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "attending_batch_id",insertable =false,updatable = false)
	public TrainingCampBatch getTrainingCampBatch() {
		return trainingCampBatch;
	}
	public void setTrainingCampBatch(TrainingCampBatch trainingCampBatch) {
		this.trainingCampBatch = trainingCampBatch;
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
	
}
