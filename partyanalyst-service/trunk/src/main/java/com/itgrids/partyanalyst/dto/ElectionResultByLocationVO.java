package com.itgrids.partyanalyst.dto;

import java.util.List;

public class ElectionResultByLocationVO {

	private String constituencyName;
	private Long constituencyId;
	private List<PartyVotesEarnedVO> mandalWisePartyVotes;
	private List<LocationWiseBoothDetailsVO> revenueVillagesInfo;
		
	public List<PartyVotesEarnedVO> getMandalWisePartyVotes() {
		return mandalWisePartyVotes;
	}
	
	public void setMandalWisePartyVotes(
			List<PartyVotesEarnedVO> mandalWisePartyVotes) {
		this.mandalWisePartyVotes = mandalWisePartyVotes;
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

	public List<LocationWiseBoothDetailsVO> getRevenueVillagesInfo() {
		return revenueVillagesInfo;
	}

	public void setRevenueVillagesInfo(
			List<LocationWiseBoothDetailsVO> revenueVillagesInfo) {
		this.revenueVillagesInfo = revenueVillagesInfo;
	}

	@Override
	public boolean equals(Object obj){
		if (!(obj instanceof ElectionResultByLocationVO))
		      return false;
		ElectionResultByLocationVO voObj = (ElectionResultByLocationVO) obj;
		return this.constituencyId.equals(voObj.getConstituencyId());
	}
	
	@Override
	public int hashCode(){
		return this.constituencyId.hashCode();
	}
	
}
