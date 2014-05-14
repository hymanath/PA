package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.DashBoardResultsVO;

public interface IDashBoardElectionResultsService {
	
	public DashBoardResultsVO getElectionResultsSummary();
	public List<Object[]> getConstituenciesDetailsForSubReport(String type,Long partyId);
}
