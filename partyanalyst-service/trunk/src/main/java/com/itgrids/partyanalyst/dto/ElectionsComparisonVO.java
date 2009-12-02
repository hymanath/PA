/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on October 20, 2009
 */
package com.itgrids.partyanalyst.dto;

import java.util.List;

public class ElectionsComparisonVO {

	private String firstYear;
	private String secondYear;
	private String partyName;
	private int votesGainedCount;
	private int votesLostCount;
	private int count;
	private int seatsWonInFirstYearForVotesGained;
	private int seatsWonInSecondYearForVotesGained;
	private int seatsLostInFirstYearForVotesGained;
	private int seatsLostInSecondYearForVotesGained;
	private List<ElectionComparisonResultVO> votesGained;
	private int seatsWonInFirstYearForVotesLost;
	private int seatsWonInSecondYearForVotesLost;
	private int seatsLostInFirstYearForVotesLost;
	private int seatsLostInSecondYearForVotesLost;
	private List<ElectionComparisonResultVO> votesLost;
	private List<ElectionComparisonResultVO> constituenciesNotConsidered;
	public int getVotesGainedCount() {
		return votesGainedCount;
	}
	public void setVotesGainedCount(int votesGainedCount) {
		this.votesGainedCount = votesGainedCount;
	}
	public int getVotesLostCount() {
		return votesLostCount;
	}
	public void setVotesLostCount(int votesLostCount) {
		this.votesLostCount = votesLostCount;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
	//getters and setters
	public String getFirstYear() {
		return firstYear;
	}
	public void setFirstYear(String firstYear) {
		this.firstYear = firstYear;
	}
	public String getSecondYear() {
		return secondYear;
	}
	public void setSecondYear(String secondYear) {
		this.secondYear = secondYear;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	
	public List<ElectionComparisonResultVO> getVotesGained() {
		return votesGained;
	}
	public void setVotesGained(List<ElectionComparisonResultVO> votesGained) {
		this.votesGained = votesGained;
	}
	public List<ElectionComparisonResultVO> getVotesLost() {
		return votesLost;
	}
	public void setVotesLost(List<ElectionComparisonResultVO> votesLost) {
		this.votesLost = votesLost;
	}
	public List<ElectionComparisonResultVO> getConstituenciesNotConsidered() {
		return constituenciesNotConsidered;
	}
	public void setConstituenciesNotConsidered(
			List<ElectionComparisonResultVO> constituenciesNotConsidered) {
		this.constituenciesNotConsidered = constituenciesNotConsidered;
	}
	public int getSeatsWonInFirstYearForVotesGained() {
		return seatsWonInFirstYearForVotesGained;
	}
	public void setSeatsWonInFirstYearForVotesGained(
			int seatsWonInFirstYearForVotesGained) {
		this.seatsWonInFirstYearForVotesGained = seatsWonInFirstYearForVotesGained;
	}
	public int getSeatsWonInSecondYearForVotesGained() {
		return seatsWonInSecondYearForVotesGained;
	}
	public void setSeatsWonInSecondYearForVotesGained(
			int seatsWonInSecondYearForVotesGained) {
		this.seatsWonInSecondYearForVotesGained = seatsWonInSecondYearForVotesGained;
	}
	public int getSeatsWonInFirstYearForVotesLost() {
		return seatsWonInFirstYearForVotesLost;
	}
	public int getSeatsLostInFirstYearForVotesGained() {
		return seatsLostInFirstYearForVotesGained;
	}
	public void setSeatsLostInFirstYearForVotesGained(
			int seatsLostInFirstYearForVotesGained) {
		this.seatsLostInFirstYearForVotesGained = seatsLostInFirstYearForVotesGained;
	}
	public int getSeatsLostInSecondYearForVotesGained() {
		return seatsLostInSecondYearForVotesGained;
	}
	public void setSeatsLostInSecondYearForVotesGained(
			int seatsLostInSecondYearForVotesGained) {
		this.seatsLostInSecondYearForVotesGained = seatsLostInSecondYearForVotesGained;
	}
	public void setSeatsWonInFirstYearForVotesLost(
			int seatsWonInFirstYearForVotesLost) {
		this.seatsWonInFirstYearForVotesLost = seatsWonInFirstYearForVotesLost;
	}
	public int getSeatsWonInSecondYearForVotesLost() {
		return seatsWonInSecondYearForVotesLost;
	}
	public void setSeatsWonInSecondYearForVotesLost(
			int seatsWonInSecondYearForVotesLost) {
		this.seatsWonInSecondYearForVotesLost = seatsWonInSecondYearForVotesLost;
	}
	public int getSeatsLostInFirstYearForVotesLost() {
		return seatsLostInFirstYearForVotesLost;
	}
	public void setSeatsLostInFirstYearForVotesLost(
			int seatsLostInFirstYearForVotesLost) {
		this.seatsLostInFirstYearForVotesLost = seatsLostInFirstYearForVotesLost;
	}
	public int getSeatsLostInSecondYearForVotesLost() {
		return seatsLostInSecondYearForVotesLost;
	}
	public void setSeatsLostInSecondYearForVotesLost(
			int seatsLostInSecondYearForVotesLost) {
		this.seatsLostInSecondYearForVotesLost = seatsLostInSecondYearForVotesLost;
	}
}
