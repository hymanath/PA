package com.itgrids.partyanalyst.dto;

import java.util.List;

public class ConstituencyRevenueVillagesVO extends ResultStatus{

	private Long totalVoters;
	private Long constituencyId;
	private String constituencyName;
	private String electionType;
	private String electionYear;
	private String areaType;
	private List<CandidatePartyInfoVO> candidateNamePartyAndStatus;
	private List<RevenueVillageElectionVO> revenueVillageElectionVO;
	private List<ConstituencyOrMandalWiseElectionVO> constituencyOrMandalWiseElectionVO;
	private String chartPath;
	private String detailedChartPath;
	private List<SelectOptionVO> elections;
	private Boolean isDataInsufficient;
	private List<SelectOptionVO> missingConstituencies;
	private Long postalBaletAndMissingConstiVotes;
	private List<ElectionWiseMandalPartyResultVO>  electionInfoByLocations;
	private List<CensusVO> censusVO;
	
	
	public List<CensusVO> getCensusVO() {
		return censusVO;
	}

	public void setCensusVO(List<CensusVO> censusVO) {
		this.censusVO = censusVO;
	}

	public List<ConstituencyOrMandalWiseElectionVO> getConstituencyOrMandalWiseElectionVO() {
		return constituencyOrMandalWiseElectionVO;
	}

	public void setConstituencyOrMandalWiseElectionVO(
			List<ConstituencyOrMandalWiseElectionVO> constituencyOrMandalWiseElectionVO) {
		this.constituencyOrMandalWiseElectionVO = constituencyOrMandalWiseElectionVO;
	}

	public String getDetailedChartPath() {
		return detailedChartPath;
	}

	public void setDetailedChartPath(String detailedChartPath) {
		this.detailedChartPath = detailedChartPath;
	}

	public Long getTotalVoters() {
		return totalVoters;
	}

	public void setTotalVoters(Long totalVoters) {
		this.totalVoters = totalVoters;
	}

	public List<CandidatePartyInfoVO> getCandidateNamePartyAndStatus() {
		return candidateNamePartyAndStatus;
	}

	public void setCandidateNamePartyAndStatus(
			List<CandidatePartyInfoVO> candidateNamePartyAndStatus) {
		this.candidateNamePartyAndStatus = candidateNamePartyAndStatus;
	}

	public List<RevenueVillageElectionVO> getRevenueVillageElectionVO() {
		return revenueVillageElectionVO;
	}

	public void setRevenueVillageElectionVO(
			List<RevenueVillageElectionVO> revenueVillageElectionVO) {
		this.revenueVillageElectionVO = revenueVillageElectionVO;
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

	public String getChartPath() {
		return chartPath;
	}

	public void setChartPath(String chartPath) {
		this.chartPath = chartPath;
	}

	public List<SelectOptionVO> getElections() {
		return elections;
	}

	public void setElections(List<SelectOptionVO> elections) {
		this.elections = elections;
	}

	public String getElectionType() {
		return electionType;
	}

	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}

	public Boolean getIsDataInsufficient() {
		return isDataInsufficient;
	}

	public void setIsDataInsufficient(Boolean isDataInsufficient) {
		this.isDataInsufficient = isDataInsufficient;
	}

	public List<SelectOptionVO> getMissingConstituencies() {
		return missingConstituencies;
	}

	public void setMissingConstituencies(List<SelectOptionVO> missingConstituencies) {
		this.missingConstituencies = missingConstituencies;
	}

	public Long getPostalBaletAndMissingConstiVotes() {
		return postalBaletAndMissingConstiVotes;
	}

	public void setPostalBaletAndMissingConstiVotes(
			Long postalBaletAndMissingConstiVotes) {
		this.postalBaletAndMissingConstiVotes = postalBaletAndMissingConstiVotes;
	}

	public List<ElectionWiseMandalPartyResultVO> getElectionInfoByLocations() {
		return electionInfoByLocations;
	}

	public void setElectionInfoByLocations(
			List<ElectionWiseMandalPartyResultVO> electionInfoByLocations) {
		this.electionInfoByLocations = electionInfoByLocations;
	}

	public String getElectionYear() {
		return electionYear;
	}

	public void setElectionYear(String electionYear) {
		this.electionYear = electionYear;
	}

	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof ConstituencyRevenueVillagesVO))
			return false;
		ConstituencyRevenueVillagesVO voObj = (ConstituencyRevenueVillagesVO) obj;
		return this.constituencyId.equals(voObj.getConstituencyId());
	}
	
	@Override
	public int hashCode(){
		return this.constituencyId.hashCode();
	}

	public String getAreaType() {
		return areaType;
	}

	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}
	
}
