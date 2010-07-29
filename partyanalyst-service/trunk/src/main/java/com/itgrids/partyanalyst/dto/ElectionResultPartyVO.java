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

public class ElectionResultPartyVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5458658991442678L;
	
	private Long partyId;
	private String partyFlag;
	private String partyLongName;
	private String partyShortName;
	private ResultStatus resultVO;
	private Long rank;
	private String status;
	private String votesShareRange;
	private List<CandidateElectionResultVO> candidateElectionResultsVO;
	private String electionType;
	private String electionYear;
	
	
	public String getVotesShareRange() {
		return votesShareRange;
	}
	public void setVotesShareRange(String votesShareRange) {
		this.votesShareRange = votesShareRange;
	}
	public ResultStatus getResultVO() {
		return resultVO;
	}
	public void setResultVO(ResultStatus resultVO) {
		this.resultVO = resultVO;
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
	private ResultStatus resultStatus;
	
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	public String getPartyFlag() {
		return partyFlag;
	}
	public void setPartyFlag(String partyFlag) {
		this.partyFlag = partyFlag;
	}
	public String getPartyLongName() {
		return partyLongName;
	}
	public void setPartyLongName(String partyLongName) {
		this.partyLongName = partyLongName;
	}
	public String getPartyShortName() {
		return partyShortName;
	}
	public void setPartyShortName(String partyShortName) {
		this.partyShortName = partyShortName;
	}
	public Long getRank() {
		return rank;
	}
	public void setRank(Long rank) {
		this.rank = rank;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<CandidateElectionResultVO> getCandidateElectionResultsVO() {
		return candidateElectionResultsVO;
	}
	public void setCandidateElectionResultsVO(
			List<CandidateElectionResultVO> candidateElectionResultsVO) {
		this.candidateElectionResultsVO = candidateElectionResultsVO;
	}
	public ResultStatus getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof ElectionResultPartyVO))
			return false;
		ElectionResultPartyVO voObj = (ElectionResultPartyVO) obj;
		return (this.electionType+this.electionYear).equals(voObj.electionType+voObj.electionYear);
	}
	
	@Override
	public int hashCode(){
		return (this.electionType+this.electionYear).hashCode();
	}
}
