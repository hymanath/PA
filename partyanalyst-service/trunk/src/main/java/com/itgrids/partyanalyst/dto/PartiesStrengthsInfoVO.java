package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class PartiesStrengthsInfoVO implements Serializable {

	private List<PartiesDetailsVO> partyResults;
	private Long constituencyId;
	private String constituencyName;
	
	
	public List<PartiesDetailsVO> getPartyResults() {
		return partyResults;
	}
	public void setPartyResults(List<PartiesDetailsVO> partyResults) {
		this.partyResults = partyResults;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	
	
}
