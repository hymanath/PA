/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on April 07,2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class DistrictWisePartyPositionsVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5962915790838575759L;
	
	private Long partyId;
	private String partyName;
	private Long totSeatsWonInAllPartiDistricts;
	private Long totalConstituencies;
	private Long totalValidVotes;
	
	public Long getTotalConstituencies() {
		return totalConstituencies;
	}
	public void setTotalConstituencies(Long totalConstituencies) {
		this.totalConstituencies = totalConstituencies;
	}
	public Long getTotalValidVotes() {
		return totalValidVotes;
	}
	public void setTotalValidVotes(Long totalValidVotes) {
		this.totalValidVotes = totalValidVotes;
	}
	private List<PartyPositionsInDistrictVO> partyResultsInDistricts;
	
	//getters and setters
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
	public Long getTotSeatsWonInAllPartiDistricts() {
		return totSeatsWonInAllPartiDistricts;
	}
	public void setTotSeatsWonInAllPartiDistricts(
			Long totSeatsWonInAllPartiDistricts) {
		this.totSeatsWonInAllPartiDistricts = totSeatsWonInAllPartiDistricts;
	}
	public List<PartyPositionsInDistrictVO> getPartyResultsInDistricts() {
		return partyResultsInDistricts;
	}
	public void setPartyResultsInDistricts(
			List<PartyPositionsInDistrictVO> partyResultsInDistricts) {
		this.partyResultsInDistricts = partyResultsInDistricts;
	}
	

}
