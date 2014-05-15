package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DashBoardResultsVO {
	
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

	
	
	public List<Long> getAllianceIds() {
		return allianceIds;
	}


	public void setAllianceIds(List<Long> allianceIds) {
		this.allianceIds = allianceIds;
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


}
