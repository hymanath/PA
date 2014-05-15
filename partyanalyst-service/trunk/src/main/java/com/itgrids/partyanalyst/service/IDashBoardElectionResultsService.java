package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.DashBoardResultsVO;

public interface IDashBoardElectionResultsService {
	
	public DashBoardResultsVO getElectionResultsSummary();
	public List<Object[]> getConstituenciesDetailsForSubReport(String type,Long partyId,Long locationId,Long scopeId );
	public List<DashBoardResultsVO>  getMatrixReportForElectionResult(Long electionId,List<Long> locationIds,Long scopeId);
	public List<DashBoardResultsVO> getSubReportForElectionResultByConstituencyReservationType(Long electionId,List<Long> locationIds,Long scopeId);
	public List<DashBoardResultsVO> getSubReportForElectionResultByConstituencyType(Long electionId,List<Long> locationIds,Long scopeId);
	public List<DashBoardResultsVO> getConstituencyWiseLiveResults(Long electionId,List<Long> constituencyIds);
}
