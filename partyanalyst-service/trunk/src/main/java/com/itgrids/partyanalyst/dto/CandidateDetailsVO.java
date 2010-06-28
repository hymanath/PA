/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on October 2, 2009
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;


public class CandidateDetailsVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long candidateId;
	private String candidateName;
	private Long age;
	private String gender;
	private String partyName;
	private Long electionId;
	private String electionType;
	private String electionYear;
	private String constituencyName;
	private Long constituencyId;
	private String districtName;
	private String stateName;
	private Long rank;
	private String votesEarned;
	private Long candidateVotesEarned;
	private String votesPercentage;
	private Boolean status;
	private String image;
	private String result;
	private String partyFlag;
	private Long commentsCount;
	private List<CandidateOppositionVO> oppositionCandidates;
	private List<SelectOptionVO> latestConstituencies ;
	private List<SelectOptionVO> getAllStates ;
	private List<SelectOptionVO> getDistricts ;
	private List<CandidateDetailsVO> candidateDetails ;
	private Long dataAvailabilityFlag;
	private String moreDetails;
	private Float votesDifference;
	private String marginVotesPercentage;
	private MandalAllElectionDetailsVO mandalAllElectionDetailsVO;
	private Long currentRowNo;
	private String localBodyElectionsConstituencyName;
	
	//getters and setters
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public Long getDataAvailabilityFlag() {
		return dataAvailabilityFlag;
	}
	public void setDataAvailabilityFlag(Long dataAvailabilityFlag) {
		this.dataAvailabilityFlag = dataAvailabilityFlag;
	}
	public List<CandidateDetailsVO> getCandidateDetails() {
		return candidateDetails;
	}
	public void setCandidateDetails(List<CandidateDetailsVO> candidateDetails) {
		this.candidateDetails = candidateDetails;
	}
	public String getResult() {
		return result;
	}
	public List<SelectOptionVO> getLatestConstituencies() {
		return latestConstituencies;
	}
	public void setLatestConstituencies(List<SelectOptionVO> latestConstituencies) {
		this.latestConstituencies = latestConstituencies;
	}
	public List<SelectOptionVO> getGetAllStates() {
		return getAllStates;
	}
	public void setGetAllStates(List<SelectOptionVO> getAllStates) {
		this.getAllStates = getAllStates;
	}
	public void setResult(String result) {
		this.result = result;
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
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public Long getElectionId() {
		return electionId;
	}
	public void setElectionId(Long electionId) {
		this.electionId = electionId;
	}
	public String getElectionType() {
		return electionType;
	}
	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}
	public String getElectionYear() {
		return electionYear;
	}
	public void setElectionYear(String electionYear) {
		this.electionYear = electionYear;
	}
	public String getPartyFlag() {
		return partyFlag;
	}
	public void setPartyFlag(String partyFlag) {
		this.partyFlag = partyFlag;
	}
	public String getConstituencyName() {
		return constituencyName;
	}
	public String getMarginVotesPercentage() {
		return marginVotesPercentage;
	}
	public void setMarginVotesPercentage(String marginVotesPercentage) {
		this.marginVotesPercentage = marginVotesPercentage;
	}
	public MandalAllElectionDetailsVO getMandalAllElectionDetailsVO() {
		return mandalAllElectionDetailsVO;
	}
	public void setMandalAllElectionDetailsVO(
			MandalAllElectionDetailsVO mandalAllElectionDetailsVO) {
		this.mandalAllElectionDetailsVO = mandalAllElectionDetailsVO;
	}
	public String getMoreDetails() {
		return moreDetails;
	}
	public void setMoreDetails(String moreDetails) {
		this.moreDetails = moreDetails;
	}
	public Float getVotesDifference() {
		return votesDifference;
	}
	public void setVotesDifference(Float votesDifference) {
		this.votesDifference = votesDifference;
	}
	public List<SelectOptionVO> getGetDistricts() {
		return getDistricts;
	}
	public void setGetDistricts(List<SelectOptionVO> getDistricts) {
		this.getDistricts = getDistricts;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public Long getRank() {
		return rank;
	}
	public void setRank(Long rank) {
		this.rank = rank;
	}
	public String getVotesEarned() {
		return votesEarned;
	}
	public void setVotesEarned(String votesEarned) {
		this.votesEarned = votesEarned;
	}
	public String getVotesPercentage() {
		return votesPercentage;
	}
	public void setVotesPercentage(String votesPercentage) {
		this.votesPercentage = votesPercentage;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public List<CandidateOppositionVO> getOppositionCandidates() {
		return oppositionCandidates;
	}
	public void setOppositionCandidates(
			List<CandidateOppositionVO> oppositionCandidates) {
		this.oppositionCandidates = oppositionCandidates;
	}
	public Long getAge() {
		return age;
	}
	public void setAge(Long age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Long getCandidateVotesEarned() {
		return candidateVotesEarned;
	}
	public void setCandidateVotesEarned(Long candidateVotesEarned) {
		this.candidateVotesEarned = candidateVotesEarned;
	}
	public Long getCurrentRowNo() {
		return currentRowNo;
	}
	public void setCurrentRowNo(Long currentRowNo) {
		this.currentRowNo = currentRowNo;
	}
		
	public Long getCommentsCount() {
		return commentsCount;
	}
	public void setCommentsCount(Long commentsCount) {
		this.commentsCount = commentsCount;
	}
	public String getLocalBodyElectionsConstituencyName() {
		return localBodyElectionsConstituencyName;
	}
	public void setLocalBodyElectionsConstituencyName(
			String localBodyElectionsConstituencyName) {
		this.localBodyElectionsConstituencyName = localBodyElectionsConstituencyName;
	}
	
}
