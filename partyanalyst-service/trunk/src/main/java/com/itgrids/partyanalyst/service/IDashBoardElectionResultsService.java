package com.itgrids.partyanalyst.service;

import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.dto.DashBoardResultsVO;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.PartyResultVO;
import com.itgrids.survey.soa.endpoints.OptionVO;

public interface IDashBoardElectionResultsService {
	
	public DashBoardResultsVO getElectionResultsSummary();
	public List<Object[]> getConstituenciesDetailsForSubReport(String type,Long partyId,Long locationId,Long scopeId );
	public List<DashBoardResultsVO>  getMatrixReportForElectionResult(Long electionId,List<Long> locationIds,Long scopeId);
	public List<DashBoardResultsVO> getSubReportForElectionResultByConstituencyReservationType(Long electionId,List<Long> locationIds,Long scopeId);
	public List<DashBoardResultsVO> getSubReportForElectionResultByConstituencyType(Long electionId,List<Long> locationIds,Long scopeId);
	public List<DashBoardResultsVO> getConstituencyWiseLiveResults(Long electionId,List<Long> constituencyIds);
	public  List<DashBoardResultsVO> getWonAndLeadCountPartyWise(Long electionId,List<Long> locationIds,Long scopeId,Long electionScopeId);
	public List<GenericVO> getPartiesInConsituenciesOfElection(Long electionId,List<Long> constituencyIds);
	public List<PartyResultVO> partysVotesShareInConstituenciesOfElection(Long electionId,List<Long> constituencyIds,List<Long> partyIds);
	public DashBoardResultsVO getPartyWiseWinningSeatsCount(Long electionId, List<Long> locationIds, Long scopeId,Long percent, Long partyId);
	public GenericVO getparticipatedPartiesInLocation(Long electionId,List<Long> regionIds,Long electionScopeId,Long scope);
	public GenericVO getReservedConstiList(Long electionId,List<Long> regionIds,Long electionScopeId,Long scope);
	
	public DashBoardResultsVO getPartyWiseWinningSeatsPercentage(Long electionId, List<Long> locationIds, Long scopeId,
			Long percent, Long partyId);
	public  Object[] getTop5CastePeopleOpnionOnParty(Long constituencyId,List<Long> surveyIds);
	
    public String getWinningCandidateInfoForAConstituency(Long constituencyId);
    
    public DashBoardResultsVO getPartyWiseCountDetailsByConstituencyIdAndSurveyIds(Long constituencyId,List<Long> surveyIds,Long electionId);
    public Map<String,String> getConstituencyDetaisByRegionid(Long regionId);

}
