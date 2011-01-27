package com.itgrids.partyanalyst.dto;

import java.util.List;

public class CensusWisePartyResultsVO {

	private String range;
	private List<PartyResultsVO> partiesResults;
	
	public String getRange() {
		return range;
	}
	
	public void setRange(String range) {
		this.range = range;
	}
	
	public List<PartyResultsVO> getPartiesResults() {
		return partiesResults;
	}
	
	public void setPartiesResults(List<PartyResultsVO> partiesResults) {
		this.partiesResults = partiesResults;
	}
	
	
	
}
