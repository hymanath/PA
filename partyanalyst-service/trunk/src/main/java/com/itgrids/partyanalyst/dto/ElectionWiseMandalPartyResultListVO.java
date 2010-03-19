package com.itgrids.partyanalyst.dto;

import java.util.List;

public class ElectionWiseMandalPartyResultListVO extends ResultStatus {

	private static final long serialVersionUID = 7146866377443891828L;
	private List<ElectionWiseMandalPartyResultVO> electionWiseMandalPartyResultVOList;
	private List<ElectionWiseMandalPartyResultVO> partyWiseElectionResultsVOList;
	private List<PartyResultVO> allPartiesAllElectionResults;
	
	public List<ElectionWiseMandalPartyResultVO> getElectionWiseMandalPartyResultVOList() {
		return electionWiseMandalPartyResultVOList;
	}

	public void setElectionWiseMandalPartyResultVOList(
			List<ElectionWiseMandalPartyResultVO> electionWiseMandalPartyResultVOList) {
		this.electionWiseMandalPartyResultVOList = electionWiseMandalPartyResultVOList;
	}

	public List<ElectionWiseMandalPartyResultVO> getPartyWiseElectionResultsVOList() {
		return partyWiseElectionResultsVOList;
	}

	public List<PartyResultVO> getAllPartiesAllElectionResults() {
		return allPartiesAllElectionResults;
	}

	public void setAllPartiesAllElectionResults(
			List<PartyResultVO> allPartiesAllElectionResults) {
		this.allPartiesAllElectionResults = allPartiesAllElectionResults;
	}

	public void setPartyWiseElectionResultsVOList(
			List<ElectionWiseMandalPartyResultVO> partyWiseElectionResultsVOList) {
		this.partyWiseElectionResultsVOList = partyWiseElectionResultsVOList;
	}
}
