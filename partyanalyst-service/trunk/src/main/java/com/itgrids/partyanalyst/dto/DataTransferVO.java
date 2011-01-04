package com.itgrids.partyanalyst.dto;

import java.util.List;

public class DataTransferVO extends ResultStatus{

	private static final long serialVersionUID = 1L;
	private Object presentYearChart;
	private Object previousYearChart;
	private Object latestYearChart;
	private Long unreadMsgCount;
	private Long totalMsgCount;
	private List<CandidateVO> candidateVO;
	private List<CandidateVO> connectedPeople;
	private List<CandidateVO> pendingRequests;
	private List<CandidateVO> peopleYouMayKnow;
	private List<CandidateVO> scraps;
	private List<CandidateVO> comments;
	private List<CandidateVO> friendRequest;
	private ResultStatus resultStatus;
	private ResultStatus resultStatusForConnectedPeople;
	private ResultStatus resultStatusForPendingRequests;
	private ResultStatus resultStatusForPeopleYouMayKnow;
	private ResultStatus resultStatusForScraps;
	private ResultStatus resultStatusForComments;
	private ResultStatus resultStatusForFriendRequest;
	private ResultStatus resultStatusForBlockRequest;
	private ResultStatus resultStatusForUnBlockRequest;
	private String loginStatus;
	private Long userId;
	private List<SelectOptionVO> seleList;
	private Long stateId;
	private Long districtId;
	private Long constituencyId;
	private String stateName;
	private String districtName;
	private String constituencyName;	
	private String districtUsersCount;
	private String constituencyUsersCount;
	private String totalResultsCount;
	private String connectedPeopleCount;
	
	
	public String getConnectedPeopleCount() {
		return connectedPeopleCount;
	}

	public void setConnectedPeopleCount(String connectedPeopleCount) {
		this.connectedPeopleCount = connectedPeopleCount;
	}

	public String getTotalResultsCount() {
		return totalResultsCount;
	}

	public void setTotalResultsCount(String totalResultsCount) {
		this.totalResultsCount = totalResultsCount;
	}

	public String getDistrictUsersCount() {
		return districtUsersCount;
	}

	public void setDistrictUsersCount(String districtUsersCount) {
		this.districtUsersCount = districtUsersCount;
	}

	public String getConstituencyUsersCount() {
		return constituencyUsersCount;
	}

	public void setConstituencyUsersCount(String constituencyUsersCount) {
		this.constituencyUsersCount = constituencyUsersCount;
	}
	
	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public Long getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getConstituencyName() {
		return constituencyName;
	}

	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	
	public List<SelectOptionVO> getSeleList() {
		return seleList;
	}

	public void setSeleList(List<SelectOptionVO> seleList) {
		this.seleList = seleList;
	}

	public ResultStatus getResultStatusForBlockRequest() {
		return resultStatusForBlockRequest;
	}

	public void setResultStatusForBlockRequest(
			ResultStatus resultStatusForBlockRequest) {
		this.resultStatusForBlockRequest = resultStatusForBlockRequest;
	}

	public ResultStatus getResultStatusForUnBlockRequest() {
		return resultStatusForUnBlockRequest;
	}

	public void setResultStatusForUnBlockRequest(
			ResultStatus resultStatusForUnBlockRequest) {
		this.resultStatusForUnBlockRequest = resultStatusForUnBlockRequest;
	}

	public List<CandidateVO> getFriendRequest() {
		return friendRequest;
	}

	public void setFriendRequest(List<CandidateVO> friendRequest) {
		this.friendRequest = friendRequest;
	}

	public ResultStatus getResultStatusForFriendRequest() {
		return resultStatusForFriendRequest;
	}

	public void setResultStatusForFriendRequest(
			ResultStatus resultStatusForFriendRequest) {
		this.resultStatusForFriendRequest = resultStatusForFriendRequest;
	}

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

	public Long getUnreadMsgCount() {
		return unreadMsgCount;
	}

	public void setUnreadMsgCount(Long unreadMsgCount) {
		this.unreadMsgCount = unreadMsgCount;
	}

	public Long getTotalMsgCount() {
		return totalMsgCount;
	}

	public void setTotalMsgCount(Long totalMsgCount) {
		this.totalMsgCount = totalMsgCount;
	}



}
