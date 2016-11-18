package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class DataSourceTypeVO implements Serializable{
	
	private static final long serialVersionUID = -1993987653731091867L;
	
	
	private String type;
	private Long   stateId;
	private String dataSourceType;
	private Long   totalCount = 0l;
	private Long   newCount = 0l;
	private Long   renewalCount = 0l;
	
	private Double newPercent= 0.0;
	private Double renewalPercent = 0.0;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDataSourceType() {
		return dataSourceType;
	}
	public void setDataSourceType(String dataSourceType) {
		this.dataSourceType = dataSourceType;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getNewCount() {
		return newCount;
	}
	public void setNewCount(Long newCount) {
		this.newCount = newCount;
	}
	public Long getRenewalCount() {
		return renewalCount;
	}
	public void setRenewalCount(Long renewalCount) {
		this.renewalCount = renewalCount;
	}
	public Double getNewPercent() {
		return newPercent;
	}
	public void setNewPercent(Double newPercent) {
		this.newPercent = newPercent;
	}
	public Double getRenewalPercent() {
		return renewalPercent;
	}
	public void setRenewalPercent(Double renewalPercent) {
		this.renewalPercent = renewalPercent;
	}
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	
}
