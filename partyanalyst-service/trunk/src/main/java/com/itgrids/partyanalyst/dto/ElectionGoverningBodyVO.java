package com.itgrids.partyanalyst.dto;

import java.util.Date;
import java.util.List;
import java.io.Serializable;

public class ElectionGoverningBodyVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long stateId;
	private String status;
	private Date fromDate;
	private Date toDate;
	private String ministerType;
	private String stateName;
	private String ministry;
	
	public String getMinistry() {
		return ministry;
	}
	public void setMinistry(String ministry) {
		this.ministry = ministry;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getMinisterType() {
		return ministerType;
	}
	public void setMinisterType(String ministerType) {
		this.ministerType = ministerType;
	}
	
	
	
	

}
