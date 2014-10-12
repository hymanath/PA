package com.itgrids.partyanalyst.dto;

import java.util.List;
import java.util.Map;

public class VO {

	private Long id;
	private String name;
	
	private List<VO> subList;
	
	private Map<String, Long> mandalMap;
	
	private Map<String, Long> panchayatMap;
	

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

	public Map<String, Long> getMandalMap() {
		return mandalMap;
	}

	public void setMandalMap(Map<String, Long> mandalMap) {
		this.mandalMap = mandalMap;
	}

	public Map<String, Long> getPanchayatMap() {
		return panchayatMap;
	}

	public void setPanchayatMap(Map<String, Long> panchayatMap) {
		this.panchayatMap = panchayatMap;
	}

	public List<VO> getSubList() {
		return subList;
	}

	public void setSubList(List<VO> subList) {
		this.subList = subList;
	}

	
	
	
	
}
