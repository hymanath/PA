package com.itgrids.dto;

import java.io.Serializable;
import java.util.Date;

public class WebserviceVO implements Serializable{

	private static final long serialVersionUID = -4789785769354271294L;
	
	private String url;
	private Long statusCode;
	private Date callTime;
	private Long timeTaken;
	private Long webserviceTrackId;
	private String status;
	private String inputData;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Long statusCode) {
		this.statusCode = statusCode;
	}

	public Date getCallTime() {
		return callTime;
	}

	public void setCallTime(Date callTime) {
		this.callTime = callTime;
	}

	public Long getTimeTaken() {
		return timeTaken;
	}

	public void setTimeTaken(Long timeTaken) {
		this.timeTaken = timeTaken;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getWebserviceTrackId() {
		return webserviceTrackId;
	}

	public void setWebserviceTrackId(Long webserviceTrackId) {
		this.webserviceTrackId = webserviceTrackId;
	}

	public String getInputData() {
		return inputData;
	}

	public void setInputData(String inputData) {
		this.inputData = inputData;
	}
	
}
