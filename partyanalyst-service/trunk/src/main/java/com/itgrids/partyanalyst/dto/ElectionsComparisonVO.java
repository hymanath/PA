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
	private int constiNotConstiCountForYearOne;
	private int constiNotConstiCountForYearTwo;
	private int seatsWonInFirstYearForVotesGained;
	private int seatsWonInSecondYearForVotesGained;
	private int seatsLostInFirstYearForVotesGained;
	private int seatsLostInSecondYearForVotesGained;
	private List<ElectionComparisonResultVO> votesGained;
	private int seatsWonInFirstYearForVotesLost;
	private int seatsWonInSecondYearForVotesLost;
	private int seatsLostInFirstYearForVotesLost;
	private int seatsLostInSecondYearForVotesLost;
	private int constiNotConsideredForYearOneSeatsWon = 0;
	private int constiNotConsideredForYearOneSeatsLost = 0;
	private int constiNotConsideredForYearTwoSeatsWon = 0;
	private int constiNotConsideredForYearTwoSeatsLost = 0;
	private List<ElectionComparisonResultVO> votesLost;
	private List<ElectionComparisonResultVO> constituenciesNotConsidered;
	private List<ElectionComparisonResultVO> constituenciesNotConsideredForYearOne;
	private List<ElectionComparisonResultVO> constituenciesNotConsideredForYearTwo;
	private List<PartyElectionResultsVO> constituenciesNotConsideredInFirstYear;
	private List<PartyElectionResultsVO> constituenciesNotConsideredInSecondYear;
	private int constiNotParticipatedForYearOne;
	private int constiNotParticipatedForYearTwo;
	
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
	public List<PartyElectionResultsVO> getConstituenciesNotConsideredInFirstYear() {
		return constituenciesNotConsideredInFirstYear;
	}
	public void setConstituenciesNotConsideredInFirstYear(
			List<PartyElectionResultsVO> constituenciesNotConsideredInFirstYear) {
		this.constituenciesNotConsideredInFirstYear = constituenciesNotConsideredInFirstYear;
	}
	public List<PartyElectionResultsVO> getConstituenciesNotConsideredInSecondYear() {
		return constituenciesNotConsideredInSecondYear;
	}
	public void setConstituenciesNotConsideredInSecondYear(
			List<PartyElectionResultsVO> constituenciesNotConsideredInSecondYear) {
		this.constituenciesNotConsideredInSecondYear = constituenciesNotConsideredInSecondYear;
	}
	public List<ElectionComparisonResultVO> getConstituenciesNotConsideredForYearOne() {
		return constituenciesNotConsideredForYearOne;
	}
	public void setConstituenciesNotConsideredForYearOne(
			List<ElectionComparisonResultVO> constituenciesNotConsideredForYearOne) {
		this.constituenciesNotConsideredForYearOne = constituenciesNotConsideredForYearOne;
	}
	public List<ElectionComparisonResultVO> getConstituenciesNotConsideredForYearTwo() {
		return constituenciesNotConsideredForYearTwo;
	}
	public void setConstituenciesNotConsideredForYearTwo(
			List<ElectionComparisonResultVO> constituenciesNotConsideredForYearTwo) {
		this.constituenciesNotConsideredForYearTwo = constituenciesNotConsideredForYearTwo;
	}
	public int getConstiNotConstiCountForYearOne() {
		return constiNotConstiCountForYearOne;
	}
	public void setConstiNotConstiCountForYearOne(int constiNotConstiCountForYearOne) {
		this.constiNotConstiCountForYearOne = constiNotConstiCountForYearOne;
	}
	public int getConstiNotConstiCountForYearTwo() {
		return constiNotConstiCountForYearTwo;
	}
	public void setConstiNotConstiCountForYearTwo(int constiNotConstiCountForYearTwo) {
		this.constiNotConstiCountForYearTwo = constiNotConstiCountForYearTwo;
	}
	public int getConstiNotParticipatedForYearOne() {
		return constiNotParticipatedForYearOne;
	}
	public void setConstiNotParticipatedForYearOne(
			int constiNotParticipatedForYearOne) {
		this.constiNotParticipatedForYearOne = constiNotParticipatedForYearOne;
	}
	public int getConstiNotParticipatedForYearTwo() {
		return constiNotParticipatedForYearTwo;
	}
	public void setConstiNotParticipatedForYearTwo(
			int constiNotParticipatedForYearTwo) {
		this.constiNotParticipatedForYearTwo = constiNotParticipatedForYearTwo;
	}
	public int getConstiNotConsideredForYearOneSeatsWon() {
		return constiNotConsideredForYearOneSeatsWon;
	}
	public void setConstiNotConsideredForYearOneSeatsWon(
			int constiNotConsideredForYearOneSeatsWon) {
		this.constiNotConsideredForYearOneSeatsWon = constiNotConsideredForYearOneSeatsWon;
	}
	public int getConstiNotConsideredForYearOneSeatsLost() {
		return constiNotConsideredForYearOneSeatsLost;
	}
	public void setConstiNotConsideredForYearOneSeatsLost(
			int constiNotConsideredForYearOneSeatsLost) {
		this.constiNotConsideredForYearOneSeatsLost = constiNotConsideredForYearOneSeatsLost;
	}
	public int getConstiNotConsideredForYearTwoSeatsWon() {
		return constiNotConsideredForYearTwoSeatsWon;
	}
	public void setConstiNotConsideredForYearTwoSeatsWon(
			int constiNotConsideredForYearTwoSeatsWon) {
		this.constiNotConsideredForYearTwoSeatsWon = constiNotConsideredForYearTwoSeatsWon;
	}
	public int getConstiNotConsideredForYearTwoSeatsLost() {
		return constiNotConsideredForYearTwoSeatsLost;
	}
	public void setConstiNotConsideredForYearTwoSeatsLost(
			int constiNotConsideredForYearTwoSeatsLost) {
		this.constiNotConsideredForYearTwoSeatsLost = constiNotConsideredForYearTwoSeatsLost;
	}
}
