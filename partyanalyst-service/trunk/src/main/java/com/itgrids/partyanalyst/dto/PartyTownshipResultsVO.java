/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 5, 2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class PartyTownshipResultsVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long townshipId;
	private String townshipName;
	private Long partyId;
	private String partyName;
	private Long votesEarned;
	private Long validVotes;
	private String votesPercent;
	
	
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
	public Long getVotesEarned() {
		return votesEarned;
	}
	public void setVotesEarned(Long votesEarned) {
		this.votesEarned = votesEarned;
	}
	public Long getValidVotes() {
		return validVotes;
	}
	public void setValidVotes(Long validVotes) {
		this.validVotes = validVotes;
	}
	public String getVotesPercent() {
		return votesPercent;
	}
	public void setVotesPercent(String votesPercent) {
		this.votesPercent = votesPercent;
	}
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	
	

}
