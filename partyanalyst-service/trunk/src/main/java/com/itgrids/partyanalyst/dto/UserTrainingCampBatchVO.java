package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class UserTrainingCampBatchVO implements Serializable{

	private static final long serialVersionUID = -5142207652821192245L;
	
	private Long trainingCampBatchId;
	private String trainingCampBatchName;
	private String trainingCampBatchCode;
	private String batchStartDate;
	private String batchEndDate;
	
	public Long getTrainingCampBatchId() {
		return trainingCampBatchId;
	}
	public void setTrainingCampBatchId(Long trainingCampBatchId) {
		this.trainingCampBatchId = trainingCampBatchId;
	}
	public String getTrainingCampBatchName() {
		return trainingCampBatchName;
	}
	public void setTrainingCampBatchName(String trainingCampBatchName) {
		this.trainingCampBatchName = trainingCampBatchName;
	}
	public String getTrainingCampBatchCode() {
		return trainingCampBatchCode;
	}
	public void setTrainingCampBatchCode(String trainingCampBatchCode) {
		this.trainingCampBatchCode = trainingCampBatchCode;
	}
	public String getBatchStartDate() {
		return batchStartDate;
	}
	public void setBatchStartDate(String batchStartDate) {
		this.batchStartDate = batchStartDate;
	}
	public String getBatchEndDate() {
		return batchEndDate;
	}
	public void setBatchEndDate(String batchEndDate) {
		this.batchEndDate = batchEndDate;
	}
	
}
