package com.itgrids.partyanalyst.dto;

import java.util.List;

public class ElectionInfoVO {

	private Long electionId;
	private String electionTypeYear;
	private String constituencyName;
	private String constituencyId;
	private List<PartyResultsInfoVO> partyResults;
	private List<PartiesDetailsVO> alliancePartyDetails;
	private Long totalConstituencies;
	private Long participatedConstituencies;
	private Long totalValidVotes;
    private Double votesPercentage;
    private Double completeVotesPercentage;
    private Long totalSeatsWon;
    private String localBodyChartName;
    private String electionType;
    private String statement;
    private boolean hasOthers;
    private Long partyId;
    private String partyName;
    private Long selectedYearsCount;
    private List<SelectOptionVO> partyOverView,allianceDetails;
    private ConstituencyElectionResults requiredConstituenciesInfo;
    private ConstituencyElectionResults remainingConstituenciesInfo;
    private ConstituencyElectionResults latestConstituenciesInfo;
    
    private ConstituencyElectionResults allPartiesDetails;
    
    
    
    //Getters and Setters
    
	public List<SelectOptionVO> getAllianceDetails() {
		return allianceDetails;
	}
	public List<PartiesDetailsVO> getAlliancePartyDetails() {
		return alliancePartyDetails;
	}
	public void setAlliancePartyDetails(List<PartiesDetailsVO> alliancePartyDetails) {
		this.alliancePartyDetails = alliancePartyDetails;
	}
	public void setAllianceDetails(List<SelectOptionVO> allianceDetails) {
		this.allianceDetails = allianceDetails;
	}
	public String getStatement() {
		return statement;
	}
	public void setStatement(String statement) {
		this.statement = statement;
	}
	public boolean isHasOthers() {
		return hasOthers;
	}
	public void setHasOthers(boolean hasOthers) {
		this.hasOthers = hasOthers;
	}
	public List<SelectOptionVO> getPartyOverView() {
		return partyOverView;
	}
	public void setPartyOverView(List<SelectOptionVO> partyOverView) {
		this.partyOverView = partyOverView;
	}
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	public ConstituencyElectionResults getAllPartiesDetails() {
		return allPartiesDetails;
	}

	public void setAllPartiesDetails(ConstituencyElectionResults allPartiesDetails) {
		this.allPartiesDetails = allPartiesDetails;
	}

	public Long getSelectedYearsCount() {
		return selectedYearsCount;
	}

	public void setSelectedYearsCount(Long selectedYearsCount) {
		this.selectedYearsCount = selectedYearsCount;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public ConstituencyElectionResults getRequiredConstituenciesInfo() {
		return requiredConstituenciesInfo;
	}

	public void setRequiredConstituenciesInfo(
			ConstituencyElectionResults requiredConstituenciesInfo) {
		this.requiredConstituenciesInfo = requiredConstituenciesInfo;
	}

	public ConstituencyElectionResults getRemainingConstituenciesInfo() {
		return remainingConstituenciesInfo;
	}

	public void setRemainingConstituenciesInfo(
			ConstituencyElectionResults remainingConstituenciesInfo) {
		this.remainingConstituenciesInfo = remainingConstituenciesInfo;
	}

	public ConstituencyElectionResults getLatestConstituenciesInfo() {
		return latestConstituenciesInfo;
	}

	public void setLatestConstituenciesInfo(
			ConstituencyElectionResults latestConstituenciesInfo) {
		this.latestConstituenciesInfo = latestConstituenciesInfo;
	}

	public Long getTotalSeatsWon() {
		return totalSeatsWon;
	}

	public void setTotalSeatsWon(Long totalSeatsWon) {
		this.totalSeatsWon = totalSeatsWon;
	}

	public Double getVotesPercentage() {
		return votesPercentage;
	}

	public void setVotesPercentage(Double votesPercentage) {
		this.votesPercentage = votesPercentage;
	}

	public Double getCompleteVotesPercentage() {
		return completeVotesPercentage;
	}

	public void setCompleteVotesPercentage(Double completeVotesPercentage) {
		this.completeVotesPercentage = completeVotesPercentage;
	}

	public Long getParticipatedConstituencies() {
		return participatedConstituencies;
	}

	public void setParticipatedConstituencies(Long participatedConstituencies) {
		this.participatedConstituencies = participatedConstituencies;
	}

	public String getElectionTypeYear() {
		return electionTypeYear;
	}
	
	public void setElectionTypeYear(String electionTypeYear) {
		this.electionTypeYear = electionTypeYear;
	}
	
	public String getConstituencyName() {
		return constituencyName;
	}
	
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	
	public String getConstituencyId() {
		return constituencyId;
	}
	
	public void setConstituencyId(String constituencyId) {
		this.constituencyId = constituencyId;
	}
	
	public List<PartyResultsInfoVO> getPartyResults() {
		return partyResults;
	}
	
	public void setPartyResults(List<PartyResultsInfoVO> partyResults) {
		this.partyResults = partyResults;
	}

	public Long getTotalConstituencies() {
		return totalConstituencies;
	}

	public void setTotalConstituencies(Long totalConstituencies) {
		this.totalConstituencies = totalConstituencies;
	}

	public Long getTotalValidVotes() {
		return totalValidVotes;
	}

	public void setTotalValidVotes(Long totalValidVotes) {
		this.totalValidVotes = totalValidVotes;
	}

	public Long getElectionId() {
		return electionId;
	}
	
	public void setElectionId(Long electionId) {
		this.electionId = electionId;
	}

	public String getLocalBodyChartName() {
		return localBodyChartName;
	}

	public void setLocalBodyChartName(String localBodyChartName) {
		this.localBodyChartName = localBodyChartName;
	}
	
	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}

	public String getElectionType() {
		return electionType;
	}
	
	
}
