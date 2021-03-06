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
	private Long totalVoters;
	private List<PartyElectionResultVO> partyElectionResultVOs;
	private Boolean isUrban = false;
	private Boolean showLink = true;
	
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
	public Long getTotalVoters() {
		return totalVoters;
	}
	public void setTotalVoters(Long totalVoters) {
		this.totalVoters = totalVoters;
	}
	public Boolean getShowLink() {
		return showLink;
	}
	public void setShowLink(Boolean showLink) {
		this.showLink = showLink;
	}
	public Boolean getIsUrban() {
		return isUrban;
	}
	public void setIsUrban(Boolean isUrban) {
		this.isUrban = isUrban;
	}
}
