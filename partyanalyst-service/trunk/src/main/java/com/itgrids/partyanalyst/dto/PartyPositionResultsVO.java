package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PartyPositionResultsVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -669421025147252419L;
	private String Name;
	private Long id;
	List<PartyPositionResultsVO> partyPositionVOList = new ArrayList<PartyPositionResultsVO>(0);
	List<PartyPositionResultsVO> weakPollingPercentVOList = new ArrayList<PartyPositionResultsVO>(0);
	List<PartyPositionResultsVO> StrongPollingPercentVOList = new ArrayList<PartyPositionResultsVO>(0);
	List<PartyPositionResultsVO> boothwisePartyPositionVOList = new ArrayList<PartyPositionResultsVO>(0);
	List<PartyPositionResultsVO> localbodyList = new ArrayList<PartyPositionResultsVO>(0);
	private String localbodyName;
	private Double minValue;
	private Double maxValue;
	private Double pollingPercentage = 0.00;
	private Double partyPercentage = 0.00;
	private Long totalValidVotes = 0L;
	private List<PartyPositionResultsVO> suggestedLocations;
	private List<PartyPositionResultsVO> addedVoterDetails;
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
    private double prevTrendPoint;
    private Double prp;
    private String type;
    
    private PartyPositionResultsVO tdpvo;
    private PartyPositionResultsVO incvo;
    private PartyPositionResultsVO trsvo;
    private PartyPositionResultsVO bjpvo;
    private PartyPositionResultsVO ysrcvo;
    private PartyPositionResultsVO prpvo;
    private PartyPositionResultsVO cpivo;
    private PartyPositionResultsVO cpmvo;
    private PartyPositionResultsVO othersvo;
    private Long partyId;
    private Long votesEarned;
    private String earnedPercentage;
    
    private Long pastYearVotesEarned;
    private Long presentYearVotersEarned;
    private String pastYearPercentage;
    private String presentYearPercentage;
    private Long diffVotes;
    private String diffVotesPercentage;
    private String status;
    private List<PartyPositionResultsVO> locationWiseList;
    private Long presentValidVotes;
    private Long pastValidVotes;
    private Long votesIncreased;
    
    private List<PartyPositionResultsVO> partiesList;
    private int partiesCount;
    private int yearSpanCount;
    private String pastYear;
    private String currentYear;
    
    private boolean pastAlianced;
    private boolean currentAlianced;
    private String pastAlianceName;
    private String currentAlianceName;
    
    
	
	
	
	public boolean isPastAlianced() {
		return pastAlianced;
	}
	public void setPastAlianced(boolean pastAlianced) {
		this.pastAlianced = pastAlianced;
	}
	public boolean isCurrentAlianced() {
		return currentAlianced;
	}
	public void setCurrentAlianced(boolean currentAlianced) {
		this.currentAlianced = currentAlianced;
	}
	public String getPastAlianceName() {
		return pastAlianceName;
	}
	public void setPastAlianceName(String pastAlianceName) {
		this.pastAlianceName = pastAlianceName;
	}
	public String getCurrentAlianceName() {
		return currentAlianceName;
	}
	public void setCurrentAlianceName(String currentAlianceName) {
		this.currentAlianceName = currentAlianceName;
	}
	public String getPastYear() {
		return pastYear;
	}
	public void setPastYear(String pastYear) {
		this.pastYear = pastYear;
	}
	public String getCurrentYear() {
		return currentYear;
	}
	public void setCurrentYear(String currentYear) {
		this.currentYear = currentYear;
	}
	public int getPartiesCount() {
		return partiesCount;
	}
	public void setPartiesCount(int partiesCount) {
		this.partiesCount = partiesCount;
	}
	public int getYearSpanCount() {
		return yearSpanCount;
	}
	public void setYearSpanCount(int yearSpanCount) {
		this.yearSpanCount = yearSpanCount;
	}
	public List<PartyPositionResultsVO> getPartiesList() {
		return partiesList;
	}
	public void setPartiesList(List<PartyPositionResultsVO> partiesList) {
		this.partiesList = partiesList;
	}
	public Long getVotesIncreased() {
		return votesIncreased;
	}
	public void setVotesIncreased(Long votesIncreased) {
		this.votesIncreased = votesIncreased;
	}
	public Long getPresentValidVotes() {
		return presentValidVotes;
	}
	public void setPresentValidVotes(Long presentValidVotes) {
		this.presentValidVotes = presentValidVotes;
	}
	public Long getPastValidVotes() {
		return pastValidVotes;
	}
	public void setPastValidVotes(Long pastValidVotes) {
		this.pastValidVotes = pastValidVotes;
	}
	public List<PartyPositionResultsVO> getLocationWiseList() {
		return locationWiseList;
	}
	public void setLocationWiseList(List<PartyPositionResultsVO> locationWiseList) {
		this.locationWiseList = locationWiseList;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getPastYearVotesEarned() {
		return pastYearVotesEarned;
	}
	public void setPastYearVotesEarned(Long pastYearVotesEarned) {
		this.pastYearVotesEarned = pastYearVotesEarned;
	}
	public Long getPresentYearVotersEarned() {
		return presentYearVotersEarned;
	}
	public void setPresentYearVotersEarned(Long presentYearVotersEarned) {
		this.presentYearVotersEarned = presentYearVotersEarned;
	}
	public String getPastYearPercentage() {
		return pastYearPercentage;
	}
	public void setPastYearPercentage(String pastYearPercentage) {
		this.pastYearPercentage = pastYearPercentage;
	}
	public String getPresentYearPercentage() {
		return presentYearPercentage;
	}
	public void setPresentYearPercentage(String presentYearPercentage) {
		this.presentYearPercentage = presentYearPercentage;
	}
	public Long getDiffVotes() {
		return diffVotes;
	}
	public void setDiffVotes(Long diffVotes) {
		this.diffVotes = diffVotes;
	}
	public String getDiffVotesPercentage() {
		return diffVotesPercentage;
	}
	public void setDiffVotesPercentage(String diffVotesPercentage) {
		this.diffVotesPercentage = diffVotesPercentage;
	}
	public Long getVotesEarned() {
		return votesEarned;
	}
	public void setVotesEarned(Long votesEarned) {
		this.votesEarned = votesEarned;
	}
	public String getEarnedPercentage() {
		return earnedPercentage;
	}
	public void setEarnedPercentage(String earnedPercentage) {
		this.earnedPercentage = earnedPercentage;
	}
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	public PartyPositionResultsVO getTdpvo() {
		return tdpvo;
	}
	public void setTdpvo(PartyPositionResultsVO tdpvo) {
		this.tdpvo = tdpvo;
	}
	public PartyPositionResultsVO getIncvo() {
		return incvo;
	}
	public void setIncvo(PartyPositionResultsVO incvo) {
		this.incvo = incvo;
	}
	public PartyPositionResultsVO getTrsvo() {
		return trsvo;
	}
	public void setTrsvo(PartyPositionResultsVO trsvo) {
		this.trsvo = trsvo;
	}
	public PartyPositionResultsVO getBjpvo() {
		return bjpvo;
	}
	public void setBjpvo(PartyPositionResultsVO bjpvo) {
		this.bjpvo = bjpvo;
	}
	public PartyPositionResultsVO getYsrcvo() {
		return ysrcvo;
	}
	public void setYsrcvo(PartyPositionResultsVO ysrcvo) {
		this.ysrcvo = ysrcvo;
	}
	public PartyPositionResultsVO getPrpvo() {
		return prpvo;
	}
	public void setPrpvo(PartyPositionResultsVO prpvo) {
		this.prpvo = prpvo;
	}
	public PartyPositionResultsVO getCpivo() {
		return cpivo;
	}
	public void setCpivo(PartyPositionResultsVO cpivo) {
		this.cpivo = cpivo;
	}
	public PartyPositionResultsVO getCpmvo() {
		return cpmvo;
	}
	public void setCpmvo(PartyPositionResultsVO cpmvo) {
		this.cpmvo = cpmvo;
	}
	public PartyPositionResultsVO getOthersvo() {
		return othersvo;
	}
	public void setOthersvo(PartyPositionResultsVO othersvo) {
		this.othersvo = othersvo;
	}
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
	public List<PartyPositionResultsVO> getLocalbodyList() {
		return localbodyList;
	}
	public void setLocalbodyList(List<PartyPositionResultsVO> localbodyList) {
		this.localbodyList = localbodyList;
	}
	public String getLocalbodyName() {
		return localbodyName;
	}
	public void setLocalbodyName(String localbodyName) {
		this.localbodyName = localbodyName;
	}
	public List<PartyPositionResultsVO> getBoothwisePartyPositionVOList() {
		return boothwisePartyPositionVOList;
	}
	public void setBoothwisePartyPositionVOList(
			List<PartyPositionResultsVO> boothwisePartyPositionVOList) {
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
	public List<PartyPositionResultsVO> getPartyPositionVOList() {
		return partyPositionVOList;
	}
	public void setPartyPositionVOList(List<PartyPositionResultsVO> partyPositionVOList) {
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
	public List<PartyPositionResultsVO> getSuggestedLocations() {
		return suggestedLocations;
	}
	public void setSuggestedLocations(List<PartyPositionResultsVO> suggestedLocations) {
		this.suggestedLocations = suggestedLocations;
	}
	
	public Double getPollingPercentage() {
		return pollingPercentage;
	}
	public void setPollingPercentage(Double pollingPercentage) {
		this.pollingPercentage = pollingPercentage;
	}
	public List<PartyPositionResultsVO> getWeakPollingPercentVOList() {
		return weakPollingPercentVOList;
	}
	public void setWeakPollingPercentVOList(
			List<PartyPositionResultsVO> weakPollingPercentVOList) {
		this.weakPollingPercentVOList = weakPollingPercentVOList;
	}
	public List<PartyPositionResultsVO> getStrongPollingPercentVOList() {
		return StrongPollingPercentVOList;
	}
	public void setStrongPollingPercentVOList(
			List<PartyPositionResultsVO> strongPollingPercentVOList) {
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
	public List<PartyPositionResultsVO> getAddedVoterDetails() {
		return addedVoterDetails;
	}
	public void setAddedVoterDetails(List<PartyPositionResultsVO> addedVoterDetails) {
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
	public double getPrevTrendPoint() {
		return prevTrendPoint;
	}
	public void setPrevTrendPoint(double prevTrendPoint) {
		this.prevTrendPoint = prevTrendPoint;
	}
	public Double getPrp() {
		return prp;
	}
	public void setPrp(Double prp) {
		this.prp = prp;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
