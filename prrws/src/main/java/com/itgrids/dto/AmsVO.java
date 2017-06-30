package com.itgrids.dto;

import java.io.Serializable;

public class AmsVO implements Serializable{

	private Long id;
	private String name;
	
	private String title;
	private String createdDate;
	private String updatedDate;
	private Long statusId;
	private String status;
	private Long interval;
	private String alertLevel;
	private String location;
	private String source;
	private String severtyColor;
	private Long subTaskCount = 0L;
	private String statusColor;
	
	private String relatedTo;
	private String problem;
	
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getInterval() {
		return interval;
	}
	public void setInterval(Long interval) {
		this.interval = interval;
	}
	public String getAlertLevel() {
		return alertLevel;
	}
	public void setAlertLevel(String alertLevel) {
		this.alertLevel = alertLevel;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getSevertyColor() {
		return severtyColor;
	}
	public void setSevertyColor(String severtyColor) {
		this.severtyColor = severtyColor;
	}
	public Long getSubTaskCount() {
		return subTaskCount;
	}
	public void setSubTaskCount(Long subTaskCount) {
		this.subTaskCount = subTaskCount;
	}
	public String getStatusColor() {
		return statusColor;
	}
	public void setStatusColor(String statusColor) {
		this.statusColor = statusColor;
	}
	public String getRelatedTo() {
		return relatedTo;
	}
	public void setRelatedTo(String relatedTo) {
		this.relatedTo = relatedTo;
	}
	public String getProblem() {
		return problem;
	}
	public void setProblem(String problem) {
		this.problem = problem;
	}
	
	
	
}
