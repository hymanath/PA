package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class CustomReportVO implements Serializable {
	private Long id;
	private String name;
	private Long count = 0l;
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Long getCount() {
		return count;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	
	
}
