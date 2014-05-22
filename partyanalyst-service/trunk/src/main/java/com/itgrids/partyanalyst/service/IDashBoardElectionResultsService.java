package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.DashBoardResultsVO;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.PartyResultVO;

public interface IDashBoardElectionResultsService {
	
	public DashBoardResultsVO getElectionResultsSummary();
	public List<Object[]> getConstituenciesDetailsForSubReport(String type,Long partyId,Long locationId,Long scopeId );
	public List<DashBoardResultsVO>  getMatrixReportForElectionResult(Long electionId,List<Long> locationIds,Long scopeId);
	public List<DashBoardResultsVO> getSubReportForElectionResultByConstituencyReservationType(Long electionId,List<Long> locationIds,Long scopeId);
	public List<DashBoardResultsVO> getSubReportForElectionResultByConstituencyType(Long electionId,List<Long> locationIds,Long scopeId);
	public List<DashBoardResultsVO> getConstituencyWiseLiveResults(Long electionId,List<Long> constituencyIds);
	public  List<DashBoardResultsVO> getWonAndLeadCountPartyWise(Long electionId,List<Long> locationIds,Long scopeId);
	public List<GenericVO> getPartiesInConsituenciesOfElection(Long electionId,List<Long> constituencyIds);
	public List<PartyResultVO> partysVotesShareInConstituenciesOfElection(Long electionId,List<Long> constituencyIds,List<Long> partyIds);
	public List<DashBoardResultsVO> getPartyWiseWinningSeatsCount(
			Long electionId, List<Long> locationIds, Long scopeId,
			Long percent, Long partyId);
}
