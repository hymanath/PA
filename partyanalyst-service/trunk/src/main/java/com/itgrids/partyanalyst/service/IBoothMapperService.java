package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.ConstituencyBoothInfoVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.ResultWithExceptionVO;

public interface IBoothMapperService {
	
	public List<ConstituencyBoothInfoVO> getBoothOfAssemblyByYear(Long constituencyId, Long year);
	
	public ResultWithExceptionVO saveBoothLocalElectionBodyMappingInfo(List<Long> boothIds,List<Long> boothIdsToModify, Long locationId, Boolean isWard);

	public ResultWithExceptionVO saveAssemblyLocalBodyMappingInfo(Long localBodyId, List<Long> localBodyOrWardIds, List<Long> localBodyOrWardIdsToModify, 
			Long assemblyId, String year, Boolean isWards);
	
	public ResultStatus setDataForVillageBoothRelation(Long districtId,Long electionYear);
}
