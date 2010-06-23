package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

import com.itgrids.partyanalyst.excel.booth.BoothResultVO;

public class AllBoothsResultsForAPartyInAMandal extends ResultStatus{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<BoothResultVO> boothResults = new ArrayList<BoothResultVO>();
	
	public List<BoothResultVO> getBoothResults() {
		return boothResults;
	}
	public void setBoothResults(List<BoothResultVO> boothResults) {
		this.boothResults = boothResults;
	}
	
}
