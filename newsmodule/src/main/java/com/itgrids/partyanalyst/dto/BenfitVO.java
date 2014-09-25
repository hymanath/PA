package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class BenfitVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1439608263562870563L;

	private Long id;
	private String name;
	private Long count;
	private Long negCount;
	private List<BenfitVO> benfitVOList;
	
	
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
	
	public List<BenfitVO> getBenfitVOList() {
		return benfitVOList;
	}
	
	public void setBenfitVOList(List<BenfitVO> benfitVOList) {
		this.benfitVOList = benfitVOList;
	}

	public Long getNegCount() {
		return negCount;
	}

	public void setNegCount(Long negCount) {
		this.negCount = negCount;
	}
	
}
