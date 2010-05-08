/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on April 15,2010
 */
package com.itgrids.partyanalyst.dto;

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
	
}
