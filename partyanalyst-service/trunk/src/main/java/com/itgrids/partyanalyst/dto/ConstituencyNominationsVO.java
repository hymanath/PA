/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on March 31, 2011
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author Sai Krishna
 *
 */
public class ConstituencyNominationsVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private ResultStatus resultstatus;
	
	private List<CandidateInfoForConstituencyVO> candidateNominations;
	
	private String electionYear;
	private String electionType;
	private String constituencyName;

	public ResultStatus getResultstatus() {
		return resultstatus;
	}

	public void setResultstatus(ResultStatus resultstatus) {
		this.resultstatus = resultstatus;
	}

	public List<CandidateInfoForConstituencyVO> getCandidateNominations() {
		return candidateNominations;
	}

	public void setCandidateNominations(
			List<CandidateInfoForConstituencyVO> candidateNominations) {
		this.candidateNominations = candidateNominations;
	}

	public String getElectionYear() {
		return electionYear;
	}

	public void setElectionYear(String electionYear) {
		this.electionYear = electionYear;
	}

	public String getElectionType() {
		return electionType;
	}

	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}

	public String getConstituencyName() {
		return constituencyName;
	}

	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}

}
