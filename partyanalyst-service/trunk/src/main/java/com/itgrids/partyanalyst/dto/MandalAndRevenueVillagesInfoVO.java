package com.itgrids.partyanalyst.dto;

import java.util.List;

public class MandalAndRevenueVillagesInfoVO extends ResultStatus{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ElectionResultByLocationVO partiesResultsInMandal;
	private List<ElectionResultByLocationVO> partiesResultsInVillages; 

	public ElectionResultByLocationVO getPartiesResultsInMandal() {
		return partiesResultsInMandal;
	}

	public void setPartiesResultsInMandal(
			ElectionResultByLocationVO partiesResultsInMandal) {
		this.partiesResultsInMandal = partiesResultsInMandal;
	}

	public List<ElectionResultByLocationVO> getPartiesResultsInVillages() {
		return partiesResultsInVillages;
	}

	public void setPartiesResultsInVillages(
			List<ElectionResultByLocationVO> partiesResultsInVillages) {
		this.partiesResultsInVillages = partiesResultsInVillages;
	}
	
	
}
