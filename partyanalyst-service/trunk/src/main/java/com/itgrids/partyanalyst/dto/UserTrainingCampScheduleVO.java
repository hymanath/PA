package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class UserTrainingCampScheduleVO implements Serializable{

	private static final long serialVersionUID = -8503607640525816461L;

	private Long userId;
	private Long trainingCampScheduleId;
	private String trainingCampScheduleCode;
	private Long trainingCampId;
	private String trainingCampName;
	private Long trainingCampProgramId;
	private String trainingCampProgramName;
	private String scheduleStartDate;
	private String scheduleEndDate;
	private Long scheduleStatusId;
	private List<UserTrainingCampBatchVO> batchList;
	
	private String cardsYear;
	
	
	public String getCardsYear() {
		return cardsYear;
	}
	public void setCardsYear(String cardsYear) {
		this.cardsYear = cardsYear;
	}
	public List<UserTrainingCampBatchVO> getBatchList() {
		return batchList;
	}
	public void setBatchList(List<UserTrainingCampBatchVO> batchList) {
		this.batchList = batchList;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getTrainingCampScheduleId() {
		return trainingCampScheduleId;
	}
	public void setTrainingCampScheduleId(Long trainingCampScheduleId) {
		this.trainingCampScheduleId = trainingCampScheduleId;
	}
	public String getTrainingCampScheduleCode() {
		return trainingCampScheduleCode;
	}
	public void setTrainingCampScheduleCode(String trainingCampScheduleCode) {
		this.trainingCampScheduleCode = trainingCampScheduleCode;
	}
	public Long getTrainingCampId() {
		return trainingCampId;
	}
	public void setTrainingCampId(Long trainingCampId) {
		this.trainingCampId = trainingCampId;
	}
	public String getTrainingCampName() {
		return trainingCampName;
	}
	public void setTrainingCampName(String trainingCampName) {
		this.trainingCampName = trainingCampName;
	}
	public Long getTrainingCampProgramId() {
		return trainingCampProgramId;
	}
	public void setTrainingCampProgramId(Long trainingCampProgramId) {
		this.trainingCampProgramId = trainingCampProgramId;
	}
	public String getTrainingCampProgramName() {
		return trainingCampProgramName;
	}
	public void setTrainingCampProgramName(String trainingCampProgramName) {
		this.trainingCampProgramName = trainingCampProgramName;
	}
	public String getScheduleStartDate() {
		return scheduleStartDate;
	}
	public void setScheduleStartDate(String scheduleStartDate) {
		this.scheduleStartDate = scheduleStartDate;
	}
	public String getScheduleEndDate() {
		return scheduleEndDate;
	}
	public void setScheduleEndDate(String scheduleEndDate) {
		this.scheduleEndDate = scheduleEndDate;
	}
	public Long getScheduleStatusId() {
		return scheduleStatusId;
	}
	public void setScheduleStatusId(Long scheduleStatusId) {
		this.scheduleStatusId = scheduleStatusId;
	}
}
