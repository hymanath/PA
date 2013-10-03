package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class VoterCountVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long count = 0l;
	private Long minValue;
	private Long maxValue;
	private String type;
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Long getMinValue() {
		return minValue;
	}
	public void setMinValue(Long minValue) {
		this.minValue = minValue;
	}
	public Long getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(Long maxValue) {
		this.maxValue = maxValue;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
