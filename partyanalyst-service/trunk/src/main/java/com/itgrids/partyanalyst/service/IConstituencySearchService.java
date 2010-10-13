package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.ConstituencyVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;

public interface IConstituencySearchService {

	public List<SelectOptionVO>  getConstituencyNamesAndIds(Long electionTypeId , Long stateId);
	
	public List<ConstituencyVO> getConstituencyDetails(String name, String electionType);

	public List<SelectOptionVO> getConstituencyNames(Long stateId);
	public List<SelectOptionVO> getConstituencyNamesByElectionScope(Long countryId, Long stateId, Long electionTypeID);
}
