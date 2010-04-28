/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on April 24,2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class CandidateElectionResultVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7640275547715541937L;
	
	private Long candidateId;
	private Long constituencyId;
	private String candidateName;
	private String constituencyName;
	
	private Long totalValidVotes;
	private Long totalVotesEarned;
	private String votesPercentage;
	
	private Long rank;
	
	private Long userComments;
	
	public Long getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	public Long getTotalValidVotes() {
		return totalValidVotes;
	}
	public void setTotalValidVotes(Long totalValidVotes) {
		this.totalValidVotes = totalValidVotes;
	}
	public Long getTotalVotesEarned() {
		return totalVotesEarned;
	}
	public void setTotalVotesEarned(Long totalVotesEarned) {
		this.totalVotesEarned = totalVotesEarned;
	}
	public String getVotesPercentage() {
		return votesPercentage;
	}
	public void setVotesPercentage(String votesPercentage) {
		this.votesPercentage = votesPercentage;
	}
	public Long getRank() {
		return rank;
	}
	public void setRank(Long rank) {
		this.rank = rank;
	}
	public Long getUserComments() {
		return userComments;
	}
	public void setUserComments(Long userComments) {
		this.userComments = userComments;
	}
	

}
