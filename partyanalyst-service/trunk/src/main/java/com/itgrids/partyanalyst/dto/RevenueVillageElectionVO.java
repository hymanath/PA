package com.itgrids.partyanalyst.dto;

import java.util.List;

public class RevenueVillageElectionVO {

	private Long townshipId;
	private String townshipName;
	private List<PartyElectionResultVO> partyElectionResultVOs;
	private List<SelectOptionVO> booths;

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
