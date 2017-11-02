package com.itgrids.core.api.service;

import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.dto.ElectionInformationVO;
import com.itgrids.partyanalyst.excel.booth.PartyBoothPerformanceVO;

public interface ILocationWiseElectionInformationDetalsService {

	public List<ElectionInformationVO> getElectionInformationLocationWiseStatus(Long locationTypeId,
			Long locationValue,  List<Long> partyIdList, List<Long> electionYrs,List<Long> electionScopeIds, List<String> subTypes,String searchType,String withAllaince);
	public ElectionInformationVO getLocationWiseCrossVotingDetails(List<Long> electionYrs,List<Long> parliamentIds,List<Long> assemlyIds ,
			List<Long> partyids,String withAlliance,Long levelId,List<Long> locationVals,List<String> subtypes,List<Long> electionScopeIds);
	
	// boothWise Results	
	//public List<PartyBoothPerformanceVO> getBoothWiseElectionResults(List<Long> partyIds, Long constituencyId, List<Long> electionYears);
	public PartyBoothPerformanceVO getVotingPercentageWiseBoothResultForParties(PartyBoothPerformanceVO performanceVO,boolean isPollingPercentage,String path,List<Long> partyIds);
	public PartyBoothPerformanceVO segrigateBoothWiseResults(List<PartyBoothPerformanceVO> partyBoothPerformanceVOList);
	List<PartyBoothPerformanceVO> getBoothWiseElectionResults(List<Long> partyIds, Long constituencyId, Long electionYears,Long electionScopeId,Long locationTypeId,Long locationValue);

	public List<ElectionInformationVO> getLocationWiseVotingDetails(List<Long> electionYrs,Long levelId,List<Long> locationVals,List<String> subtypes,String searchLevel,String clickType,List<Long> partyIds);
	public List<ElectionInformationVO> getElectionInformationLocationWiseStatusAndYearWise(Long locationTypeId,Long locationValue,List<Long> partyIdList,
			List<Long> electionYrs,List<Long> electionScopeIds, List<String> subTypes,String searchType,String statusType,String year,List<Long> locationIdsList,String withAllaince);
	public List<Object[]> segregateAlianceParties(List<Object[]> inputObjList,List<String> subTypes,List<Long> electionYear,List<Long> electionScopeIds,Map<Long, Map<Long, ElectionInformationVO>> alliancedPartiesWithGroupId);
	public Map<Long, Map<Long, ElectionInformationVO>> getSegregateAliancePartiesMap(List<String> subTypes, List<Long> electionYrs,List<Long> electionScopeIds);
	public PartyBoothPerformanceVO getBoothWiseElectionResultsForAssamblyAndParlaiment(PartyBoothPerformanceVO assemblyBoothResultVO,PartyBoothPerformanceVO parliamentBoothResultVO);

	//crossVoting
	
	public List<ElectionInformationVO> getLocationBasedCrossVotingReult(List<Long> electionYears,Long locationTypeId,List<Long>locationValues,Long electionId,List<String> subTypes,List<Long> partyIds,List<Long> electionScopeIds,String withAlliance);


}
