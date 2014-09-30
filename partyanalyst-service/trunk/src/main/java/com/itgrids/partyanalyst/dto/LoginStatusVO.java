package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class LoginStatusVO implements Serializable{

	private Long statusId;
	private List<Long> statusRelatedValues;
	
	
	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	public List<Long> getStatusRelatedValues() {
		return statusRelatedValues;
	}
	public void setStatusRelatedValues(List<Long> statusRelatedValues) {
		this.statusRelatedValues = statusRelatedValues;
	}
	
	
	
}
