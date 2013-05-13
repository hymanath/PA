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

public class PartyResultsVO {

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
	private List<String> locationNames;
	
	// getters and setters
	
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
		if(!(obj instanceof PartyResultsVO))
			return false;
		PartyResultsVO voObj = (PartyResultsVO) obj;
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
	
}
