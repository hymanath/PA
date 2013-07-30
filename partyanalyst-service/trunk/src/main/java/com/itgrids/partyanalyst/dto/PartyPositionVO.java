package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PartyPositionVO implements Serializable{

	private String Name;
	private Long id;
	List<PartyPositionVO> partyPositionVOList = new ArrayList<PartyPositionVO>(0);
	List<PartyPositionVO> weakPollingPercentVOList = new ArrayList<PartyPositionVO>(0);
	List<PartyPositionVO> StrongPollingPercentVOList = new ArrayList<PartyPositionVO>(0);
	
	
	private Double minValue;
	private Double maxValue;
	private Double pollingPercentage = 0.00;
	private Double partyPercentage = 0.00;
	private Long totalValidVotes = 0L;
	
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
	
	public Double getPollingPercentage() {
		return pollingPercentage;
	}
	public void setPollingPercentage(Double pollingPercentage) {
		this.pollingPercentage = pollingPercentage;
	}
	public List<PartyPositionVO> getWeakPollingPercentVOList() {
		return weakPollingPercentVOList;
	}
	public void setWeakPollingPercentVOList(
			List<PartyPositionVO> weakPollingPercentVOList) {
		this.weakPollingPercentVOList = weakPollingPercentVOList;
	}
	public List<PartyPositionVO> getStrongPollingPercentVOList() {
		return StrongPollingPercentVOList;
	}
	public void setStrongPollingPercentVOList(
			List<PartyPositionVO> strongPollingPercentVOList) {
		StrongPollingPercentVOList = strongPollingPercentVOList;
	}
	public Double getPartyPercentage() {
		return partyPercentage;
	}
	public void setPartyPercentage(Double partyPercentage) {
		this.partyPercentage = partyPercentage;
	}
	public Long getTotalValidVotes() {
		return totalValidVotes;
	}
	public void setTotalValidVotes(Long totalValidVotes) {
		this.totalValidVotes = totalValidVotes;
	}
	
	
}
