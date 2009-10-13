package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.ConstituencyVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;

public interface IConstituencySearchService {

	public List<SelectOptionVO>  getConstituencyNamesAndIds();
	
	public List<ConstituencyVO> getConstituencyDetails(String name);
	
	public List<SelectOptionVO> getConstituencyNames(Long stateId);
}
