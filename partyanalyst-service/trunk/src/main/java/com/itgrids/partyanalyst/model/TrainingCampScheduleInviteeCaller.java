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

@Entity
@Table(name = "training_camp_schedule_invitee_caller")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TrainingCampScheduleInviteeCaller {
private Long trainingCampScheduleInviteeCallerId;
private Long trainingCampCallerAdminId;
private Long trainingCampCallerId;
private Long callPurposeId;
private CampCallPurpose campCallPurpose;
private CampCallStatus campCallStatus;
private Long trainingCampScheduleInviteeId;
private TrainingCampScheduleInvitee trainingCampScheduleInvitee;
private Long callStatusId;
private Long insertedBy;
private Long updatedBy;
private Date insertedTime;
private Date updatedTime;
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "training_camp_schedule_invitee_caller_id", unique = true, nullable = false)
public Long getTrainingCampScheduleInviteeCallerId() {
	return trainingCampScheduleInviteeCallerId;
}
public void setTrainingCampScheduleInviteeCallerId(
		Long trainingCampScheduleInviteeCallerId) {
	this.trainingCampScheduleInviteeCallerId = trainingCampScheduleInviteeCallerId;
}
@Column(name = "training_camp_caller_admin_id")
public Long getTrainingCampCallerAdminId() {
	return trainingCampCallerAdminId;
}
public void setTrainingCampCallerAdminId(Long trainingCampCallerAdminId) {
	this.trainingCampCallerAdminId = trainingCampCallerAdminId;
}
@Column(name = "training_camp_caller_id")
public Long getTrainingCampCallerId() {
	return trainingCampCallerId;
}
public void setTrainingCampCallerId(Long trainingCampCallerId) {
	this.trainingCampCallerId = trainingCampCallerId;
}
@Column(name = "call_purpose_id")
public Long getCallPurposeId() {
	return callPurposeId;
}
public void setCallPurposeId(Long callPurposeId) {
	this.callPurposeId = callPurposeId;
}
@Column(name = "training_camp_schedule_invitee_id")
public Long getTrainingCampScheduleInviteeId() {
	return trainingCampScheduleInviteeId;
}
public void setTrainingCampScheduleInviteeId(Long trainingCampScheduleInviteeId) {
	this.trainingCampScheduleInviteeId = trainingCampScheduleInviteeId;
}
@Column(name = "call_status_id")
public Long getCallStatusId() {
	return callStatusId;
}
public void setCallStatusId(Long callStatusId) {
	this.callStatusId = callStatusId;
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
@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
@JoinColumn(name = "call_purpose_id",insertable =false,updatable = false)
public CampCallPurpose getCampCallPurpose() {
	return campCallPurpose;
}
public void setCampCallPurpose(CampCallPurpose campCallPurpose) {
	this.campCallPurpose = campCallPurpose;
}
@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
@JoinColumn(name = "call_status_id",insertable =false,updatable = false)
public CampCallStatus getCampCallStatus() {
	return campCallStatus;
}
public void setCampCallStatus(CampCallStatus campCallStatus) {
	this.campCallStatus = campCallStatus;
}
@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
@JoinColumn(name = "training_camp_schedule_invitee_id",insertable =false,updatable = false)
public TrainingCampScheduleInvitee getTrainingCampScheduleInvitee() {
	return trainingCampScheduleInvitee;
}
public void setTrainingCampScheduleInvitee(
		TrainingCampScheduleInvitee trainingCampScheduleInvitee) {
	this.trainingCampScheduleInvitee = trainingCampScheduleInvitee;
}




}
