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
@Table(name = "training_camp_schedule")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TrainingCampSchedule {
private Long trainingCampScheduleId;
private Long trainingCampId;

private TrainingCamp trainingCamp;
private Long trainingCampProgramId;
private TrainingCampProgram trainingCampProgram;
private String trainingCampScheduleCode;
private String description;
private Date fromDate;
private Date toDate;
private Long createdBy;
private Long updatedBy;
private Date insertedTime;
private Date updatedTime;
private String status;
private Long enrollmentYearId;
private EnrollmentYear enrollmentYear;
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name="training_camp_schedule_id", unique=true, nullable=false)
public Long getTrainingCampScheduleId() {
	return trainingCampScheduleId;
}
public void setTrainingCampScheduleId(Long trainingCampScheduleId) {
	this.trainingCampScheduleId = trainingCampScheduleId;
}
@Column(name="training_camp_id")
public Long getTrainingCampId() {
	return trainingCampId;
}
public void setTrainingCampId(Long trainingCampId) {
	this.trainingCampId = trainingCampId;
}
@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
@JoinColumn(name = "training_camp_id",insertable =false,updatable = false)
public TrainingCamp getTrainingCamp() {
	return trainingCamp;
}
public void setTrainingCamp(TrainingCamp trainingCamp) {
	this.trainingCamp = trainingCamp;
}
@Column(name = "training_camp_program_id")
public Long getTrainingCampProgramId() {
	return trainingCampProgramId;
}
public void setTrainingCampProgramId(Long trainingCampProgramId) {
	this.trainingCampProgramId = trainingCampProgramId;
}
@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
@JoinColumn(name = "training_camp_program_id",insertable =false,updatable = false)
public TrainingCampProgram getTrainingCampProgram() {
	return trainingCampProgram;
}
public void setTrainingCampProgram(TrainingCampProgram trainingCampProgram) {
	this.trainingCampProgram = trainingCampProgram;
}
@Column(name = "training_camp_schedule_code")
public String getTrainingCampScheduleCode() {
	return trainingCampScheduleCode;
}
public void setTrainingCampScheduleCode(String trainingCampScheduleCode) {
	this.trainingCampScheduleCode = trainingCampScheduleCode;
}
@Column(name = "description")
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
@Column(name = "from_date")
public Date getFromDate() {
	return fromDate;
}
public void setFromDate(Date fromDate) {
	this.fromDate = fromDate;
}
@Column(name = "to_date")
public Date getToDate() {
	return toDate;
}
public void setToDate(Date toDate) {
	this.toDate = toDate;
}
@Column(name = "created_by")
public Long getCreatedBy() {
	return createdBy;
}
public void setCreatedBy(Long createdBy) {
	this.createdBy = createdBy;
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
@Column(name = "status")
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
@Column(name = "enrollment_year_id")
public Long getEnrollmentYearId() {
	return enrollmentYearId;
}
public void setEnrollmentYearId(Long enrollmentYearId) {
	this.enrollmentYearId = enrollmentYearId;
}
@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
@JoinColumn(name = "enrollment_year_id",insertable =false,updatable = false)
public EnrollmentYear getEnrollmentYear() {
	return enrollmentYear;
}
public void setEnrollmentYear(EnrollmentYear enrollmentYear) {
	this.enrollmentYear = enrollmentYear;
}


}
