package com.itgrids.partyanalyst.dto;

import java.util.List;

public class DataTransferVO extends ResultStatus{

	private static final long serialVersionUID = 1L;
	private Object presentYearChart;
	private Object previousYearChart;
	private Object latestYearChart;
	private List<CandidateVO> candidateVO;
	private List<CandidateVO> connectedPeople;
	private List<CandidateVO> pendingRequests;
	private List<CandidateVO> peopleYouMayKnow;
	private List<CandidateVO> scraps;
	private List<CandidateVO> comments;
	private ResultStatus resultStatus;
	private ResultStatus resultStatusForConnectedPeople;
	private ResultStatus resultStatusForPendingRequests;
	private ResultStatus resultStatusForPeopleYouMayKnow;
	private ResultStatus resultStatusForScraps;
	private ResultStatus resultStatusForComments;
	private String loginStatus;
	private Long userId; 
	
	
	public ResultStatus getResultStatusForConnectedPeople() {
		return resultStatusForConnectedPeople;
	}

	public void setResultStatusForConnectedPeople(
			ResultStatus resultStatusForConnectedPeople) {
		this.resultStatusForConnectedPeople = resultStatusForConnectedPeople;
	}

	public ResultStatus getResultStatusForPendingRequests() {
		return resultStatusForPendingRequests;
	}

	public void setResultStatusForPendingRequests(
			ResultStatus resultStatusForPendingRequests) {
		this.resultStatusForPendingRequests = resultStatusForPendingRequests;
	}

	public ResultStatus getResultStatusForPeopleYouMayKnow() {
		return resultStatusForPeopleYouMayKnow;
	}

	public void setResultStatusForPeopleYouMayKnow(
			ResultStatus resultStatusForPeopleYouMayKnow) {
		this.resultStatusForPeopleYouMayKnow = resultStatusForPeopleYouMayKnow;
	}

	public ResultStatus getResultStatusForScraps() {
		return resultStatusForScraps;
	}

	public void setResultStatusForScraps(ResultStatus resultStatusForScraps) {
		this.resultStatusForScraps = resultStatusForScraps;
	}

	public ResultStatus getResultStatusForComments() {
		return resultStatusForComments;
	}

	public void setResultStatusForComments(ResultStatus resultStatusForComments) {
		this.resultStatusForComments = resultStatusForComments;
	}

	public List<CandidateVO> getConnectedPeople() {
		return connectedPeople;
	}

	public void setConnectedPeople(List<CandidateVO> connectedPeople) {
		this.connectedPeople = connectedPeople;
	}

	public List<CandidateVO> getPendingRequests() {
		return pendingRequests;
	}

	public void setPendingRequests(List<CandidateVO> pendingRequests) {
		this.pendingRequests = pendingRequests;
	}

	public List<CandidateVO> getPeopleYouMayKnow() {
		return peopleYouMayKnow;
	}

	public void setPeopleYouMayKnow(List<CandidateVO> peopleYouMayKnow) {
		this.peopleYouMayKnow = peopleYouMayKnow;
	}

	public List<CandidateVO> getScraps() {
		return scraps;
	}

	public void setScraps(List<CandidateVO> scraps) {
		this.scraps = scraps;
	}

	public List<CandidateVO> getComments() {
		return comments;
	}

	public void setComments(List<CandidateVO> comments) {
		this.comments = comments;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}



	public List<CandidateVO> getCandidateVO() {
		return candidateVO;
	}

	public void setCandidateVO(List<CandidateVO> candidateVO) {
		this.candidateVO = candidateVO;
	}

	public Object getPresentYearChart() {
		return presentYearChart;
	}
	
	public void setPresentYearChart(Object presentYearChart) {
		this.presentYearChart = presentYearChart;
	}
	
	public Object getPreviousYearChart() {
		return previousYearChart;
	}
	
	public void setPreviousYearChart(Object previousYearChart) {
		this.previousYearChart = previousYearChart;
	}

	public Object getLatestYearChart() {
		return latestYearChart;
	}

	public void setLatestYearChart(Object latestYearChart) {
		this.latestYearChart = latestYearChart;
	}

}
