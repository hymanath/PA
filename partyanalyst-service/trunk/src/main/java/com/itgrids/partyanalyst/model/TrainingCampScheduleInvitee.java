package com.itgrids.partyanalyst.model;

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
	
	

}
