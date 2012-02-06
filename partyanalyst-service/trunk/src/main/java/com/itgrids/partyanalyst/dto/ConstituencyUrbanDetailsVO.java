package com.itgrids.partyanalyst.dto;

import java.util.List;

public class ConstituencyUrbanDetailsVO implements java.io.Serializable{

	private static final long serialVersionUID = -1658013718258970468L;
	
	private String range;
	private List<PartyWiseResultVO> partyResults;
	
	public String getRange() {
		return range;
	}
	public void setRange(String range) {
		this.range = range;
	}
	public List<PartyWiseResultVO> getPartyResults() {
		return partyResults;
	}
	public void setPartyResults(List<PartyWiseResultVO> partyResults) {
		this.partyResults = partyResults;
	}
	

}
