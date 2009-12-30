package com.itgrids.partyanalyst.dto;

import java.math.BigDecimal;
import java.util.Map;

import com.itgrids.partyanalyst.BaseObject;

public class StateLevelPartyReportVO extends BaseObject {

	private static final long serialVersionUID = 1L;

	/** Total no of seats contested by the party */
	private int totalSeatsContested;
	/** Total no of seats won by the party */
	private int totalSeatsWon;
	/** Total no of seats lost by the party */
	private int totalSeatsLost;
	/** Total percentage of votes got by the party */
	private BigDecimal totalPercentageOfVotesWon;
	/** Different positions party achieved */
	private Map<Integer, Integer> positionDistribution;
	/** Position won by the party by a major percentage */
	private int positionsWonMajorBand;
	/** Position won by the party by a minor percentage */
	private int positionsWonMinorBand;
	/** Position lost by the party by a major percentage */
	private int positionsLostMajorBand;
	/** Position lost by the party by a minor percentage */
	private int positionsLostMinorBand;
	/** Percentage of votes that might have gone to other parties */
	private Map<String, BigDecimal> toPartySwing;

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

	public int getPositionsWonMajorBand() {
		return positionsWonMajorBand;
	}

	public void setPositionsWonMajorBand(int positionsWonMajorBand) {
		this.positionsWonMajorBand = positionsWonMajorBand;
	}

	public int getPositionsWonMinorBand() {
		return positionsWonMinorBand;
	}

	public void setPositionsWonMinorBand(int positionsWonMinorBand) {
		this.positionsWonMinorBand = positionsWonMinorBand;
	}

	public int getPositionsLostMajorBand() {
		return positionsLostMajorBand;
	}

	public void setPositionsLostMajorBand(int positionsLostMajorBand) {
		this.positionsLostMajorBand = positionsLostMajorBand;
	}

	public int getPositionsLostMinorBand() {
		return positionsLostMinorBand;
	}

	public void setPositionsLostMinorBand(int positionsLostMinorBand) {
		this.positionsLostMinorBand = positionsLostMinorBand;
	}

	public Map<String, BigDecimal> getToPartySwing() {
		return toPartySwing;
	}

	public void setToPartySwing(Map<String, BigDecimal> toPartySwing) {
		this.toPartySwing = toPartySwing;
	}

}
