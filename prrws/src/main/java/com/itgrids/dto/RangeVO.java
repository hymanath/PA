package com.itgrids.dto;

import java.io.Serializable;
import java.util.List;

public class RangeVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String value;
	private String locationIds;
	private List<Long> nonFundLocIds;
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
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getLocationIds() {
		return locationIds;
	}
	public void setLocationIds(String locationIds) {
		this.locationIds = locationIds;
	}
	public List<Long> getNonFundLocIds() {
		return nonFundLocIds;
	}
	public void setNonFundLocIds(List<Long> nonFundLocIds) {
		this.nonFundLocIds = nonFundLocIds;
	}
	
	
	
}
