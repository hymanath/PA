package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class PartiesDetailsVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7667826325392347241L;
	
	private String electionYear;
	private String partyName;
	private Long partyId;
	private String candidateName;
	private String votesEarned;
	private Long count;
	private Long constituencyId;
	private String constituencyName;
	private Long constituencyCount;
	private Long wonTimes;
	private Long lostTimes;
	private Long won;
	private List<SelectOptionVO>  details;
	private Map<Long,Long>  partyStrenghCount;
	private Map<Long,SelectOptionVO>  partyWeaknessCount;
	private List<ContenetTransferVO>  partyDetails;
	private List<Long> constIds;
	private String partyFlag;

	
	public String getPartyFlag() {
		return partyFlag;
	}
	public void setPartyFlag(String partyFlag) {
		this.partyFlag = partyFlag;
	}
	public Long getWon() {
		return won;
	}
	public void setWon(Long won) {
		this.won = won;
	}
	public Map<Long, SelectOptionVO> getPartyWeaknessCount() {
		return partyWeaknessCount;
	}
	public void setPartyWeaknessCount(Map<Long, SelectOptionVO> partyWeaknessCount) {
		this.partyWeaknessCount = partyWeaknessCount;
	}
	public Long getWonTimes() {
		return wonTimes;
	}
	public void setWonTimes(Long wonTimes) {
		this.wonTimes = wonTimes;
	}
	public Long getLostTimes() {
		return lostTimes;
	}
	public void setLostTimes(Long lostTimes) {
		this.lostTimes = lostTimes;
	}
	public List<Long> getConstIds() {
		return constIds;
	}
	public void setConstIds(List<Long> constIds) {
		this.constIds = constIds;
	}
	public Long getConstituencyCount() {
		return constituencyCount;
	}
	public void setConstituencyCount(Long constituencyCount) {
		this.constituencyCount = constituencyCount;
	}
	public List<ContenetTransferVO> getPartyDetails() {
		return partyDetails;
	}
	public void setPartyDetails(List<ContenetTransferVO> partyDetails) {
		this.partyDetails = partyDetails;
	}
	public Map<Long, Long> getPartyStrenghCount() {
		return partyStrenghCount;
	}
	public void setPartyStrenghCount(Map<Long, Long> partyStrenghCount) {
		this.partyStrenghCount = partyStrenghCount;
	}
	public List<SelectOptionVO> getDetails() {
		return details;
	}
	public void setDetails(List<SelectOptionVO> details) {
		this.details = details;
	}
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	public String getElectionYear() {
		return electionYear;
	}
	public void setElectionYear(String electionYear) {
		this.electionYear = electionYear;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public String getVotesEarned() {
		return votesEarned;
	}
	public void setVotesEarned(String votesEarned) {
		this.votesEarned = votesEarned;
	}
}
