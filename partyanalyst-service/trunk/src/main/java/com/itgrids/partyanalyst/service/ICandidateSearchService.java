package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CandidateVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;

public interface ICandidateSearchService {

	public List<SelectOptionVO> getCandidateNamesAndIds();
	
	public List<CandidateVO> getCandidatesDetails(Long candidateId, String name);
}
