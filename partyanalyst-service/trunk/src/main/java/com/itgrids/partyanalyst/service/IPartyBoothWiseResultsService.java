package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.ConstituencyVO;
import com.itgrids.partyanalyst.dto.ElectionWiseMandalPartyResultListVO;
import com.itgrids.partyanalyst.dto.ResultWithExceptionVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.TeshilPartyInfoVO;
import com.itgrids.partyanalyst.excel.booth.PartyBoothPerformanceVO;

public interface IPartyBoothWiseResultsService {
	
	public List<PartyBoothPerformanceVO> getBoothWiseResultsForParty(Long partyId, Long constituencyId, String electionYear);
	
	public ResultWithExceptionVO getBoothPageInfo(Long boothId);
	
	public ElectionWiseMandalPartyResultListVO getPartyGenderWiseBoothVotesForMandal(Long tehsilID, String locationType);
	
	public ElectionWiseMandalPartyResultListVO getAllMPTCAndZPTCElectionsInfoInTehsil(Long tehsilId);
	
	public ElectionWiseMandalPartyResultListVO getAllElectionsResultsInAMandal(Long mandalId);
	
	public List<TeshilPartyInfoVO> getMPTCandZPTCResultsInAMandalForAElection(Long tehsilId,String electionType,String electionYear);
	
	public ConstituencyVO getBoothwiseResultsOfTwoElectionsForAConstituency(Long constituencyId);
	
	public List getMandalAllElectionDetails(Long tehsilID, Long partyID, boolean allianceFlag);
	
	public PartyBoothPerformanceVO getVotingPercentageWiseBoothResult(PartyBoothPerformanceVO performanceVO,boolean isPollingPercentage,String path);
	
	public PartyBoothPerformanceVO getVotingPercentageWiseBoothResultForParties(PartyBoothPerformanceVO performanceVO,boolean isPollingPercentage,String path,List<Long> partyIds);
	
	public List<PartyBoothPerformanceVO> getBoothWiseElectionResults(List<Long> partyIds, Long constituencyId, List<String> electionYears);
	
	public SelectOptionVO getStateWiseDetailByStateType(Long stateTypeId, String electionType,Long electionYear,Long constituencyId);
	
	public PartyBoothPerformanceVO getVotingPercentagWiseBoothResult(List<PartyBoothPerformanceVO> performanceVOList,boolean isPollingPercentage,String path);
	
	public PartyBoothPerformanceVO segrigateBoothWiseResults(List<PartyBoothPerformanceVO> partyBoothPerformanceVOList);
	
	public SelectOptionVO getPartyDetailsForConstituencyAction(Long electionYear, Long constituencyId);
	
	public SelectOptionVO getAssemblyDetailsForParliamnt(Long parliamentCosntiID, Long electionYear);
	
}
