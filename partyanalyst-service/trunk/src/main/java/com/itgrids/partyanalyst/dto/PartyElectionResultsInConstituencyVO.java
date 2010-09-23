/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on Jun 12, 2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class PartyElectionResultsInConstituencyVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long constituencyId;
	private String constituencyName;
	
	private Long totalVoters;
	private Long totalPolledVotes;
	
	private Long wonPartyId;
	private String wonPartyName;
	
	private String wonCandidate;
	private String wonCandDesignation;
	
	private List<PartyResultsVO> partyElecResults;
	private List<PartyElectionResultsVO> partyElectionResultsVO;

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

	public List<PartyResultsVO> getPartyElecResults() {
		return partyElecResults;
	}

	public void setPartyElecResults(List<PartyResultsVO> partyElecResults) {
		this.partyElecResults = partyElecResults;
	}

	public Long getTotalVoters() {
		return totalVoters;
	}

	public void setTotalVoters(Long totalVoters) {
		this.totalVoters = totalVoters;
	}

	public Long getTotalPolledVotes() {
		return totalPolledVotes;
	}

	public void setTotalPolledVotes(Long totalPolledVotes) {
		this.totalPolledVotes = totalPolledVotes;
	}

	public Long getWonPartyId() {
		return wonPartyId;
	}

	public void setWonPartyId(Long wonPartyId) {
		this.wonPartyId = wonPartyId;
	}

	public String getWonPartyName() {
		return wonPartyName;
	}

	public void setWonPartyName(String wonPartyName) {
		this.wonPartyName = wonPartyName;
	}

	public String getWonCandidate() {
		return wonCandidate;
	}

	public void setWonCandidate(String wonCandidate) {
		this.wonCandidate = wonCandidate;
	}

	public String getWonCandDesignation() {
		return wonCandDesignation;
	}

	public void setWonCandDesignation(String wonCandDesignation) {
		this.wonCandDesignation = wonCandDesignation;
	}

	public List<PartyElectionResultsVO> getPartyElectionResultsVO() {
		return partyElectionResultsVO;
	}

	public void setPartyElectionResultsVO(
			List<PartyElectionResultsVO> partyElectionResultsVO) {
		this.partyElectionResultsVO = partyElectionResultsVO;
	}

}
