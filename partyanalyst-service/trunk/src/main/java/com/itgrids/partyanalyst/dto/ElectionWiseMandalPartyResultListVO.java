package com.itgrids.partyanalyst.dto;

import java.util.List;

public class ElectionWiseMandalPartyResultListVO extends ResultStatus {

	private static final long serialVersionUID = 7146866377443891828L;
	private List<ElectionWiseMandalPartyResultVO> electionWiseMandalPartyResultVOList;
	private List<ElectionWiseMandalPartyResultVO> partyWiseElectionResultsVOList;
	private List<PartyResultVO> allPartiesAllElectionResults;
	private List<SelectOptionVO> elections;
	private String chartName;
	private List<ElectionWiseMandalPartyResultVO> mptcZptcElectionResultsVO;
		
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

	public void setElections(List<SelectOptionVO> elections) {
		this.elections = elections;
	}

	public List<SelectOptionVO> getElections() {
		return elections;
	}
	
	public void setChartName(String chartName) {
		this.chartName = chartName;
	}

	public String getChartName() {
		return chartName;
	}

	public List<ElectionWiseMandalPartyResultVO> getMptcZptcElectionResultsVO() {
		return mptcZptcElectionResultsVO;
	}

	public void setMptcZptcElectionResultsVO(
			List<ElectionWiseMandalPartyResultVO> mptcZptcElectionResultsVO) {
		this.mptcZptcElectionResultsVO = mptcZptcElectionResultsVO;
	}
	
	

}
