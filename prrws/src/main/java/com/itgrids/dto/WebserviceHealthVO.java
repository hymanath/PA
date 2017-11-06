package com.itgrids.dto;

import java.io.Serializable;
import java.util.Date;

public class WebserviceHealthVO implements Serializable {
	private Long urlId;
	 private String url;
	 private Date startTime;
	 private Date endDate;
	 private Long statusCode;
	 private String status;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Long getUrlId() {
		return urlId;
	}
	public void setUrlId(Long urlId) {
		this.urlId = urlId;
	}
	public Long getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Long statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	 

}
