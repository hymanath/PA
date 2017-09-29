package com.itgrids.dto;

import java.io.Serializable;

public class WebserviceDetailsVO implements Serializable {
	private Long providerId;
	private String providerName;
	private Long moduleId;
	private String moduleName;
	private Long webserviceId;
	private String webserviceName;
	private Long totalCalls;
	private Long totalSuccess = 0L;
	private Long totalFail = 0L;
	private Double totalTime = 0.0D;
	private Double totalTimeInMin = 0.0D;
	private Double averageTime = 0.0D;
	private Long noResponce = 0L;
	public Long getProviderId() {
		return providerId;
	}
	public void setProviderId(Long providerId) {
		this.providerId = providerId;
	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	public Long getModuleId() {
		return moduleId;
	}
	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public Long getWebserviceId() {
		return webserviceId;
	}
	public void setWebserviceId(Long webserviceId) {
		this.webserviceId = webserviceId;
	}
	public String getWebserviceName() {
		return webserviceName;
	}
	public void setWebserviceName(String webserviceName) {
		this.webserviceName = webserviceName;
	}
	public Long getTotalCalls() {
		return totalCalls;
	}
	public void setTotalCalls(Long totalCalls) {
		this.totalCalls = totalCalls;
	}
	public Long getTotalSuccess() {
		return totalSuccess;
	}
	public void setTotalSuccess(Long totalSuccess) {
		this.totalSuccess = totalSuccess;
	}
	public Long getTotalFail() {
		return totalFail;
	}
	public void setTotalFail(Long totalFail) {
		this.totalFail = totalFail;
	}
	public Double getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(Double totalTime) {
		this.totalTime = totalTime;
	}
	public Double getAverageTime() {
		return averageTime;
	}
	public void setAverageTime(Double averageTime) {
		this.averageTime = averageTime;
	}
	public Long getNoResponce() {
		return noResponce;
	}
	public void setNoResponce(Long noResponce) {
		this.noResponce = noResponce;
	}
	public Double getTotalTimeInMin() {
		return totalTimeInMin;
	}
	public void setTotalTimeInMin(Double totalTimeInMin) {
		this.totalTimeInMin = totalTimeInMin;
	}
	
}
