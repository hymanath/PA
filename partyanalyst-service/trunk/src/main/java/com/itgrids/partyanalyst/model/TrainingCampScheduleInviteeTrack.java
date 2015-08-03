package com.itgrids.partyanalyst.model;

import java.io.Serializable;

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
@Table(name = "training_camp_schedule_invitee_track")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TrainingCampScheduleInviteeTrack implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long trainingCampScheduleInviteeTrackId;
	private Long trainingCampScheduleInviteeId;
	private Long trainingCampScheduleId;
	private Long tdpCadreId;
	private Long scheduleInviteeStatusId;
	private Long attendingBatchId;
	private String remarks;
	private Long insertedBy;
	private Long updatedBy;
	
	private TrainingCampScheduleInvitee trainingCampScheduleInvitee;
	private TrainingCampSchedule trainingCampSchedule;
	private TdpCadre tdpCadre;
	

	
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "training_camp_schedule_invitee_track_id", unique = true, nullable = false)
	public Long getTrainingCampScheduleInviteeTrackId() {
		return trainingCampScheduleInviteeTrackId;
	}


	public void setTrainingCampScheduleInviteeTrackId(
			Long trainingCampScheduleInviteeTrackId) {
		this.trainingCampScheduleInviteeTrackId = trainingCampScheduleInviteeTrackId;
	}

   @Column(name = "training_camp_schedule_invitee_id")
	public Long getTrainingCampScheduleInviteeId() {
		return trainingCampScheduleInviteeId;
	}


	public void setTrainingCampScheduleInviteeId(Long trainingCampScheduleInviteeId) {
		this.trainingCampScheduleInviteeId = trainingCampScheduleInviteeId;
	}


	@Column(name = "training_camp_schedule_id")
	public Long getTrainingCampScheduleId() {
		return trainingCampScheduleId;
	}


	public void setTrainingCampScheduleId(Long trainingCampScheduleId) {
		this.trainingCampScheduleId = trainingCampScheduleId;
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

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "training_camp_schedule_invitee_id", insertable = false, updatable = false)
    @LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TrainingCampScheduleInvitee getTrainingCampScheduleInvitee() {
		return trainingCampScheduleInvitee;
	}


	public void setTrainingCampScheduleInvitee(
			TrainingCampScheduleInvitee trainingCampScheduleInvitee) {
		this.trainingCampScheduleInvitee = trainingCampScheduleInvitee;
	}

	 @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	 @JoinColumn(name = "training_camp_schedule_id", insertable = false, updatable = false)
	 @LazyToOne(LazyToOneOption.NO_PROXY)
     @org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TrainingCampSchedule getTrainingCampSchedule() {
		return trainingCampSchedule;
	}


	public void setTrainingCampSchedule(TrainingCampSchedule trainingCampSchedule) {
		this.trainingCampSchedule = trainingCampSchedule;
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

}
