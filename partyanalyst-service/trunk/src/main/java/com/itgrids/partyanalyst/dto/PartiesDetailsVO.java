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
	private List<SelectOptionVO>  details;
	private Map<Long,Long>  partyStrenghCount;

	private List<SelectOptionVO>  partyDetails;
	private List<Long> constIds;
	
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
	public List<SelectOptionVO> getPartyDetails() {
		return partyDetails;
	}
	public void setPartyDetails(List<SelectOptionVO> partyDetails) {
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
