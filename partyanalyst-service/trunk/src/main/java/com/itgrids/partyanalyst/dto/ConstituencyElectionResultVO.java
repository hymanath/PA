/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on November 6, 2009
 */
package com.itgrids.partyanalyst.dto;

public class ConstituencyElectionResultVO {

	private String year;
	private Long candidateId;
	private Long constituencyId;
	private Long districtId;
	private String districtName;
	private String constituencyName;
	private String candidateName;
	private Long partyId;
	private String partyName;
	private Long votesEarned;
	private int rank;
	private String percentageOfVotes;
	private Boolean hasRebel;
	private CandidateWonVO rebelCandidate;
	
	private Long totalVotes;
	private Long validVotes;
	private Long totPolledVotes;
	
	private String circleName;
	private String circleZone;
	private String status;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	//getters and setters
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
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
	
	public String getPercentageOfVotes() {
		return percentageOfVotes;
	}
	public void setPercentageOfVotes(String percentageOfVotes) {
		this.percentageOfVotes = percentageOfVotes;
	}
	public CandidateWonVO getRebelCandidate() {
		return rebelCandidate;
	}
	public void setRebelCandidate(CandidateWonVO rebelCandidate) {
		this.rebelCandidate = rebelCandidate;
	}
	public Boolean getHasRebel() {
		return hasRebel;
	}
	public void setHasRebel(Boolean hasRebel) {
		this.hasRebel = hasRebel;
	}
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
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public Long getVotesEarned() {
		return votesEarned;
	}
	public void setVotesEarned(Long votesEarned) {
		this.votesEarned = votesEarned;
	}
	public Long getValidVotes() {
		return validVotes;
	}
	public void setValidVotes(Long validVotes) {
		this.validVotes = validVotes;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public Long getTotalVotes() {
		return totalVotes;
	}
	public void setTotalVotes(Long totalVotes) {
		this.totalVotes = totalVotes;
	}
	public Long getTotPolledVotes() {
		return totPolledVotes;
	}
	public void setTotPolledVotes(Long totPolledVotes) {
		this.totPolledVotes = totPolledVotes;
	}
	public String getCircleName() {
		return circleName;
	}
	public void setCircleName(String circleName) {
		this.circleName = circleName;
	}
	public String getCircleZone() {
		return circleZone;
	}
	public void setCircleZone(String circleZone) {
		this.circleZone = circleZone;
	}
}
