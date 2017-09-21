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
@Table(name = "training_camp_batch")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TrainingCampBatch  extends BaseModel implements Serializable{
private Long trainingCampBatchId;
private Long trainingCampScheduleId;
private TrainingCampSchedule trainingCampSchedule;
private String trainingCampBatchName;
private String trainingCampBatchCode;
private Date fromDate;
private Date toDate;
private Long batchStatusId;
private Long maxMembers;
private BatchStatus batchStatus;
private String isFeedbackUpdatable;
private String isCancelled;
private AttendeeType attendeeType;
private Long attendeeTypeId;
private Long trainingCampBatchTypeId;

private TrainingCampBatchType trainingCampBatchType;

@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name="training_camp_batch_id", unique=true, nullable=false)
public Long getTrainingCampBatchId() {
	return trainingCampBatchId;
}
public void setTrainingCampBatchId(Long trainingCampBatchId) {
	this.trainingCampBatchId = trainingCampBatchId;
}
@Column(name="training_camp_schedule_id")
public Long getTrainingCampScheduleId() {
	return trainingCampScheduleId;
}
public void setTrainingCampScheduleId(Long trainingCampScheduleId) {
	this.trainingCampScheduleId = trainingCampScheduleId;
}
@Column(name="training_camp_batch_name")
public String getTrainingCampBatchName() {
	return trainingCampBatchName;
}
public void setTrainingCampBatchName(String trainingCampBatchName) {
	this.trainingCampBatchName = trainingCampBatchName;
}
@Column(name="training_camp_batch_code")
public String getTrainingCampBatchCode() {
	return trainingCampBatchCode;
}
public void setTrainingCampBatchCode(String trainingCampBatchCode) {
	this.trainingCampBatchCode = trainingCampBatchCode;
}
@Column(name="from_date")
public Date getFromDate() {
	return fromDate;
}
public void setFromDate(Date fromDate) {
	this.fromDate = fromDate;
}
@Column(name="to_date")
public Date getToDate() {
	return toDate;
}
public void setToDate(Date toDate) {
	this.toDate = toDate;
}
@Column(name="batch_status_id")
public Long getBatchStatusId() {
	return batchStatusId;
}
public void setBatchStatusId(Long batchStatusId) {
	this.batchStatusId = batchStatusId;
}
@Column(name="max_members")
public Long getMaxMembers() {
	return maxMembers;
}
public void setMaxMembers(Long maxMembers) {
	this.maxMembers = maxMembers;
}
@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
@JoinColumn(name = "training_camp_schedule_id",insertable =false,updatable = false)
public TrainingCampSchedule getTrainingCampSchedule() {
	return trainingCampSchedule;
}
public void setTrainingCampSchedule(TrainingCampSchedule trainingCampSchedule) {
	this.trainingCampSchedule = trainingCampSchedule;
}

@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
@JoinColumn(name = "batch_status_id", insertable = false, updatable = false)
public BatchStatus getBatchStatus() {
	return batchStatus;
}


public void setBatchStatus(BatchStatus batchStatus) {
	this.batchStatus = batchStatus;
}
@Column(name="is_feedback_updatable")
public String getIsFeedbackUpdatable() {
	return isFeedbackUpdatable;
}
public void setIsFeedbackUpdatable(String isFeedbackUpdatable) {
	this.isFeedbackUpdatable = isFeedbackUpdatable;
}
@Column(name="is_cancelled")
public String getIsCancelled() {
	return isCancelled;
}
public void setIsCancelled(String isCancelled) {
	this.isCancelled = isCancelled;
}

@ManyToOne(fetch = FetchType.LAZY )
@JoinColumn(name = "attendee_type_id" , insertable = false, updatable = false)
@LazyToOne(LazyToOneOption.NO_PROXY)
@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
public AttendeeType getAttendeeType() {
	return attendeeType;
}
public void setAttendeeType(AttendeeType attendeeType) {
	this.attendeeType = attendeeType;
}

@Column(name="attendee_type_id")
public Long getAttendeeTypeId() {
	return attendeeTypeId;
}
public void setAttendeeTypeId(Long attendeeTypeId) {
	this.attendeeTypeId = attendeeTypeId;
}

@Column(name="training_camp_batch_type_id")
public Long getTrainingCampBatchTypeId() {
	return trainingCampBatchTypeId;
}
public void setTrainingCampBatchTypeId(Long trainingCampBatchTypeId) {
	this.trainingCampBatchTypeId = trainingCampBatchTypeId;
}

@ManyToOne(fetch = FetchType.LAZY )
@JoinColumn(name = "training_camp_batch_type_id" , insertable = false, updatable = false)
@LazyToOne(LazyToOneOption.NO_PROXY)
@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
public TrainingCampBatchType getTrainingCampBatchType() {
	return trainingCampBatchType;
}
public void setTrainingCampBatchType(TrainingCampBatchType trainingCampBatchType) {
	this.trainingCampBatchType = trainingCampBatchType;
}


}
