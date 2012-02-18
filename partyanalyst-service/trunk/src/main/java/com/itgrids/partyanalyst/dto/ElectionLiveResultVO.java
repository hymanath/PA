package com.itgrids.partyanalyst.dto;

public class ElectionLiveResultVO {

	private Long totalSeats;
	private Long countOfLeadConstituences;
	private Long oldConstituenciesCount;
	private Long newConstituenciesCount;
	private String partyName;
	private Long countOfWinningConstituencies;
	private String leadingPartyName;
	private String winningPartyName;
	private boolean partialResult;
	private Long leadCountInOld;
	private Long wonCountInOld;
	private Long leadCountInNew;
	private Long wonCountInNew;
		
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
}
