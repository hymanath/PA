package com.itgrids.dto;

import java.util.Date;

public class WebServiceDataVO {
	
	private Long       webServiceDataId;
	private Long       webserviceId;
	private String     inputData;
	private String     responceData;
	private Date       dataDate;
	private String     insertedTime;
	private String     isDeleted;
	private String     status;
	
	public Long getWebServiceDataId() {
		return webServiceDataId;
	}
	public void setWebServiceDataId(Long webServiceDataId) {
		this.webServiceDataId = webServiceDataId;
	}
	public Long getWebserviceId() {
		return webserviceId;
	}
	public void setWebserviceId(Long webserviceId) {
		this.webserviceId = webserviceId;
	}
	public String getInputData() {
		return inputData;
	}
	public void setInputData(String inputData) {
		this.inputData = inputData;
	}
	public String getResponceData() {
		return responceData;
	}
	public void setResponceData(String responceData) {
		this.responceData = responceData;
	}
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	public String getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(String insertedTime) {
		this.insertedTime = insertedTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getDataDate() {
		return dataDate;
	}
	public void setDataDate(Date dataDate) {
		this.dataDate = dataDate;
	}
	}
