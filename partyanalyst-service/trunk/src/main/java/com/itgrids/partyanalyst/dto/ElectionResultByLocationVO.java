package com.itgrids.partyanalyst.dto;

import java.util.List;

public class ElectionResultByLocationVO {

	private String villageName;
	private Long villageId;
	private String constituencyName;
	private Long constituencyId;
	private List<PartyVotesEarnedVO> mandalWisePartyVotes;
	
	public String getVillageName() {
		return villageName;
	}
	
	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}
	
	public Long getVillageId() {
		return villageId;
	}
	
	public void setVillageId(Long villageId) {
		this.villageId = villageId;
	}
	
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

	@Override
	public boolean equals(Object obj){
		if (!(obj instanceof ElectionResultByLocationVO))
		      return false;
		ElectionResultByLocationVO voObj = (ElectionResultByLocationVO) obj;
		return this.villageId.equals(voObj.getVillageId());
	}
	
	@Override
	public int hashCode(){
		return this.villageId.hashCode();
	}
	
}
