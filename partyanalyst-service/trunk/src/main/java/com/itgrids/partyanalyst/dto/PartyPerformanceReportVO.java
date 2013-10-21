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
import java.util.SortedMap;

import com.itgrids.partyanalyst.BaseObject;
import com.itgrids.partyanalyst.model.Party;

public class PartyPerformanceReportVO extends BaseObject {

	private static final long serialVersionUID = 1L;

	/** Total no of seats contested by the party */
	private int totalSeatsContested;
	/** Total no of seats won by the party */
	private int totalSeatsWon;
	/** Total no of seats won by the party for previous year*/
	private int prevYearTotalSeatsWon;
	/** Total no of seats lost by the party */
	private int totalSeatsLost;
	/** Difference of seats won w.r.t previous year **/
	private int diffSeatsWon;
	/** Total percentage of votes got by the party */
	private BigDecimal totalPercentageOfVotesWon;
	/** Total percentage of votes got by the party for previous year*/
	private BigDecimal prevYeartotalPercentageOfVotesWon;
	/** Total percentage of votes got by the party in previous Election*/
    private BigDecimal totalPercentageOfVotesWonPreviousElection;
	/** Different positions party achieved */
	private SortedMap<String, Integer> positionDistribution;
	/** Election Year **/
	private String year;
	/** Previous Election Year **/
	private String prevYear;
	/** State **/
	private String state;
	/** Party Name **/
	private String party;
	/** Percentage of votes that might have gone to other parties */
	private Map<String, String> toPartySwing;
	/** Percentage Margins **/
	private List<ConstituencyPositionsVO> constituencyPositions;
	
	/** all reports available for the party performance report added **/
	private Map<PositionType, List<ConstituencyPositionDetailVO>> reportsInfo;
	
	/**Total win seats differences between present and previous Elections **/
	private int differenceOfTotalWinWithPrevElection;
	
	/**Differences of total percentage of seats win between present and previous Elections **/
	private BigDecimal diffOfTotalPercentageWinWithPrevElection;
	
	/** total no. of party postions w.r.t 2nd, 3rd, 4th and nth positions **/
	//private int[] partyPositions;
	
	/** percentage of votes flown to other parties -- difference with previous year **/
	private Map<String, String> partyVotesFlown;
	private Map<String, BigDecimal> votesFlown;
	private List<Party> allianceParties;
	private List<Party> previousYearAllianceParties;
	private List<ConstituencyPositionDetailVO> rebelPartyCandidates;
	private List<ConstituencyPositionDetailVO> partyWinners;
	private List<ConstituencyPositionDetailVO> partyLosers;
	private String district;
	private Long electionTypeId;
	private Long electionId;
	private Long stateId;
	private Long partyId;
	private Long districtId;
	private Boolean hasAlliances;
	private String reportLevel;
	private Boolean reportSuccessOrFailure;
	
	private BigDecimal totalPercentageOfVotesWon1;
	

	public BigDecimal getTotalPercentageOfVotesWon1() {
		return totalPercentageOfVotesWon1;
	}

	public void setTotalPercentageOfVotesWon1(BigDecimal totalPercentageOfVotesWon1) {
		this.totalPercentageOfVotesWon1 = totalPercentageOfVotesWon1;
	}

	/** party positions in an election,like first pos ,second pos.... **/
	private List<PartyPositionsVO> partyPositionsVO;
		
	public List<ConstituencyPositionDetailVO> getPartyWinners() {
		return partyWinners;
	}

	public void setPartyWinners(List<ConstituencyPositionDetailVO> partyWinners) {
		this.partyWinners = partyWinners;
	}

	public List<ConstituencyPositionDetailVO> getPartyLosers() {
		return partyLosers;
	}

	public void setPartyLosers(List<ConstituencyPositionDetailVO> partyLosers) {
		this.partyLosers = partyLosers;
	}

	public List<ConstituencyPositionDetailVO> getRebelPartyCandidates() {
		return rebelPartyCandidates;
	}

	public void setRebelPartyCandidates(
			List<ConstituencyPositionDetailVO> rebels) {
		this.rebelPartyCandidates = rebels;
	}

	public Map<String, BigDecimal> getVotesFlown() {
		return votesFlown;
	}

	public void setVotesFlown(Map<String, BigDecimal> votesFlown) {
		this.votesFlown = votesFlown;
	}
	
	public List<Party> getAllianceParties() {
		return allianceParties;
	}

	public void setAllianceParties(List<Party> allianceParties) {
		this.allianceParties = allianceParties;
	}

	public Map<String, String> getPartyVotesFlown(){
		return partyVotesFlown;
	}
	
	public void setPartyVotesFlown(Map<String, String> partyVotesFlown){
		this.partyVotesFlown = partyVotesFlown;
	}
	
	/*public int[] getPartyPositions(){
		return partyPositions;
	}
	
	public void setPartyPositions(int[] partyPositions){
		this.partyPositions = partyPositions;
	}*/
	
	public int getDifferenceOfTotalWinWithPrevElection(){
		return differenceOfTotalWinWithPrevElection;
	}
	
	public void setDifferenceOfTotalWinWithPrevElection(int differenceOfTotalWinWithPrevElection){
		this.differenceOfTotalWinWithPrevElection = differenceOfTotalWinWithPrevElection;
	}
	
	public BigDecimal getDiffOfTotalPercentageWinWithPrevElection(){
		return diffOfTotalPercentageWinWithPrevElection;
	}
	
	public Map<PositionType, List<ConstituencyPositionDetailVO>> getReportsInfo(){
		return reportsInfo;
	}

	public void setReportsInfo(Map<PositionType, List<ConstituencyPositionDetailVO>> reportsInfo){
		this.reportsInfo = reportsInfo;
	}
	
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

	public SortedMap<String, Integer> getPositionDistribution() {
		return positionDistribution;
	}

	public void setPositionDistribution(
			SortedMap<String, Integer> positionDistribution) {
		this.positionDistribution = positionDistribution;
	}

	/**
	 * @param diffOfTotalPercentageWinWithPrevElection the diffOfTotalPercentageWinWithPrevElection to set
	 */
	public void setDiffOfTotalPercentageWinWithPrevElection(
			BigDecimal diffOfTotalPercentageWinWithPrevElection) {
		this.diffOfTotalPercentageWinWithPrevElection = diffOfTotalPercentageWinWithPrevElection;
	}
	public Map<String, String> getToPartySwing() {
		return toPartySwing;
	}

	public void setToPartySwing(Map<String, String> toPartySwing) {
		this.toPartySwing = toPartySwing;
	}

	public String getDistrict(){
		return district;
	}
	
	public void setDistrict(String districtName) {
		this.district = districtName;
	}

	public Long getElectionTypeId() {
		return electionTypeId;
	}

	public void setElectionTypeId(Long electionTypeId) {
		this.electionTypeId = electionTypeId;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public Long getPartyId() {
		return partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public Boolean getHasAlliances() {
		return hasAlliances;
	}

	public void setHasAlliances(Boolean hasAlliances) {
		this.hasAlliances = hasAlliances;
	}

	public String getPrevYear() {
		return prevYear;
	}

	public void setPrevYear(String prevYear) {
		this.prevYear = prevYear;
	}

	public int getPrevYearTotalSeatsWon() {
		return prevYearTotalSeatsWon;
	}

	public void setPrevYearTotalSeatsWon(int prevYearTotalSeatsWon) {
		this.prevYearTotalSeatsWon = prevYearTotalSeatsWon;
	}

	public BigDecimal getPrevYeartotalPercentageOfVotesWon() {
		return prevYeartotalPercentageOfVotesWon;
	}

	public void setPrevYeartotalPercentageOfVotesWon(
			BigDecimal prevYeartotalPercentageOfVotesWon) {
		this.prevYeartotalPercentageOfVotesWon = prevYeartotalPercentageOfVotesWon;
	}

	public String getReportLevel() {
		return reportLevel;
	}

	public void setReportLevel(String reportLevel) {
		this.reportLevel = reportLevel;
	}

	public List<PartyPositionsVO> getPartyPositionsVO() {
		return partyPositionsVO;
	}

	public void setPartyPositionsVO(List<PartyPositionsVO> partyPositionsVO) {
		this.partyPositionsVO = partyPositionsVO;
	}

	public Long getElectionId() {
		return electionId;
	}

	public void setElectionId(Long electionId) {
		this.electionId = electionId;
	}

	public List<Party> getPreviousYearAllianceParties() {
		return previousYearAllianceParties;
	}

	public void setPreviousYearAllianceParties(
			List<Party> previousYearAllianceParties) {
		this.previousYearAllianceParties = previousYearAllianceParties;
	}

	public Boolean getReportSuccessOrFailure() {
		return reportSuccessOrFailure;
	}

	public void setReportSuccessOrFailure(Boolean reportSuccessOrFailure) {
		this.reportSuccessOrFailure = reportSuccessOrFailure;
	}

	
}
