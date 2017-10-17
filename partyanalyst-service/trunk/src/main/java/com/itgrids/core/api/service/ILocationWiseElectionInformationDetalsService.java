package com.itgrids.core.api.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.ElectionInformationVO;

public interface ILocationWiseElectionInformationDetalsService {

	public List<ElectionInformationVO> getElectionInformationLocationWiseStatus(Long locationTypeId,
			Long locationValue,  List<Long> partyIdList, List<Long> electionYrs,List<Long> electionScopeIds, List<String> subTypes,String searchType);
	public ElectionInformationVO getLocationWiseCrossVotingDetails(List<Long> electionYrs,List<Long> parliamentIds,List<Long> assemlyIds ,
			List<Long> partyids,String withAlliance,Long levelId,List<Long> locationVals,List<String> subtypes);
	public List<ElectionInformationVO> getLocationWiseVotingDetails(List<Long> electionYrs,Long levelId,List<Long> locationVals,List<String> subtypes,String searchLevel);
}
