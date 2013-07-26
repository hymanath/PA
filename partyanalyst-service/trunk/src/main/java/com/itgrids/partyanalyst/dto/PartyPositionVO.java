package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PartyPositionVO implements Serializable{

	private String Name;
	private Long id;
	List<PartyPositionVO> partyPositionVOList = new ArrayList<PartyPositionVO>(0);
	private Double minValue;
	private Double maxValue;
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<PartyPositionVO> getPartyPositionVOList() {
		return partyPositionVOList;
	}
	public void setPartyPositionVOList(List<PartyPositionVO> partyPositionVOList) {
		this.partyPositionVOList = partyPositionVOList;
	}
	
	public Double getMinValue() {
		return minValue;
	}
	public void setMinValue(Double minValue) {
		this.minValue = minValue;
	}
	public Double getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(Double maxValue) {
		this.maxValue = maxValue;
	}
	
	
}
