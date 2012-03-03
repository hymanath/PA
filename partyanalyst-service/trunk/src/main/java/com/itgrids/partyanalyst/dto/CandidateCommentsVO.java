/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on April 15,2010
 */
package com.itgrids.partyanalyst.dto;

import java.util.List;

public class CandidateCommentsVO implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7800041292600461335L;
	
	private Long candidateId;
	private String candidate;	
	private Long commentId;
	private String commentDesc;
	private String commentedBy;
	private String commentedOn;
	private String commentCategory;	
	private String constituencyName;
	private String partyName;
	private Long rank;
	private Long userId;
	private String userName;
	private Long postedUsersCount;
	private Float reasonScore = 0.0f;
	private List<UserCommentsInfoVO> commetsAndScores;
	private Long nominationId;
	private String electionType;
	private String electionYear;
	private String isApproved;
	private Long approvedReasonsCount;
	private Long rejectedReasonsCount;
	private Long notConsideredReasonsCount;
	private Long totalPostedReasonsCount;
	private Long postedReasonsCountByOtherUsers;
	private String postedBY;
	private String constituency;
	private String status;
	private List<Long> longList;
	private List<String> stringList;
	private Long messageToCandidateId;
	private String message;
	private String time;
	private Long consituencyId;
	private String visibility;
	private Long totalResultsCount;
	
	
	public Long getConsituencyId() {
		return consituencyId;
	}
	public void setConsituencyId(Long consituencyId) {
		this.consituencyId = consituencyId;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Long getMessageToCandidateId() {
		return messageToCandidateId;
	}
	public void setMessageToCandidateId(Long messageToCandidateId) {
		this.messageToCandidateId = messageToCandidateId;
	}
	public List<Long> getLongList() {
		return longList;
	}
	public void setLongList(List<Long> longList) {
		this.longList = longList;
	}
	public List<String> getStringList() {
		return stringList;
	}
	public void setStringList(List<String> stringList) {
		this.stringList = stringList;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPostedBY() {
		return postedBY;
	}
	public void setPostedBY(String postedBY) {
		this.postedBY = postedBY;
	}
	
	public String getConstituency() {
		return constituency;
	}
	public void setConstituency(String constituency) {
		this.constituency = constituency;
	}
	public Long getPostedReasonsCountByOtherUsers() {
		return postedReasonsCountByOtherUsers;
	}
	public void setPostedReasonsCountByOtherUsers(
			Long postedReasonsCountByOtherUsers) {
		this.postedReasonsCountByOtherUsers = postedReasonsCountByOtherUsers;
	}
	public Long getTotalPostedReasonsCount() {
		return totalPostedReasonsCount;
	}
	public void setTotalPostedReasonsCount(Long totalPostedReasonsCount) {
		this.totalPostedReasonsCount = totalPostedReasonsCount;
	}
	public Long getApprovedReasonsCount() {
		return approvedReasonsCount;
	}
	public void setApprovedReasonsCount(Long approvedReasonsCount) {
		this.approvedReasonsCount = approvedReasonsCount;
	}
	public Long getRejectedReasonsCount() {
		return rejectedReasonsCount;
	}
	public void setRejectedReasonsCount(Long rejectedReasonsCount) {
		this.rejectedReasonsCount = rejectedReasonsCount;
	}
	public Long getNotConsideredReasonsCount() {
		return notConsideredReasonsCount;
	}
	public void setNotConsideredReasonsCount(Long notConsideredReasonsCount) {
		this.notConsideredReasonsCount = notConsideredReasonsCount;
	}
	public String getIsApproved() {
		return isApproved;
	}
	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}
	public Long getCommentId() {
		return commentId;
	}
	public void setCommentId(Long commentId) {
		this.commentId = commentId;
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
	public Long getNominationId() {
		return nominationId;
	}
	public void setNominationId(Long nominationId) {
		this.nominationId = nominationId;
	}
	
	//getters and setters
	public Long getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}
	public String getCandidate() {
		return candidate;
	}
	public void setCandidate(String candidate) {
		this.candidate = candidate;
	}
	public String getCommentDesc() {
		return commentDesc;
	}
	public void setCommentDesc(String commentDesc) {
		this.commentDesc = commentDesc;
	}
	public String getCommentedBy() {
		return commentedBy;
	}
	public void setCommentedBy(String commentedBy) {
		this.commentedBy = commentedBy;
	}
	public String getCommentedOn() {
		return commentedOn;
	}
	public void setCommentedOn(String commentedOn) {
		this.commentedOn = commentedOn;
	}
	public String getCommentCategory() {
		return commentCategory;
	}
	public void setCommentCategory(String commentCategory) {
		this.commentCategory = commentCategory;
	}
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public Long getRank() {
		return rank;
	}
	public void setRank(Long rank) {
		this.rank = rank;
	}
	public List<UserCommentsInfoVO> getCommetsAndScores() {
		return commetsAndScores;
	}
	public void setCommetsAndScores(List<UserCommentsInfoVO> commetsAndScores) {
		this.commetsAndScores = commetsAndScores;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getPostedUsersCount() {
		return postedUsersCount;
	}
	public void setPostedUsersCount(Long postedUsersCount) {
		this.postedUsersCount = postedUsersCount;
	}
	public Float getReasonScore() {
		return reasonScore;
	}
	public void setReasonScore(Float reasonScore) {
		this.reasonScore = reasonScore;
	}
	public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	public Long getTotalResultsCount() {
		return totalResultsCount;
	}
	public void setTotalResultsCount(Long totalResultsCount) {
		this.totalResultsCount = totalResultsCount;
	}
	
	
	
	
}
