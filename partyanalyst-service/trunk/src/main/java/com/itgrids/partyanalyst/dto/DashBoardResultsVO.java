package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.itgrids.survey.soa.endpoints.SurveyReportVO;

public class DashBoardResultsVO  implements Comparable<DashBoardResultsVO>{
	
	private Long partyId;
	private String partyName;
	private String percent;
	private Long totalCount = 0L;
	private Long votesCount = 0L;
	private Long count = 0L;
	
	private Long parliamentNo;
	private String parliamentName;
	private Long assemblyNo;
	private String assemblyName;
	private String firstRankCandidateName;
	private String secondRankCandidateName;
	private Long leadBy;
	private List<DashBoardResultsVO> electionsVotesDetails = new ArrayList<DashBoardResultsVO>();
	private List<DashBoardResultsVO> surveyResults = new ArrayList<DashBoardResultsVO>();
	private List<DashBoardResultsVO> leadingSummaryDetails= new ArrayList<DashBoardResultsVO>();
	private List<DashBoardResultsVO> subReportDetails= new ArrayList<DashBoardResultsVO>();
	private List<DashBoardResultsVO> partyMarginDetails= new ArrayList<DashBoardResultsVO>();
	private List<DashBoardResultsVO> constituencyWiseDetails= new ArrayList<DashBoardResultsVO>();
	private List<DashBoardResultsVO> subList = new ArrayList<DashBoardResultsVO>();
	private Set<Long> partyIds = new HashSet<Long>();
	
	private List<DashBoardResultsVO> reservationDetails = new ArrayList<DashBoardResultsVO>();
	private List<DashBoardResultsVO> partiesDetails = new ArrayList<DashBoardResultsVO>();
	private List<Long> allianceIds;
	private String totalVotes;
	private String validVotes;
	private Long gainedVotes;
	private String gainedPercent;
	private Long rank = 0L;
	
	private Map<String,Long> partyWiseCountBefore;
	private Map<String,Long> partyWiseCountAfter;
	private Map<String,Integer> resultMap;
	private Map<String,String> percentageMap,percentageMap1;
	
	private Long surveyCount = 0L;	
	private String surveyPercent;
	private List<com.itgrids.survey.soa.endpoints.GenericVO> casteDetails;
	private String candidateName;
	private List<SurveyReportVO> surveyWiseResult;
	
	private String marginPercent;
	private String votesPercentage;
	
	
	public String getMarginPercent() {
		return marginPercent;
	}


	public void setMarginPercent(String marginPercent) {
		this.marginPercent = marginPercent;
	}


	public String getVotesPercentage() {
		return votesPercentage;
	}


	public void setVotesPercentage(String votesPercentage) {
		this.votesPercentage = votesPercentage;
	}


	public Map<String, String> getPercentageMap1() {
		return percentageMap1;
	}


	public List<SurveyReportVO> getSurveyWiseResult() {
		return surveyWiseResult;
	}


	public void setSurveyWiseResult(List<SurveyReportVO> surveyWiseResult) {
		this.surveyWiseResult = surveyWiseResult;
	}


	public void setPercentageMap1(Map<String, String> percentageMap1) {
		this.percentageMap1 = percentageMap1;
	}


	public Map<String, String> getPercentageMap() {
		return percentageMap;
	}


	public void setPercentageMap(Map<String, String> percentageMap) {
		this.percentageMap = percentageMap;
	}


	private List<GenericVO> participantsList = new ArrayList<GenericVO>();
	
	
	
	public Map<String, Integer> getResultMap() {
		return resultMap;
	}


	public void setResultMap(Map<String, Integer> resultMap) {
		this.resultMap = resultMap;
	}


	public Map<String, Long> getPartyWiseCountBefore() {
		return partyWiseCountBefore;
	}


	public void setPartyWiseCountBefore(Map<String, Long> partyWiseCountBefore) {
		this.partyWiseCountBefore = partyWiseCountBefore;
	}


	public Map<String, Long> getPartyWiseCountAfter() {
		return partyWiseCountAfter;
	}


	public void setPartyWiseCountAfter(Map<String, Long> partyWiseCountAfter) {
		this.partyWiseCountAfter = partyWiseCountAfter;
	}


	public Long getRank() {
		return rank;
	}


	public void setRank(Long rank) {
		this.rank = rank;
	}

	private List<DashBoardResultsVO> constnTypeVoterShare;
	private List<DashBoardResultsVO> reservationTypeVoterShare;

	public List<DashBoardResultsVO> getConstnTypeVoterShare() {
		return constnTypeVoterShare;
	}


	public void setConstnTypeVoterShare(
			List<DashBoardResultsVO> constnTypeVoterShare) {
		this.constnTypeVoterShare = constnTypeVoterShare;
	}


	public List<DashBoardResultsVO> getReservationTypeVoterShare() {
		return reservationTypeVoterShare;
	}


	public void setReservationTypeVoterShare(
			List<DashBoardResultsVO> reservationTypeVoterShare) {
		this.reservationTypeVoterShare = reservationTypeVoterShare;
	}


	public String getGainedPercent() {
		return gainedPercent;
	}


	public void setGainedPercent(String gainedPercent) {
		this.gainedPercent = gainedPercent;
	}


	public Long getGainedVotes() {
		return gainedVotes;
	}


	public void setGainedVotes(Long gainedVotes) {
		this.gainedVotes = gainedVotes;
	}


	


	public String getTotalVotes() {
		return totalVotes;
	}


	public void setTotalVotes(String totalVotes) {
		this.totalVotes = totalVotes;
	}


	public String getValidVotes() {
		return validVotes;
	}


	public void setValidVotes(String validVotes) {
		this.validVotes = validVotes;
	}
	
	public List<Long> getAllianceIds() {
		return allianceIds;
	}


	public void setAllianceIds(List<Long> allianceIds) {
		this.allianceIds = allianceIds;
	}
	
	private Long winCount = 0L;
	private Long leadCount = 0L;
	
	private Long winTotalCount = 0L;
	private Long leadTotalCount = 0L;
	
	
	
	public Long getWinTotalCount() {
		return winTotalCount;
	}


	public void setWinTotalCount(Long winTotalCount) {
		this.winTotalCount = winTotalCount;
	}


	public Long getLeadTotalCount() {
		return leadTotalCount;
	}


	public void setLeadTotalCount(Long leadTotalCount) {
		this.leadTotalCount = leadTotalCount;
	}


	private List<DashBoardResultsVO> summaryDetails;

	public List<DashBoardResultsVO> getSummaryDetails() {
		return summaryDetails;
	}


	public void setSummaryDetails(List<DashBoardResultsVO> summaryDetails) {
		this.summaryDetails = summaryDetails;
	}


	public Long getWinCount() {
		return winCount;
	}


	public void setWinCount(Long winCount) {
		this.winCount = winCount;
	}


	public Long getLeadCount() {
		return leadCount;
	}


	public void setLeadCount(Long leadCount) {
		this.leadCount = leadCount;
	}


	public List<DashBoardResultsVO> getPartiesDetails() {
		return partiesDetails;
	}


	public void setPartiesDetails(List<DashBoardResultsVO> partiesDetails) {
		this.partiesDetails = partiesDetails;
	}


	public List<DashBoardResultsVO> getReservationDetails() {
		return reservationDetails;
	}


	public void setReservationDetails(List<DashBoardResultsVO> reservationDetails) {
		this.reservationDetails = reservationDetails;
	}


	public Set<Long> getPartyIds() {
		return partyIds;
	}


	public void setPartyIds(Set<Long> partyIds) {
		this.partyIds = partyIds;
	}


	private Long id;
	private String name;
	private Long locationId;
	private String locationName;
	private Double percentage;
	private String year;
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	

	
	public String getYear() {
		return year;
	}


	public void setYear(String year) {
		this.year = year;
	}


	public Double getPercentage() {
		return percentage;
	}


	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}


	public Long getLocationId() {
		return locationId;
	}


	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}


	public String getLocationName() {
		return locationName;
	}


	public void setLocationName(String locationName) {
		this.locationName = locationName;
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


	public String getPercent() {
		return percent;
	}


	public void setPercent(String percent) {
		this.percent = percent;
	}


	public Long getTotalCount() {
		return totalCount;
	}


	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}


	public Long getVotesCount() {
		return votesCount;
	}


	public void setVotesCount(Long votesCount) {
		this.votesCount = votesCount;
	}


	public Long getParliamentNo() {
		return parliamentNo;
	}


	public void setParliamentNo(Long parliamentNo) {
		this.parliamentNo = parliamentNo;
	}


	public String getParliamentName() {
		return parliamentName;
	}


	public void setParliamentName(String parliamentName) {
		this.parliamentName = parliamentName;
	}


	public Long getAssemblyNo() {
		return assemblyNo;
	}


	public void setAssemblyNo(Long assemblyNo) {
		this.assemblyNo = assemblyNo;
	}


	public String getAssemblyName() {
		return assemblyName;
	}


	public void setAssemblyName(String assemblyName) {
		this.assemblyName = assemblyName;
	}


	public String getFirstRankCandidateName() {
		return firstRankCandidateName;
	}


	public void setFirstRankCandidateName(String firstRankCandidateName) {
		this.firstRankCandidateName = firstRankCandidateName;
	}


	public String getSecondRankCandidateName() {
		return secondRankCandidateName;
	}


	public void setSecondRankCandidateName(String secondRankCandidateName) {
		this.secondRankCandidateName = secondRankCandidateName;
	}


	public Long getLeadBy() {
		return leadBy;
	}


	public void setLeadBy(Long leadBy) {
		this.leadBy = leadBy;
	}


	public List<DashBoardResultsVO> getElectionsVotesDetails() {
		return electionsVotesDetails;
	}


	public void setElectionsVotesDetails(
			List<DashBoardResultsVO> electionsVotesDetails) {
		this.electionsVotesDetails = electionsVotesDetails;
	}


	public List<DashBoardResultsVO> getSurveyResults() {
		return surveyResults;
	}


	public void setSurveyResults(List<DashBoardResultsVO> surveyResults) {
		this.surveyResults = surveyResults;
	}


	public List<DashBoardResultsVO> getLeadingSummaryDetails() {
		return leadingSummaryDetails;
	}


	public void setLeadingSummaryDetails(
			List<DashBoardResultsVO> leadingSummaryDetails) {
		this.leadingSummaryDetails = leadingSummaryDetails;
	}


	public List<DashBoardResultsVO> getSubReportDetails() {
		return subReportDetails;
	}


	public void setSubReportDetails(List<DashBoardResultsVO> subReportDetails) {
		this.subReportDetails = subReportDetails;
	}


	public List<DashBoardResultsVO> getPartyMarginDetails() {
		return partyMarginDetails;
	}


	public void setPartyMarginDetails(List<DashBoardResultsVO> partyMarginDetails) {
		this.partyMarginDetails = partyMarginDetails;
	}


	public List<DashBoardResultsVO> getConstituencyWiseDetails() {
		return constituencyWiseDetails;
	}


	public void setConstituencyWiseDetails(
			List<DashBoardResultsVO> constituencyWiseDetails) {
		this.constituencyWiseDetails = constituencyWiseDetails;
	}


	public List<DashBoardResultsVO> getSubList() {
		return subList;
	}


	public void setSubList(List<DashBoardResultsVO> subList) {
		this.subList = subList;
	}
	
	public Long getCount() {
		return count;
	}


	public void setCount(Long count) {
		this.count = count;
	}


	public int compareTo(DashBoardResultsVO vo) {
		
		if(this.parliamentNo < vo.getParliamentNo())
			return -1;
		else if(this.parliamentNo > vo.getParliamentNo())
			return 1;
		
		return 0;
	}


	public List<GenericVO> getParticipantsList() {
		return participantsList;
	}


	public void setParticipantsList(List<GenericVO> participantsList) {
		this.participantsList = participantsList;
	}
	
	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	
	public List<com.itgrids.survey.soa.endpoints.GenericVO> getCasteDetails() {
		return casteDetails;
	}

	public void setCasteDetails(List<com.itgrids.survey.soa.endpoints.GenericVO> list) {
		this.casteDetails = list;
	}
	
	public Long getSurveyCount() {
		return surveyCount;
	}

	public void setSurveyCount(Long surveyCount) {
		this.surveyCount = surveyCount;
	}

	public String getSurveyPercent() {
		return surveyPercent;
	}

	public void setSurveyPercent(String surveyPercent) {
		this.surveyPercent = surveyPercent;
	}


}
