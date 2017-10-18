package com.itgrids.core.api.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.ElectionInformationVO;
import com.itgrids.partyanalyst.excel.booth.PartyBoothPerformanceVO;

public interface ILocationWiseElectionInformationDetalsService {

	public List<ElectionInformationVO> getElectionInformationLocationWiseStatus(Long locationTypeId,
			Long locationValue,  List<Long> partyIdList, List<Long> electionYrs,List<Long> electionScopeIds, List<String> subTypes,String searchType);
	public ElectionInformationVO getLocationWiseCrossVotingDetails(List<Long> electionYrs,List<Long> parliamentIds,List<Long> assemlyIds ,
			List<Long> partyids,String withAlliance,Long levelId,List<Long> locationVals,List<String> subtypes);
	
	// boothWise Results	
	//public List<PartyBoothPerformanceVO> getBoothWiseElectionResults(List<Long> partyIds, Long constituencyId, List<Long> electionYears);
	public PartyBoothPerformanceVO getVotingPercentageWiseBoothResultForParties(PartyBoothPerformanceVO performanceVO,boolean isPollingPercentage,String path,List<Long> partyIds);
	public PartyBoothPerformanceVO segrigateBoothWiseResults(List<PartyBoothPerformanceVO> partyBoothPerformanceVOList);
	List<PartyBoothPerformanceVO> getBoothWiseElectionResults(List<Long> partyIds, Long constituencyId, Long electionYears,Long electionScopeId);

	public List<ElectionInformationVO> getLocationWiseVotingDetails(List<Long> electionYrs,Long levelId,List<Long> locationVals,List<String> subtypes,String searchLevel);
	public List<ElectionInformationVO> getElectionInformationLocationWiseStatusAndYearWise(Long locationTypeId,Long locationValue,List<Long> partyIdList,
			List<Long> electionYrs,List<Long> electionScopeIds, List<String> subTypes,String searchType,String statusType,String year,List<Long> locationIdsList);
}
