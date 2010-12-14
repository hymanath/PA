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
	
	
}
