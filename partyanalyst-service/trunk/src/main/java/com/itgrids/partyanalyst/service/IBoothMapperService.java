package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.ConstituencyBoothInfoVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;

public interface IBoothMapperService {
	
	public List<SelectOptionVO> getLocalElectionBodiesOfADistrict(Long districtId);
	
	public List<SelectOptionVO> getWardsInALocalElectionBody(Long localElectionBodyId);
	
	public List<ConstituencyBoothInfoVO> getBoothOfAssemblyByYear(Long constituencyId, Long year);
	
	public void saveBoothLocalElectionBodyMappingInfo(List<Long> boothIds, Long wardId);

}
