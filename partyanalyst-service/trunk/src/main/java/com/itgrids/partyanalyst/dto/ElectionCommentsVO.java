/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on April 15,2010
 */
package com.itgrids.partyanalyst.dto;

import java.util.List;

public class ElectionCommentsVO implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7463097954198587386L;

	private Long electionId;
	private String electionType;
	private String electionYear;
	
	private PartyCommentsVO partyCommentsSaved;
	private CandidateCommentsVO candidateCommentsSaved;
	private ConstituencyCommentsVO constituencyCommentsSaved;
	
	private List<PartyCommentsVO> partyCommentsVO;
	private List<CandidateCommentsVO> candidateCommentsVO;
	private List<ConstituencyCommentsVO> constituencyCommentsVO;
	
	private ResultStatus resultStatus;

	//getters and setters 
	public Long getElectionId() {
		return electionId;
	}

	public void setElectionId(Long electionId) {
		this.electionId = electionId;
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

	public List<CandidateCommentsVO> getCandidateCommentsVO() {
		return candidateCommentsVO;
	}

	public PartyCommentsVO getPartyCommentsSaved() {
		return partyCommentsSaved;
	}

	public void setPartyCommentsSaved(PartyCommentsVO partyCommentsSaved) {
		this.partyCommentsSaved = partyCommentsSaved;
	}

	public CandidateCommentsVO getCandidateCommentsSaved() {
		return candidateCommentsSaved;
	}

	public void setCandidateCommentsSaved(CandidateCommentsVO candidateCommentsSaved) {
		this.candidateCommentsSaved = candidateCommentsSaved;
	}

	public ConstituencyCommentsVO getConstituencyCommentsSaved() {
		return constituencyCommentsSaved;
	}

	public void setConstituencyCommentsSaved(
			ConstituencyCommentsVO constituencyCommentsSaved) {
		this.constituencyCommentsSaved = constituencyCommentsSaved;
	}

	public void setCandidateCommentsVO(List<CandidateCommentsVO> candidateCommentsVO) {
		this.candidateCommentsVO = candidateCommentsVO;
	}

	public List<PartyCommentsVO> getPartyCommentsVO() {
		return partyCommentsVO;
	}

	public void setPartyCommentsVO(List<PartyCommentsVO> partyCommentsVO) {
		this.partyCommentsVO = partyCommentsVO;
	}

	public List<ConstituencyCommentsVO> getConstituencyCommentsVO() {
		return constituencyCommentsVO;
	}

	public void setConstituencyCommentsVO(
			List<ConstituencyCommentsVO> constituencyCommentsVO) {
		this.constituencyCommentsVO = constituencyCommentsVO;
	}

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
}
