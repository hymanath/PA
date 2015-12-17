package com.itgrids.partyanalyst.dto;

import java.util.List;

public class TrainingCampFeedBackVO {
	
	private String name;
	private Long id;
	private String constituency;
	private String district;
	
	private List<TrainingCampFeedBackVO> subList;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<TrainingCampFeedBackVO> getSubList() {
		return subList;
	}
	public void setSubList(List<TrainingCampFeedBackVO> subList) {
		this.subList = subList;
	}
	
	
}
