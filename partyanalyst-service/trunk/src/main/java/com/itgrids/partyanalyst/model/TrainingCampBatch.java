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


}
