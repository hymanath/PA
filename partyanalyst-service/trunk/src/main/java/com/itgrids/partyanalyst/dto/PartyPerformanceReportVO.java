package com.itgrids.partyanalyst.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.BaseObject;

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
	/** Positions lost by the party by doping voting percentage */
	private int positionsLostByDroppingVotes;
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

	private int positionsWonWithPositiveSwing;
	private int positionsWonWithNegativeSwing;
	private int positionsLostWithPositiveSwing;
	private int positionsLostWithNegativeSwing;

	/** Percentage of votes that might have gone to other parties */
	private Map<String, BigDecimal> toPartySwing;
	/** Percentage Margins **/
	private Map<PositionType, List<ConstituencyPositionDetailVO>> constituencyPositionDetails;

	
	
	public BigDecimal getTotalPercentageOfVotesWonPreviousElection() {
		return totalPercentageOfVotesWonPreviousElection;
	}

	public void setTotalPercentageOfVotesWonPreviousElection(
			BigDecimal totalPercentageOfVotesWonPreviousElection) {
		this.totalPercentageOfVotesWonPreviousElection = totalPercentageOfVotesWonPreviousElection;
	}

	public Map<PositionType, List<ConstituencyPositionDetailVO>> getConstituencyPositionDetails() {
		return constituencyPositionDetails;
	}

	public void setConstituencyPositionDetails(
			Map<PositionType, List<ConstituencyPositionDetailVO>> constituencyPositionDetails) {
		this.constituencyPositionDetails = constituencyPositionDetails;
	}

	public int getPositionsWonWithPositiveSwing() {
		return positionsWonWithPositiveSwing;
	}

	public void setPositionsWonWithPositiveSwing(
			int positionsWonWithPositiveSwing) {
		this.positionsWonWithPositiveSwing = positionsWonWithPositiveSwing;
	}

	public int getPositionsWonWithNegativeSwing() {
		return positionsWonWithNegativeSwing;
	}

	public void setPositionsWonWithNegativeSwing(
			int positionsWonWithNegativeSwing) {
		this.positionsWonWithNegativeSwing = positionsWonWithNegativeSwing;
	}

	public int getPositionsLostWithPositiveSwing() {
		return positionsLostWithPositiveSwing;
	}

	public void setPositionsLostWithPositiveSwing(
			int positionsLostWithPositiveSwing) {
		this.positionsLostWithPositiveSwing = positionsLostWithPositiveSwing;
	}

	public int getPositionsLostWithNegativeSwing() {
		return positionsLostWithNegativeSwing;
	}

	public void setPositionsLostWithNegativeSwing(
			int positionsLostWithNegativeSwing) {
		this.positionsLostWithNegativeSwing = positionsLostWithNegativeSwing;
	}

	public int getDiffSeatsWon() {
		return diffSeatsWon;
	}

	public void setDiffSeatsWon(int diffSeatsWon) {
		this.diffSeatsWon = diffSeatsWon;
	}

	public int getPositionsLostByDroppingVotes() {
		return positionsLostByDroppingVotes;
	}

	public void setPositionsLostByDroppingVotes(int positionsLostByDroppingVotes) {
		this.positionsLostByDroppingVotes = positionsLostByDroppingVotes;
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

	/** Election Year **/
	private String year;
	/** State **/
	private String state;
	/** Party Name **/
	private String party;

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
