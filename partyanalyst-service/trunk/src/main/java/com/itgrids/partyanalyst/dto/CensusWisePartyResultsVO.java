package com.itgrids.partyanalyst.dto;

import java.util.List;

public class CensusWisePartyResultsVO {

	private String range;
	private List<PartyResultsVO> partiesResults;
	private Long count;
	
	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

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
