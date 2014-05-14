package com.itgrids.partyanalyst.dto;

import java.util.List;

public class DashBoardResultsVO {
	
	private Long partyId;
	private String partyName;
	private String percent;
	private Long totalCount;
	private Long votesCount;
	private Long parliamentNo;
	private String parliamentName;
	private Long assemblyNo;
	private String assemblyName;
	private String firstRankCandidateName;
	private String secondRankCandidateName;
	private Long leadBy;
	private List<DashBoardResultsVO> electionsVotesDetails;
	private List<DashBoardResultsVO> surveyResults;
	private List<DashBoardResultsVO> leadingSummaryDetails;
	private List<DashBoardResultsVO> subReportDetails;
	private List<DashBoardResultsVO> partyMarginDetails;
	private List<DashBoardResultsVO> constituencyWiseDetails;
	
	
	private List<DashBoardResultsVO> subList;
	

	
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
}
