/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 09, 2009
 */

package com.itgrids.partyanalyst.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.model.BaseObject;

public class PartyPerformanceReportVO extends BaseObject {

	private static final long serialVersionUID = 1L;

	/** Total no of seats contested by the party */
	private int totalSeatsContested;
	/** Total no of seats won by the party */
	private int totalSeatsWon;
	/** Total no of seats lost by the party */
	private int totalSeatsLost;
	/** Difference of seats won w.r.t previous year **/
	private int diffSeatsWon;
	/** Total percentage of votes got by the party */
	private BigDecimal totalPercentageOfVotesWon;
	/** Total percentage of votes got by the party in previous Election*/
    private BigDecimal totalPercentageOfVotesWonPreviousElection;
	/** Different positions party achieved */
	private Map<Integer, Integer> positionDistribution;
	/** Election Year **/
	private String year;
	/** State **/
	private String state;
	/** Party Name **/
	private String party;
	/** Percentage of votes that might have gone to other parties */
	private Map<String, BigDecimal> toPartySwing;
	/** Percentage Margins **/
	private List<ConstituencyPositionsVO> constituencyPositions;

	public List<ConstituencyPositionsVO> getConstituencyPositions() {
		return constituencyPositions;
	}

	public void setConstituencyPositions(
			List<ConstituencyPositionsVO> constituencyPositions) {
		this.constituencyPositions = constituencyPositions;
	}

	public BigDecimal getTotalPercentageOfVotesWonPreviousElection() {
		return totalPercentageOfVotesWonPreviousElection;
	}

	public void setTotalPercentageOfVotesWonPreviousElection(
			BigDecimal totalPercentageOfVotesWonPreviousElection) {
		this.totalPercentageOfVotesWonPreviousElection = totalPercentageOfVotesWonPreviousElection;
	}

	public int getDiffSeatsWon() {
		return diffSeatsWon;
	}

	public void setDiffSeatsWon(int diffSeatsWon) {
		this.diffSeatsWon = diffSeatsWon;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public int getTotalSeatsContested() {
		return totalSeatsContested;
	}

	public void setTotalSeatsContested(int totalSeatsContested) {
		this.totalSeatsContested = totalSeatsContested;
	}

	public int getTotalSeatsWon() {
		return totalSeatsWon;
	}

	public void setTotalSeatsWon(int totalSeatsWon) {
		this.totalSeatsWon = totalSeatsWon;
	}

	public int getTotalSeatsLost() {
		return totalSeatsLost;
	}

	public void setTotalSeatsLost(int totalSeatsLost) {
		this.totalSeatsLost = totalSeatsLost;
	}

	public BigDecimal getTotalPercentageOfVotesWon() {
		return totalPercentageOfVotesWon;
	}

	public void setTotalPercentageOfVotesWon(
			BigDecimal totalPercentageOfVotesWon) {
		this.totalPercentageOfVotesWon = totalPercentageOfVotesWon;
	}

	public Map<Integer, Integer> getPositionDistribution() {
		return positionDistribution;
	}

	public void setPositionDistribution(
			Map<Integer, Integer> positionDistribution) {
		this.positionDistribution = positionDistribution;
	}

	public Map<String, BigDecimal> getToPartySwing() {
		return toPartySwing;
	}

	public void setToPartySwing(Map<String, BigDecimal> toPartySwing) {
		this.toPartySwing = toPartySwing;
	}

}
