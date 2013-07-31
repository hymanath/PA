package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PartyPositionVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -669421025147252419L;
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
	private List<PartyPositionVO> suggestedLocations;
	private List<PartyPositionVO> addedVoterDetails;
	private Long addedVotersCount;
	private String tempVar;
	private boolean addedVotersPresent;
	
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
	public List<PartyPositionVO> getSuggestedLocations() {
		return suggestedLocations;
	}
	public void setSuggestedLocations(List<PartyPositionVO> suggestedLocations) {
		this.suggestedLocations = suggestedLocations;
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
	public List<PartyPositionVO> getAddedVoterDetails() {
		return addedVoterDetails;
	}
	public void setAddedVoterDetails(List<PartyPositionVO> addedVoterDetails) {
		this.addedVoterDetails = addedVoterDetails;
	}
	public Long getAddedVotersCount() {
		return addedVotersCount;
	}
	public void setAddedVotersCount(Long addedVotersCount) {
		this.addedVotersCount = addedVotersCount;
	}
	public String getTempVar() {
		return tempVar;
	}
	public void setTempVar(String tempVar) {
		this.tempVar = tempVar;
	}
	public boolean isAddedVotersPresent() {
		return addedVotersPresent;
	}
	public void setAddedVotersPresent(boolean addedVotersPresent) {
		this.addedVotersPresent = addedVotersPresent;
	}
	
	
}
