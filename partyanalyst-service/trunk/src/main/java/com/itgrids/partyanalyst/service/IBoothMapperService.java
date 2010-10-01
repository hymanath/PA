package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.ConstituencyBoothInfoVO;

public interface IBoothMapperService {
	
	public List<ConstituencyBoothInfoVO> getBoothOfAssemblyByYear(Long constituencyId, Long year);
	
	public void saveBoothLocalElectionBodyMappingInfo(List<Long> boothIds, Long wardId);

}
