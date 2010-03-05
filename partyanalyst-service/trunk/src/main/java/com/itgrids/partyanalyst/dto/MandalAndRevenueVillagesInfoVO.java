package com.itgrids.partyanalyst.dto;

import java.util.List;

public class MandalAndRevenueVillagesInfoVO extends ResultStatus{

	private List<PartyVotesEarnedVO> mandalWisePartyVotes;
	private List<LocationWiseBoothDetailsVO> revenueVillagesInfo;
	
	public List<PartyVotesEarnedVO> getMandalWisePartyVotes() {
		return mandalWisePartyVotes;
	}
	
	public void setMandalWisePartyVotes(
			List<PartyVotesEarnedVO> mandalWisePartyVotes) {
		this.mandalWisePartyVotes = mandalWisePartyVotes;
	}
	
	public List<LocationWiseBoothDetailsVO> getRevenueVillagesInfo() {
		return revenueVillagesInfo;
	}
	
	public void setRevenueVillagesInfo(
			List<LocationWiseBoothDetailsVO> revenueVillagesInfo) {
		this.revenueVillagesInfo = revenueVillagesInfo;
	}
	
	
}
