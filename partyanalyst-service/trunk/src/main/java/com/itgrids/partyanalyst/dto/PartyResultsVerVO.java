/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on September 17, 2009
 */
package com.itgrids.partyanalyst.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class PartyResultsVerVO{

	private Long partyId;
	private String partyName;
	private Long candidateId;
	private String candidateName;
	private String partyFlag;
	private int seatsParticipated;
	private Long votesEarned;
	private Long validVotes;
	private String percentage ="0.00";
	private String avgPercentage;
	private String pConstavgPercentage ="0.00";
	private BigDecimal votesPercent;
	private Integer totalSeatsWon;
	private Long totalPolledVotes;
	private Long rank;
	private List<ConstituencyWisePartyInfoVO> constituencyWisePatiesInfoVOs;
	private String ballotVotesPercentage;
	private Long ballotVotes;
	private String diffPercent;
	private String type;
	private List<PartyResultsVO> partyResultsVOList;
	private Map<String,List<Long>> locationResults;
	private Map<String,List<Long>> attributeResults;
	private Map<String,List<BigDecimal>> locationPercnts;
	private Map<String,List<BigDecimal>> attributePercnts;
	private Map<String,List<Long>> polledVotes;
	private Map<String,List<Long>> totalVotes;
	private Map<String,List<BigDecimal>> polledVotesPercnts;
	private List<String> locationNames;
	private List<CasteWiseResultVO> casteWiseResultList;
	private Long electionId;
	private Long year;
	private Long votesPolled;
	private String electionName;
	private Long otherVotes;
	private Long marginVotes;
	private String otherVotesPercent;
	private String marginPercent;
	private String location;
	private Long locationId;
	private List<PartyResultsVO> muncipalCorpResults;
	private List<PartyResultsVO> gmcResults;
	private List<PartyResultsVO> partyStrengths;
	private List<PartyResultsVO> otherPartyStrengths;
	private Long won;
	private Long participated;
	
	private Long districtId;
	// getters and setters
	
	
	public Long getVotesPolled() {
		return votesPolled;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public Long getWon() {
		return won;
	}

	public void setWon(Long won) {
		this.won = won;
	}

	public Long getParticipated() {
		return participated;
	}

	public void setParticipated(Long participated) {
		this.participated = participated;
	}

	public List<PartyResultsVO> getMuncipalCorpResults() {
		return muncipalCorpResults;
	}



	public void setMuncipalCorpResults(List<PartyResultsVO> muncipalCorpResults) {
		this.muncipalCorpResults = muncipalCorpResults;
	}



	public List<PartyResultsVO> getGmcResults() {
		return gmcResults;
	}



	public void setGmcResults(List<PartyResultsVO> gmcResults) {
		this.gmcResults = gmcResults;
	}



	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getMarginPercent() {
		return marginPercent;
	}

	public void setMarginPercent(String marginPercent) {
		this.marginPercent = marginPercent;
	}

	public String getOtherVotesPercent() {
		return otherVotesPercent;
	}

	public void setOtherVotesPercent(String otherVotesPercent) {
		this.otherVotesPercent = otherVotesPercent;
	}

	public Long getMarginVotes() {
		return marginVotes;
	}

	public void setMarginVotes(Long marginVotes) {
		this.marginVotes = marginVotes;
	}

	public Long getOtherVotes() {
		return otherVotes;
	}

	public void setOtherVotes(Long otherVotes) {
		this.otherVotes = otherVotes;
	}

	public String getElectionName() {
		return electionName;
	}

	public void setElectionName(String electionName) {
		this.electionName = electionName;
	}

	public void setVotesPolled(Long votesPolled) {
		this.votesPolled = votesPolled;
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

	public String getPartyFlag() {
		return partyFlag;
	}
	
	public void setPartyFlag(String partyFlag) {
		this.partyFlag = partyFlag;
	}
	
	public Integer getTotalSeatsWon() {
		return totalSeatsWon;
	}

	public void setTotalSeatsWon(Integer totalSeatsWon) {
		this.totalSeatsWon = totalSeatsWon;
	}

	public List<ConstituencyWisePartyInfoVO> getConstituencyWisePatiesInfoVOs() {
		return constituencyWisePatiesInfoVOs;
	}
	
	public void setConstituencyWisePatiesInfoVOs(
			List<ConstituencyWisePartyInfoVO> constituencyWisePatiesInfoVOs) {
		this.constituencyWisePatiesInfoVOs = constituencyWisePatiesInfoVOs;
	}
	
	public int getSeatsParticipated() {
		return seatsParticipated;
	}
	
	public void setSeatsParticipated(int seatsParticipated) {
		this.seatsParticipated = seatsParticipated;
	}
	
	public Long getVotesEarned() {
		return votesEarned;
	}
	
	public void setVotesEarned(Long votesEarned) {
		this.votesEarned = votesEarned;
	}
	
	public String getPercentage() {
		return percentage;
	}
	
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}
	
	public Long getTotalPolledVotes() {
		return totalPolledVotes;
	}
	
	public void setTotalPolledVotes(Long totalPolledVotes) {
		this.totalPolledVotes = totalPolledVotes;
	}
	
	public Long getCandidateId() {
		return candidateId;
	}
	
	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}
	
	public String getCandidateName() {
		return candidateName;
	}
	
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	
	public Long getValidVotes() {
		return validVotes;
	}
	
	public void setValidVotes(Long validVotes) {
		this.validVotes = validVotes;
	}
	
	public Long getRank() {
		return rank;
	}
	
	public void setRank(Long rank) {
		this.rank = rank;
	}
	
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof PartyResultsVerVO))
			return false;
		PartyResultsVerVO voObj = (PartyResultsVerVO) obj;
		return this.partyId.equals(voObj.getPartyId());
	}
	
	@Override
	public int hashCode(){
		return this.partyId.hashCode();
	}
	
	public void setBallotVotesPercentage(String ballotVotesPercentage) {
		this.ballotVotesPercentage = ballotVotesPercentage;
	}
	
	public String getBallotVotesPercentage() {
		return ballotVotesPercentage;
	}
	
	public void setBallotVotes(Long ballotVotes) {
		this.ballotVotes = ballotVotes;
	}
	
	public Long getBallotVotes() {
		return ballotVotes;
	}
	
	public String getDiffPercent() {
		return diffPercent;
	}
	
	public void setDiffPercent(String diffPercent) {
		this.diffPercent = diffPercent;
	}
	
	public BigDecimal getVotesPercent() {
		return votesPercent;
	}
	
	public void setVotesPercent(BigDecimal votesPercent) {
		this.votesPercent = votesPercent;
	}
	
	public String getAvgPercentage() {
		return avgPercentage;
	}
	
	public void setAvgPercentage(String avgPercentage) {
		this.avgPercentage = avgPercentage;
	}

	public String getPConstavgPercentage() {
		return pConstavgPercentage;
	}

	public void setPConstavgPercentage(String constavgPercentage) {
		pConstavgPercentage = constavgPercentage;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	

	public List<PartyResultsVO> getPartyResultsVOList() {
		return partyResultsVOList;
	}

	public void setPartyResultsVOList(List<PartyResultsVO> partyResultsVOList) {
		this.partyResultsVOList = partyResultsVOList;
	}

	public Map<String, List<Long>> getLocationResults() {
		return locationResults;
	}

	public void setLocationResults(Map<String, List<Long>> locationResults) {
		this.locationResults = locationResults;
	}

	public Map<String, List<Long>> getAttributeResults() {
		return attributeResults;
	}

	public void setAttributeResults(Map<String, List<Long>> attributeResults) {
		this.attributeResults = attributeResults;
	}

	public List<String> getLocationNames() {
		return locationNames;
	}

	public void setLocationNames(List<String> locationNames) {
		this.locationNames = locationNames;
	}

	public Map<String, List<BigDecimal>> getLocationPercnts() {
		return locationPercnts;
	}

	public void setLocationPercnts(Map<String, List<BigDecimal>> locationPercnts) {
		this.locationPercnts = locationPercnts;
	}

	public Map<String, List<BigDecimal>> getAttributePercnts() {
		return attributePercnts;
	}

	public void setAttributePercnts(Map<String, List<BigDecimal>> attributePercnts) {
		this.attributePercnts = attributePercnts;
	}

	public Map<String, List<Long>> getPolledVotes() {
		return polledVotes;
	}

	public void setPolledVotes(Map<String, List<Long>> polledVotes) {
		this.polledVotes = polledVotes;
	}

	public Map<String, List<Long>> getTotalVotes() {
		return totalVotes;
	}

	public void setTotalVotes(Map<String, List<Long>> totalVotes) {
		this.totalVotes = totalVotes;
	}

	public Map<String, List<BigDecimal>> getPolledVotesPercnts() {
		return polledVotesPercnts;
	}

	public void setPolledVotesPercnts(
			Map<String, List<BigDecimal>> polledVotesPercnts) {
		this.polledVotesPercnts = polledVotesPercnts;
	}

	public List<CasteWiseResultVO> getCasteWiseResultList() {
		return casteWiseResultList;
	}

	public void setCasteWiseResultList(List<CasteWiseResultVO> casteWiseResultList) {
		this.casteWiseResultList = casteWiseResultList;
	}

	public Long getElectionId() {
		return electionId;
	}

	public void setElectionId(Long electionId) {
		this.electionId = electionId;
	}

	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	public List<PartyResultsVO> getPartyStrengths() {
		return partyStrengths;
	}

	public void setPartyStrengths(List<PartyResultsVO> partyStrengths) {
		this.partyStrengths = partyStrengths;
	}

	public List<PartyResultsVO> getOtherPartyStrengths() {
		return otherPartyStrengths;
	}

	public void setOtherPartyStrengths(List<PartyResultsVO> otherPartyStrengths) {
		this.otherPartyStrengths = otherPartyStrengths;
	}
	
	
		
	
}
