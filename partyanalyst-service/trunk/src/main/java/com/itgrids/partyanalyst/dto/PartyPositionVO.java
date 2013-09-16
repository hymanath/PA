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
	List<PartyPositionVO> boothwisePartyPositionVOList = new ArrayList<PartyPositionVO>(0);
	List<PartyPositionVO> localbodyList = new ArrayList<PartyPositionVO>(0);
	private String localbodyName;
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
	private double rangePercentage = 0.00;
	private Long selectedPartyTotalVoters;
	private Long constituencyId;
	private Long totalVoters = 0L;
	private double percentage = 0.00;
	private double margin = 0.00;
	private int priorityOrder = 0;
	private String partyName ;
	private Long partyTotalvotes;
	private Long LostSeats;
	private Long toTarget;
	private boolean selectedParty = false;
	private Long toDecrease;
	private Long toImprove;
	private String constituencyType;
    private String location;
    private String villagesCovered;
    
   
	public String getVillagesCovered() {
		return villagesCovered;
	}
	public void setVillagesCovered(String villagesCovered) {
		this.villagesCovered = villagesCovered;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Long getToDecrease() {
		return toDecrease;
	}
	public void setToDecrease(Long toDecrease) {
		this.toDecrease = toDecrease;
	}
	public Long getToImprove() {
		return toImprove;
	}
	public void setToImprove(Long toImprove) {
		this.toImprove = toImprove;
	}
	public boolean isSelectedParty() {
		return selectedParty;
	}
	public void setSelectedParty(boolean selectedParty) {
		this.selectedParty = selectedParty;
	}
	public Long getToTarget() {
		return toTarget;
	}
	public void setToTarget(Long toTarget) {
		this.toTarget = toTarget;
	}
	public Long getLostSeats() {
		return LostSeats;
	}
	public void setLostSeats(Long lostSeats) {
		LostSeats = lostSeats;
	}
	public Long getPartyTotalvotes() {
		return partyTotalvotes;
	}
	public void setPartyTotalvotes(Long partyTotalvotes) {
		this.partyTotalvotes = partyTotalvotes;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public List<PartyPositionVO> getLocalbodyList() {
		return localbodyList;
	}
	public void setLocalbodyList(List<PartyPositionVO> localbodyList) {
		this.localbodyList = localbodyList;
	}
	public String getLocalbodyName() {
		return localbodyName;
	}
	public void setLocalbodyName(String localbodyName) {
		this.localbodyName = localbodyName;
	}
	public List<PartyPositionVO> getBoothwisePartyPositionVOList() {
		return boothwisePartyPositionVOList;
	}
	public void setBoothwisePartyPositionVOList(
			List<PartyPositionVO> boothwisePartyPositionVOList) {
		this.boothwisePartyPositionVOList = boothwisePartyPositionVOList;
	}
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
	public double getRangePercentage() {
		return rangePercentage;
	}
	public void setRangePercentage(double rangePercentage) {
		this.rangePercentage = rangePercentage;
	}
	public Long getSelectedPartyTotalVoters() {
		return selectedPartyTotalVoters;
	}
	public void setSelectedPartyTotalVoters(Long selectedPartyTotalVoters) {
		this.selectedPartyTotalVoters = selectedPartyTotalVoters;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public Long getTotalVoters() {
		return totalVoters;
	}
	public void setTotalVoters(Long totalVoters) {
		this.totalVoters = totalVoters;
	}
	public double getPercentage() {
		return percentage;
	}
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	public double getMargin() {
		return margin;
	}
	public void setMargin(double margin) {
		this.margin = margin;
	}
	public int getPriorityOrder() {
		return priorityOrder;
	}
	public void setPriorityOrder(int priorityOrder) {
		this.priorityOrder = priorityOrder;
	}
	public String getConstituencyType() {
		return constituencyType;
	}
	public void setConstituencyType(String constituencyType) {
		this.constituencyType = constituencyType;
	}
	
	
	
}
