package com.itgrids.core.api.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.ElectionInformationVO;

public interface ILocationWiseElectionInformationDetalsService {

	public List<ElectionInformationVO> getElectionInformationLocationWiseStatus(Long locationTypeId,
			Long locationValue,  List<Long> partyIdList, List<Long> electionYrs,List<Long> electionScopeIds, List<String> subTypes,String searchType);
}
