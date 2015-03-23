package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CadreAddressVO;
import com.itgrids.partyanalyst.dto.TdpCadreVO;

public interface ICadreVoterSearchService {
	public List<CadreAddressVO> getAllDistrictsAndConstis(String type,Long id);
	public List<TdpCadreVO> getCadreVoterDetailsBySearchCriteria(String searchType,Long stateId, String locationType,Long locationId,Long casteStateId, String nameStr,String isFinal);
}
