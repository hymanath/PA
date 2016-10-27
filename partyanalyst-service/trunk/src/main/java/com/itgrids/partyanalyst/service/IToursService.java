package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.ToursBasicVO;

public interface IToursService {
	 public List<ToursBasicVO> getDesigationList();
	 public List<ToursBasicVO> getConstituenciesList(Long stateId);
	 public List<ToursBasicVO> getCandidateList(Long designationId);
	 public ToursBasicVO getCandiateDetails(Long candidateId);
	 public List<ToursBasicVO> getSearchMembersDetails(Long locationId,String searchType,String searchValue);
}
