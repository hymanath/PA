/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on April 24,2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class CandidateElectionResultVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7640275547715541937L;
	
	private Long partyId;
	private Long candidateId;
	private Long constituencyId;
	private String partyName;
	private String candidateName;
	private String constituencyName;
	private Long totalValidVotes;
	private Long totalVotesEarned;
	private String votesPercentage;
	private String votesMargin;
	private String marginVotes;
	private Long rank;
	private Long userComments;
	private Long districtId;
	private String districtName;
	
	public String getMarginVotes() {
		return marginVotes;
	}
	public void setMarginVotes(String marginVotes) {
		this.marginVotes = marginVotes;
	}
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
	public String getVotesMargin() {
		return votesMargin;
	}
	public void setVotesMargin(String votesMargin) {
		this.votesMargin = votesMargin;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	
	
	

}
