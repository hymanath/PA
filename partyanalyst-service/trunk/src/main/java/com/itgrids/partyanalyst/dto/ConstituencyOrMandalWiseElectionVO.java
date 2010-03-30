package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class ConstituencyOrMandalWiseElectionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long locationId;
	private String locationName;
	private Long totalPolledVotes;
	private List<PartyElectionResultVO> partyElectionResultVOs;
	
	
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public Long getTotalPolledVotes() {
		return totalPolledVotes;
	}
	public void setTotalPolledVotes(Long totalPolledVotes) {
		this.totalPolledVotes = totalPolledVotes;
	}
	public List<PartyElectionResultVO> getPartyElectionResultVOs() {
		return partyElectionResultVOs;
	}
	public void setPartyElectionResultVOs(
			List<PartyElectionResultVO> partyElectionResultVOs) {
		this.partyElectionResultVOs = partyElectionResultVOs;
	}
}
