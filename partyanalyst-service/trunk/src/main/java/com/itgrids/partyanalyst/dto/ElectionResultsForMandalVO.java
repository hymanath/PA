/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on Jun 12, 2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class ElectionResultsForMandalVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long electionId;
	private String electionType;
	private String electionYear;
	private List<PartyResultsVO> PartiesList;
	private List<SelectOptionVO> partysList;
	private List<MandalElectionResultVO> electionResultsForMandal;
	private List<PartyResultsVO> partyResultsSum;
	private ResultStatus resultStatus;

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

	public List<MandalElectionResultVO> getElectionResultsForMandal() {
		return electionResultsForMandal;
	}

	public void setElectionResultsForMandal(
			List<MandalElectionResultVO> electionResultsForMandal) {
		this.electionResultsForMandal = electionResultsForMandal;
	}

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	public List<SelectOptionVO> getPartysList() {
		return partysList;
	}

	public void setPartysList(List<SelectOptionVO> partysList) {
		this.partysList = partysList;
	}

	public void setPartiesList(List<PartyResultsVO> partiesList) {
		PartiesList = partiesList;
	}

	public List<PartyResultsVO> getPartiesList() {
		return PartiesList;
	}

	public List<PartyResultsVO> getPartyResultsSum() {
		return partyResultsSum;
	}

	public void setPartyResultsSum(List<PartyResultsVO> partyResultsSum) {
		this.partyResultsSum = partyResultsSum;
	}

}
