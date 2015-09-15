package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SurveyTrainingsVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private Long campId;
	private Long programId;
	private Long scheduleId;
	private Long batchId;
	private String campName;
	private String programName;
	private String scheduleName;
	private String batchName;
	private Date fromDate;
	private Date toDate;
	private Long createdBy;
	private Long updatedBy;
	private Date insertedTime;
	private Date updatedTime;
	private String status;
	private String description;
	private String location;
	private String address;
	private Long durationInDays;
	private String trainingCampScheduleCode;
	private String trainingCampBatchCode;
	private Long batchStatusId;
	private Long maxMembers;
	private String isFeedbackUpdatable;
	
	private List<SurveyTrainingsVO> trainingCampVOList = new ArrayList<SurveyTrainingsVO>(0);
	private List<SurveyTrainingsVO> trainingProgramVOList = new ArrayList<SurveyTrainingsVO>(0);
	private List<SurveyTrainingsVO> trainingScheduleVOList = new ArrayList<SurveyTrainingsVO>(0);
	private List<SurveyTrainingsVO> trainingBatchVOList = new ArrayList<SurveyTrainingsVO>(0);
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getCampId() {
		return campId;
	}
	public void setCampId(Long campId) {
		this.campId = campId;
	}
	public Long getProgramId() {
		return programId;
	}
	public void setProgramId(Long programId) {
		this.programId = programId;
	}
	public Long getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(Long scheduleId) {
		this.scheduleId = scheduleId;
	}
	public Long getBatchId() {
		return batchId;
	}
	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}
	public String getCampName() {
		return campName;
	}
	public void setCampName(String campName) {
		this.campName = campName;
	}
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public String getScheduleName() {
		return scheduleName;
	}
	public void setScheduleName(String scheduleName) {
		this.scheduleName = scheduleName;
	}
	public String getBatchName() {
		return batchName;
	}
	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getDurationInDays() {
		return durationInDays;
	}
	public void setDurationInDays(Long durationInDays) {
		this.durationInDays = durationInDays;
	}
	public String getTrainingCampScheduleCode() {
		return trainingCampScheduleCode;
	}
	public void setTrainingCampScheduleCode(String trainingCampScheduleCode) {
		this.trainingCampScheduleCode = trainingCampScheduleCode;
	}
	public String getTrainingCampBatchCode() {
		return trainingCampBatchCode;
	}
	public void setTrainingCampBatchCode(String trainingCampBatchCode) {
		this.trainingCampBatchCode = trainingCampBatchCode;
	}
	public Long getBatchStatusId() {
		return batchStatusId;
	}
	public void setBatchStatusId(Long batchStatusId) {
		this.batchStatusId = batchStatusId;
	}
	public Long getMaxMembers() {
		return maxMembers;
	}
	public void setMaxMembers(Long maxMembers) {
		this.maxMembers = maxMembers;
	}
	public String getIsFeedbackUpdatable() {
		return isFeedbackUpdatable;
	}
	public void setIsFeedbackUpdatable(String isFeedbackUpdatable) {
		this.isFeedbackUpdatable = isFeedbackUpdatable;
	}
	public List<SurveyTrainingsVO> getTrainingCampVOList() {
		return trainingCampVOList;
	}
	public void setTrainingCampVOList(List<SurveyTrainingsVO> trainingCampVOList) {
		this.trainingCampVOList = trainingCampVOList;
	}
	public List<SurveyTrainingsVO> getTrainingProgramVOList() {
		return trainingProgramVOList;
	}
	public void setTrainingProgramVOList(
			List<SurveyTrainingsVO> trainingProgramVOList) {
		this.trainingProgramVOList = trainingProgramVOList;
	}
	public List<SurveyTrainingsVO> getTrainingScheduleVOList() {
		return trainingScheduleVOList;
	}
	public void setTrainingScheduleVOList(
			List<SurveyTrainingsVO> trainingScheduleVOList) {
		this.trainingScheduleVOList = trainingScheduleVOList;
	}
	public List<SurveyTrainingsVO> getTrainingBatchVOList() {
		return trainingBatchVOList;
	}
	public void setTrainingBatchVOList(List<SurveyTrainingsVO> trainingBatchVOList) {
		this.trainingBatchVOList = trainingBatchVOList;
	}
	
}
