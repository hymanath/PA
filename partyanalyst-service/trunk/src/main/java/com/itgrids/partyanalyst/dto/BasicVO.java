package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BasicVO implements Serializable{

	private Long id;
	private Long count;
	private String name;
	private Double perc;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPerc() {
		return perc;
	}
	public void setPerc(Double perc) {
		this.perc = perc;
	}
	
	
}
