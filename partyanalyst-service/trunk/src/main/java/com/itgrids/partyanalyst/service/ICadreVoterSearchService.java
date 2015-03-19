package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CadreAddressVO;

public interface ICadreVoterSearchService {
	public List<CadreAddressVO> getAllDistrictsAndConstis(String type,Long id);
}
