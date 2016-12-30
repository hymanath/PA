package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class CardPrintStatusVO implements Serializable{
	
	private Long id;
	private String name;
	private Long superId;
	private String superName;
	private String state;
	
	private Long count = 0l;
	
	private List<CardPrintStatusVO> subList;
	private Map<Long , CardPrintStatusVO> subMap;
	
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}


	public List<CardPrintStatusVO> getSubList() {
		return subList;
	}

	public void setSubList(List<CardPrintStatusVO> subList) {
		this.subList = subList;
	}

	public Map<Long, CardPrintStatusVO> getSubMap() {
		return subMap;
	}

	public void setSubMap(Map<Long, CardPrintStatusVO> subMap) {
		this.subMap = subMap;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public Long getSuperId() {
		return superId;
	}

	public void setSuperId(Long superId) {
		this.superId = superId;
	}

	public String getSuperName() {
		return superName;
	}

	public void setSuperName(String superName) {
		this.superName = superName;
	}
	
	
}
