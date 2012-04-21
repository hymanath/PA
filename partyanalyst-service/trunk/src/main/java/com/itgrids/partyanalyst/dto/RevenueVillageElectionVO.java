package com.itgrids.partyanalyst.dto;

import java.util.List;
import java.util.Set;

public class RevenueVillageElectionVO {

	private Long townshipId;
	private String townshipName;
	private Long totalVoters;
	private Long votesPolled;
	private List<PartyElectionResultVO> partyElectionResultVOs;
	private List<SelectOptionVO> booths;
	private Set<SelectOptionVO> boothsSet;
	private List<SelectOptionVO> hamletsOfTownship;

	public Long getTotalVoters() {
		return totalVoters;
	}

	public void setTotalVoters(Long totalVoters) {
		this.totalVoters = totalVoters;
	}

	public Long getVotesPolled() {
		return votesPolled;
	}

	public void setVotesPolled(Long votesPolled) {
		this.votesPolled = votesPolled;
	}

	public Set<SelectOptionVO> getBoothsSet() {
		return boothsSet;
	}

	public void setBoothsSet(Set<SelectOptionVO> boothsSet) {
		this.boothsSet = boothsSet;
	}

	public List<SelectOptionVO> getHamletsOfTownship() {
		return hamletsOfTownship;
	}

	public void setHamletsOfTownship(List<SelectOptionVO> hamletsOfTownship) {
		this.hamletsOfTownship = hamletsOfTownship;
	}

	public Long getTownshipId() {
		return townshipId;
	}

	public void setTownshipId(Long townshipId) {
		this.townshipId = townshipId;
	}

	public String getTownshipName() {
		return townshipName;
	}

	public void setTownshipName(String townshipName) {
		this.townshipName = townshipName;
	}

	public List<PartyElectionResultVO> getPartyElectionResultVOs() {
		return partyElectionResultVOs;
	}

	public void setPartyElectionResultVOs(
			List<PartyElectionResultVO> partyElectionResultVOs) {
		this.partyElectionResultVOs = partyElectionResultVOs;
	} 
	
	public List<SelectOptionVO> getBooths() {
		return booths;
	}

	public void setBooths(List<SelectOptionVO> booths) {
		this.booths = booths;
	}

	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof RevenueVillageElectionVO))
			return false;
		RevenueVillageElectionVO voObj = (RevenueVillageElectionVO) obj;
		return this.townshipId.equals(voObj.getTownshipId());
	}
	
	@Override
	public int hashCode(){
		return this.townshipId.hashCode();
	}
	
}
