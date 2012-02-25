package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class ElectionLiveResultVO {

	private Long totalSeats = 0L;
	private Long countOfLeadConstituences = 0L;
	private Long oldConstituenciesCount = 0L;
	private Long newConstituenciesCount = 0L;
	private Long partyId;
	private String partyName;
	private Long countOfWinningConstituencies = 0L;
	private String leadingPartyName;
	private String winningPartyName;
	private boolean partialResult;
	private Long leadCountInOld = 0L;
	private Long wonCountInOld = 0L;
	private Long leadCountInNew = 0L;
	private Long wonCountInNew = 0L;
	private Long wonOrLeadCount = 0L;
	private Long retainedCount = 0L;
	private Boolean isFirstElectionAfterDelimtation;
	private List<SelectOptionVO> wonFromOtherParties = new ArrayList<SelectOptionVO>(0);
	private List<SelectOptionVO> lostToOtherParties = new ArrayList<SelectOptionVO>(0);
	private List<SelectOptionVO> participatedConstituencies = new ArrayList<SelectOptionVO>(0);
	private List<SelectOptionVO> lostSeatsInPrevWonToOtherParties = new ArrayList<SelectOptionVO>(0);
	private List<SelectOptionVO> lostSeatsInPrevLostToOtherParties = new ArrayList<SelectOptionVO>(0);
	private List<SelectOptionVO> prevWonConstituenciesList = new ArrayList<SelectOptionVO>(0);
	private List<SelectOptionVO> prevLostConstituenciesList = new ArrayList<SelectOptionVO>(0);
	private List<SelectOptionVO> lostConstituenciesList = new ArrayList<SelectOptionVO>(0);
	private List<SelectOptionVO> oldConstituenciesParticipated = new ArrayList<SelectOptionVO>(0);
	private List<SelectOptionVO> newConstituenciesParticipated = new ArrayList<SelectOptionVO>(0);
	private List<Long> constituencyIdsList = new ArrayList<Long>(0);
	private List<Long> prevLostConstIds = new ArrayList<Long>(0);
	private List<Long> prevWonConstIds = new ArrayList<Long>(0);
	private List<Long> lostConstIdsList = new ArrayList<Long>(0);
	private Long totalSeatsParticipated = 0L;
	private Long lostCount = 0L;
	private Long lostCountInPrevWon = 0L;
	private Long lostCountInPrevLost = 0L;
	private Long oldConstituencyParticipatedCount = 0L;
	private Long newConstituencyParticipatedCount = 0L;
	private String winOrLeadPercent = "0.00";
	private String oldWinOrLeadPercent = "0.00";
	private String newWinOrLeadPercent = "0.00";
	private Double winOrLeadPer = 0D;
	private Double oldWinOrLeadPer = 0D;
	private Double newWinOrLeadPer = 0D;
	private Long totalKnownCount = 0L;
	private Long oldKnownCount = 0L;
	private Long newKnownCount = 0L;
	private Long wonOrLeadCountInOld = 0L;
	private Long wonOrLeadCountInNew = 0L;
	private Long wonFromOtherPartiesCount = 0L;
		
	public Long getWonFromOtherPartiesCount() {
		return wonFromOtherPartiesCount;
	}
	public void setWonFromOtherPartiesCount(Long wonFromOtherPartiesCount) {
		this.wonFromOtherPartiesCount = wonFromOtherPartiesCount;
	}
	public Double getWinOrLeadPer() {
		return winOrLeadPer;
	}
	public void setWinOrLeadPer(Double winOrLeadPer) {
		this.winOrLeadPer = winOrLeadPer;
	}
	public Double getOldWinOrLeadPer() {
		return oldWinOrLeadPer;
	}
	public void setOldWinOrLeadPer(Double oldWinOrLeadPer) {
		this.oldWinOrLeadPer = oldWinOrLeadPer;
	}
	public Double getNewWinOrLeadPer() {
		return newWinOrLeadPer;
	}
	public void setNewWinOrLeadPer(Double newWinOrLeadPer) {
		this.newWinOrLeadPer = newWinOrLeadPer;
	}
	public String getNewWinOrLeadPercent() {
		return newWinOrLeadPercent;
	}
	public void setNewWinOrLeadPercent(String newWinOrLeadPercent) {
		this.newWinOrLeadPercent = newWinOrLeadPercent;
	}
	public Long getWonOrLeadCountInOld() {
		return wonOrLeadCountInOld;
	}
	public void setWonOrLeadCountInOld(Long wonOrLeadCountInOld) {
		this.wonOrLeadCountInOld = wonOrLeadCountInOld;
	}
	public Long getWonOrLeadCountInNew() {
		return wonOrLeadCountInNew;
	}
	public void setWonOrLeadCountInNew(Long wonOrLeadCountInNew) {
		this.wonOrLeadCountInNew = wonOrLeadCountInNew;
	}
	public Long getTotalKnownCount() {
		return totalKnownCount;
	}
	public void setTotalKnownCount(Long totalKnownCount) {
		this.totalKnownCount = totalKnownCount;
	}
	public Long getOldKnownCount() {
		return oldKnownCount;
	}
	public void setOldKnownCount(Long oldKnownCount) {
		this.oldKnownCount = oldKnownCount;
	}
	public Long getNewKnownCount() {
		return newKnownCount;
	}
	public void setNewKnownCount(Long newKnownCount) {
		this.newKnownCount = newKnownCount;
	}
	public List<SelectOptionVO> getOldConstituenciesParticipated() {
		return oldConstituenciesParticipated;
	}
	public void setOldConstituenciesParticipated(
			List<SelectOptionVO> oldConstituenciesParticipated) {
		this.oldConstituenciesParticipated = oldConstituenciesParticipated;
	}
	public List<SelectOptionVO> getNewConstituenciesParticipated() {
		return newConstituenciesParticipated;
	}
	public void setNewConstituenciesParticipated(
			List<SelectOptionVO> newConstituenciesParticipated) {
		this.newConstituenciesParticipated = newConstituenciesParticipated;
	}
	public Long getOldConstituencyParticipatedCount() {
		return oldConstituencyParticipatedCount;
	}
	public void setOldConstituencyParticipatedCount(
			Long oldConstituencyParticipatedCount) {
		this.oldConstituencyParticipatedCount = oldConstituencyParticipatedCount;
	}
	public Long getNewConstituencyParticipatedCount() {
		return newConstituencyParticipatedCount;
	}
	public void setNewConstituencyParticipatedCount(
			Long newConstituencyParticipatedCount) {
		this.newConstituencyParticipatedCount = newConstituencyParticipatedCount;
	}
	public String getWinOrLeadPercent() {
		return winOrLeadPercent;
	}
	public void setWinOrLeadPercent(String winOrLeadPercent) {
		this.winOrLeadPercent = winOrLeadPercent;
	}
	public String getOldWinOrLeadPercent() {
		return oldWinOrLeadPercent;
	}
	public void setOldWinOrLeadPercent(String oldWinOrLeadPercent) {
		this.oldWinOrLeadPercent = oldWinOrLeadPercent;
	}
	public List<Long> getLostConstIdsList() {
		return lostConstIdsList;
	}
	public void setLostConstIdsList(List<Long> lostConstIdsList) {
		this.lostConstIdsList = lostConstIdsList;
	}
	public List<SelectOptionVO> getLostConstituenciesList() {
		return lostConstituenciesList;
	}
	public void setLostConstituenciesList(
			List<SelectOptionVO> lostConstituenciesList) {
		this.lostConstituenciesList = lostConstituenciesList;
	}
	public List<Long> getPrevLostConstIds() {
		return prevLostConstIds;
	}
	public void setPrevLostConstIds(List<Long> prevLostConstIds) {
		this.prevLostConstIds = prevLostConstIds;
	}
	public List<Long> getPrevWonConstIds() {
		return prevWonConstIds;
	}
	public void setPrevWonConstIds(List<Long> prevWonConstIds) {
		this.prevWonConstIds = prevWonConstIds;
	}
	public List<SelectOptionVO> getPrevWonConstituenciesList() {
		return prevWonConstituenciesList;
	}
	public void setPrevWonConstituenciesList(
			List<SelectOptionVO> prevWonConstituenciesList) {
		this.prevWonConstituenciesList = prevWonConstituenciesList;
	}
	public List<SelectOptionVO> getPrevLostConstituenciesList() {
		return prevLostConstituenciesList;
	}
	public void setPrevLostConstituenciesList(
			List<SelectOptionVO> prevLostConstituenciesList) {
		this.prevLostConstituenciesList = prevLostConstituenciesList;
	}
	public Long getLostCount() {
		return lostCount;
	}
	public void setLostCount(Long lostCount) {
		this.lostCount = lostCount;
	}
	public Long getTotalSeatsParticipated() {
		return totalSeatsParticipated;
	}
	public void setTotalSeatsParticipated(Long totalSeatsParticipated) {
		this.totalSeatsParticipated = totalSeatsParticipated;
	}
	public Boolean getIsFirstElectionAfterDelimtation() {
		return isFirstElectionAfterDelimtation;
	}
	public void setIsFirstElectionAfterDelimtation(
			Boolean isFirstElectionAfterDelimtation) {
		this.isFirstElectionAfterDelimtation = isFirstElectionAfterDelimtation;
	}
	public List<Long> getConstituencyIdsList() {
		return constituencyIdsList;
	}
	public void setConstituencyIdsList(List<Long> constituencyIdsList) {
		this.constituencyIdsList = constituencyIdsList;
	}
	public Long getWonOrLeadCount() {
		return wonOrLeadCount;
	}
	public void setWonOrLeadCount(Long wonOrLeadCount) {
		this.wonOrLeadCount = wonOrLeadCount;
	}
	public Long getRetainedCount() {
		return retainedCount;
	}
	public void setRetainedCount(Long retainedCount) {
		this.retainedCount = retainedCount;
	}
	public List<SelectOptionVO> getWonFromOtherParties() {
		return wonFromOtherParties;
	}
	public void setWonFromOtherParties(List<SelectOptionVO> wonFromOtherParties) {
		this.wonFromOtherParties = wonFromOtherParties;
	}
	public List<SelectOptionVO> getLostToOtherParties() {
		return lostToOtherParties;
	}
	public void setLostToOtherParties(List<SelectOptionVO> lostToOtherParties) {
		this.lostToOtherParties = lostToOtherParties;
	}
	public Long getTotalSeats() {
		return totalSeats;
	}
	public Long getCountOfLeadConstituences() {
		return countOfLeadConstituences;
	}
	public Long getOldConstituenciesCount() {
		return oldConstituenciesCount;
	}
	public Long getNewConstituenciesCount() {
		return newConstituenciesCount;
	}
	public Long getCountOfWinningConstituencies() {
		return countOfWinningConstituencies;
	}
	public void setTotalSeats(Long totalSeats) {
		this.totalSeats = totalSeats;
	}
	public void setCountOfLeadConstituences(Long countOfLeadConstituences) {
		this.countOfLeadConstituences = countOfLeadConstituences;
	}
	public void setOldConstituenciesCount(Long oldConstituenciesCount) {
		this.oldConstituenciesCount = oldConstituenciesCount;
	}
	public void setNewConstituenciesCount(Long newConstituenciesCount) {
		this.newConstituenciesCount = newConstituenciesCount;
	}
	public void setCountOfWinningConstituencies(Long countOfWinningConstituencies) {
		this.countOfWinningConstituencies = countOfWinningConstituencies;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setLeadingPartyName(String leadingPartyName) {
		this.leadingPartyName = leadingPartyName;
	}
	public String getLeadingPartyName() {
		return leadingPartyName;
	}
	
	public void setWinningPartyName(String winningPartyName) {
		this.winningPartyName = winningPartyName;
	}
	public String getWinningPartyName() {
		return winningPartyName;
	}
	public void setPartialResult(boolean partialResult) {
		this.partialResult = partialResult;
	}
	public boolean isPartialResult() {
		return partialResult;
	}
	public Long getLeadCountInOld() {
		return leadCountInOld;
	}
	public Long getWonCountInOld() {
		return wonCountInOld;
	}
	public Long getLeadCountInNew() {
		return leadCountInNew;
	}
	public Long getWonCountInNew() {
		return wonCountInNew;
	}
	public void setLeadCountInOld(Long leadCountInOld) {
		this.leadCountInOld = leadCountInOld;
	}
	public void setWonCountInOld(Long wonCountInOld) {
		this.wonCountInOld = wonCountInOld;
	}
	public void setLeadCountInNew(Long leadCountInNew) {
		this.leadCountInNew = leadCountInNew;
	}
	public void setWonCountInNew(Long wonCountInNew) {
		this.wonCountInNew = wonCountInNew;
	}
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	public List<SelectOptionVO> getParticipatedConstituencies() {
		return participatedConstituencies;
	}
	public void setParticipatedConstituencies(
			List<SelectOptionVO> participatedConstituencies) {
		this.participatedConstituencies = participatedConstituencies;
	}
	public List<SelectOptionVO> getLostSeatsInPrevWonToOtherParties() {
		return lostSeatsInPrevWonToOtherParties;
	}
	public void setLostSeatsInPrevWonToOtherParties(
			List<SelectOptionVO> lostSeatsInPrevWonToOtherParties) {
		this.lostSeatsInPrevWonToOtherParties = lostSeatsInPrevWonToOtherParties;
	}
	public List<SelectOptionVO> getLostSeatsInPrevLostToOtherParties() {
		return lostSeatsInPrevLostToOtherParties;
	}
	public void setLostSeatsInPrevLostToOtherParties(
			List<SelectOptionVO> lostSeatsInPrevLostToOtherParties) {
		this.lostSeatsInPrevLostToOtherParties = lostSeatsInPrevLostToOtherParties;
	}
	public Long getLostCountInPrevWon() {
		return lostCountInPrevWon;
	}
	public void setLostCountInPrevWon(Long lostCountInPrevWon) {
		this.lostCountInPrevWon = lostCountInPrevWon;
	}
	public Long getLostCountInPrevLost() {
		return lostCountInPrevLost;
	}
	public void setLostCountInPrevLost(Long lostCountInPrevLost) {
		this.lostCountInPrevLost = lostCountInPrevLost;
	}
}
