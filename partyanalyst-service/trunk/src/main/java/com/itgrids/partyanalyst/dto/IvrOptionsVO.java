package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class IvrOptionsVO implements Serializable{

	private Long id;
	private String name;
	private Long count = 0l;
	private String perc;
	private Long total = 0l;
	
	private Long answeredCount;
	private Long unAnsweredCount;
	private Long othersCount;
	
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
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public String getPerc() {
		return perc;
	}
	public void setPerc(String perc) {
		this.perc = perc;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public Long getAnsweredCount() {
		return answeredCount;
	}
	public void setAnsweredCount(Long answeredCount) {
		this.answeredCount = answeredCount;
	}
	public Long getUnAnsweredCount() {
		return unAnsweredCount;
	}
	public void setUnAnsweredCount(Long unAnsweredCount) {
		this.unAnsweredCount = unAnsweredCount;
	}
	public Long getOthersCount() {
		return othersCount;
	}
	public void setOthersCount(Long othersCount) {
		this.othersCount = othersCount;
	}
	
	
}
